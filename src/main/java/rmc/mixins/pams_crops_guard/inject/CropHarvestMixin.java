package rmc.mixins.pams_crops_guard.inject;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import pam.pamhc2crops.events.harvest.CropHarvest;
import rmc.libs.event_factory.EventFactory;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = CropHarvest.class)
public abstract class CropHarvestMixin {

    @Inject(method = "Lpam/pamhc2crops/events/harvest/CropHarvest;onCropHarvest(Lnet/minecraftforge/event/entity/player/PlayerInteractEvent$RightClickBlock;)V",
            cancellable = true,
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/block/Block;getDrops(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/tileentity/TileEntity;)Ljava/util/List;"))
    private void guardCropHarvest(RightClickBlock event, CallbackInfo mixin) {
        if (!EventFactory.testBlockBreak(EventFactory.convert(event.getPlayer()), event.getWorld(), event.getPos())) {
            mixin.cancel();
        }
    }

}