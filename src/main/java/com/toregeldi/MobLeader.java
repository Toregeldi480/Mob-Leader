package com.toregeldi;

import com.toregeldi.item.ModItemGroups;
import com.toregeldi.item.ModItems;
import com.toregeldi.network.c2s.LeadingMobEntityAttackModePayload;
import com.toregeldi.network.c2s.LeadingMobEntityNavigationModePayload;
import com.toregeldi.network.s2c.LeadingMobEntitiesPayload;
import com.toregeldi.network.s2c.LeadingMobEntityCapacityPayload;
import com.toregeldi.network.s2c.LeadingMobEntityWeightUsagePayload;
import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobLeader implements ModInitializer {
	public static final String MOD_ID = "mob-leader";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModItemGroups.initialize();

		addMaterialToLootTable(EntityType.BLAZE, ModItems.BLAZE_MATERIAL);
		addMaterialToLootTable(EntityType.BOGGED, ModItems.BOGGED_MATERIAL);
		addMaterialToLootTable(EntityType.BREEZE, ModItems.BREEZE_MATERIAL);
		addMaterialToLootTable(EntityType.CAVE_SPIDER, ModItems.CAVE_SPIDER_MATERIAL);
		addMaterialToLootTable(EntityType.CREEPER, ModItems.CREEPER_MATERIAL);
		addMaterialToLootTable(EntityType.DROWNED, ModItems.DROWNED_MATERIAL);
		addMaterialToLootTable(EntityType.ELDER_GUARDIAN, ModItems.OCEAN_STAR, true);
		addMaterialToLootTable(EntityType.ENDER_DRAGON, ModItems.END_STAR, true);
		addMaterialToLootTable(EntityType.ENDERMAN, ModItems.ENDERMAN_MATERIAL);
		addMaterialToLootTable(EntityType.EVOKER, ModItems.EVOKER_MATERIAL);
		addMaterialToLootTable(EntityType.GHAST, ModItems.GHAST_MATERIAL);
		addMaterialToLootTable(EntityType.HUSK, ModItems.HUSK_MATERIAL);
		addMaterialToLootTable(EntityType.ILLUSIONER, ModItems.OVERWORLD_STAR, true);
		addMaterialToLootTable(EntityType.MAGMA_CUBE, ModItems.MAGMACUBE_MATERIAL);
		addMaterialToLootTable(EntityType.PILLAGER, ModItems.PILLAGER_MATERIAL);
		addMaterialToLootTable(EntityType.SKELETON, ModItems.SKELETON_MATERIAL);
		addMaterialToLootTable(EntityType.SLIME, ModItems.SLIME_MATERIAL);
		addMaterialToLootTable(EntityType.SPIDER, ModItems.SPIDER_MATERIAL);
		addMaterialToLootTable(EntityType.STRAY, ModItems.STRAY_MATERIAL);
		addMaterialToLootTable(EntityType.VINDICATOR, ModItems.VINDICATOR_MATERIAL);
		addMaterialToLootTable(EntityType.WARDEN, ModItems.CAVE_STAR, true);
		addMaterialToLootTable(EntityType.WITHER_SKELETON, ModItems.WITHER_SKELETON_MATERIAL);
		addMaterialToLootTable(EntityType.ZOMBIE, ModItems.ZOMBIE_MATERIAL);
		addMaterialToLootTable(EntityType.ZOMBIE_VILLAGER, ModItems.ZOMBIE_MATERIAL);

		PayloadTypeRegistry.playS2C().register(LeadingMobEntityWeightUsagePayload.ID, LeadingMobEntityWeightUsagePayload.CODEC);
		ServerPlayConnectionEvents.JOIN.register((handler, packetSender, server) ->
				ServerPlayNetworking.send(handler.player, new LeadingMobEntityWeightUsagePayload(((PlayerEntityAccessor)handler.player).getLeadingMobEntityWeightUsage())));

		PayloadTypeRegistry.playS2C().register(LeadingMobEntityCapacityPayload.ID, LeadingMobEntityCapacityPayload.CODEC);
		ServerPlayConnectionEvents.JOIN.register((handler, packetSender, server) ->
				ServerPlayNetworking.send(handler.player, new LeadingMobEntityCapacityPayload(((PlayerEntityAccessor)handler.player).getLeadingMobEntityCapacity())));

		PayloadTypeRegistry.playS2C().register(LeadingMobEntitiesPayload.ID, LeadingMobEntitiesPayload.CODEC);
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> syncLeadingMobEntitiesGeneralData(handler.player));

		PayloadTypeRegistry.playC2S().register(LeadingMobEntityNavigationModePayload.ID, LeadingMobEntityNavigationModePayload.CODEC);
		PayloadTypeRegistry.playC2S().register(LeadingMobEntityAttackModePayload.ID, LeadingMobEntityAttackModePayload.CODEC);
	}

	private static void syncLeadingMobEntitiesGeneralData(ServerPlayerEntity player) {
		NbtList list = new NbtList();
		((PlayerEntityAccessor)player).getLeadingMobEntities().forEach(uuid -> {
			NbtCompound compound = new NbtCompound();
			compound.putUuid("UUID", uuid);
			if(((ServerWorld)player.getWorld()).getEntity(uuid) instanceof LivingEntity livingEntity) {
				MobEntityAccessor accessor = (MobEntityAccessor)(MobEntity)livingEntity;
				compound.putString("EntityType", livingEntity.getType().toString());
				compound.putInt("AttackMode", accessor.getAttackMode());
				compound.putInt("NavigationMode", accessor.getNavigationMode());;
			}
			list.add(compound);
		});

		ServerPlayNetworking.send(player, new LeadingMobEntitiesPayload(list));
		ServerPlayNetworking.send(player, LeadingMobEntitiesPayload.loadMobEntityTypeData(((PlayerEntityAccessor) player).getLeadingMobEntities(), player.getWorld()));
	}

	private static void addMaterialToLootTable(EntityType entityType, Item material) {
		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if(source.isBuiltin() && entityType.getLootTableId().equals(key)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceLootCondition.builder(0.25f))
						.with(ItemEntry.builder(material))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)));
				tableBuilder.pool(poolBuilder);
			}
		});
	}

	private static void addMaterialToLootTable(EntityType entityType, Item material, boolean boss) {
		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if(source.isBuiltin() && entityType.getLootTableId().equals(key)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(material))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)));
				tableBuilder.pool(poolBuilder);
			}
		});
	}
}