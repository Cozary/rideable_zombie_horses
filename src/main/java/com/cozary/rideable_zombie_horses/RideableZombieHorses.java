package com.cozary.rideable_zombie_horses;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(RideableZombieHorses.MODID)
public class RideableZombieHorses {

    public static final String MODID = "rideable_zombie_horses";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RideableZombieHorses() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
