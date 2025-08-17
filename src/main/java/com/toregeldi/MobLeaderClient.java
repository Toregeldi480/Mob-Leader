package com.toregeldi;

import com.toregeldi.gui.PlayerLeadingEntityGUI;
import com.toregeldi.network.LeadingMobEntitiesPayload;
import com.toregeldi.network.LeadingMobEntityCapacityPayload;
import com.toregeldi.network.LeadingMobEntityWeightPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

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
                context.client().execute(() -> PlayerLeadingEntityGUI.getLeadingMobEntitiesFromServer(payload.mobEntities())));
    }
}
