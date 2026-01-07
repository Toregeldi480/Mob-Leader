package com.toregeldi.util;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;

import java.util.List;
import java.util.UUID;

public interface PlayerEntityAccessor {
    boolean checkArmor(List<Item> armor);
    List<UUID> getLeadingMobEntities();
    void addLeadingMobEntity(UUID uuid, int weight);
    void removeLeadingMobEntity(UUID uuid, int weight);
    void clearLeadingMobEntities();

    List<MobEntity> getSelectedMobs();
    void addSelectedMob(MobEntity mob);
    void removeSelectedMob(MobEntity mob);

    int getLeadingMobEntityCapacity();
    void setLeadingMobEntityCapacity(int capacity);

    boolean canLeadMobEntity(int weight);

    int getLeadingMobEntityWeightUsage();
    void setLeadingMobEntityWeightUsage(int value);
    void changeLeadingMobEntityWeightUsage(int value);
}
