package club.someoneice.rest;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Main.MODID, dependencies = "required-after:manametalmod;after:sittable")
public class Main {
    public static final String MODID = "with_rest";
    static boolean isSittableInstall = false;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded("sittable")) isSittableInstall = true;

        MinecraftForge.EVENT_BUS.register(new Event());
        FMLCommonHandler.instance().bus().register(new Event());
    }
}
