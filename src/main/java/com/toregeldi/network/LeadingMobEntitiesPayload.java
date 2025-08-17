package com.toregeldi.network;

import com.toregeldi.MobLeader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public record LeadingMobEntitiesPayload(NbtList mobEntities) implements CustomPayload {
    public static final Identifier LEADING_MOB_ENTITIES_ID = Identifier.of(MobLeader.MOD_ID, "leading_mob_entities");
    public static final Id<LeadingMobEntitiesPayload> ID = new Id<>(LEADING_MOB_ENTITIES_ID);
    public static final PacketCodec<RegistryByteBuf, LeadingMobEntitiesPayload> CODEC = PacketCodec.of(
            (payload, buf) -> {
                NbtCompound compound = new NbtCompound();
                compound.put("LeadingMobEntities", payload.mobEntities);
                buf.writeNbt(compound);
            },
            buf -> {
                NbtCompound compound = buf.readNbt();
                if(compound == null || !compound.contains("LeadingMobEntities", NbtElement.LIST_TYPE)) {
                    return new LeadingMobEntitiesPayload(new NbtList());
                }
                return new LeadingMobEntitiesPayload(compound.getList("LeadingMobEntities", NbtElement.COMPOUND_TYPE));
            }
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static LeadingMobEntitiesPayload create(List<UUID> uuids, World world) {
        NbtList list = new NbtList();
        uuids.forEach(uuid -> {
            if(world instanceof ServerWorld serverWorld) {
                Entity entity = serverWorld.getEntity(uuid);
                if(entity instanceof LivingEntity living) {
                    NbtCompound compound = new NbtCompound();
                    compound.putUuid("UUID", uuid);
                    compound.putFloat("Health", living.getHealth());
                    compound.putFloat("MaxHealth", living.getMaxHealth());
                    list.add(compound);
                }
            }
        });
        return new LeadingMobEntitiesPayload(list);
    }
}
