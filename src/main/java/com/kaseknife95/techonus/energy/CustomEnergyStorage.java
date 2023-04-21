package com.kaseknife95.techonus.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;

public class CustomEnergyStorage implements IEnergyStorage {

    private int energy;
    private int capacity;
    private int maxExtract;
    private int maxReceive;

    public CustomEnergyStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public CustomEnergyStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = energy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!canReceive()) {
            return 0;
        }

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!canExtract()) {
            return 0;
        }

        int energyExtracted = Math.min(energy, this.maxExtract);
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    public int setEnergy(int i, boolean simulate){
        if(!simulate){
            energy = i;
        }
        return i;
    }

    public int addEnergy(int i, boolean simulate) {
        if(!simulate){
            energy += i;
        }
     return i;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return maxReceive > 0;
    }

    // NBT serialization and deserialization

    public void readFromNBT(NBTTagCompound compound) {
        energy = compound.getInteger("Energy");
        capacity = compound.getInteger("Capacity");
        maxReceive = compound.getInteger("MaxReceive");
        maxExtract = compound.getInteger("MaxExtract");
    }

    public void writeToNBT(NBTTagCompound compound) {
        compound.setInteger("Energy", energy);
        compound.setInteger("Capacity", capacity);
        compound.setInteger("MaxReceive", maxReceive);
        compound.setInteger("MaxExtract", maxExtract);
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return true;
    }


}
