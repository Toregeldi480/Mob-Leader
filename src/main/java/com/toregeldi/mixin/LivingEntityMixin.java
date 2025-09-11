package com.toregeldi.mixin;

import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    private void remove(Entity.RemovalReason removalReason, CallbackInfo ci) {
        if((Object)this instanceof MobEntity mob) {
            LivingEntity leader = ((MobEntityAccessor)mob).getLeader();
            if(leader instanceof PlayerEntityAccessor accessor) {
                accessor.removeLeadingMobEntity(mob.getUuid());
                accessor.changeLeadingMobEntityWeightUsage(-((MobEntityAccessor)mob).getMobEntityWeight());
            }
        }
    }
}
