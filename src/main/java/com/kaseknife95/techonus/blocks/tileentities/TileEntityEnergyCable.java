package com.kaseknife95.techonus.blocks.tileentities;
import com.kaseknife95.techonus.energy.CustomEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityEnergyCable extends TileEntity implements ICapabilitySerializable<NBTTagCompound>, ITickable {

    // Define the maximum energy capacity for the cable
    private static final int MAX_ENERGY_CAPACITY = 1000;

    // Create an instance of the EnergyStorage capability
    private CustomEnergyStorage energyStorage = new CustomEnergyStorage(MAX_ENERGY_CAPACITY);

    // Override methods for TileEntity



    // Override methods for ICapabilitySerializable

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        }
        return null;
    }





    // Custom methods for transferring energy between blocks

    // Get the energy stored in the cable
    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    // Get the maximum energy capacity of the cable
    public int getMaxEnergyCapacity() {
        return energyStorage.getMaxEnergyStored();
    }

    // Transfer energy to the cable
    public void transferEnergy(int energy) {
        energyStorage.receiveEnergy(energy, false);
    }

    // Transfer energy from the cable
    public int extractEnergy(int maxExtract, boolean simulate) {
        return energyStorage.extractEnergy(maxExtract, simulate);
    }

    // Transfer energy to adjacent blocks
    public void transferEnergyToAdjacentBlocks() {
        for (EnumFacing facing : EnumFacing.values()) {
            TileEntity te = world.getTileEntity(pos.offset(facing));
            if (te != null && te.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                IEnergyStorage energyHandler = te.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
                if (energyHandler != null && energyHandler.canReceive()) {
                    int energyToTransfer = energyStorage.extractEnergy(energyStorage.getMaxExtract(), true);
                    int energyReceived = energyHandler.receiveEnergy(energyToTransfer, false);
                    energyStorage.extractEnergy(energyReceived, false);
                }
            }
        }
    }

    @Override
    public void update() {
        transferEnergyToAdjacentBlocks();
    }
}