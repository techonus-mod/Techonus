package com.kaseknife95.techonus.blocks;

import com.kaseknife95.techonus.init.ModBlocks;
import com.kaseknife95.techonus.init.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluid extends BlockFluidClassic
{
    public BlockFluid(String name, Fluid fluid, Material material)
    {
        super(fluid, material);

        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
