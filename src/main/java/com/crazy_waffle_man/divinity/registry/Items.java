package com.crazy_waffle_man.divinity.registry;

import com.crazy_waffle_man.divinity.CWMDivinity;
import com.crazy_waffle_man.divinity.item.GodWand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CWMDivinity.MODID);

    public static final DeferredItem<Item> GOD_WAND = ITEMS.register("god_wand",
                () -> new GodWand(
                        new Item.Properties()
                                .fireResistant()
                                .rarity(Rarity.EPIC)
                )
            );
}
