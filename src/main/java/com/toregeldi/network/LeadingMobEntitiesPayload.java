package com.toregeldi.network;

import com.toregeldi.MobLeader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.UUID;

public record LeadingMobEntitiesPayload(NbtList mobEntities) implements CustomPayload {
    public static final Identifier LEADING_MOB_ENTITIES_ID = Identifier.of(MobLeader.MOD_ID, "leading_mob_entities");
    public static final Id<LeadingMobEntitiesPayload> ID = new Id<>(LEADING_MOB_ENTITIES_ID);
    public static final PacketCodec<RegistryByteBuf, LeadingMobEntitiesPayload> CODEC = PacketCodec.of(
            (payload, buf) -> buf.writeNbt(payload.mobEntities),
            buf -> new LeadingMobEntitiesPayload(buf.readNbt().getList("LeadingEntities", NbtElement.INT_ARRAY_TYPE))
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
