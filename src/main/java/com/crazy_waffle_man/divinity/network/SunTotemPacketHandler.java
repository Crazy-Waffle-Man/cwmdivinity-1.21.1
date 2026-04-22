package com.crazy_waffle_man.divinity.network;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SunTotemPacketHandler {
    private static final SunTotemPacketHandler INSTANCE = new SunTotemPacketHandler();

    public static SunTotemPacketHandler getInstance() {
        return INSTANCE;
    }
    public void handleUseTotem(TotemEffectPacket msg, IPayloadContext ctx) {
        ctx.enqueueWork(() -> TotemEffectPacket.handle(msg))
                .exceptionally(e -> {
                    ctx.disconnect(Component.translatable("cwm_suntotem.networking_failed", e.getMessage()));
                    return null;
                });
    }
}
