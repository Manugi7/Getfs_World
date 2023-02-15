package com.manugi.getf_world.client.renderer;

import com.manugi.getf_world.Getf_world;
import com.manugi.getf_world.client.models.CrispyChickModel;
import com.manugi.getf_world.client.models.MarbModel;
import com.manugi.getf_world.entities.CrispyChickEntity;
import com.manugi.getf_world.entities.MarbEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrispyChickRenderer extends GeoEntityRenderer<CrispyChickEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Getf_world.MODID, "textures/entities/galinha.png");
    public CrispyChickRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CrispyChickModel());
        this.shadowRadius = 0.3f;
    }

    public ResourceLocation getTextureLocation(CrispyChickEntity p_114482_) {
        return TEXTURE;
    }

    @Override
    public void preRender(PoseStack poseStack, CrispyChickEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue,
                          float alpha) {
        this.entityRenderTranslations = new Matrix4f(poseStack.last().pose());

        if (animatable.isBaby())
            poseStack.scale(0.7F,0.7F,0.7F);
    }


}
