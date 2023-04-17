package com.kaseknife95.techonus.blocks.machines;

import com.kaseknife95.techonus.Techonus;
import com.kaseknife95.techonus.blocks.BlockBase;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityEnergyStorage;
import com.kaseknife95.techonus.util.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyStorage extends BlockBase {

    public EnergyStorage(String name) {
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
        return new TileEntityEnergyStorage();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            playerIn.openGui(Techonus.INSTANCE, Reference.GUI_ENERGY_STORAGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }
}
