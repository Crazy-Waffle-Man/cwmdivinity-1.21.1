package com.crazy_waffle_man.divinity.item;

import com.crazy_waffle_man.divinity.CWMDivinity;
import com.crazy_waffle_man.divinity.Config;
import com.crazy_waffle_man.divinity.network.TotemEffectPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class SunTotem extends Item {
    public SunTotem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
        // Let's hear it for Javadocs
        // net.neoforged.neoforge.common.extensions.IItemExtension says that returning false prevents it
        // from being dropped
    }

    @Override
    public boolean canFitInsideContainerItems(ItemStack stack) {
        return false;
        // You can't get rid of me that easily
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (level.getDayTime() % 24000 == Config.SUN_TOTEM_TRIGGER_TIME.get()) {
            if (entity instanceof LivingEntity) {
                //We copy the stack here to avoid sending a ItemStack.EMPTY
                PacketDistributor.sendToPlayersTrackingEntityAndSelf((LivingEntity) entity, new TotemEffectPacket(stack.copy(), entity.getId()));
                stack.shrink(1);
            }
            // Totem trigger flag, handles VFX
            //TODO: Boss spawn
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        CWMDivinity.LOGGER.info("Time is {}", level.getDayTime() % 24000);
        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
