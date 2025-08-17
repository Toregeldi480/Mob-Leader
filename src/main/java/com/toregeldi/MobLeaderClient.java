package com.toregeldi;

import com.toregeldi.gui.PlayerLeadingEntityGUI;
import com.toregeldi.network.LeadingMobEntitiesPayload;
import com.toregeldi.network.LeadingMobEntityCapacityPayload;
import com.toregeldi.network.LeadingMobEntityWeightPayload;
import com.toregeldi.network.data.LeadingMobEntityData;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public class MobLeaderClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new PlayerLeadingEntityGUI());
        ClientPlayNetworking.registerGlobalReceiver(LeadingMobEntityWeightPayload.ID, ((payload, context) ->
                context.client().execute(() -> PlayerLeadingEntityGUI.getLeadingMobEntityWeightFromServer(payload.weight()))));

        ClientPlayNetworking.registerGlobalReceiver(LeadingMobEntityCapacityPayload.ID, ((payload, context) ->
                context.client().execute(() -> PlayerLeadingEntityGUI.getLeadingMobEntityCapacityFromServer(payload.capacity()))));

        ClientPlayNetworking.registerGlobalReceiver(LeadingMobEntitiesPayload.ID, (payload, context) ->
                context.client().execute(() -> {
                    List<LeadingMobEntityData> list = new ArrayList<>();
                    for(NbtElement element: payload.mobEntities()) {
                        if(element instanceof NbtCompound compound) {
                            list.add(new LeadingMobEntityData(
                                    compound.getUuid("UUID"),
                                    compound.getFloat("Health"),
                                    compound.getFloat("MaxHealth")
                            ));
                        }
                    }
                    PlayerLeadingEntityGUI.getLeadingMobEntitiesFromServer(list);
                }));
    }
}
