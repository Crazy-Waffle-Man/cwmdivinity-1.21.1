package com.crazy_waffle_man.divinity;

import com.crazy_waffle_man.divinity.registry.Items;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CWMDivinity.MODID)
public class CWMDivinity {
    public static final String MODID = "cwm_divinity";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CWMDivinity(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
//        modEventBus.addListener(this::commonSetup);
//         Do not add this line if there are no @SubscribeEvent -annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        Items.ITEMS.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    private void tick(ServerTickEvent.Post event) {

    }
}
