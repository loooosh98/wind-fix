package com.commandert3706.mixin;

import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionImpl;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true, print = true)
@Mixin(ExplosionImpl.class)
public class ExplosionImplMixin {
    @Shadow @Final private Explosion.DestructionType destructionType;

    @ModifyVariable(method = "damageEntities", at = @At("STORE"), ordinal = 1)
    private double windchargefix$overrideKnockbackResistance(double original) {
        if (destructionType == Explosion.DestructionType.TRIGGER_BLOCK) {
            return 0.0;
        }

        return original;
    }
}