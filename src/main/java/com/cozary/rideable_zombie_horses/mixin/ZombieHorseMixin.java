package com.cozary.rideable_zombie_horses.mixin;

import com.cozary.rideable_zombie_horses.goal.RunAroundLikeCrazyZombieHorseGoal;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieHorse.class)
public abstract class ZombieHorseMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public InteractionResult mobInteract(Player p_31001_, InteractionHand p_31002_) {
        ItemStack itemstack = p_31001_.getItemInHand(p_31002_);
        if (((ZombieHorse) (Object) this).isBaby()) {
            return ((ZombieHorse) (Object) this).mobInteract(p_31001_, p_31002_);
        } else if (p_31001_.isSecondaryUseActive()) {
            ((ZombieHorse) (Object) this).openCustomInventoryScreen(p_31001_);
            return InteractionResult.sidedSuccess(((ZombieHorse) (Object) this).level.isClientSide);
        } else if (((ZombieHorse) (Object) this).isVehicle()) {
            return ((ZombieHorse) (Object) this).mobInteract(p_31001_, p_31002_);
        } else {
            if (!itemstack.isEmpty()) {
                if (itemstack.is(Items.SADDLE) && !((ZombieHorse) (Object) this).isSaddled()) {
                    ((ZombieHorse) (Object) this).openCustomInventoryScreen(p_31001_);
                    return InteractionResult.sidedSuccess(((ZombieHorse) (Object) this).level.isClientSide);
                }

                InteractionResult interactionresult = itemstack.interactLivingEntity(p_31001_, ((ZombieHorse) (Object) this), p_31002_);
                if (interactionresult.consumesAction()) {
                    return interactionresult;
                }
            }

            ((ZombieHorse) (Object) this).doPlayerRide(p_31001_);
            return InteractionResult.sidedSuccess(((ZombieHorse) (Object) this).level.isClientSide);
        }
    }


    @Inject(
            method = "addBehaviourGoals",
            at = @At(
                    value = "HEAD", target = "Lnet/minecraft/world/entity/animal/horse/ZombieHorse;addBehaviourGoals()V")
    )

    protected void allowTame(CallbackInfo ci) {


        ((ZombieHorse) (Object) this).goalSelector.addGoal(0, new RunAroundLikeCrazyZombieHorseGoal(((ZombieHorse) (Object) this), 1.5D));


/*        if (!((ZombieHorse) (Object) this).isTamed()){
            ((ZombieHorse) (Object) this).tameWithName(p_31001_);
        }*/

/*        if (!((ZombieHorse) (Object) this).isTamed()) {
            Entity entity = ((ZombieHorse) (Object) this).getPassengers().get(0);
            if (entity == null) {
                return;
            }

            if (entity instanceof Player) {
                int i = ((ZombieHorse) (Object) this).getTemper();
                int j = ((ZombieHorse) (Object) this).getMaxTemper();
                if (j > 0 && ((ZombieHorse) (Object) this).getRandom().nextInt(j) < i && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(((ZombieHorse) (Object) this), (Player)entity)) {
                    ((ZombieHorse) (Object) this).tameWithName((Player)entity);
                    return;
                }

                ((ZombieHorse) (Object) this).modifyTemper(5);
            }

            ((ZombieHorse) (Object) this).ejectPassengers();
            ((ZombieHorse) (Object) this).makeMad();
            ((ZombieHorse) (Object) this).level.broadcastEntityEvent(((ZombieHorse) (Object) this), (byte)6);
        }*/


    }


}
