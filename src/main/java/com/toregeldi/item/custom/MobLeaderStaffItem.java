package com.toregeldi.item.custom;

import com.toregeldi.MobLeader;
import com.toregeldi.screen.MobEntityInteractionScreen;
import com.toregeldi.util.MobEntityAccessor;
import com.toregeldi.util.PlayerEntityAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashSet;
import java.util.List;

public class MobLeaderStaffItem extends Item {
    public MobLeaderStaffItem(Settings settings) {
        super(settings);
    }
    private MobEntity mob;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if(user.getWorld().isClient && user.isSneaking()) {
            MinecraftClient.getInstance().setScreen(new MobEntityInteractionScreen(user));
        }

        return TypedActionResult.success(itemStack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof MobEntity && ((PlayerEntityAccessor)user).getLeadingMobEntities().contains(entity.getUuid())) {
            this.mob = (MobEntity)entity;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(mob != null) {
            ((MobEntityAccessor)mob).setStayPos(context.getBlockPos());
        }
        return super.useOnBlock(context);
    }
}
