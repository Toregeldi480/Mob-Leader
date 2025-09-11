package com.toregeldi.mixin;

import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {
    @Unique
    private final SlimeEntity slimeEntity = (SlimeEntity)(Object)this;

    @Unique
    protected void damage(LivingEntity target) {
        if(slimeEntity.isAlive() && slimeEntity.isInAttackRange(target) && slimeEntity.canSee(target)) {
            DamageSource damageSource = slimeEntity.getDamageSources().mobAttack(slimeEntity);
            if (target.damage(damageSource, this.getDamageAmount())) {
                slimeEntity.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (slimeEntity.getRandom().nextFloat() - slimeEntity.getRandom().nextFloat()) * 0.2F + 1.0F);
                World var4 = slimeEntity.getWorld();
                if (var4 instanceof ServerWorld) {
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

    @Unique
    protected boolean canAttack() {
        return slimeEntity.canMoveVoluntarily();
    }

    @Inject(method = "pushAwayFrom", at = @At("TAIL"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo ci) {
        LivingEntity target = (LivingEntity)entity;
        this.damage(target);
    }

    @Inject(method = "remove", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/mob/SlimeEntity;setPersistent()V", shift = At.Shift.BEFORE),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void remove(Entity.RemovalReason reason, CallbackInfo ci, int i, Text text, boolean bl, float f, float g, int j, int k, int l, float h, float m, SlimeEntity slimeEntity1) {
        MobEntity mob1 = (MobEntity)slimeEntity1;
        PlayerEntityAccessor leaderAccessor = (PlayerEntityAccessor)(PlayerEntity)((MobEntityAccessor)(MobEntity)slimeEntity).getLeader();

        if(leaderAccessor != null) {
            leaderAccessor.addLeadingMobEntity(mob1.getUuid(), ((MobEntityAccessor)mob1).getMobEntityWeight());
            leaderAccessor.changeLeadingMobEntityWeightUsage(((MobEntityAccessor)mob1).getMobEntityWeight());
        }
    }
}
