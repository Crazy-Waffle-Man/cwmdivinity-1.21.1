package com.crazy_waffle_man.divinity.registry;

import com.crazy_waffle_man.divinity.CWMDivinity;
import com.crazy_waffle_man.divinity.item.GodWand;
import com.crazy_waffle_man.divinity.item.SunTotem;
import net.minecraft.core.component.DataComponents;
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
                                .setNoRepair()
                                .durability(256)
                                .stacksTo(1)
                )
            );
    public static final DeferredItem<Item> SUN_TOTEM = ITEMS.register("sun_totem",
            ()-> new SunTotem(
                    new Item.Properties()
                            .fireResistant()
                            .stacksTo(1)
            )
        );
}
