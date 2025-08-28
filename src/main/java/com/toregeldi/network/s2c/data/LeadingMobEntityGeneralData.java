package com.toregeldi.network.s2c.data;

import java.util.UUID;

public record LeadingMobEntityGeneralData(UUID uuid, String type, float health, float maxHealth, int attackMode, int navigationMode, double posX, double posY, double posZ) {
}
