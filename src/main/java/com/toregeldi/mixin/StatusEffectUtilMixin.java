package com.toregeldi.mixin;

import com.toregeldi.MobLeader;
import com.toregeldi.util.MobEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(StatusEffectUtil.class)
public class StatusEffectUtilMixin {
    @Inject(method = "addEffectToPlayersWithinDistance", at = @At("HEAD"), cancellable = true)
    private static void addEffectToPlayersWithinDistance(ServerWorld world, @Nullable Entity entity, Vec3d origin, double range, StatusEffectInstance statusEffectInstance, int duration, CallbackInfoReturnable<List<ServerPlayerEntity>> cir) {
        RegistryEntry<StatusEffect> registryEntry = statusEffectInstance.getEffectType();
        List<ServerPlayerEntity> list = world.getPlayers(
                player -> player.interactionManager.isSurvivalLike()
                        && !((MobEntityAccessor)entity).isLeader(player)
                        && (entity == null || !entity.isTeammate(player))
                        && origin.isInRange(player.getPos(), range)
                        && (
                        !player.hasStatusEffect(registryEntry)
                                || player.getStatusEffect(registryEntry).getAmplifier() < statusEffectInstance.getAmplifier()
                                || player.getStatusEffect(registryEntry).isDurationBelow(duration - 1)
                )
        );
        list.forEach(player -> player.addStatusEffect(new StatusEffectInstance(statusEffectInstance), entity));
        cir.setReturnValue(list);
    }
}
