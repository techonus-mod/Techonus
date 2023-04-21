package com.kaseknife95.techonus.blocks.machines;


import com.kaseknife95.techonus.Techonus;
import com.kaseknife95.techonus.blocks.BlockBase;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityGenerator;
import com.kaseknife95.techonus.util.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Generator extends BlockBase {


    public Generator(String name) {
        super(name, Material.IRON, Techonus.tab);

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
       if(!worldIn.isRemote)
       {
           playerIn.openGui(Techonus.INSTANCE, Reference.GUI_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
       }
       return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
       return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityGenerator();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityGenerator tileentity = (TileEntityGenerator)worldIn.getTileEntity(pos);
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
        super.breakBlock(worldIn, pos, state);
    }


}
