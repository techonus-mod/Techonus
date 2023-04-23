package com.kaseknife95.techonus.blocks.containers;

import com.kaseknife95.techonus.blocks.tileentities.TileEntityEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;

public class ContainerEnergyStorage extends Container {
    private final TileEntityEnergyStorage tileentity;
    private int energy;

    public ContainerEnergyStorage(InventoryPlayer player, TileEntityEnergyStorage tileentity)
    {
        this.tileentity = tileentity;

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileentity.isUsableByPlayer(playerIn);
    }

    @Override
    public void updateProgressBar(int id, int data)
    {
        this.tileentity.setField(id, data);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for(int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener listener = (IContainerListener)this.listeners.get(i);
            if(this.energy != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
        }

        this.energy = this.tileentity.getField(0);
    }
}
