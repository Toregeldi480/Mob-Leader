package com.toregeldi.screen;

import com.toregeldi.MobLeader;
import com.toregeldi.network.c2s.LeadingMobEntityAttackModePayload;
import com.toregeldi.network.c2s.LeadingMobEntityNavigationModePayload;
import com.toregeldi.network.s2c.data.LeadingMobEntityGeneralData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MobEntityInteractionScreen extends Screen {
    private final String ICON_DIRECTORY = "textures/gui/";
    private final PlayerEntity player;
    private TextFieldWidget inputPosX;
    private static List<LeadingMobEntityGeneralData> leadingMobEntities;

    public MobEntityInteractionScreen(PlayerEntity player) {
        super(Text.empty());
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();

//        Text text = isFollowing ? Text.of("Stay") : Text.of("Follow");
//        this.addDrawableChild(new ButtonWidget.Builder(text, this::onNavigationBehaviour).dimensions(width - 90, this.height / 4 + 72, 80, 20).build());

//        this.inputPosX = new TextFieldWidget(this.textRenderer, this.width / 2 - 152, 80, 80, 20, Text.of("Position X"));

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.clearChildren();
        int i = 2;
        for(LeadingMobEntityGeneralData data: leadingMobEntities) {
            context.drawTexture(Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + data.type().toLowerCase().split("\\.")[2] + ".png"), 8, i, 0, 0, 16, 16, 16, 16);

            Text attack = handleAttackText(data);
            this.addDrawableChild(new ButtonWidget.Builder(attack, button -> {
                if(this.player.getWorld().isClient()) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    if(data.attackMode() < 2) {
                        compound.putInt("AttackMode", data.attackMode() + 1);
                    } else {
                        compound.putInt("AttackMode", 0);
                    }
                    ClientPlayNetworking.send(new LeadingMobEntityAttackModePayload(compound));
                }
            }).dimensions(30, i, 80, 18).build());

            Text navigation = handleNavigationText(data);
            this.addDrawableChild(new ButtonWidget.Builder(navigation, button -> {
                if(this.player.getWorld().isClient()) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    if(data.navigationMode() < 2) {
                        compound.putInt("NavigationMode", data.navigationMode() + 1);
                    } else {
                        compound.putInt("NavigationMode", 0);
                    }
                    ClientPlayNetworking.send(new LeadingMobEntityNavigationModePayload(compound));
                }
            }).dimensions(112, i, 80, 18).build());

            String pos = "X:" +  data.posX() + " Y:" + data.posY() + " Z:" + data.posZ();
            context.drawTextWithShadow(this.textRenderer, pos, 500, i + 4, 0xffffff00);

            String health = data.health() + "/" + data.maxHealth();
            context.drawTextWithShadow(this.textRenderer, health, this.width - this.textRenderer.getWidth(health), i, 0xffff0000);

            i += 18;
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    public static void getLeadingMobEntitiesFromServer(List<LeadingMobEntityGeneralData> list) {
        leadingMobEntities = new ArrayList<>(list);
    }

    private Text handleNavigationText(LeadingMobEntityGeneralData data) {
        if(data.navigationMode() == 0) {
            return Text.of("Wander");
        }
        if(data.navigationMode() == 1) {
            return Text.of("Stay");
        }
        if(data.navigationMode() == 2) {
            return Text.of("Follow");
        }
        return null;
    }

    private Text handleAttackText(LeadingMobEntityGeneralData data) {
        if(data.attackMode() == 0) {
            return Text.of("Defensive");
        }
        if(data.attackMode() == 1) {
            return Text.of("Targeted");
        }
        if(data.attackMode() == 2) {
            return Text.of("Aggressive");
        }
        return null;
    }
}
