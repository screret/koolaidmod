package koolaidmod.util;

import koolaidmod.Base;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

public class RegistryUtil {

    public static Item setItemName(@NotNull final Item item, @NotNull final String name) {
        item.setRegistryName(Base.MODID, name).setTranslationKey(Base.MODID + "." + name);
        return item;
    }

    public static Block setBlockName(@NotNull final Block block, @NotNull final String name) {
        block.setRegistryName(Base.MODID, name).setTranslationKey(block.getRegistryName().toString());
        return block;
    }

}