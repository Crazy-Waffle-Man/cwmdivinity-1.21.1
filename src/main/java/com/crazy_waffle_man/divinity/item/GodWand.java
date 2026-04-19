package com.crazy_waffle_man.divinity.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public class GodWand extends Item {
    private static ArrayList<Entity> doomedEntities;

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
        doomedEntities.add(entity);
        //TODO Set the entity's speed to 0 here
        entity.hurt(player.damageSources().freeze(), 1);
        return true;
    }

    public static void tickDoomedEntities() {
        //TODO Iterate through doomedEntities and drag them underground until their eyes are underground (suffocating)
    }
}
