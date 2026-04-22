package com.crazy_waffle_man.divinity.network;

import com.crazy_waffle_man.divinity.CWMDivinity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public record TotemEffectPacket(ItemStack itemStack, int entityId) implements CustomPacketPayload {

    public static final Type<TotemEffectPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(CWMDivinity.MODID, "sun_totem_packet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, TotemEffectPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.STREAM_CODEC,
                    TotemEffectPacket::itemStack,
                    ByteBufCodecs.INT,
                    TotemEffectPacket::entityId,
                    TotemEffectPacket::new);

    public static void handle(TotemEffectPacket msg) {
        playActivateAnimation(msg.itemStack, msg.entityId);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void playActivateAnimation(ItemStack itemStack, int entityId) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level != null) {
            Entity entity = mc.level.getEntity(entityId);
            if (entity != null) {
                mc.particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
                level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, entity.getSoundSource(), 1.0F, 1.0F, false);
                if (entity == mc.player) {
                    mc.gameRenderer.displayItemActivation(itemStack);
                }
            }
        }

    }
}
  