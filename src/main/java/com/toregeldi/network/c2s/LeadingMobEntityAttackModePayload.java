package com.toregeldi.network.c2s;

import com.toregeldi.MobLeader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record LeadingMobEntityAttackModePayload(NbtCompound compound) implements CustomPayload {
    public static final Identifier LEADING_MOB_ENTITY_ATTACK_MODE_ID = Identifier.of(MobLeader.MOD_ID, "leading_mob_entity_attack_mode");
    public static final Id<LeadingMobEntityAttackModePayload> ID = new Id<>(LEADING_MOB_ENTITY_ATTACK_MODE_ID);
    public static final PacketCodec<RegistryByteBuf, LeadingMobEntityAttackModePayload> CODEC = PacketCodec.tuple(PacketCodecs.NBT_COMPOUND, LeadingMobEntityAttackModePayload::compound, LeadingMobEntityAttackModePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
