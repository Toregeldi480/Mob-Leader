package com.toregeldi;

import com.toregeldi.gui.PlayerLeadingEntityGUI;
import com.toregeldi.network.c2s.LeadingMobEntityAttackModePayload;
import com.toregeldi.network.c2s.LeadingMobEntityNavigationModePayload;
import com.toregeldi.network.s2c.LeadingMobEntitiesPayload;
import com.toregeldi.network.s2c.LeadingMobEntityCapacityPayload;
import com.toregeldi.network.s2c.LeadingMobEntityWeightUsagePayload;
import com.toregeldi.network.s2c.data.LeadingMobEntityGeneralData;
import com.toregeldi.screen.MobEntityInteractionScreen;
import com.toregeldi.util.MobEntityAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class MobLeaderClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new PlayerLeadingEntityGUI());

        ClientPlayNetworking.registerGlobalReceiver(LeadingMobEntityWeightUsagePayload.ID, ((payload, context) ->
                context.client().execute(() -> PlayerLeadingEntityGUI.getLeadingMobEntityWeightUsageFromServer(payload.weight()))));

        ClientPlayNetworking.registerGlobalReceiver(LeadingMobEntityCapacityPayload.ID, ((payload, context) ->
                context.client().execute(() -> PlayerLeadingEntityGUI.getLeadingMobEntityCapacityFromServer(payload.capacity()))));

        ClientPlayNetworking.registerGlobalReceiver(LeadingMobEntitiesPayload.ID, (payload, context) ->
                context.client().execute(() -> {
                    List<LeadingMobEntityGeneralData> list = new ArrayList<>();
                    for(NbtElement element: payload.mobEntities()) {
                        if(element instanceof NbtCompound compound) {
                            list.add(new LeadingMobEntityGeneralData(
                                    compound.getUuid("UUID"),
                                    compound.getString("EntityType"),
                                    compound.getInt("AttackMode"),
                                    compound.getInt("NavigationMode")
                            ));
                        }
                    }
                    MobEntityInteractionScreen.getLeadingMobEntitiesFromServer(list);
                }));

        ServerPlayNetworking.registerGlobalReceiver(LeadingMobEntityNavigationModePayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                if(context.player().getWorld() instanceof ServerWorld serverWorld) {
                    MobEntity mob = (MobEntity)serverWorld.getEntity(payload.compound().getUuid("UUID"));
                    ((MobEntityAccessor)mob).setNavigationMode(payload.compound().getInt("NavigationMode"));
                }
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(LeadingMobEntityAttackModePayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                if(context.player().getWorld() instanceof ServerWorld serverWorld) {
                    MobEntity mob = (MobEntity)serverWorld.getEntity(payload.compound().getUuid("UUID"));
                    ((MobEntityAccessor)mob).setAttackMode(payload.compound().getInt("AttackMode"));
                }
            });
        });
    }
}
