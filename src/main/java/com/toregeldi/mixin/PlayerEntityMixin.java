package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import com.toregeldi.network.LeadingMobEntitiesPayload;
import com.toregeldi.network.LeadingMobEntityCapacityPayload;
import com.toregeldi.network.LeadingMobEntityWeightPayload;
import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityAccessor {
    @Unique
    private final PlayerEntity player = (PlayerEntity)(Object)this;

    @Unique
    private final List<UUID> leadingMobEntities = new ArrayList<>();

    @Unique
    private int leadingMobEntityWeight;

    @Unique
    private int leadingMobEntityCapacity = 40;

    @Unique
    @Override
    public boolean checkArmor(List<Item> armor) {
        return player != null &&
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() == armor.get(0) &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == armor.get(1) &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == armor.get(2) &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == armor.get(3);
    }

    @Unique
    @Override
    public List<UUID> getLeadingMobEntities() {
        return leadingMobEntities;
    }

    @Unique
    @Override
    public void addLeadingMobEntity(UUID uuid) {
        leadingMobEntities.add(uuid);
        syncMobEntitiesData();
    }

    @Unique
    @Override
    public void removeLeadingMobEntity(UUID uuid) {
        leadingMobEntities.remove(uuid);
        syncMobEntitiesData();
    }

    @Unique
    @Override
    public void clearLeadingMobEntities() {
        leadingMobEntities.clear();
        syncMobEntitiesData();
    }

    @Unique
    @Override
    public int getLeadingMobEntityCapacity() {
        return leadingMobEntityCapacity;
    }

    @Unique
    @Override
    public void setLeadingMobEntityCapacity(int capacity) {
        leadingMobEntityCapacity = capacity;
    }

    @Unique
    @Override
    public void changeLeadingMobEntityCapacity(int capacity) {
        setLeadingMobEntityCapacity(this.leadingMobEntityCapacity + capacity);
        syncCapacityData();
    }

    @Unique
    @Override
    public boolean canLeadMobEntity(int weight) {
        return leadingMobEntityWeight + weight <= leadingMobEntityCapacity;
    }

    @Unique
    @Override
    public int getLeadingMobEntityWeight() {
        return this.leadingMobEntityWeight;
    }

    @Unique
    @Override
    public void setLeadingMobEntityWeight(int value) {
        this.leadingMobEntityWeight = value;
        syncWeightData();
    }

    @Unique
    @Override
    public void changeLeadingMobEntityWeight(int value) {
        this.setLeadingMobEntityWeight(this.leadingMobEntityWeight + value);
        syncWeightData();
    }

    @Unique
    private void syncMobEntitiesData() {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            NbtList uuidList = new NbtList();
            leadingMobEntities.forEach(uuid -> uuidList.add(NbtHelper.fromUuid(uuid)));
            ServerPlayNetworking.send(serverPlayer, new LeadingMobEntitiesPayload(uuidList));
        }
    }

    @Unique
    public void syncCapacityData() {
        if(player instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new LeadingMobEntityCapacityPayload(this.leadingMobEntityCapacity));
        }
    }

    @Unique
    public void syncWeightData() {
        if(player instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new LeadingMobEntityWeightPayload(this.leadingMobEntityWeight));
        }
    }

    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)
    private void tick(CallbackInfo ci) {
        World world = player.getWorld();
        if(world instanceof ServerWorld serverWorld && !leadingMobEntities.isEmpty()) {
            MobEntity mob = (MobEntity)serverWorld.getEntity(leadingMobEntities.get(0));
            if(mob != null) {
                if(!checkArmor(((MobEntityAccessor)mob).getMobEntityArmorSet(mob.getType()))) {
                    for(UUID uuid: leadingMobEntities) {
                        ((MobEntityAccessor)(serverWorld).getEntity(uuid)).setLeader(null);
                    }
                    clearLeadingMobEntities();
                    setLeadingMobEntityWeight(0);
                }
            }
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("LeadingMobEntityWeight", this.leadingMobEntityWeight);
        nbt.putInt("LeadingMobEntityCapacity", this.leadingMobEntityCapacity);
        NbtList list = new NbtList();
        for(UUID uuid: leadingMobEntities) {
            NbtCompound compound = new NbtCompound();
            compound.putUuid("UUID", uuid);
            list.add(compound);
            leadingMobEntities.stream().forEach(uuid1 -> {});
        }
        nbt.put("LeadingMobEntities", list);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if(nbt.contains("LeadingMobEntityWeight")) {
            this.leadingMobEntityWeight = nbt.getInt("LeadingMobEntityWeight");
        }
        if(nbt.contains("LeadingMobEntityCapacity")) {
            this.leadingMobEntityCapacity = nbt.getInt("LeadingMobEntityCapacity");
        }
        if(nbt.contains("LeadingMobEntities")) {
            NbtList list = nbt.getList("LeadingMobEntities", NbtElement.COMPOUND_TYPE);
            list.stream().forEach(compound -> leadingMobEntities.add(((NbtCompound)compound).getUuid("UUID")));
        }
    }
}
