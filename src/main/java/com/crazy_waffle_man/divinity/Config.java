package com.crazy_waffle_man.divinity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

//    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
//            .comment("Whether to log the dirt block on common setup")
//            .define("logDirtBlock", true);
//
//    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
//            .comment("A magic number")
//            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);
//
//    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
//            .comment("What you want the introduction message to be for the magic number")
//            .define("magicNumberIntroduction", "The magic number is... ");
//
//    // a list of strings that are treated as resource locations for items
//    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
//            .comment("A list of items to log on common setup.")
//            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);

    public static final ModConfigSpec.ConfigValue<Integer> SUN_TOTEM_TRIGGER_TIME = BUILDER
            .comment("When to trigger the Sun Totem, if it's in a player's inventory.\nDefaults to 10 seconds after players wake up from their beds.")
            .defineInRange("sunTotemTriggerTime", 200, 0, 24000);

    public static final ModConfigSpec.ConfigValue<Integer> GOD_WAND_PPT = BUILDER
            .comment("How many particles (i.e. how detailed) should doomed mobs spawn each tick?\n(Lower value --> better performance but worse aesthetics)")
            .defineInRange("godWandParticlesPerTick", 5, 1, 100);
    public static final ModConfigSpec.ConfigValue<Double> GOD_WAND_DOOMED_MOB_DESCENT_RATE = BUILDER
            .comment("Blocks per tick to move entities doomed by the God Wand downward")
            .defineInRange("godWandDoomedMobDescentRate", 0.000125, 0, Double.MAX_VALUE);
    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
