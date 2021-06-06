package divinerpg.entities.projectile;

import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.*;
import net.minecraft.world.*;

public class EntitySerenadeOfDeath extends DivineThrowable {


    public EntitySerenadeOfDeath(EntityType<? extends ThrowableEntity> type, World world) {
        super(type, world);
    }

    public EntitySerenadeOfDeath(EntityType<? extends ThrowableEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    public EntitySerenadeOfDeath(EntityType<? extends ThrowableEntity> type, LivingEntity entity, World world) {
        super(type, entity, world);
        Vector3d vector = entity.getLookAngle().scale(1.5);
        this.setPos(xo + vector.x, yo + vector.y, zo + vector.z);
    }

    @Override
    protected void onHit(RayTraceResult var1) {
        if (var1.hitInfo != null && var1.hitInfo instanceof Entity) {
            Entity entity = (Entity) var1.hitInfo;
            entity.hurt(DamageSource.thrown(this, this.getOwner()), 14.0F);
            if (var1.hitInfo instanceof LivingEntity) {
                ((LivingEntity) var1.hitInfo).addEffect(new EffectInstance(Effects.POISON, 45, 3));
            }
        }

        if (!this.level.isClientSide) {
            this.kill();
        }
    }
}