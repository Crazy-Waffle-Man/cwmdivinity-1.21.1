package com.crazy_waffle_man.divinity.item;

import com.crazy_waffle_man.divinity.CWMDivinity;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.AttributeCommand;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GodWand extends Item {

    public GodWand(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("item.cwm.god_wand.tooltip"));
        }
        else {
            tooltipComponents.add(Component.translatable("item.cwm.shift_tooltip"));
        }
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack)); // damage the item
        if (entity instanceof LivingEntity) {
            Objects.requireNonNull(
                    ((LivingEntity) entity)
                            .getAttributes()
                            .getInstance(Attributes.MOVEMENT_SPEED)
            ).setBaseValue(0.0);
            entity.addTag(CWMDivinity.MODID + ":doomed");
        }
        return true;
    }
}
