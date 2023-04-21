package com.kaseknife95.techonus;

import com.kaseknife95.techonus.proxy.CommonProxy;
import com.kaseknife95.techonus.tab.TabMenu;
import com.kaseknife95.techonus.util.handlers.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.kaseknife95.techonus.util.Reference.*;

@Mod(
        modid = MODID,
        name = NAME,
        version = VERSION
)
public class Techonus {



    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(MODID)
    public static Techonus INSTANCE;

    @SidedProxy(modId = MODID, clientSide = CLIENT, serverSide = COMMON)
    public static CommonProxy proxy;

    public static final TabMenu tab = new TabMenu("maintab");
    public static final TabMenu itemtab = new TabMenu("itemtab");
    public static final TabMenu tab3 = new TabMenu("oretab");
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries(event);
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        RegistryHandler.initRegistries();
    }


    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }


    @GameRegistry.ObjectHolder(MODID)
    public static class Blocks {

    }


    @GameRegistry.ObjectHolder(MODID)
    public static class Items {

    }


    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {

       @SubscribeEvent
       public static void addItems(RegistryEvent.Register<Item> event) {

       }

       @SubscribeEvent
       public static void addBlocks(RegistryEvent.Register<Block> event) {

       }
    }

}
