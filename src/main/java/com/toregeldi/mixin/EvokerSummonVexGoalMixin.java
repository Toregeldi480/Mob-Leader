package com.toregeldi.mixin;

import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(targets = "net.minecraft.entity.mob.EvokerEntity$SummonVexGoal")
public abstract class EvokerSummonVexGoalMixin {
    @Inject(method = "castSpell", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/mob/VexEntity;setOwner(Lnet/minecraft/entity/mob/MobEntity;)V", shift = At.Shift.AFTER),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void castSpell(CallbackInfo ci, ServerWorld serverWorld, Team team, int i, BlockPos blockPos, VexEntity vexEntity) {
        MobEntityAccessor vexAccessor = (MobEntityAccessor)vexEntity;
        MobEntityAccessor mobAccessor = (MobEntityAccessor)vexEntity.getOwner();
        PlayerEntity leader = (PlayerEntity)mobAccessor.getLeader();
        PlayerEntityAccessor leaderAccessor = (PlayerEntityAccessor)leader;

        if(leaderAccessor != null) {
            leaderAccessor.addLeadingMobEntity(vexEntity.getUuid(), vexAccessor.getMobEntityWeight());
            vexAccessor.setLeader(leader);
            vexAccessor.setNavigationMode(mobAccessor.getNavigationMode());
            vexAccessor.setAttackMode(mobAccessor.getAttackMode());
        }
    }
}
