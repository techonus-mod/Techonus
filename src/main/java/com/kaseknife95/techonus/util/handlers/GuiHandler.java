package com.kaseknife95.techonus.util.handlers;

import com.kaseknife95.techonus.blocks.containers.ContainerEnergyStorage;
import com.kaseknife95.techonus.blocks.containers.ContainerGenerator;
import com.kaseknife95.techonus.blocks.guis.GuiEnergyStorage;
import com.kaseknife95.techonus.blocks.guis.GuiGenerator;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityEnergyStorage;
import com.kaseknife95.techonus.blocks.tileentities.TileEntityGenerator;
import com.kaseknife95.techonus.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
      if(ID == Reference.GUI_GENERATOR) return new ContainerGenerator(player.inventory, (TileEntityGenerator)world.getTileEntity(new BlockPos(x,y,z)));
      if(ID == Reference.GUI_ENERGY_STORAGE) return new ContainerEnergyStorage(player.inventory, (TileEntityEnergyStorage)world.getTileEntity(new BlockPos(x,y,z)));
      return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
      if(ID == Reference.GUI_GENERATOR) return new GuiGenerator(player.inventory, (TileEntityGenerator)world.getTileEntity(new BlockPos(x,y,z)));
      if(ID == Reference.GUI_ENERGY_STORAGE) return new GuiEnergyStorage(player.inventory, (TileEntityEnergyStorage)world.getTileEntity(new BlockPos(x,y,z)));
      return null;
    }
}
