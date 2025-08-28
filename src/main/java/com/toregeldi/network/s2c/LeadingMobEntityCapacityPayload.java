package com.toregeldi.network.s2c;

import com.toregeldi.MobLeader;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record LeadingMobEntityCapacityPayload(int capacity) implements CustomPayload {
    public static final Identifier LEADING_MOB_ENTITY_CAPACITY_ID = Identifier.of(MobLeader.MOD_ID, "leading_mob_entity_capacity");
    public static final Id<LeadingMobEntityCapacityPayload> ID = new Id<>(LEADING_MOB_ENTITY_CAPACITY_ID);
    public static final PacketCodec<RegistryByteBuf, LeadingMobEntityCapacityPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, LeadingMobEntityCapacityPayload::capacity, LeadingMobEntityCapacityPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
