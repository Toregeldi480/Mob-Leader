package com.toregeldi.network;

import com.toregeldi.MobLeader;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record LeadingMobEntityWeightPayload(int weight) implements CustomPayload {
    public static final Identifier LEADING_MOB_ENTITY_WEIGHT_ID = Identifier.of(MobLeader.MOD_ID, "leading_mob_entity_weight");
    public static final Id<LeadingMobEntityWeightPayload> ID = new CustomPayload.Id<>(LEADING_MOB_ENTITY_WEIGHT_ID);
    public static final PacketCodec<RegistryByteBuf, LeadingMobEntityWeightPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, LeadingMobEntityWeightPayload::weight, LeadingMobEntityWeightPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
