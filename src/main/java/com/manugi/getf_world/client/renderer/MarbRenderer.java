package com.manugi.getf_world.client.renderer;

import com.manugi.getf_world.Getf_world;
import com.manugi.getf_world.client.models.*;
import com.manugi.getf_world.entities.MarbEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MarbRenderer extends MobRenderer<MarbEntity, MarbModel<MarbEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Getf_world.MODID, "textures/entities/marb.png");

    public MarbRenderer(EntityRendererProvider.Context context){

        super(context, new MarbModel(context.bakeLayer(MarbModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(MarbEntity p_114482_) {
        return TEXTURE;
    }

    @Override
    protected void scale(MarbEntity marb, PoseStack poseStack, float f) {
        if(marb.isBaby()) {
            poseStack.scale(0.3F, 0.3F, 0.3F);
        }
    }

}
