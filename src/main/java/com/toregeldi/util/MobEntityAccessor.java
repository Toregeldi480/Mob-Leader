package com.toregeldi.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface MobEntityAccessor {
    boolean isSelected();
    void setSelected(boolean selected);

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
    void setLeader(LivingEntity livingEntity);
    void setLeaderUuid(UUID uuid);
    boolean isLeader(LivingEntity livingEntity);
}
