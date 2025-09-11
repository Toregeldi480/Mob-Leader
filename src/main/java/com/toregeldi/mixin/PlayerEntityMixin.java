package com.toregeldi.mixin;

import com.toregeldi.network.s2c.LeadingMobEntitiesPayload;
import com.toregeldi.network.s2c.LeadingMobEntityCapacityPayload;
import com.toregeldi.network.s2c.LeadingMobEntityWeightUsagePayload;
import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityAccessor {
    @Unique
    private boolean nbtValidated = false;
    @Unique
    private final PlayerEntity player = (PlayerEntity)(Object)this;

    @Unique
    private final Map<UUID, Integer> leadingMobEntities = new HashMap<>();

    @Unique
    private int leadingMobEntityWeight = 0;

    @Unique
    private int leadingMobEntityWeightUsage = 0;

    @Unique
    private int leadingMobEntityCapacity = 200;

    @Unique
    @Override
    public boolean checkArmor(List<Item> armor) {
        return player != null && (
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() == armor.get(0) &&
                        player.getEquippedStack(EquipmentSlot.CHEST).getItem() == armor.get(1) &&
                        player.getEquippedStack(EquipmentSlot.LEGS).getItem() == armor.get(2) &&
                        player.getEquippedStack(EquipmentSlot.FEET).getItem() == armor.get(3)
                ) || (
                player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Items.NETHERITE_HELMET &&
                        player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.NETHERITE_CHESTPLATE &&
                        player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Items.NETHERITE_LEGGINGS &&
                        player.getEquippedStack(EquipmentSlot.FEET).getItem() == Items.NETHERITE_BOOTS);
    }

    @Unique
    @Override
    public List<UUID> getLeadingMobEntities() {
        return leadingMobEntities.keySet().stream().toList();
    }

    @Unique
    @Override
    public void addLeadingMobEntity(UUID uuid, int weight) {
        leadingMobEntities.put(uuid, weight);
        syncMobEntitiesGeneralData();
    }

    @Unique
    @Override
    public void removeLeadingMobEntity(UUID uuid) {
        leadingMobEntities.remove(uuid);
        syncMobEntitiesGeneralData();
    }

    @Unique
    @Override
    public void clearLeadingMobEntities() {
        leadingMobEntities.clear();
        syncMobEntitiesGeneralData();
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
        return leadingMobEntityWeightUsage + weight <= leadingMobEntityCapacity;
    }

    @Unique
    @Override
    public int getLeadingMobEntityWeight() {
        return this.leadingMobEntityWeight;
    }

    @Unique
    @Override
    public void setLeadingMobEntityWeight(int weight) {
        this.leadingMobEntityWeight = weight;
    }

    @Unique
    @Override
    public int getLeadingMobEntityWeightUsage() {
        return this.leadingMobEntityWeightUsage;
    }

    @Unique
    @Override
    public void setLeadingMobEntityWeightUsage(int value) {
        this.leadingMobEntityWeightUsage = value;
        syncWeightUsageData();
    }

    @Unique
    @Override
    public void changeLeadingMobEntityWeightUsage(int value) {
        this.setLeadingMobEntityWeightUsage(this.leadingMobEntityWeightUsage + value);
    }

    @Unique
    private void syncMobEntitiesGeneralData() {
        if(player instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, LeadingMobEntitiesPayload.loadMobEntityTypeData(leadingMobEntities.keySet().stream().toList(), player.getWorld()));
        }
    }

    @Unique
    public void syncCapacityData() {
        if(player instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new LeadingMobEntityCapacityPayload(this.leadingMobEntityCapacity));
        }
    }

    @Unique
    public void syncWeightUsageData() {
        if(player instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new LeadingMobEntityWeightUsagePayload(this.leadingMobEntityWeightUsage));
        }
    }

    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)
    private void tick(CallbackInfo ci) {
        World world = player.getWorld();
        if(world instanceof ServerWorld serverWorld && !leadingMobEntities.isEmpty()) {
            loop: for(UUID uuid: leadingMobEntities.keySet().stream().toList()) {
                MobEntity mob = (MobEntity)serverWorld.getEntity(uuid);
                if(mob != null) {
                    if(!checkArmor(((MobEntityAccessor)mob).getMobEntityArmorSet())) {
                        for(UUID uuid1: leadingMobEntities.keySet()) {
                            ((MobEntityAccessor)(serverWorld).getEntity(uuid1)).setLeader(null);
                        }
                        clearLeadingMobEntities();
                        setLeadingMobEntityWeightUsage(0);
                        break loop;
                    }
                }
            }

            if(!nbtValidated) {
                for(Map.Entry<UUID, Integer> entry: leadingMobEntities.entrySet()) {
                    MobEntity mob1 = (MobEntity)serverWorld.getEntity(entry.getKey());
                    if(mob1 == null) {
                        leadingMobEntities.remove(entry.getKey());
                        changeLeadingMobEntityWeightUsage(-entry.getValue());
                    }
                }
                nbtValidated = true;
                syncWeightUsageData();
            }
        }
        syncMobEntitiesGeneralData();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("LeadingMobEntityWeight", this.leadingMobEntityWeight);
        nbt.putInt("LeadingMobEntityWeightUsage", this.leadingMobEntityWeightUsage);
        nbt.putInt("LeadingMobEntityCapacity", this.leadingMobEntityCapacity);
        NbtList list = new NbtList();
        for(Map.Entry<UUID, Integer> entry: leadingMobEntities.entrySet()) {
            NbtCompound compound = new NbtCompound();
            compound.putUuid("UUID", entry.getKey());
            compound.putInt("Weight", entry.getValue());
            list.add(compound);
        }
        nbt.put("LeadingMobEntities", list);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if(nbt.contains("LeadingMobEntityWeight")) {
            this.leadingMobEntityWeight = nbt.getInt("LeadingMobEntityWeight");
        }
        if(nbt.contains("LeadingMobEntityWeightUsage")) {
            this.leadingMobEntityWeightUsage = nbt.getInt("LeadingMobEntityWeightUsage");
        }
        if(nbt.contains("LeadingMobEntityCapacity")) {
            this.leadingMobEntityCapacity = nbt.getInt("LeadingMobEntityCapacity");
        }
        if(nbt.contains("LeadingMobEntities")) {
            NbtList list = nbt.getList("LeadingMobEntities", NbtElement.COMPOUND_TYPE);
            list.stream().forEach(compound -> leadingMobEntities.put(((NbtCompound)compound).getUuid("UUID"), ((NbtCompound)compound).getInt("Weight")));
        }
    }
}
