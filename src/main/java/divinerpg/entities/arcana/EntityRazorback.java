package divinerpg.entities.arcana;

import divinerpg.entities.base.EntityDivineMonster;
import divinerpg.registries.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityRazorback extends EntityDivineMonster {
    public EntityRazorback(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.225F;
    }
    @Override public boolean isAggressive() {return true;}
    @Override public boolean fireImmune() {return true;}
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.RAZORBACK.get();
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.RAZORBACK_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.RAZORBACK_HURT.get();
    }

}
