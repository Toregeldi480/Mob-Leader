package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import com.toregeldi.item.ModItems;
import com.toregeldi.keybinds.ModKeyBinds;
import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Unique
    LivingEntity living = (LivingEntity)(Object)this;

    @Unique
    protected void setRotation(float yaw, float pitch) {
        living.setYaw(yaw % 360.0F);
        living.setPitch(pitch % 360.0F);
    }

    @Unique
    protected Vec2f getControlledRotation(LivingEntity controllingPassenger) {
        return new Vec2f(controllingPassenger.getPitch() * 0.5F, controllingPassenger.getYaw());
    }

    @Unique
    protected Vec3d getControlledMovementInput(PlayerEntity controllingPlayer, Vec3d movementInput) {
        float f = controllingPlayer.sidewaysSpeed * 0.5F;
        float g = controllingPlayer.forwardSpeed;
        float v = 0.0f;

        if(living.isFallFlying() && g <= 0.0F) {
            g *= 0.25F;
        } else {
            if(living.getWorld().isClient()) {
                v = this.getVerticalInput(movementInput);
            }
        }


        return new Vec3d((double)f, (double)v, (double)g);
    }

    @Unique
    private float getVerticalInput(Vec3d movementInput) {
        MinecraftClient client = MinecraftClient.getInstance();

        boolean ascending = ModKeyBinds.ASCEND_KEY.isPressed();
        boolean descending = ModKeyBinds.DESCEND_KEY.isPressed();

        if(living instanceof FlyingEntity) {
            if((ascending || client.options.jumpKey.isPressed()) && !descending) {
                return 0.3f;
            } else if (descending && !ascending) {
                return -0.3f;
            }
        }
        if(living instanceof BlazeEntity || living instanceof BreezeEntity || living.isInFluid()) {
            if((ascending || client.options.jumpKey.isPressed()) && !descending) {
                return 0.8f;
            } else if (descending && !ascending) {
                return -0.8f;
            }
        }
        if(living instanceof GuardianEntity && living.isInFluid()) {
            if((ascending || client.options.jumpKey.isPressed()) && !descending) {
                return 2.4f;
            } else if (descending && !ascending) {
                return -2.4f;
            }
        }

        return 0;
    }

    @Inject(method = "tickControlled", at = @At("HEAD"), cancellable = true)
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput, CallbackInfo ci) {
        Vec2f vec2f = this.getControlledRotation(controllingPlayer);
        this.setRotation(vec2f.y, vec2f.x);
        living.prevYaw = living.bodyYaw = living.headYaw = living.getYaw();

        double d = 0;
        if(!(living instanceof BlazeEntity || living instanceof BreezeEntity || living instanceof FlyingEntity)) {
            living.getFinalGravity();
        }

        boolean bl = living.getVelocity().y <= (double)0.0F;
        if (bl && living.hasStatusEffect(StatusEffects.SLOW_FALLING)) {
            d = Math.min(d, 0.01f);
        }

        FluidState fluidState = living.getWorld().getFluidState(living.getBlockPos());
        if(living.isTouchingWater() && !living.canWalkOnFluid(fluidState)) {
            double e = living.getY();
            float f = living.isSprinting() ? 0.9f : 0.8f;
            float g = 0.02f;
            float h = (float)living.getAttributeValue(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY);
            float k = 0.8f;
            if(!living.isOnGround()) {
                h *= 0.5f;
            }

            if(h > 0.0F) {
                f += (0.54600006f - f) * h;
                g += (living.getMovementSpeed() - g) * h;
            }

            if(living.hasStatusEffect(StatusEffects.DOLPHINS_GRACE)) {
                f = 0.96f;
            }

            if(living instanceof GuardianEntity) {
                f = 1.1f;
                k = 1.0f;
            }

            living.updateVelocity(g, getControlledMovementInput(controllingPlayer, movementInput));
            living.move(MovementType.SELF, living.getVelocity());
            Vec3d vec3d = living.getVelocity();
            if(living.horizontalCollision && living.isClimbing()) {
                vec3d = new Vec3d(vec3d.x, 0.2f, vec3d.z);
            }

            living.setVelocity(vec3d.multiply((double)f, (double)k, (double)f));

            Vec3d vec3d2 = living.applyFluidMovingSpeed(d, bl, living.getVelocity());
            living.setVelocity(vec3d2);
            if(living.horizontalCollision && living.doesNotCollide(vec3d2.x, vec3d2.y + (double)0.6f - living.getY() + e, vec3d2.z)) {
                living.setVelocity(vec3d2.x, (double)0.3f, vec3d2.z);
            }
        } else if (living.isInLava() && !living.canWalkOnFluid(fluidState)) {
            double e = living.getY();
            living.updateVelocity(0.02f, getControlledMovementInput(controllingPlayer, movementInput));
            living.move(MovementType.SELF, living.getVelocity());
            if (living.getFluidHeight(FluidTags.LAVA) <= living.getSwimHeight()) {
                living.setVelocity(living.getVelocity().multiply((double)0.5f, (double)0.8f, (double)0.5f));
                Vec3d vec3d3 = living.applyFluidMovingSpeed(d, bl, living.getVelocity());
                living.setVelocity(vec3d3);
            } else {
                living.setVelocity(living.getVelocity().multiply((double)0.5f));
            }

            Vec3d vec3d3 = living.getVelocity();
            if (living.horizontalCollision && living.doesNotCollide(vec3d3.x, vec3d3.y + (double)0.6F - living.getY() + e, vec3d3.z)) {
                living.setVelocity(vec3d3.x, (double)0.3F, vec3d3.z);
            }
        } else if (living.isFallFlying()) {
            if(living instanceof FlyingEntity || living instanceof BreezeEntity || living instanceof BlazeEntity) {
                living.updateVelocity(0.1f, getControlledMovementInput(controllingPlayer, movementInput));
            } else {
                living.limitFallDistance();
                Vec3d vec3d4 = living.getVelocity();
                Vec3d vec3d5 = living.getRotationVector();
                float f = living.getPitch() * ((float)Math.PI / 180F);
                double i = Math.sqrt(vec3d5.x * vec3d5.x + vec3d5.z * vec3d5.z);
                double j = vec3d4.horizontalLength();
                double k = vec3d5.length();
                double l = Math.cos((double)f);
                l = l * l * Math.min((double)1.0F, k / 0.4);
                vec3d4 = living.getVelocity().add((double)0.0F, d * ((double)-1.0F + l * (double)0.75F), (double)0.0F);
                if (vec3d4.y < (double)0.0F && i > (double)0.0F) {
                    double m = vec3d4.y * -0.1 * l;
                    vec3d4 = vec3d4.add(vec3d5.x * m / i, m, vec3d5.z * m / i);
                }

                if (f < 0.0F && i > (double)0.0F) {
                    double m = j * (double)(-MathHelper.sin(f)) * 0.04;
                    vec3d4 = vec3d4.add(-vec3d5.x * m / i, m * 3.2, -vec3d5.z * m / i);
                }

                if (i > (double)0.0F) {
                    vec3d4 = vec3d4.add((vec3d5.x / i * j - vec3d4.x) * 0.1, (double)0.0F, (vec3d5.z / i * j - vec3d4.z) * 0.1);
                }

                living.setVelocity(vec3d4.multiply((double)0.99F, (double)0.98F, (double)0.99F));
                if (living.horizontalCollision && !living.getWorld().isClient) {
                    double m = living.getVelocity().horizontalLength();
                    double n = j - m;
                    float o = (float) (n * (double) 10.0F - (double) 3.0F);
                    if (o > 0.0F) {
                        living.damage(living.getDamageSources().flyIntoWall(), o);
                    }
                }
            }
        } else {
            BlockPos blockPos = living.getVelocityAffectingPos();
            float p = living.getWorld().getBlockState(blockPos).getBlock().getSlipperiness();
            float f = living.isOnGround() ? p * 0.91f : 0.7f;

            living.updateVelocity(0.1f, getControlledMovementInput(controllingPlayer, movementInput));
            living.move(MovementType.SELF, living.getVelocity());

            Vec3d vec3d6 = living.getVelocity();

            if(living.horizontalCollision && living.isClimbing()) {
                vec3d6 = new Vec3d(vec3d6.x, 0.2f, vec3d6.z);
            }

            double q = vec3d6.y;
            if(living.hasStatusEffect(StatusEffects.LEVITATION)) {
                q += (0.05f * (double)(living.getStatusEffect(StatusEffects.LEVITATION).getAmplifier() + 1) - vec3d6.y) * 0.2f;
            } else if(living.getWorld().isClient && living.isOnGround()) {
                // NOTE: Hardcoded the jump logic, could not implement in verticalInput method
                // FIX: The problem might be due to the value of d at the head of tickControlled, I set it to 0 to avoid final gravity
                MinecraftClient client = MinecraftClient.getInstance();
                if(client.options.jumpKey.isPressed() || living instanceof GuardianEntity) {
                    q = 0.3f;
                    living.setVelocity(vec3d6.x * f, q,vec3d6.z * f);
                }
            } else if(living.getWorld().isClient && !living.getWorld().isChunkLoaded(blockPos)) {
                if (living.getY() > (double)living.getWorld().getBottomY()) {
                    q = -0.1f;
                } else {
                    q = (double)0.0f;
                }
            } else {
                q -= d;
            }

            if (living.hasNoDrag()) {
                living.setVelocity(vec3d6.x, q, vec3d6.z);
            } else {
                living.setVelocity(vec3d6.x * (double)f, living instanceof Flutterer ? q * (double)f : q * (double)0.98F, vec3d6.z * (double)f);
            }
        }
    }

    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    private void remove(Entity.RemovalReason removalReason, CallbackInfo ci) {
        if(living instanceof MobEntity mob) {
            LivingEntity leader = ((MobEntityAccessor)mob).getLeader();
            if(leader instanceof PlayerEntityAccessor accessor) {
                accessor.changeLeadingMobEntityWeightUsage(-((MobEntityAccessor)mob).getMobEntityWeight());
                accessor.removeLeadingMobEntity(mob.getUuid(), ((MobEntityAccessor)mob).getMobEntityWeight());
            }
        }
    }
}
