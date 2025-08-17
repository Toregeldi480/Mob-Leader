package com.toregeldi;

import com.toregeldi.item.ModItemGroups;
import com.toregeldi.item.ModItems;
import com.toregeldi.network.LeadingMobEntitiesPayload;
import com.toregeldi.network.LeadingMobEntityCapacityPayload;
import com.toregeldi.network.LeadingMobEntityWeightPayload;
import com.toregeldi.util.PlayerEntityAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MobLeader implements ModInitializer {
	public static final String MOD_ID = "mob-leader";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModItemGroups.initialize();

		addMaterialToLootTable(EntityType.BOGGED, ModItems.BOGGED_MATERIAL);
		addMaterialToLootTable(EntityType.CAVE_SPIDER, ModItems.CAVE_SPIDER_MATERIAL);
		addMaterialToLootTable(EntityType.CREEPER, ModItems.CREEPER_MATERIAL);
		addMaterialToLootTable(EntityType.DROWNED, ModItems.DROWNED_MATERIAL);
		addMaterialToLootTable(EntityType.ENDERMAN, ModItems.ENDERMAN_MATERIAL);
		addMaterialToLootTable(EntityType.EVOKER, ModItems.EVOKER_MATERIAL);
		addMaterialToLootTable(EntityType.HUSK, ModItems.HUSK_MATERIAL);
		addMaterialToLootTable(EntityType.SKELETON, ModItems.SKELETON_MATERIAL);
		addMaterialToLootTable(EntityType.VINDICATOR, ModItems.VINDICATOR_MATERIAL);
		addMaterialToLootTable(EntityType.WITHER_SKELETON, ModItems.WITHER_SKELETON_MATERIAL);
		addMaterialToLootTable(EntityType.ZOMBIE, ModItems.ZOMBIE_MATERIAL);

		PayloadTypeRegistry.playS2C().register(LeadingMobEntityWeightPayload.ID, LeadingMobEntityWeightPayload.CODEC);
		ServerPlayConnectionEvents.JOIN.register((handler, packetSender, server) ->
				ServerPlayNetworking.send(handler.player, new LeadingMobEntityWeightPayload(((PlayerEntityAccessor)handler.player).getLeadingMobEntityWeight())));

		PayloadTypeRegistry.playS2C().register(LeadingMobEntityCapacityPayload.ID, LeadingMobEntityCapacityPayload.CODEC);
		ServerPlayConnectionEvents.JOIN.register((handler, packetSender, server) ->
				ServerPlayNetworking.send(handler.player, new LeadingMobEntityCapacityPayload(((PlayerEntityAccessor)handler.player).getLeadingMobEntityCapacity())));

		PayloadTypeRegistry.playS2C().register(LeadingMobEntitiesPayload.ID, LeadingMobEntitiesPayload.CODEC);
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> syncLeadingEntities(handler.player));

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("test_command").executes(context -> {
				context.getSource().sendFeedback(() -> Text.literal("Called /test_command."), false);
				return 1;
			}));
		});
	}

	private static void syncLeadingEntities(ServerPlayerEntity player) {
		NbtList list = new NbtList();
		for(UUID uuid: ((PlayerEntityAccessor)player).getLeadingMobEntities()) {
			NbtCompound compound = new NbtCompound();
			compound.putUuid("UUID", uuid);
			list.add(compound);
		}

		ServerPlayNetworking.send(player, new LeadingMobEntitiesPayload(list));
	}

	private static void addMaterialToLootTable(EntityType entityType, Item material) {
		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if(source.isBuiltin() && entityType.getLootTableId().equals(key)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceLootCondition.builder(0.01f))
						.with(ItemEntry.builder(material))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)));
				tableBuilder.pool(poolBuilder);
			}
		});
	}
}