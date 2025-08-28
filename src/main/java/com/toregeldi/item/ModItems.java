package com.toregeldi.item;

import com.toregeldi.MobLeader;
import com.toregeldi.item.custom.MobLeaderStaffItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BOGGED_HELMET = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "bogged_helmet");
    public static final Item BOGGED_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "bogged_chestplate");
    public static final Item BOGGED_LEGGINGS = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "bogged_leggings");
    public static final Item BOGGED_BOOTS = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "bogged_boots");
    public static final Item BOGGED_MATERIAL = register(new Item(new Item.Settings()), "bogged_material");

    public static final Item CAVE_SPIDER_HELMET = register(new ArmorItem(ModArmorMaterials.CAVE_SPIDER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "cave_spider_helmet");
    public static final Item CAVE_SPIDER_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.CAVE_SPIDER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "cave_spider_chestplate");
    public static final Item CAVE_SPIDER_LEGGINGS = register(new ArmorItem(ModArmorMaterials.CAVE_SPIDER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "cave_spider_leggings");
    public static final Item CAVE_SPIDER_BOOTS = register(new ArmorItem(ModArmorMaterials.CAVE_SPIDER, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "cave_spider_boots");
    public static final Item CAVE_SPIDER_MATERIAL = register(new Item(new Item.Settings()), "cave_spider_material");

    public static final Item CREEPER_HELMET = register(new ArmorItem(ModArmorMaterials.CREEPER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "creeper_helmet");
    public static final Item CREEPER_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.CREEPER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "creeper_chestplate");
    public static final Item CREEPER_LEGGINGS = register(new ArmorItem(ModArmorMaterials.CREEPER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "creeper_leggings");
    public static final Item CREEPER_BOOTS = register(new ArmorItem(ModArmorMaterials.CREEPER, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "creeper_boots");
    public static final Item CREEPER_MATERIAL = register(new Item(new Item.Settings()), "creeper_material");

    public static final Item DROWNED_HELMET = register(new ArmorItem(ModArmorMaterials.DROWNED, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "drowned_helmet");
    public static final Item DROWNED_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.DROWNED, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "drowned_chestplate");
    public static final Item DROWNED_LEGGINGS = register(new ArmorItem(ModArmorMaterials.DROWNED, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "drowned_leggings");
    public static final Item DROWNED_BOOTS = register(new ArmorItem(ModArmorMaterials.DROWNED, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "drowned_boots");
    public static final Item DROWNED_MATERIAL = register(new Item(new Item.Settings()), "drowned_material");

    public static final Item ENDERMAN_HELMET = register(new ArmorItem(ModArmorMaterials.ENDERMAN, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "enderman_helmet");
    public static final Item ENDERMAN_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.ENDERMAN, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "enderman_chestplate");
    public static final Item ENDERMAN_LEGGINGS = register(new ArmorItem(ModArmorMaterials.ENDERMAN, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "enderman_leggings");
    public static final Item ENDERMAN_BOOTS = register(new ArmorItem(ModArmorMaterials.ENDERMAN, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "enderman_boots");
    public static final Item ENDERMAN_MATERIAL = register(new Item(new Item.Settings()), "enderman_material");

    public static final Item EVOKER_HELMET = register(new ArmorItem(ModArmorMaterials.EVOKER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "evoker_helmet");
    public static final Item EVOKER_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.EVOKER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "evoker_chestplate");
    public static final Item EVOKER_LEGGINGS = register(new ArmorItem(ModArmorMaterials.EVOKER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "evoker_leggings");
    public static final Item EVOKER_BOOTS = register(new ArmorItem(ModArmorMaterials.EVOKER, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "evoker_boots");
    public static final Item EVOKER_MATERIAL = register(new Item(new Item.Settings()), "evoker_material");

    public static final Item HUSK_HELMET = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "husk_helmet");
    public static final Item HUSK_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "husk_chestplate");
    public static final Item HUSK_LEGGINGS = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "husk_leggings");
    public static final Item HUSK_BOOTS = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "husk_boots");
    public static final Item HUSK_MATERIAL = register(new Item(new Item.Settings()), "husk_material");

    public static final Item SKELETON_HELMET = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "skeleton_helmet");
    public static final Item SKELETON_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "skeleton_chestplate");
    public static final Item SKELETON_LEGGINGS = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "skeleton_leggings");
    public static final Item SKELETON_BOOTS = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "skeleton_boots");
    public static final Item SKELETON_MATERIAL = register(new Item(new Item.Settings()), "skeleton_material");

    public static final Item VINDICATOR_HELMET = register(new ArmorItem(ModArmorMaterials.VINDICATOR, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "vindicator_helmet");
    public static final Item VINDICATOR_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.VINDICATOR, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "vindicator_chestplate");
    public static final Item VINDICATOR_LEGGINGS = register(new ArmorItem(ModArmorMaterials.VINDICATOR, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "vindicator_leggings");
    public static final Item VINDICATOR_BOOTS = register(new ArmorItem(ModArmorMaterials.VINDICATOR, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "vindicator_boots");
    public static final Item VINDICATOR_MATERIAL = register(new Item(new Item.Settings()), "vindicator_material");

    public static final Item WITHER_SKELETON_HELMET = register(new ArmorItem(ModArmorMaterials.WITHER_SKELETON, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "wither_skeleton_helmet");
    public static final Item WITHER_SKELETON_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.WITHER_SKELETON, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "wither_skeleton_chestplate");
    public static final Item WITHER_SKELETON_LEGGINGS = register(new ArmorItem(ModArmorMaterials.WITHER_SKELETON, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "wither_skeleton_leggings");
    public static final Item WITHER_SKELETON_BOOTS = register(new ArmorItem(ModArmorMaterials.WITHER_SKELETON, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "wither_skeleton_boots");
    public static final Item WITHER_SKELETON_MATERIAL = register(new Item(new Item.Settings()), "wither_skeleton_material");

    public static final Item ZOMBIE_HELMET = register(new ArmorItem(ModArmorMaterials.ZOMBIE, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "zombie_helmet");
    public static final Item ZOMBIE_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.ZOMBIE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "zombie_chestplate");
    public static final Item ZOMBIE_LEGGINGS = register(new ArmorItem(ModArmorMaterials.ZOMBIE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "zombie_leggings");
    public static final Item ZOMBIE_BOOTS = register(new ArmorItem(ModArmorMaterials.ZOMBIE, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "zombie_boots");
    public static final Item ZOMBIE_MATERIAL = register(new Item(new Item.Settings()), "zombie_material");

    public static final Item MOB_LEADER_STAFF = register(new MobLeaderStaffItem(new Item.Settings()), "mob_leader_staff");

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(MobLeader.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {}
}
