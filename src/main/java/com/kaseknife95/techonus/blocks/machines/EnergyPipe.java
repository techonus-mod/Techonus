package com.kaseknife95.techonus.blocks.machines;

import com.kaseknife95.techonus.Techonus;
import com.kaseknife95.techonus.blocks.BlockBase;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityEnergyCable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EnergyPipe extends BlockBase {

    public EnergyPipe(String name) {
        super(name, Material.IRON, Techonus.tab);
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityEnergyCable();
    }

}