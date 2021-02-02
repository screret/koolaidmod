package koolaid.mod.init;

import koolaid.mod.Base;
import koolaid.mod.blocks.test_block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Base.MODID)
public class ModBlocks {

    public static final test_block TEST_BLOCK = new test_block(Material.GLASS);
}