package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import com.toregeldi.util.MobEntityAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Unique
    MinecraftClient client = (MinecraftClient)(Object)this;

    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    private void hasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof MobEntity mob && ((MobEntityAccessor)mob).getLeader() == client.player) {
            cir.setReturnValue(((MobEntityAccessor)mob).isSelected());
        }
    }
}
