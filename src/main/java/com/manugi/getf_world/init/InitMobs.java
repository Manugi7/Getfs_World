package com.manugi.getf_world.init;

import com.manugi.getf_world.Getf_world;
import com.manugi.getf_world.entities.CrispyChickEntity;
import com.manugi.getf_world.entities.MarbEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class InitMobs {

    public static final DeferredRegister<EntityType<?>> MOBS = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Getf_world.MODID);

    public static final RegistryObject<EntityType<MarbEntity>> MARB = MOBS.register("marb",
            () -> EntityType.Builder.of(MarbEntity::new, MobCategory.CREATURE)
                    .sized(0.55F, 0.55F)
                    .build(Getf_world.MODID + ":marb")
    );

    public static final RegistryObject<EntityType<CrispyChickEntity>> CRISPY_CHICK = MOBS.register("crispychick",
            () -> EntityType.Builder.of(CrispyChickEntity::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .build(Getf_world.MODID + ":crispy_chick")
    );

}
