package com.manugi.getf_world.init;

import com.google.common.eventbus.Subscribe;
import com.manugi.getf_world.Getf_world;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InitItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(  //Hay que registrar cada nuevo item en la memoria del mine, para ello se usa esto
            ForgeRegistries.ITEMS, Getf_world.MODID                              //si no se registra el juego lo ignora
    );

    public static final RegistryObject<Item> PELOTA = ITEMS.register(
            "pelota", () -> new Item(new Item.Properties()
                    .requiredFeatures()
                    .fireResistant())
    );

    public static final RegistryObject<Item> GETF = ITEMS.register(
            "getf", () -> new Item(new Item.Properties()
                    .requiredFeatures()
                    .fireResistant())
    );

    public static final RegistryObject<Item> CRISPY = ITEMS.register(
            "crispy", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(10).saturationMod(1.0F).build()))
    );

    public static final RegistryObject<ForgeSpawnEggItem> MARB_EGG = ITEMS.register(
            "marb_spawn_egg", () -> new ForgeSpawnEggItem(InitMobs.MARB, 0xFFBEC9, 0xE0CDB8, new Item.Properties())
    );

    public static final RegistryObject<ForgeSpawnEggItem> CRISPY_CHICK_EGG = ITEMS.register(
            "crispy_chick_spawn_egg", () -> new ForgeSpawnEggItem(InitMobs.CRISPY_CHICK, 0x5B423B, 0x11AA38, new Item.Properties())
    );


}
