package divinerpg.entities.vanilla.overworld;

import divinerpg.entities.base.*;
import divinerpg.registries.*;

import java.util.*;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.*;

public class EntityCyclops extends EntityPeacefulUntilAttacked {
    public EntityCyclops(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        xpReward = 40;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 3.5F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.CYCLOPS.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.CYCLOPS_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.CYCLOPS_HURT.get();
    }

    public static boolean canSpawnOn(EntityType<? extends Mob> typeIn, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random randomIn) {
        return true;
    }
    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader reader) {
        return 0.0F;
    }
}