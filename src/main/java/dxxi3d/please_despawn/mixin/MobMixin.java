package dxxi3d.please_despawn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.DropChances;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mob.class)
public abstract class MobMixin {

    @WrapOperation(method = "checkDespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;isPersistenceRequired()Z"))
    private boolean dxxi3d$pleaseDespawn$checkDespawn$isPersistenceRequired(Mob mob, Operation<Boolean> original) {
        if (!mob.hasCustomName()) {
            DropChances dropChances = mob.getDropChances();
            for (EquipmentSlot slot : EquipmentSlot.VALUES) {
                if (dropChances.isPreserved(slot) && !mob.getItemBySlot(slot).isEmpty()) {
                    return false;
                }
            }
        }
        return original.call(mob);
    }

    @WrapOperation(method = "checkDespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;discard()V"))
    private void dxxi3d$pleaseDespawn$checkDespawn$discard(Mob mob, Operation<Void> original) {
        if (mob.isPersistenceRequired() && mob.level() instanceof ServerLevel serverLevel) {
            mob.dropPreservedEquipment(serverLevel);
        }
        original.call(mob);
    }
}