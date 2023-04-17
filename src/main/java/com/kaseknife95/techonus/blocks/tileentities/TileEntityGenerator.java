package com.kaseknife95.techonus.blocks.tileentities;

import com.kaseknife95.techonus.blocks.machines.Generator;
import com.kaseknife95.techonus.energy.CustomEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityGenerator extends TileEntity implements ITickable {
    public ItemStackHandler handler = new ItemStackHandler(1);
    private CustomEnergyStorage storage = new CustomEnergyStorage(10000, 0, 100);
    public int energy = storage.getEnergyStored();
    private String custonName;
    public int cooktime;

    @Override
    public void update()
    {

        if(energy == storage.getMaxEnergyStored()) return;
        if(!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0)))
        {
            cooktime++;
            if(cooktime == 25)
            {
                energy += getFuelValue(handler.getStackInSlot(0));
                handler.getStackInSlot(0).shrink(1);
                cooktime = 0;
            }
        }
        for (EnumFacing facing : EnumFacing.VALUES) {
            if (facing != getWorld().getBlockState(pos).getValue(Generator.FACING)) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
                if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                    int energyToTransfer = Math.min(energy, storage.getMaxExtract());
                    int energyExtracted = storage.extractEnergy(energyToTransfer, true);
                    int energyReceived = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).receiveEnergy(energyExtracted, false);
                    storage.extractEnergy(energyReceived, false);
                }
            }
        }
    }

    private boolean isItemFuel(ItemStack stack)
    {
        return getFuelValue(stack) > 0;
    }

    private int getFuelValue(ItemStack stack)
    {
        if(stack.getItem() == Items.GLOWSTONE_DUST) return 1000;
        else return 0;
    }


    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return (T)this.storage;
        if(capability== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return true;
        if(capability== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        return super.hasCapability(capability, facing);
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
         super.writeToNBT(compound);
         compound.setTag("Inventory", this.handler.serializeNBT());
         compound.setInteger("CookTime", this.cooktime);
         compound.setInteger("GuiEnergy", this.energy);
         compound.setString("Name", getDisplayName().toString());
         this.storage.writeToNBT(compound);
         return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.cooktime = compound.getInteger("CookTime");
        this.energy = compound.getInteger("GuiEnergy");
        this.custonName = compound.getString("Name");
        this.storage.readFromNBT(compound);

    }

    public ITextComponent getDisplayName(){
        return new TextComponentTranslation("container.generator");
    }

    public int getEnergyStored() {
        return this.energy;
    }

    public int getMaxEnergyStored(){
        return this.storage.getMaxEnergyStored();
    }


    public int receiveEnergy(int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }


    public int extractEnergy(int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    public int getField(int id) {
        switch(id)
        {
            case 0:
                return this.energy;

            case 1:
                return this.cooktime;

            default:
                return 0;


        }
    }

    public void setField(int id, int value) {
        switch(id)
        {
            case 0:
                this.energy = value;
            case 1:
                this.cooktime = value;
        }
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY()+ 0.5D, (double)this.pos.getZ()+ 0.5D) <= 64.0D;
    }

}
