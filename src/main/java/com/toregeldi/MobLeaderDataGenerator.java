package com.toregeldi;

import com.toregeldi.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
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
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.BOGGED_HELMET);
            itemModelGenerator.register(ModItems.BOGGED_MATERIAL, Models.GENERATED);

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

            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.HUSK_HELMET);
            itemModelGenerator.register(ModItems.HUSK_MATERIAL, Models.GENERATED);

            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_BOOTS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_LEGGINGS);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_CHESTPLATE);
            itemModelGenerator.registerArmor((ArmorItem) ModItems.SKELETON_HELMET);
            itemModelGenerator.register(ModItems.SKELETON_MATERIAL, Models.GENERATED);

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

            itemModelGenerator.register(ModItems.MOB_LEADER_STAFF, Models.HANDHELD);
        }
    }

    static class MobLeaderRecipeGenerator extends FabricRecipeProvider {
        public MobLeaderRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        public void generate(RecipeExporter recipeExporter) {
            createArmorSet(ModItems.BOGGED_BOOTS, ModItems.BOGGED_LEGGINGS, ModItems.BOGGED_CHESTPLATE, ModItems.BOGGED_HELMET, ModItems.BOGGED_MATERIAL, recipeExporter);
            createArmorSet(ModItems.CAVE_SPIDER_BOOTS, ModItems.CAVE_SPIDER_LEGGINGS, ModItems.CAVE_SPIDER_CHESTPLATE, ModItems.CAVE_SPIDER_HELMET, ModItems.CAVE_SPIDER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.CREEPER_BOOTS, ModItems.CREEPER_LEGGINGS, ModItems.CREEPER_CHESTPLATE, ModItems.CREEPER_HELMET, ModItems.CREEPER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.DROWNED_BOOTS, ModItems.DROWNED_LEGGINGS, ModItems.DROWNED_CHESTPLATE, ModItems.DROWNED_HELMET, ModItems.DROWNED_MATERIAL, recipeExporter);
            createArmorSet(ModItems.ENDERMAN_BOOTS, ModItems.ENDERMAN_LEGGINGS, ModItems.ENDERMAN_CHESTPLATE, ModItems.ENDERMAN_HELMET, ModItems.ENDERMAN_MATERIAL, recipeExporter);
            createArmorSet(ModItems.EVOKER_BOOTS, ModItems.EVOKER_LEGGINGS, ModItems.EVOKER_CHESTPLATE, ModItems.EVOKER_HELMET, ModItems.EVOKER_MATERIAL, recipeExporter);
            createArmorSet(ModItems.HUSK_BOOTS, ModItems.HUSK_LEGGINGS, ModItems.HUSK_CHESTPLATE, ModItems.HUSK_HELMET, ModItems.HUSK_MATERIAL, recipeExporter);
            createArmorSet(ModItems.SKELETON_BOOTS, ModItems.SKELETON_LEGGINGS, ModItems.SKELETON_CHESTPLATE, ModItems.SKELETON_HELMET, ModItems.SKELETON_MATERIAL, recipeExporter);
            createArmorSet(ModItems.VINDICATOR_BOOTS, ModItems.VINDICATOR_LEGGINGS, ModItems.VINDICATOR_CHESTPLATE, ModItems.VINDICATOR_HELMET, ModItems.VINDICATOR_MATERIAL, recipeExporter);
            createArmorSet(ModItems.WITHER_SKELETON_BOOTS, ModItems.WITHER_SKELETON_LEGGINGS, ModItems.WITHER_SKELETON_CHESTPLATE, ModItems.WITHER_SKELETON_HELMET, ModItems.WITHER_SKELETON_MATERIAL, recipeExporter);
            createArmorSet(ModItems.ZOMBIE_BOOTS, ModItems.ZOMBIE_LEGGINGS, ModItems.ZOMBIE_CHESTPLATE, ModItems.ZOMBIE_HELMET, ModItems.ZOMBIE_MATERIAL, recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MOB_LEADER_STAFF)
                    .input('x', Items.STICK)
                    .input('#', Items.DIAMOND)
                    .input('%', Items.NETHER_STAR)
                    .pattern(" #%")
                    .pattern(" x#")
                    .pattern("x  ")
                    .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
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
