package com.toregeldi.gui;

import com.toregeldi.MobLeader;
import com.toregeldi.util.PlayerEntityAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public class PlayerLeadingEntityGUI implements HudRenderCallback {
    private static final String ICON_DIRECTORY = "textures/gui/sprites/hud/entity/";
    private static final Map<EntityType, Identifier> MOB_ENTITY_ICON = new HashMap<>() {{
        put(EntityType.BOGGED, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "bogged"));
        put(EntityType.CAVE_SPIDER, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "cave_spider"));
        put(EntityType.CREEPER, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "creeper"));
        put(EntityType.DROWNED, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "drowned"));
        put(EntityType.ENDERMAN, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "enderman"));
        put(EntityType.EVOKER, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "evoker"));
        put(EntityType.HUSK, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "husk"));
        put(EntityType.SKELETON, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "skeleton"));
        put(EntityType.VINDICATOR, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "vindicator"));
        put(EntityType.WITHER_SKELETON, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "wither_skeleton"));
        put(EntityType.ZOMBIE, Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + "zombie"));
    }};

    private static List<UUID> leadingMobEntities;
    private static int weight = 0;
    private static int capacity = 0;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if(player != null) {
            World world = player.getWorld();
            int x = (drawContext.getScaledWindowWidth() / 2);
            int y = 0;
            int textX = x - (client.textRenderer.getWidth(String.valueOf(weight)) / 2);
            int textY = y + 12;

            drawContext.drawTextWithShadow(client.textRenderer, String.valueOf(weight + "/" + capacity), textX, textY, 0xffffff);

            if(world instanceof ServerWorld serverWorld) {
                for(UUID uuid: leadingMobEntities) {
                    MobLeader.LOGGER.info("UUID:" + uuid);
                    drawContext.drawTextWithShadow(client.textRenderer, String.valueOf(((LivingEntity)serverWorld.getEntity(uuid)).getHealth()), textX + 10, textY, 0xffff0000);
                }
            }
        }

    }

    public static void getLeadingMobEntityWeightFromServer(int weightx) {
        weight = weightx;
    }

    public static void getLeadingMobEntityCapacityFromServer(int capacityx) {
        capacity = capacityx;
    }

    public static void getLeadingMobEntitiesFromServer(NbtList list) {
        list.stream().forEach(compound -> leadingMobEntities.add(((NbtCompound)compound).getUuid("UUID")));
    }
}
