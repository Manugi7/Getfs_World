package com.manugi.getf_world.client.models;

import com.manugi.getf_world.Getf_world;
import com.manugi.getf_world.entities.CrispyChickEntity;
import com.manugi.getf_world.entities.MarbEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.DefaultedGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class CrispyChickModel<T extends CrispyChickEntity> extends DefaultedEntityGeoModel<CrispyChickEntity> {

    public CrispyChickModel(){

        super(new ResourceLocation(Getf_world.MODID, "crispy_chick/galinha"));
    }

    /*
    @Override
    public ResourceLocation getModelResource(GeoAnimatable animatable) {
        return new ResourceLocation(Getf_world.MODID, "geo/crispychickanim.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return new ResourceLocation(Getf_world.MODID, "textures/entities/galinha.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
        return new ResourceLocation(Getf_world.MODID, "animations/crispywalkoriginal.animation.json");
    }*/
}
