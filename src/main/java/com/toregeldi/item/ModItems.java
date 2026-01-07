package com.toregeldi.item;

import com.toregeldi.MobLeader;
import com.toregeldi.item.custom.MobLeaderStaffItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BLAZE_HELMET = register(new ArmorItem(ModArmorMaterials.BLAZE, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "blaze_helmet");
    public static final Item BLAZE_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.BLAZE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "blaze_chestplate");
    public static final Item BLAZE_LEGGINGS = register(new ArmorItem(ModArmorMaterials.BLAZE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "blaze_leggings");
    public static final Item BLAZE_BOOTS = register(new ArmorItem(ModArmorMaterials.BLAZE, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "blaze_boots");
    public static final Item BLAZE_MATERIAL = register(new Item(new Item.Settings()), "blaze_material");

    public static final Item BOGGED_HELMET = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "bogged_helmet");
    public static final Item BOGGED_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "bogged_chestplate");
    public static final Item BOGGED_LEGGINGS = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "bogged_leggings");
    public static final Item BOGGED_BOOTS = register(new ArmorItem(ModArmorMaterials.BOGGED, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "bogged_boots");
    public static final Item BOGGED_MATERIAL = register(new Item(new Item.Settings()), "bogged_material");

//    public static final Item BREEZE_HELMET = register(new ArmorItem(ModArmorMaterials.BREEZE, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "breeze_helmet");
//    public static final Item BREEZE_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.BREEZE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "breeze_chestplate");
//    public static final Item BREEZE_LEGGINGS = register(new ArmorItem(ModArmorMaterials.BREEZE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "breeze_leggings");
//    public static final Item BREEZE_BOOTS = register(new ArmorItem(ModArmorMaterials.BREEZE, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "breeze_boots");
//    public static final Item BREEZE_MATERIAL = register(new Item(new Item.Settings()), "breeze_material");

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

    public static final Item GHAST_HELMET = register(new ArmorItem(ModArmorMaterials.GHAST, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "ghast_helmet");
    public static final Item GHAST_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.GHAST, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "ghast_chestplate");
    public static final Item GHAST_LEGGINGS = register(new ArmorItem(ModArmorMaterials.GHAST, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "ghast_leggings");
    public static final Item GHAST_BOOTS = register(new ArmorItem(ModArmorMaterials.GHAST, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "ghast_boots");
    public static final Item GHAST_MATERIAL = register(new Item(new Item.Settings()), "ghast_material");

    public static final Item HEROBRINE_HELMET = register(new ArmorItem(ModArmorMaterials.HEROBRINE, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "herobrine_helmet");
    public static final Item HEROBRINE_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.HEROBRINE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "herobrine_chestplate");
    public static final Item HEROBRINE_LEGGINGS = register(new ArmorItem(ModArmorMaterials.HEROBRINE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "herobrine_leggings");
    public static final Item HEROBRINE_BOOTS = register(new ArmorItem(ModArmorMaterials.HEROBRINE, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "herobrine_boots");
    public static final Item HEROBRINE_MATERIAL = register(new Item(new Item.Settings()), "herobrine_material");

    public static final Item HUSK_HELMET = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "husk_helmet");
    public static final Item HUSK_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "husk_chestplate");
    public static final Item HUSK_LEGGINGS = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "husk_leggings");
    public static final Item HUSK_BOOTS = register(new ArmorItem(ModArmorMaterials.HUSK, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "husk_boots");
    public static final Item HUSK_MATERIAL = register(new Item(new Item.Settings()), "husk_material");

    public static final Item MAGMACUBE_HELMET = register(new ArmorItem(ModArmorMaterials.MAGMACUBE, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "magmacube_helmet");
    public static final Item MAGMACUBE_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.MAGMACUBE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "magmacube_chestplate");
    public static final Item MAGMACUBE_LEGGINGS = register(new ArmorItem(ModArmorMaterials.MAGMACUBE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "magmacube_leggings");
    public static final Item MAGMACUBE_BOOTS = register(new ArmorItem(ModArmorMaterials.MAGMACUBE, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "magmacube_boots");
    public static final Item MAGMACUBE_MATERIAL = register(new Item(new Item.Settings()), "magmacube_material");

    public static final Item PILLAGER_HELMET = register(new ArmorItem(ModArmorMaterials.PILLAGER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "pillager_helmet");
    public static final Item PILLAGER_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.PILLAGER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "pillager_chestplate");
    public static final Item PILLAGER_LEGGINGS = register(new ArmorItem(ModArmorMaterials.PILLAGER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "pillager_leggings");
    public static final Item PILLAGER_BOOTS = register(new ArmorItem(ModArmorMaterials.PILLAGER, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "pillager_boots");
    public static final Item PILLAGER_MATERIAL = register(new Item(new Item.Settings()), "pillager_material");

    public static final Item SKELETON_HELMET = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "skeleton_helmet");
    public static final Item SKELETON_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "skeleton_chestplate");
    public static final Item SKELETON_LEGGINGS = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "skeleton_leggings");
    public static final Item SKELETON_BOOTS = register(new ArmorItem(ModArmorMaterials.SKELETON, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "skeleton_boots");
    public static final Item SKELETON_MATERIAL = register(new Item(new Item.Settings()), "skeleton_material");

    public static final Item SLIME_HELMET = register(new ArmorItem(ModArmorMaterials.SLIME, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "slime_helmet");
    public static final Item SLIME_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.SLIME, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "slime_chestplate");
    public static final Item SLIME_LEGGINGS = register(new ArmorItem(ModArmorMaterials.SLIME, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "slime_leggings");
    public static final Item SLIME_BOOTS = register(new ArmorItem(ModArmorMaterials.SLIME, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "slime_boots");
    public static final Item SLIME_MATERIAL = register(new Item(new Item.Settings()), "slime_material");

    public static final Item SPIDER_HELMET = register(new ArmorItem(ModArmorMaterials.SPIDER, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "spider_helmet");
    public static final Item SPIDER_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.SPIDER, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "spider_chestplate");
    public static final Item SPIDER_LEGGINGS = register(new ArmorItem(ModArmorMaterials.SPIDER, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "spider_leggings");
    public static final Item SPIDER_BOOTS = register(new ArmorItem(ModArmorMaterials.SPIDER, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "spider_boots");
    public static final Item SPIDER_MATERIAL = register(new Item(new Item.Settings()), "spider_material");

    public static final Item STRAY_HELMET = register(new ArmorItem(ModArmorMaterials.STRAY, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1)), "stray_helmet");
    public static final Item STRAY_CHESTPLATE = register(new ArmorItem(ModArmorMaterials.STRAY, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1)), "stray_chestplate");
    public static final Item STRAY_LEGGINGS = register(new ArmorItem(ModArmorMaterials.STRAY, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1)), "stray_leggings");
    public static final Item STRAY_BOOTS = register(new ArmorItem(ModArmorMaterials.STRAY, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1)), "stray_boots");
    public static final Item STRAY_MATERIAL = register(new Item(new Item.Settings()), "stray_material");

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

    public static final Item CAVE_STAR = register(new Item(new Item.Settings()), "cave_star");
    public static final Item END_STAR = register(new Item(new Item.Settings()), "end_star");
    public static final Item OCEAN_STAR = register(new Item(new Item.Settings()), "ocean_star");
    public static final Item OVERWORLD_STAR = register(new Item(new Item.Settings()), "overworld_star");

    public static final Item MOB_LEADER_STAFF = register(new MobLeaderStaffItem(new Item.Settings().maxCount(1)), "mob_leader_staff");

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(MobLeader.MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static void initialize() {}
}
