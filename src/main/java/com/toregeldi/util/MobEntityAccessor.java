package com.toregeldi.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

import java.util.List;
import java.util.UUID;

public interface MobEntityAccessor {
    Item getMobEntityTamingItem(EntityType type);
    List<Item> getMobEntityArmorSet(EntityType type);
    int getMobEntityWeight(EntityType type);

    LivingEntity getLeader();
    UUID getLeaderUuid();
    void setLeader(PlayerEntity player);
    void setLeaderUuid(UUID uuid);
    boolean isLeader(LivingEntity livingEntity);
}
