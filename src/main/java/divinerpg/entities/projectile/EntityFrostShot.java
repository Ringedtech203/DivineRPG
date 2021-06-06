package divinerpg.entities.projectile;

import divinerpg.registries.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class EntityFrostShot extends DivineFireball {
    public EntityFrostShot(EntityType<? extends FireballEntity> type, World world) {
        super(type, world);
    }

    public EntityFrostShot(World world, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(world, x, y, z, accelX, accelY, accelZ);
    }

    public EntityFrostShot(World world, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(world, shooter, accelX, accelY, accelZ);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onHit(RayTraceResult result) {
        if (result.hitInfo == null || !result.hitInfo.equals(this.shootingEntity)) {
            if (!this.level.isClientSide) {
                List<LivingEntity> list = this.level.<LivingEntity>getEntitiesOfClass(LivingEntity.class,
                        this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));

                EntityFrostCloud frostCloud = new EntityFrostCloud(EntityRegistry.FROST_CLOUD, this.level, this.xo, this.yo, this.zo);

                frostCloud.setOwner(this.shootingEntity);
                frostCloud.setRadius(3.0F);
                frostCloud.setDuration(50);
                frostCloud.setRadiusPerTick((0.0F - frostCloud.getRadius()) / frostCloud.getDuration());

                if (!list.isEmpty()) {
                    for (LivingEntity entitylivingbase : list) {
                        double d0 = this.distanceTo(entitylivingbase);

                        if (d0 < 16.0D) {
                            frostCloud.setPos(entitylivingbase.xo, entitylivingbase.yo, entitylivingbase.zo);
                            break;
                        }
                    }
                }

                this.level.playSound((PlayerEntity) null, this.xo, this.yo, this.zo, SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F,
                        (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F);
                this.level.addFreshEntity(frostCloud);
                this.kill();
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        level.addParticle(ParticleRegistry.FROST.get(), this.xo + (random.nextFloat() * 2),
                this.yo + (random.nextFloat() * 2), this.zo + (random.nextFloat() * 2), 0, 1, 0);
    }
}
