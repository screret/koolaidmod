package koolaidmod.util;

import koolaidmod.Base;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RegistryUtil {

    public static Item setItemName(final Item item, final String name) {
        item.setRegistryName(Base.MODID, name).setTranslationKey(Base.MODID + "." + name);
        return item;
    }

    public static Block setBlockName(final Block block, final String name) {
        block.setRegistryName(Base.MODID, name).setTranslationKey(Base.MODID + "." + name);
        return block;
    }

}