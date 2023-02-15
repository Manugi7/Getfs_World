package com.manugi.getf_world.events;

import com.manugi.getf_world.Getf_world;
import com.manugi.getf_world.entities.CrispyChickEntity;
import com.manugi.getf_world.entities.MarbEntity;
import com.manugi.getf_world.init.InitMobs;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Getf_world.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobsAttrsEvent {

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event){

        event.put(InitMobs.MARB.get(), MarbEntity.createAttributes().build());
        event.put(InitMobs.CRISPY_CHICK.get(), CrispyChickEntity.createAttributes().build());
    }
}
