package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Unique
    Entity entity = (Entity)(Object)this;

    @Inject(method = "updatePassengerPosition", at = @At("TAIL"), cancellable = true)
    private void updatePassengerPosition(Entity passenger, CallbackInfo ci) {
        if(entity.hasPassenger(passenger) && passenger instanceof GhastEntity) {
            MobLeader.LOGGER.info("SOMETHING IN THE WAY");
            Vec3d vec3d1 = entity.getPassengerRidingPos(passenger);
            Vec3d vec3d2 = passenger.getVehicleAttachmentPos(entity);
            entity.setPosition(vec3d1.x, vec3d1.y - vec3d2.y, vec3d1.z - vec3d2.z);
        }
    }
}
