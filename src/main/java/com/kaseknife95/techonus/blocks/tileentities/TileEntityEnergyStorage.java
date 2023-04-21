package com.kaseknife95.techonus.blocks.tileentities;

import com.kaseknife95.techonus.energy.CustomEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

public class TileEntityEnergyStorage extends TileEntity implements ITickable {
    private CustomEnergyStorage storage = new CustomEnergyStorage(200000);
    public int energy = storage.getEnergyStored();
    private String customName;

    @Override
    public void update()
    {

    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY) return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY) return (T)this.storage;
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("GuiEnergy", this.storage.getEnergyStored());
        compound.setString("Name", this.getDisplayName().toString());
        this.storage.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.storage.setEnergy(compound.getInteger("GuiEnergy"), false) ;
        this.customName = compound.getString("Name");
        this.storage.readFromNBT(compound);
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation("container.energy_storage");
    }

    public int getEnergyStored()
    {
        return this.storage.getEnergyStored();
    }

    public int getMaxEnergyStored()
    {
        return this.storage.getMaxEnergyStored();
    }

    public int getField(int id)
    {
        switch(id)
        {
            case 0:
                return this.storage.getEnergyStored();
            default:
                return 0;
        }
    }

    public int receiveEnergy(int maxReceive, boolean simulate) { return storage.receiveEnergy(maxReceive, simulate); }


    public int extractEnergy(int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    public void setField(int id, int value)
    {
        switch(id)
        {
            case 0:
                this.storage.setEnergy(value, false);
        }
    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64D;
    }
}
