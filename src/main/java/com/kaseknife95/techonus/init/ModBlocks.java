package com.kaseknife95.techonus.init;


import com.kaseknife95.techonus.blocks.machines.EnergyPipe;
import com.kaseknife95.techonus.blocks.machines.EnergyStorage;
import com.kaseknife95.techonus.blocks.machines.Generator;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static Block WOOD_GENERATOR = new Generator("burning_generator");
    public static Block ENERGY_STORAGE = new EnergyStorage("energy_storage");
    public static Block ENERGY_PIPE = new EnergyPipe("energy_pipe");
}
