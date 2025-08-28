package com.toregeldi.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.toregeldi.MobLeader;
import com.toregeldi.item.ModItems;
import com.toregeldi.network.s2c.data.LeadingMobEntityGeneralData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;

import java.util.*;

@Environment(EnvType.CLIENT)
public class PlayerLeadingEntityGUI implements HudRenderCallback {
    private static int weightUsage = 0;
    private static int capacity = 0;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if(player != null && (player.getMainHandStack().getItem() == ModItems.MOB_LEADER_STAFF || player.getOffHandStack().getItem() == ModItems.MOB_LEADER_STAFF)) {
            String weightInfo = String.valueOf(weightUsage + "/" + capacity);
            int x = drawContext.getScaledWindowWidth() / 2;
            int textX = x - (client.textRenderer.getWidth(weightInfo) / 2);
            int textY = 12 + (drawContext.getScaledWindowHeight() / 2);

            drawContext.drawTextWithShadow(client.textRenderer, weightInfo, textX, textY, 0xffffff);
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            drawContext.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public static void getLeadingMobEntityWeightUsageFromServer(int weightx) {
        weightUsage = weightx;
    }

    public static void getLeadingMobEntityCapacityFromServer(int capacityx) {
        capacity = capacityx;
    }
}
