package com.commandert3706.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @Shadow @Final private Explosion.BlockInteraction blockInteraction;

    @ModifyExpressionValue(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getAttributeValue(Lnet/minecraft/core/Holder;)D"))
    private double windchargefix$overrideKnockbackResistance(double original) {
        if (blockInteraction == Explosion.BlockInteraction.TRIGGER_BLOCK) {
            return 0.0;
        }
        return original;
    }
}
