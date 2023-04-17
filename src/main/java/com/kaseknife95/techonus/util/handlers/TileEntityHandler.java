package com.kaseknife95.techonus.util.handlers;

import com.kaseknife95.techonus.blocks.tileentities.TileEntityEnergyCable;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityEnergyStorage;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityGenerator;
import com.kaseknife95.techonus.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities()
    {

        GameRegistry.registerTileEntity(TileEntityGenerator.class, new ResourceLocation(Reference.MODID + ":burning_generator"));
        GameRegistry.registerTileEntity(TileEntityEnergyStorage.class, new ResourceLocation(Reference.MODID + ":energy_storage"));
        GameRegistry.registerTileEntity(TileEntityEnergyCable.class, new ResourceLocation(Reference.MODID + ":energy_pipe"));
    }
}
