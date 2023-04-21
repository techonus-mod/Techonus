package com.kaseknife95.techonus.blocks.tileentities;

import com.kaseknife95.techonus.energy.CustomEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
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
    public int furnaceBurnTime;
    private int currentItemBurnTime;
    private int currentItemEnergyValue;
    @Override
    public void update()
    {
        if(this.isBurning()){
            --this.furnaceBurnTime;
            if(this.energy < this.storage.getMaxEnergyStored()) {
                this.energy += currentItemEnergyValue / 20;
                this.storage.setEnergy(energy, false);
            }
        }

        if(!this.world.isRemote){
            if(!this.isBurning() && this.canBurn()){
                this.furnaceBurnTime = getItemBurnTime(this.handler.getStackInSlot(0));
                this.currentItemBurnTime = getItemBurnTime(this.handler.getStackInSlot(0));
                this.currentItemEnergyValue = getItemEnergyValue(this.handler.getStackInSlot(0));
                this.handler.getStackInSlot(0).shrink(1);
            }
        }

        for (EnumFacing facing : EnumFacing.VALUES) {

                TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
                if (tileEntity != null && tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                    int energyToTransfer = Math.min(energy, storage.getMaxExtract());
                    int energyExtracted = storage.extractEnergy(energyToTransfer, true);
                    int energyReceived = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).receiveEnergy(energyExtracted, false);

                    this.energy -= energyReceived;
                    this.storage.setEnergy(energy, false);
                }

        }
    }

    private boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
            {
                return 150;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOOL))
            {
                return 100;
            }
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
            {
                return 67;
            }
            else if (item == Item.getItemFromBlock(Blocks.LADDER))
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
            {
                return 100;
            }
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
            {
                return 1600;
            }
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
            {
                return 200;
            }
            else if (item == Items.STICK)
            {
                return 100;
            }
            else if (item != Items.BOW && item != Items.FISHING_ROD)
            {
                if (item == Items.SIGN)
                {
                    return 200;
                }
                else if (item == Items.COAL)
                {
                    return 1600;
                }
                else if (item == Items.LAVA_BUCKET)
                {
                    return 2000;
                }
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if (item == Items.BLAZE_ROD)
                    {
                        return 240;
                    }
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
                    {
                        return 200;
                    }
                    else
                    {
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else
                {
                    return 100;
                }
            }
            else
            {
                return 300;
            }
        }
    }

    public static int getItemEnergyValue(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
            {
                return 150;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOOL))
            {
                return 100;
            }
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
            {
                return 67;
            }
            else if (item == Item.getItemFromBlock(Blocks.LADDER))
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
            {
                return 100;
            }
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
            {
                return 16000;
            }
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
            {
                return 200;
            }
            else if (item == Items.STICK)
            {
                return 100;
            }
            else if (item != Items.BOW && item != Items.FISHING_ROD)
            {
                if (item == Items.SIGN)
                {
                    return 200;
                }
                else if (item == Items.COAL)
                {
                    return 1600;
                }
                else if (item == Items.LAVA_BUCKET)
                {
                    return 20000;
                }
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if (item == Items.BLAZE_ROD)
                    {
                        return 2400;
                    }
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
                    {
                        return 200;
                    }
                    else
                    {
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else
                {
                    return 100;
                }
            }
            else
            {
                return 300;
            }
        }
    }

    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }



    private boolean canBurn(){
        if (((ItemStack)this.handler.getStackInSlot(0)).isEmpty())
        {
            return false;
        }else{
            return this.isItemFuel(this.handler.getStackInSlot(0));
        }
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
         compound.setInteger("furnaceBurnTime", this.furnaceBurnTime);
         compound.setInteger("GuiEnergy", this.energy);
         compound.setString("Name", getDisplayName().toString());
         this.storage.writeToNBT(compound);
         return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.furnaceBurnTime = compound.getInteger("furnaceBurnTime");
        this.energy = compound.getInteger("GuiEnergy");
        String custonName = compound.getString("Name");
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
                return this.furnaceBurnTime;
            case 2:
                return this.currentItemBurnTime;
            case 3:
                return this.currentItemEnergyValue;
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
                this.furnaceBurnTime = value;
            case 2:
                this.currentItemBurnTime = value;
        }
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY()+ 0.5D, (double)this.pos.getZ()+ 0.5D) <= 64.0D;
    }

    public int getTFperTick() {
        return this.currentItemEnergyValue / 20;
    }
}
