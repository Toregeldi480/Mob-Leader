package com.toregeldi.item;

import com.toregeldi.MobLeader;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MOB_ARMOR_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CREEPER_HELMET))
            .displayName(Text.translatable("itemGroup.mob-leader.mob_armor_group"))
            .entries((context, entries) -> {
                entries.add(ModItems.MOB_LEADER_STAFF);

                entries.add(ModItems.BOGGED_HELMET);
                entries.add(ModItems.BOGGED_CHESTPLATE);
                entries.add(ModItems.BOGGED_LEGGINGS);
                entries.add(ModItems.BOGGED_BOOTS);

                entries.add(ModItems.CAVE_SPIDER_HELMET);
                entries.add(ModItems.CAVE_SPIDER_CHESTPLATE);
                entries.add(ModItems.CAVE_SPIDER_LEGGINGS);
                entries.add(ModItems.CAVE_SPIDER_BOOTS);

                entries.add(ModItems.CREEPER_HELMET);
                entries.add(ModItems.CREEPER_CHESTPLATE);
                entries.add(ModItems.CREEPER_LEGGINGS);
                entries.add(ModItems.CREEPER_BOOTS);

                entries.add(ModItems.DROWNED_HELMET);
                entries.add(ModItems.DROWNED_CHESTPLATE);
                entries.add(ModItems.DROWNED_LEGGINGS);
                entries.add(ModItems.DROWNED_BOOTS);

                entries.add(ModItems.ENDERMAN_HELMET);
                entries.add(ModItems.ENDERMAN_CHESTPLATE);
                entries.add(ModItems.ENDERMAN_LEGGINGS);
                entries.add(ModItems.ENDERMAN_BOOTS);

                entries.add(ModItems.EVOKER_HELMET);
                entries.add(ModItems.EVOKER_CHESTPLATE);
                entries.add(ModItems.EVOKER_LEGGINGS);
                entries.add(ModItems.EVOKER_BOOTS);

                entries.add(ModItems.GHAST_HELMET);
                entries.add(ModItems.GHAST_CHESTPLATE);
                entries.add(ModItems.GHAST_LEGGINGS);
                entries.add(ModItems.GHAST_BOOTS);

                entries.add(ModItems.HUSK_HELMET);
                entries.add(ModItems.HUSK_CHESTPLATE);
                entries.add(ModItems.HUSK_LEGGINGS);
                entries.add(ModItems.HUSK_BOOTS);

                entries.add(ModItems.PILLAGER_HELMET);
                entries.add(ModItems.PILLAGER_CHESTPLATE);
                entries.add(ModItems.PILLAGER_LEGGINGS);
                entries.add(ModItems.PILLAGER_BOOTS);

                entries.add(ModItems.SKELETON_HELMET);
                entries.add(ModItems.SKELETON_CHESTPLATE);
                entries.add(ModItems.SKELETON_LEGGINGS);
                entries.add(ModItems.SKELETON_BOOTS);

                entries.add(ModItems.SLIME_HELMET);
                entries.add(ModItems.SLIME_CHESTPLATE);
                entries.add(ModItems.SLIME_LEGGINGS);
                entries.add(ModItems.SLIME_BOOTS);

                entries.add(ModItems.STRAY_HELMET);
                entries.add(ModItems.STRAY_CHESTPLATE);
                entries.add(ModItems.STRAY_LEGGINGS);
                entries.add(ModItems.STRAY_BOOTS);

                entries.add(ModItems.VINDICATOR_HELMET);
                entries.add(ModItems.VINDICATOR_CHESTPLATE);
                entries.add(ModItems.VINDICATOR_LEGGINGS);
                entries.add(ModItems.VINDICATOR_BOOTS);

                entries.add(ModItems.WITHER_SKELETON_HELMET);
                entries.add(ModItems.WITHER_SKELETON_CHESTPLATE);
                entries.add(ModItems.WITHER_SKELETON_LEGGINGS);
                entries.add(ModItems.WITHER_SKELETON_BOOTS);

                entries.add(ModItems.ZOMBIE_HELMET);
                entries.add(ModItems.ZOMBIE_CHESTPLATE);
                entries.add(ModItems.ZOMBIE_LEGGINGS);
                entries.add(ModItems.ZOMBIE_BOOTS);
            }).build();

    public static final ItemGroup MOB_MISC_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CREEPER_MATERIAL))
            .displayName(Text.translatable("itemGroup.mob-leader.mob_misc_group"))
            .entries((context, entries) -> {
                entries.add(ModItems.BOGGED_MATERIAL);
                entries.add(ModItems.CAVE_SPIDER_MATERIAL);
                entries.add(ModItems.CREEPER_MATERIAL);
                entries.add(ModItems.DROWNED_MATERIAL);
                entries.add(ModItems.ENDERMAN_MATERIAL);
                entries.add(ModItems.EVOKER_MATERIAL);
                entries.add(ModItems.GHAST_MATERIAL);
                entries.add(ModItems.HUSK_MATERIAL);
                entries.add(ModItems.PILLAGER_MATERIAL);
                entries.add(ModItems.SKELETON_MATERIAL);
                entries.add(ModItems.SLIME_MATERIAL);
                entries.add(ModItems.STRAY_MATERIAL);
                entries.add(ModItems.VINDICATOR_MATERIAL);
                entries.add(ModItems.WITHER_SKELETON_MATERIAL);
                entries.add(ModItems.ZOMBIE_MATERIAL);
            }).build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(MobLeader.MOD_ID, "mob_armor"), MOB_ARMOR_GROUP);
        Registry.register(Registries.ITEM_GROUP, Identifier.of(MobLeader.MOD_ID, "mob_misc"), MOB_MISC_GROUP);
    }
}
