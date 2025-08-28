package com.toregeldi.network.s2c;

import com.toregeldi.MobLeader;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record LeadingMobEntityWeightUsagePayload(int weight) implements CustomPayload {
    public static final Identifier LEADING_MOB_ENTITY_WEIGHT_USAGE_ID = Identifier.of(MobLeader.MOD_ID, "leading_mob_entity_weight");
    public static final Id<LeadingMobEntityWeightUsagePayload> ID = new CustomPayload.Id<>(LEADING_MOB_ENTITY_WEIGHT_USAGE_ID);
    public static final PacketCodec<RegistryByteBuf, LeadingMobEntityWeightUsagePayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, LeadingMobEntityWeightUsagePayload::weight, LeadingMobEntityWeightUsagePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
