package com.toregeldi.util;

import net.minecraft.item.Item;

import java.util.List;
import java.util.UUID;

public interface PlayerEntityAccessor {
    boolean checkArmor(List<Item> armor);
    List<UUID> getLeadingMobEntities();
    void addLeadingMobEntity(UUID uuid);
    void removeLeadingMobEntity(UUID uuid);
    void clearLeadingMobEntities();

    int getLeadingMobEntityCapacity();
    void setLeadingMobEntityCapacity(int capacity);
    void changeLeadingMobEntityCapacity(int capacity);

    boolean canLeadMobEntity(int weight);

    int getLeadingMobEntityWeight();
    void setLeadingMobEntityWeight(int weight);

    int getLeadingMobEntityWeightUsage();
    void setLeadingMobEntityWeightUsage(int value);
    void changeLeadingMobEntityWeightUsage(int value);
}
