package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import com.toregeldi.item.ModItems;
import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin implements MobEntityAccessor {
    @Unique
    private static final Map<EntityType<?>, Item> MOB_ENTITY_TAMING_ITEM = new HashMap<>() {{
        put(EntityType.BOGGED, Items.ARROW);
        put(EntityType.CAVE_SPIDER, Items.SPIDER_EYE);
        put(EntityType.CREEPER, Items.GUNPOWDER);
        put(EntityType.DROWNED, Items.ROTTEN_FLESH);
        put(EntityType.ENDERMAN, Items.ENDER_PEARL);
        put(EntityType.EVOKER, Items.EMERALD);
        put(EntityType.HUSK, Items.ROTTEN_FLESH);
        put(EntityType.SKELETON, Items.ARROW);
        put(EntityType.VINDICATOR, Items.EMERALD);
        put(EntityType.WITHER_SKELETON, Items.COAL);
        put(EntityType.ZOMBIE, Items.ROTTEN_FLESH);
    }};

    @Unique
    private static final Map<EntityType<?>, List<Item>> MOB_ENTITY_ARMOR_SET = new HashMap<>() {{
        put(EntityType.BOGGED, List.of(ModItems.BOGGED_HELMET, ModItems.BOGGED_CHESTPLATE, ModItems.BOGGED_LEGGINGS, ModItems.BOGGED_BOOTS));
        put(EntityType.CAVE_SPIDER, List.of(ModItems.CAVE_SPIDER_HELMET, ModItems.CAVE_SPIDER_CHESTPLATE, ModItems.CAVE_SPIDER_LEGGINGS, ModItems.CAVE_SPIDER_BOOTS));
        put(EntityType.CREEPER, List.of(ModItems.CREEPER_HELMET, ModItems.CREEPER_CHESTPLATE, ModItems.CREEPER_LEGGINGS, ModItems.CREEPER_BOOTS));
        put(EntityType.DROWNED, List.of(ModItems.DROWNED_HELMET, ModItems.DROWNED_CHESTPLATE, ModItems.DROWNED_LEGGINGS, ModItems.DROWNED_BOOTS));
        put(EntityType.ENDERMAN, List.of(ModItems.ENDERMAN_HELMET, ModItems.ENDERMAN_CHESTPLATE, ModItems.ENDERMAN_LEGGINGS, ModItems.ENDERMAN_BOOTS));
        put(EntityType.EVOKER, List.of(ModItems.EVOKER_HELMET, ModItems.EVOKER_CHESTPLATE, ModItems.EVOKER_LEGGINGS, ModItems.EVOKER_BOOTS));
        put(EntityType.HUSK, List.of(ModItems.HUSK_HELMET, ModItems.HUSK_CHESTPLATE, ModItems.HUSK_LEGGINGS, ModItems.HUSK_BOOTS));
        put(EntityType.SKELETON, List.of(ModItems.SKELETON_HELMET, ModItems.SKELETON_CHESTPLATE, ModItems.SKELETON_LEGGINGS, ModItems.SKELETON_BOOTS));
        put(EntityType.VINDICATOR, List.of(ModItems.VINDICATOR_HELMET, ModItems.VINDICATOR_CHESTPLATE, ModItems.VINDICATOR_LEGGINGS, ModItems.VINDICATOR_BOOTS));
        put(EntityType.WITHER_SKELETON, List.of(ModItems.WITHER_SKELETON_HELMET, ModItems.WITHER_SKELETON_CHESTPLATE, ModItems.WITHER_SKELETON_LEGGINGS, ModItems.WITHER_SKELETON_BOOTS));
        put(EntityType.ZOMBIE, List.of(ModItems.ZOMBIE_HELMET, ModItems.ZOMBIE_CHESTPLATE, ModItems.ZOMBIE_LEGGINGS, ModItems.ZOMBIE_BOOTS));
    }};

    @Unique
    private static final Map<EntityType<?>, Integer> MOB_ENTITY_WEIGHT = new HashMap<>() {{
        put(EntityType.BOGGED, 3);
        put(EntityType.CAVE_SPIDER, 3);
        put(EntityType.CREEPER, 3);
        put(EntityType.DROWNED, 3);
        put(EntityType.ENDERMAN, 3);
        put(EntityType.EVOKER, 3);
        put(EntityType.HUSK, 3);
        put(EntityType.SKELETON, 3);
        put(EntityType.VINDICATOR, 3);
        put(EntityType.WITHER_SKELETON, 3);
        put(EntityType.ZOMBIE, 3);
    }};

    @Unique
    private final MobEntity mob = (MobEntity)(Object)this;

//    @Unique
//    private final SimpleInventory inventory = new SimpleInventory(9);

    @Unique
    private static TrackedData<Optional<UUID>> LEADER_UUID;

    @Unique
    @Override
    public Item getMobEntityTamingItem(EntityType type) {
        return MOB_ENTITY_TAMING_ITEM.get(type);
    }

    @Unique
    @Override
    public List<Item> getMobEntityArmorSet(EntityType type) {
        return MOB_ENTITY_ARMOR_SET.get(type);
    }

    @Unique
    @Override
    public int getMobEntityWeight(EntityType type) {
        return MOB_ENTITY_WEIGHT.get(type);
    }

    @Unique
    @Nullable
    @Override
    public LivingEntity getLeader() {
        UUID uuid = this.getLeaderUuid();
        return uuid == null ? null : mob.getWorld().getPlayerByUuid(uuid);
    }

    @Unique
    @Nullable
    @Override
    public UUID getLeaderUuid() {
        return (UUID)((Optional)mob.getDataTracker().get(LEADER_UUID)).orElse((Object)null);
    }

    @Unique
    @Override
    public void setLeaderUuid(@Nullable UUID uuid) {
        mob.getDataTracker().set(LEADER_UUID, Optional.ofNullable(uuid));
    }

    @Unique
    @Override
    public void setLeader(@Nullable PlayerEntity player) {
        if(player == null) {
            this.setLeaderUuid(null);
        } else {
            this.setLeaderUuid(player.getUuid());
        }
    }

    @Unique
    @Override
    public boolean isLeader(LivingEntity entity) {
        return entity == this.getLeader();
    }

    @Unique
    protected void showEmoteParticle() {
        ParticleEffect particleEffect = ParticleTypes.HEART;
        for(int i = 0; i < 7; ++i) {
            double d = mob.getRandom().nextGaussian() * 0.02;
            mob.getWorld().addParticle(particleEffect, mob.getParticleX(1.0f), mob.getRandomBodyY() + (double)0.5F, mob.getParticleZ(1.0f), d, d, d);
        }
    }

//    @Unique
//    private void openInventory(PlayerEntity player) {
//        player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player1) -> new Generic3x3ContainerScreenHandler(syncId, playerInventory, inventory), Text.of("Inventory")));
//    }

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void setTarget(LivingEntity target, CallbackInfo ci) {
        if((target instanceof PlayerEntity && isLeader(target)) ||
                (target instanceof Tameable tameable1 && tameable1.getOwner() == getLeader()) ||
                (mob instanceof Tameable tameable2 && target instanceof MobEntity && tameable2.getOwner() == ((MobEntityAccessor) target).getLeader()) ||
                (target instanceof MobEntity && ((MobEntityAccessor) mob).getLeader() == ((MobEntityAccessor) target).getLeader()) ||
                (mob instanceof Ownable ownable && (ownable.getOwner() == null || ((MobEntityAccessor) ownable.getOwner()).getLeader() == target)) ||
                (mob == target)
        ) {
            ci.cancel();
        }
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack item = player.getStackInHand(hand);
        PlayerEntityAccessor accessor = (PlayerEntityAccessor)player;
        if(MOB_ENTITY_TAMING_ITEM.get(mob.getType()) == item.getItem()) {
            if(!mob.getWorld().isClient && accessor.checkArmor(MOB_ENTITY_ARMOR_SET.get(mob.getType())) && getLeader() == null && ((PlayerEntityAccessor) player).canLeadMobEntity(MOB_ENTITY_WEIGHT.get(mob.getType()))) {
                accessor.addLeadingMobEntity(mob.getUuid());
                accessor.changeLeadingMobEntityWeight(MOB_ENTITY_WEIGHT.get(mob.getType()));
                setLeader(player);
                item.decrementUnlessCreative(1, player);
            }
            if(isLeader(player)) {
                mob.heal(1.0f);
            }
            showEmoteParticle();
            cir.setReturnValue(ActionResult.SUCCESS);
        }
//        if(!mob.getWorld().isClient && isLeader(player) && player.isSneaking()) {
//            openInventory(player);
//        }
        // TODO: Inventory for Equipment (Armor), (Enchanted Bow for Skeleton, Enchanted Stone Sword for Wither Skeleton),
        //  maybe implement Donkey, Llama like Storage with Chest
    }

    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)
    private void tick(CallbackInfo ci) {
        LivingEntity leader = getLeader();
        if(leader != null) {
            if(mob.squaredDistanceTo(leader) > (10 * 10)) {
                mob.getNavigation().startMovingTo(leader, 1.0f);
                if((mob instanceof EndermanEntity || mob instanceof EndermiteEntity) && mob.squaredDistanceTo(leader) > (32 * 32)) {
                    double d = mob.getRandom().nextGaussian();
                    mob.teleport(leader.getX() + d, leader.getY(), leader.getZ() + d, ParticleTypes.ASH.shouldAlwaysSpawn());
                }
            }
            if(leader.getAttacking() != null) {
                mob.setTarget(leader.getAttacking());
            }
            if(leader.getAttacker() != null && leader.getAttacker().isAlive()) {
                mob.setTarget(leader.getAttacker());
            }
        }
    }

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void init(CallbackInfo ci) {
        LEADER_UUID = DataTracker.registerData(MobEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"), cancellable = true)
    private void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(LEADER_UUID, Optional.empty());
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"), cancellable = true)
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (this.getLeaderUuid() != null) {
            nbt.putUuid("Leader", this.getLeaderUuid());
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"), cancellable = true)
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        UUID uuid;
        if (nbt.containsUuid("Leader")) {
            uuid = nbt.getUuid("Leader");
        } else {
            String string = nbt.getString("Leader");
            uuid = ServerConfigHandler.getPlayerUuidByName(mob.getServer(), string);
        }

        if (uuid != null) {
            try {
                this.setLeaderUuid(uuid);
            } catch (Throwable var4) {
            }
        }
    }
}
