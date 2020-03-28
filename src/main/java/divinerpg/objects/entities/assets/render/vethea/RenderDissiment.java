package divinerpg.objects.entities.assets.render.vethea;

import divinerpg.api.Reference;
import divinerpg.objects.entities.assets.model.vethea.ModelDissiment;
import divinerpg.objects.entities.entity.vethea.EntityDissiment;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDissiment extends RenderLiving<EntityDissiment> {


    ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/entity/dissiment.png");

    public RenderDissiment(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelDissiment(), 1F);

    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDissiment entity) {
        return texture;
    }

	}