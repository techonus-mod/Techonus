package com.kaseknife95.techonus.blocks;


import com.kaseknife95.techonus.init.ModBlocks;
import com.kaseknife95.techonus.init.ModItems;
import com.kaseknife95.techonus.util.IHasModel;
import com.kaseknife95.techonus.Techonus;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material, CreativeTabs tab)
    {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModels()
    {
        Techonus.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }
}
