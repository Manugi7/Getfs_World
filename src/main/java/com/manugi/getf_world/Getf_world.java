package com.manugi.getf_world;

import com.manugi.getf_world.events.MobsAttrsEvent;
import com.manugi.getf_world.events.MobsRendererEvents;
import com.manugi.getf_world.init.InitItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.manugi.getf_world.init.InitItems.*;
import static com.manugi.getf_world.init.InitMobs.MOBS;

@Mod(Getf_world.MODID)
@Mod.EventBusSubscriber

public class Getf_world {
    public static final String MODID = "getf_world";
    public static CreativeModeTab Getf_tab;

    public Getf_world(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::registerTabs);
        bus.addListener(this::addItemsToTabs);

        InitItems.ITEMS.register(bus);

        bus.register(new MobsAttrsEvent());
        bus.register(new MobsRendererEvents());
        MOBS.register(bus);

    }

    @SubscribeEvent
    public void registerTabs(CreativeModeTabEvent.Register event){
        Getf_tab = event.registerCreativeModeTab(new ResourceLocation(MODID, "main_tab"), builder -> builder //Es muy importante no cambiar el main_tab
                .icon(() -> new ItemStack(GETF.get()))
                .title(Component.translatable("Getf's World"))
                .displayItems((featureFlags, output, hasOp) -> {
                    //TOOLS_AND_UTILITIES
                    output.accept(PELOTA.get());
                    //FOOD_AND_DRINKS
                    output.accept(CRISPY.get());
                    //SPAWN_EGGS
                    output.accept(MARB_EGG.get());
                    output.accept(CRISPY_CHICK_EGG.get());
                })
        );
    }
    private void addItemsToTabs(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            event.accept(PELOTA);
        }
        if (event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS)
        {
            event.accept(CRISPY);
        }
        if (event.getTab() == CreativeModeTabs.SPAWN_EGGS)
        {
            event.accept(MARB_EGG);
            event.accept(CRISPY_CHICK_EGG);
        }
    }
}
