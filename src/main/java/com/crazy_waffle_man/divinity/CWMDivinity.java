package com.crazy_waffle_man.divinity;

import com.crazy_waffle_man.divinity.network.SunTotemPacketHandler;
import com.crazy_waffle_man.divinity.network.TotemEffectPacket;
import com.crazy_waffle_man.divinity.registry.Items;
import com.mojang.logging.LogUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

import java.util.Objects;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CWMDivinity.MODID)
public class CWMDivinity {
    public static final String MODID = "cwm_divinity";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CWMDivinity(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::registerPayloadHandler);
//         Do not add this line if there are no @SubscribeEvent -annotated functions in this class, like onServerStarting() below.
//        NeoForge.EVENT_BUS.register(this);

        Items.ITEMS.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    private void registerPayloadHandler(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("sun_totem");
        CustomPacketPayload.Type<TotemEffectPacket> payload = TotemEffectPacket.TYPE;
        StreamCodec<RegistryFriendlyByteBuf, TotemEffectPacket> codec = TotemEffectPacket.STREAM_CODEC;
        SunTotemPacketHandler handler = SunTotemPacketHandler.getInstance();
        Objects.requireNonNull(handler);
        registrar.playToClient(payload, codec, handler::handleUseTotem);
        //Can also registrarify more stuff if needed, but I really don't want to.
    }
}
