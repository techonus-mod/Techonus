package com.kaseknife95.techonus.util.handlers;

import com.kaseknife95.techonus.Techonus;
import com.kaseknife95.techonus.init.ModBlocks;
import com.kaseknife95.techonus.init.ModFluids;
import com.kaseknife95.techonus.init.ModItems;
import com.kaseknife95.techonus.util.IHasModel;
import com.kaseknife95.techonus.init.OreDictionaryInit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {

        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
        Techonus.proxy.registerStateMapper();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ModItems.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : ModBlocks.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }
    }

    public static void initRegistries(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Techonus.INSTANCE, new GuiHandler());
        OreDictionaryInit.register();
    }

    public static void preInitRegistries(FMLPreInitializationEvent event)
    {
        ModFluids.registerFluids();

    }
}
