package com.crazy_waffle_man.divinity.mixin;

import com.crazy_waffle_man.divinity.CWMDivinity;
import com.crazy_waffle_man.divinity.Config;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.RandomSequences;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onLivingEntityTick(CallbackInfo  ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.getTags().contains(CWMDivinity.MODID + ":doomed")) {
            self.setPos(self.getX(), self.getY() - Config.GOD_WAND_DOOMED_MOB_DESCENT_RATE.get(), self.getZ());
            for (int i = 0; i < Config.GOD_WAND_PPT.get(); i++) {
                Vec3 velocity = new Vec3(0, 0, 1);
                velocity.yRot((float) (2 * Math.PI / Config.GOD_WAND_PPT.get()));
                self.level().addParticle(ParticleTypes.TOTEM_OF_UNDYING, self.getX(), self.getY(), self.getZ(), velocity.x(), velocity.y(), velocity.z()); //TODO: Make sure the particles can actually display on clientside
            }
        }
    }
}
