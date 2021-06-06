package divinerpg.entities.projectile;

import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.particles.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.*;

import java.util.*;

public class EntitySoundOfCarols extends DivineThrowable {
    Random r = new Random();
    int color = r.nextInt(25);

    public EntitySoundOfCarols(EntityType<? extends ThrowableEntity> type, World world) {
        super(type, world);
    }

    public EntitySoundOfCarols(EntityType<? extends ThrowableEntity> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    public EntitySoundOfCarols(EntityType<? extends ThrowableEntity> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void tick() {
        super.tick();
        if (color >= 24) {
            color = 0;
        } else {
            color++;
        }
        level.addParticle(ParticleTypes.NOTE, this.xo, this.yo, this.zo, (double) color / 24.0D, 0.0D,
                0.0D);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.hitInfo != null) {
            ((LivingEntity)result.hitInfo).hurt(DamageSource.thrown(this, this.getOwner()), 16.0F);
        }

        if (!this.level.isClientSide) {
            this.kill();
        }
    }
}