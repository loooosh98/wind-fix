package com.commandert3706.mixin;

import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ServerExplosion.class)
public class ServerExplosionMixin {
    @Shadow @Final private Explosion.BlockInteraction blockInteraction;

    @ModifyVariable(method = "hurtEntities", at = @At("STORE"), ordinal = 1)
    private double windchargefix$overrideKnockbackResistance(double original) {
        if (blockInteraction == Explosion.BlockInteraction.TRIGGER_BLOCK) {
            return 0.0;
        }
        return original;
    }
}
