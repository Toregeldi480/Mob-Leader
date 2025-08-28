package com.toregeldi.network.c2s;

import com.toregeldi.MobLeader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record LeadingMobEntityNavigationModePayload(NbtCompound compound) implements CustomPayload {
    public static final Identifier LEADING_MOB_ENTITY_NAVIGATION_MODE_ID = Identifier.of(MobLeader.MOD_ID, "leading_mob_entity_navigation_mode");
    public static final Id<LeadingMobEntityNavigationModePayload> ID = new Id<>(LEADING_MOB_ENTITY_NAVIGATION_MODE_ID);
    public static final PacketCodec<RegistryByteBuf, LeadingMobEntityNavigationModePayload> CODEC = PacketCodec.tuple(PacketCodecs.NBT_COMPOUND, LeadingMobEntityNavigationModePayload::compound, LeadingMobEntityNavigationModePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
