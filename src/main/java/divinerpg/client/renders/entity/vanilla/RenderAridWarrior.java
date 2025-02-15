package divinerpg.client.renders.entity.vanilla;


import divinerpg.DivineRPG;
import divinerpg.client.models.vanilla.*;
import divinerpg.client.renders.layer.AridWarriorBowLayer;
import divinerpg.entities.vanilla.overworld.EntityAridWarrior;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class RenderAridWarrior extends MobRenderer<EntityAridWarrior, ModelAridWarrior<EntityAridWarrior>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DivineRPG.MODID, "textures/entity/arid_warrior.png");
    public RenderAridWarrior(Context context) {
        super(context, new ModelAridWarrior<>(context), 0.8F);
        this.addLayer(new AridWarriorBowLayer(this));
    }
    @Override
    public ResourceLocation getTextureLocation(EntityAridWarrior entity) {
        return TEXTURE;
    }
}