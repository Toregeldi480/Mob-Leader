package com.toregeldi.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.UUID;

public interface MobEntityAccessor {
    int[] getStayPos();
    void setStayPos(BlockPos blockPos);

    int getAttackMode();
    void setAttackMode(int mode);

    int getNavigationMode();
    void setNavigationMode(int mode);

    Item getMobEntityTamingItem();
    List<Item> getMobEntityArmorSet();
    int getMobEntityWeight();

    LivingEntity getLeader();
    UUID getLeaderUuid();
    void setLeader(PlayerEntity player);
    void setLeaderUuid(UUID uuid);
    boolean isLeader(LivingEntity livingEntity);
}
