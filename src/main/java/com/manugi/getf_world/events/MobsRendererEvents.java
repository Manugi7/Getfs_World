package com.manugi.getf_world.events;

import com.manugi.getf_world.Getf_world;
import com.manugi.getf_world.client.models.MarbModel;
import com.manugi.getf_world.client.renderer.CrispyChickRenderer;
import com.manugi.getf_world.client.renderer.MarbRenderer;
import com.manugi.getf_world.init.InitMobs;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Getf_world.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MobsRendererEvents {

    @SubscribeEvent
    public static void entityRenderer(EntityRenderersEvent.RegisterRenderers event){

        event.registerEntityRenderer(InitMobs.MARB.get(), MarbRenderer::new);
        event.registerEntityRenderer(InitMobs.CRISPY_CHICK.get(), CrispyChickRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){

        event.registerLayerDefinition(MarbModel.LAYER_LOCATION, MarbModel::createBodyLayer);
    }

}
