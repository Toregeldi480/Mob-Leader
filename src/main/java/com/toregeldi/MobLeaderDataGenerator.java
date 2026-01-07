package com.toregeldi;

import com.toregeldi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class MobLeaderDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(MobLeaderModelGenerator::new);
        pack.addProvider(MobLeaderRecipeGenerator::new);
    }
    
    static class MobLeaderModelGenerator extends FabricModelProvider {
        public MobLeaderModelGenerator(FabricDataOutput output) {
            super(output);
        }
        @Override

        public void generateBlockStateModels(net.minecraft.data.client.BlockStateModelGenerator blockStateModelGenerator) {

        }

        @Override
        public void generateItemModels(net.minecraft.data.client.ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BLAZE_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BLAZE_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BLAZE_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BLAZE_HELMET);
            itemModelGenerator.register(ModItems.BLAZE_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_HELMET);
            itemModelGenerator.register(ModItems.BOGGED_MATERIAL, Models.GENERATED);

//            itemModelGenerator.registerArmor((ArmorItem) ModItems.BREEZE_BOOTS);
//            itemModelGenerator.registerArmor((ArmorItem) ModItems.BREEZE_LEGGINGS);
//            itemModelGenerator.registerArmor((ArmorItem) ModItems.BREEZE_CHESTPLATE);
//            itemModelGenerator.registerArmor((ArmorItem) ModItems.BREEZE_HELMET);
//            itemModelGenerator.register(ModItems.BREEZE_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.CAVE_SPIDER_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.CAVE_SPIDER_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.CAVE_SPIDER_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.CAVE_SPIDER_HELMET);
            itemModelGenerator.register(ModItems.CAVE_SPIDER_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.CREEPER_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.CREEPER_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.CREEPER_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.CREEPER_HELMET);
            itemModelGenerator.register(ModItems.CREEPER_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.DROWNED_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.DROWNED_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.DROWNED_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.DROWNED_HELMET);
            itemModelGenerator.register(ModItems.DROWNED_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.ENDERMAN_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.ENDERMAN_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.ENDERMAN_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.ENDERMAN_HELMET);
            itemModelGenerator.register(ModItems.ENDERMAN_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.EVOKER_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.EVOKER_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.EVOKER_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.EVOKER_HELMET);
            itemModelGenerator.register(ModItems.EVOKER_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.GHAST_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.GHAST_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.GHAST_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.GHAST_HELMET);
            itemModelGenerator.register(ModItems.GHAST_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.HEROBRINE_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HEROBRINE_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HEROBRINE_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HEROBRINE_HELMET);
            itemModelGenerator.register(ModItems.HEROBRINE_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_HELMET);
            itemModelGenerator.register(ModItems.HUSK_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.MAGMACUBE_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.MAGMACUBE_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.MAGMACUBE_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.MAGMACUBE_HELMET);
            itemModelGenerator.register(ModItems.MAGMACUBE_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.PILLAGER_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.PILLAGER_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.PILLAGER_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.PILLAGER_HELMET);
            itemModelGenerator.register(ModItems.PILLAGER_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_HELMET);
            itemModelGenerator.register(ModItems.SKELETON_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.SLIME_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SLIME_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SLIME_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SLIME_HELMET);
            itemModelGenerator.register(ModItems.SLIME_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.SPIDER_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SPIDER_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SPIDER_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SPIDER_HELMET);
            itemModelGenerator.register(ModItems.SPIDER_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.STRAY_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.STRAY_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.STRAY_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.STRAY_HELMET);
            itemModelGenerator.register(ModItems.STRAY_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.VINDICATOR_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.VINDICATOR_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.VINDICATOR_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.VINDICATOR_HELMET);
            itemModelGenerator.register(ModItems.VINDICATOR_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.WITHER_SKELETON_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.WITHER_SKELETON_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.WITHER_SKELETON_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.WITHER_SKELETON_HELMET);
            itemModelGenerator.register(ModItems.WITHER_SKELETON_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.ZOMBIE_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.ZOMBIE_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.ZOMBIE_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.ZOMBIE_HELMET);
            itemModelGenerator.register(ModItems.ZOMBIE_MATERIAL, Models.GENERATED);

            itemModelGenerator.register(ModItems.CAVE_STAR, Models.GENERATED);
            itemModelGenerator.register(ModItems.END_STAR, Models.GENERATED);
            itemModelGenerator.register(ModItems.OCEAN_STAR, Models.GENERATED);
            itemModelGenerator.register(ModItems.OVERWORLD_STAR, Models.GENERATED);

            itemModelGenerator.register(ModItems.HEROBRINE_MATERIAL, Models.GENERATED);

            itemModelGenerator.register(ModItems.MOB_LEADER_STAFF, Models.HANDHELD);
        }
    }

    static class MobLeaderRecipeGenerator extends FabricRecipeProvider {
        public MobLeaderRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        public void generate(RecipeExporter recipeExporter) {
            createArmorSet(ModItems.BLAZE_BOOTS, ModItems.BLAZE_LEGGINGS, ModItems.BLAZE_CHESTPLATE, ModItems.BLAZE_HELMET, ModItems.BLAZE_MATERIAL, recipeExporter);
            createArmorSet(ModItems.BOGGED_BOOTS, ModItems.BOGGED_LEGGINGS, ModItems.BOGGED_CHESTPLATE, ModItems.BOGGED_HELMET, ModItems.BOGGED_MATERIAL, recipeExporter);
//            createArmorSet(ModItems.BREEZE_BOOTS, ModItems.BREEZE_LEGGINGS, ModItems.BREEZE_CHESTPLATE, ModItems.BREEZE_HELMET, ModItems.BREEZE_MATERIAL, recipeExporter);
            createArmorSet(ModItems.CAVE_SPIDER_BOOTS, ModItems.CAVE_SPIDER_LEGGINGS, ModItems.CAVE_SPIDER_CHESTPLATE, ModItems.CAVE_SPIDER_HELMET, ModItems.CAVE_SPIDER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.CREEPER_BOOTS, ModItems.CREEPER_LEGGINGS, ModItems.CREEPER_CHESTPLATE, ModItems.CREEPER_HELMET, ModItems.CREEPER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.DROWNED_BOOTS, ModItems.DROWNED_LEGGINGS, ModItems.DROWNED_CHESTPLATE, ModItems.DROWNED_HELMET, ModItems.DROWNED_MATERIAL, recipeExporter);
            createArmorSet(ModItems.ENDERMAN_BOOTS, ModItems.ENDERMAN_LEGGINGS, ModItems.ENDERMAN_CHESTPLATE, ModItems.ENDERMAN_HELMET, ModItems.ENDERMAN_MATERIAL, recipeExporter);
            createArmorSet(ModItems.EVOKER_BOOTS, ModItems.EVOKER_LEGGINGS, ModItems.EVOKER_CHESTPLATE, ModItems.EVOKER_HELMET, ModItems.EVOKER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.GHAST_BOOTS, ModItems.GHAST_LEGGINGS, ModItems.GHAST_CHESTPLATE, ModItems.GHAST_HELMET, ModItems.GHAST_MATERIAL, recipeExporter);
            createArmorSet(ModItems.HEROBRINE_BOOTS, ModItems.HEROBRINE_LEGGINGS, ModItems.HEROBRINE_CHESTPLATE, ModItems.HEROBRINE_HELMET, ModItems.HEROBRINE_MATERIAL, recipeExporter);
            createArmorSet(ModItems.HUSK_BOOTS, ModItems.HUSK_LEGGINGS, ModItems.HUSK_CHESTPLATE, ModItems.HUSK_HELMET, ModItems.HUSK_MATERIAL, recipeExporter);
            createArmorSet(ModItems.MAGMACUBE_BOOTS, ModItems.MAGMACUBE_LEGGINGS, ModItems.MAGMACUBE_CHESTPLATE, ModItems.MAGMACUBE_HELMET, ModItems.MAGMACUBE_MATERIAL, recipeExporter);
            createArmorSet(ModItems.PILLAGER_BOOTS, ModItems.PILLAGER_LEGGINGS, ModItems.PILLAGER_CHESTPLATE, ModItems.PILLAGER_HELMET, ModItems.PILLAGER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.SKELETON_BOOTS, ModItems.SKELETON_LEGGINGS, ModItems.SKELETON_CHESTPLATE, ModItems.SKELETON_HELMET, ModItems.SKELETON_MATERIAL, recipeExporter);
            createArmorSet(ModItems.SLIME_BOOTS, ModItems.SLIME_LEGGINGS, ModItems.SLIME_CHESTPLATE, ModItems.SLIME_HELMET, ModItems.SLIME_MATERIAL, recipeExporter);
            createArmorSet(ModItems.SPIDER_BOOTS, ModItems.SPIDER_LEGGINGS, ModItems.SPIDER_CHESTPLATE, ModItems.SPIDER_HELMET, ModItems.SPIDER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.STRAY_BOOTS, ModItems.STRAY_LEGGINGS, ModItems.STRAY_CHESTPLATE, ModItems.STRAY_HELMET, ModItems.STRAY_MATERIAL, recipeExporter);
            createArmorSet(ModItems.VINDICATOR_BOOTS, ModItems.VINDICATOR_LEGGINGS, ModItems.VINDICATOR_CHESTPLATE, ModItems.VINDICATOR_HELMET, ModItems.VINDICATOR_MATERIAL, recipeExporter);
            createArmorSet(ModItems.WITHER_SKELETON_BOOTS, ModItems.WITHER_SKELETON_LEGGINGS, ModItems.WITHER_SKELETON_CHESTPLATE, ModItems.WITHER_SKELETON_HELMET, ModItems.WITHER_SKELETON_MATERIAL, recipeExporter);
            createArmorSet(ModItems.ZOMBIE_BOOTS, ModItems.ZOMBIE_LEGGINGS, ModItems.ZOMBIE_CHESTPLATE, ModItems.ZOMBIE_HELMET, ModItems.ZOMBIE_MATERIAL, recipeExporter);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEROBRINE_MATERIAL, 64)
                    .input(ModItems.CAVE_STAR, 1)
                    .input(ModItems.END_STAR, 1)
                    .input(Items.NETHER_STAR, 1)
                    .input(ModItems.OCEAN_STAR, 1)
                    .input(ModItems.OVERWORLD_STAR, 1)
                    .criterion(hasItem(ModItems.CAVE_STAR), conditionsFromItem(ModItems.CAVE_STAR))
                    .criterion(hasItem(ModItems.END_STAR), conditionsFromItem(ModItems.END_STAR))
                    .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                    .criterion(hasItem(ModItems.OCEAN_STAR), conditionsFromItem(ModItems.OCEAN_STAR))
                    .criterion(hasItem(ModItems.OVERWORLD_STAR), conditionsFromItem(ModItems.OVERWORLD_STAR))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MOB_LEADER_STAFF)
                    .input('x', Items.STICK)
                    .input('#', Items.DIAMOND)
                    .input('%', Items.EMERALD)
                    .pattern(" #%")
                    .pattern(" x#")
                    .pattern("x  ")
                    .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                    .offerTo(recipeExporter);
        }

        private void createArmorSet(Item boots, Item leggings, Item chestplate, Item helmet, Item material, RecipeExporter exporter) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, boots, 1).input(material, 4).input(Items.DIAMOND_BOOTS, 1).criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, leggings, 1).input(material, 7).input(Items.DIAMOND_LEGGINGS, 1).criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, chestplate, 1).input(material, 8).input(Items.DIAMOND_CHESTPLATE, 1).criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, helmet, 1).input(material, 5).input(Items.DIAMOND_HELMET, 1).criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);
        }

        @Override
        public String getName() {
            return "MobLeaderRecipeGenerator";
        }
    }
}
