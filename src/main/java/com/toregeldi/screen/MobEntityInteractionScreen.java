package com.toregeldi.screen;

import com.toregeldi.MobLeader;
import com.toregeldi.network.c2s.LeadingMobEntityAttackModePayload;
import com.toregeldi.network.c2s.LeadingMobEntityNavigationModePayload;
import com.toregeldi.network.s2c.data.LeadingMobEntityGeneralData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MobEntityInteractionScreen extends Screen {
    private final String ICON_DIRECTORY = "textures/gui/";
    private final PlayerEntity player;
    private static List<LeadingMobEntityGeneralData> leadingMobEntities;

    public MobEntityInteractionScreen(PlayerEntity player) {
        super(Text.empty());
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.clearChildren();
        int x = 0;
        int y = 2;
        for(LeadingMobEntityGeneralData data: leadingMobEntities) {
            String type = data.type().toLowerCase().split("\\.")[2];
            if(type.equals("vex")) {
                continue;
            }

            context.drawTexture(Identifier.of(MobLeader.MOD_ID, ICON_DIRECTORY + type + ".png"), x + 2, y, 0, 0, 16, 16, 16, 16);

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
            }).dimensions(x + 20, y, 60, 18).build());

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
            }).dimensions(x + 82, y, 60, 18).build());

            y += 18;
            if(y >= this.height - 38) {
                y = 2;
                x += 150;
            }
        }

        this.addDrawableChild(new ButtonWidget.Builder(Text.of("All Defense"), button -> {
            if(this.player.getWorld().isClient) {
                for(LeadingMobEntityGeneralData data: leadingMobEntities) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    compound.putInt("AttackMode", 0);
                    ClientPlayNetworking.send(new LeadingMobEntityAttackModePayload(compound));
                }
            }
        }).dimensions(0, this.height - 20, 80, 18).build());

        this.addDrawableChild(new ButtonWidget.Builder(Text.of("All Targeted"), button -> {
            if(this.player.getWorld().isClient) {
                for(LeadingMobEntityGeneralData data: leadingMobEntities) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    compound.putInt("AttackMode", 1);
                    ClientPlayNetworking.send(new LeadingMobEntityAttackModePayload(compound));
                }
            }
        }).dimensions(80, this.height - 20, 80, 18).build());

        this.addDrawableChild(new ButtonWidget.Builder(Text.of("All Aggressive"), button -> {
            if(this.player.getWorld().isClient) {
                for(LeadingMobEntityGeneralData data: leadingMobEntities) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    compound.putInt("AttackMode", 2);
                    ClientPlayNetworking.send(new LeadingMobEntityAttackModePayload(compound));
                }
            }
        }).dimensions(160, this.height - 20, 80, 18).build());

        this.addDrawableChild(new ButtonWidget.Builder(Text.of("All Wander"), button -> {
            if(this.player.getWorld().isClient) {
                for(LeadingMobEntityGeneralData data: leadingMobEntities) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    compound.putInt("NavigationMode", 0);
                    ClientPlayNetworking.send(new LeadingMobEntityNavigationModePayload(compound));
                }
            }
        }).dimensions(240, this.height - 20, 80, 18).build());

        this.addDrawableChild(new ButtonWidget.Builder(Text.of("All Stay"), button -> {
            if(this.player.getWorld().isClient) {
                for(LeadingMobEntityGeneralData data: leadingMobEntities) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    compound.putInt("NavigationMode", 1);
                    ClientPlayNetworking.send(new LeadingMobEntityNavigationModePayload(compound));
                }
            }
        }).dimensions(320, this.height - 20, 80, 18).build());

        this.addDrawableChild(new ButtonWidget.Builder(Text.of("All Follow"), button -> {
            if(this.player.getWorld().isClient) {
                for(LeadingMobEntityGeneralData data: leadingMobEntities) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", data.uuid());
                    compound.putInt("NavigationMode", 2);
                    ClientPlayNetworking.send(new LeadingMobEntityNavigationModePayload(compound));
                }
            }
        }).dimensions(400, this.height - 20, 80, 18).build());
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
