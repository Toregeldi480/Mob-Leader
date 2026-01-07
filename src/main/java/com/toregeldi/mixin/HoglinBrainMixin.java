package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import com.toregeldi.util.MobEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.mob.HoglinBrain;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HoglinBrain.class)
public class HoglinBrainMixin {
    @Inject(method = "targetEnemy", at = @At("HEAD"), cancellable = true)
    private static void targetEnemy(HoglinEntity hoglin, LivingEntity target, CallbackInfo ci) {
        LivingEntity leader = ((MobEntityAccessor)hoglin).getLeader();
        if(((MobEntityAccessor)hoglin).isLeader(target) ||
                (target instanceof Tameable tameable1 && tameable1.getOwner() == leader) ||
                (target instanceof MobEntity && leader == ((MobEntityAccessor)target).getLeader()) ||
                (hoglin == target)
        ) {
            ci.cancel();
        }
    }
}
