package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(SlimeEntity.class)
public abstract class SlimeEntityMixin {
    @Unique
    private final SlimeEntity slimeEntity = (SlimeEntity)(Object)this;

    @Unique
    private final Random random = new Random();

    @Unique
    protected void damage(LivingEntity target) {
        if(slimeEntity.isAlive() && slimeEntity.isInAttackRange(target) && slimeEntity.canSee(target)) {
            DamageSource damageSource = slimeEntity.getDamageSources().mobAttack(slimeEntity);
            if(target.damage(damageSource, this.getDamageAmount())) {
                slimeEntity.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (slimeEntity.getRandom().nextFloat() - slimeEntity.getRandom().nextFloat()) * 0.2F + 1.0F);
                World var4 = slimeEntity.getWorld();
                if(var4 instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld)var4;
                    EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
                }
            }
        }

    }

    @Unique
    protected float getDamageAmount() {
        return (float)slimeEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    @Inject(method = "pushAwayFrom", at = @At("TAIL"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo ci) {
        LivingEntity target = (LivingEntity)entity;
        if(target == slimeEntity.getTarget()) {
            this.damage(target);
        }
    }

    @Inject(method = "onPlayerCollision", at = @At("HEAD"), cancellable = true)
    public void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        if(((MobEntityAccessor)slimeEntity).isLeader(player)){
            ci.cancel();
        }
    }

    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    public void remove(Entity.RemovalReason reason, CallbackInfo ci) {
        if(((MobEntityAccessor)slimeEntity).getLeader() != null) {
            int i = slimeEntity.getSize();
            if (!slimeEntity.getWorld().isClient && i > 1 && slimeEntity.isDead()) {
                Text text = slimeEntity.getCustomName();
                boolean bl = slimeEntity.isAiDisabled();
                float f = slimeEntity.getDimensions(slimeEntity.getPose()).width();
                float g = f / 2.0F;
                int j = i / 2;
                MobLeader.LOGGER.info("SIZE!::" + j);

                for (int l = 0; l < 2; l++) {
                    MobLeader.LOGGER.info("L:" + l);
                    float h = (l % 2 - 0.5F) * g;
                    float m = (l / 2 - 0.5F) * g;
                    SlimeEntity slimeEntity1 = slimeEntity.getType().create(slimeEntity.getWorld());
                    if (slimeEntity != null) {
                        if(slimeEntity.isPersistent()) {
                            slimeEntity.setPersistent();
                        }

                        slimeEntity1.setCustomName(text);
                        slimeEntity1.setAiDisabled(bl);
                        slimeEntity1.setInvulnerable(slimeEntity.isInvulnerable());
                        slimeEntity1.setSize(j, true);
                        slimeEntity1.refreshPositionAndAngles(slimeEntity.getX() + h, slimeEntity.getY() + 0.5, slimeEntity.getZ() + m, this.random.nextFloat() * 360.0F, 0.0F);

                        MobEntity mob1 = (MobEntity)slimeEntity1;
                        PlayerEntityAccessor leaderAccessor = (PlayerEntityAccessor)(PlayerEntity)((MobEntityAccessor)(MobEntity)slimeEntity).getLeader();

                        if(leaderAccessor != null) {
                            leaderAccessor.addLeadingMobEntity(mob1.getUuid(), ((MobEntityAccessor)mob1).getMobEntityWeight());
                            leaderAccessor.changeLeadingMobEntityWeightUsage(((MobEntityAccessor)mob1).getMobEntityWeight());
                        }

                        ((MobEntityAccessor)slimeEntity1).setLeader(((MobEntityAccessor)(MobEntity)slimeEntity).getLeader());

                        slimeEntity.getWorld().spawnEntity(slimeEntity1);
                    }
                }
            }
            MobEntity mob = (MobEntity)slimeEntity;
            PlayerEntityAccessor leaderAccessor = (PlayerEntityAccessor)(PlayerEntity)((MobEntityAccessor)(MobEntity)slimeEntity).getLeader();

            if(leaderAccessor != null) {
                leaderAccessor.changeLeadingMobEntityWeightUsage(-((MobEntityAccessor)mob).getMobEntityWeight());
                leaderAccessor.removeLeadingMobEntity(mob.getUuid(), ((MobEntityAccessor)mob).getMobEntityWeight());
            }

            slimeEntity.setRemoved(reason);
            slimeEntity.getBrain().forgetAll();

            ci.cancel();
        }
    }
}
