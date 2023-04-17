package com.kaseknife95.techonus.items;

import com.kaseknife95.techonus.Techonus;
import com.kaseknife95.techonus.init.ModItems;
import com.kaseknife95.techonus.util.IHasModel;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name)
    {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(Techonus.itemtab);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Techonus.proxy.registerItemRenderer(this, 0 );
    }
}
