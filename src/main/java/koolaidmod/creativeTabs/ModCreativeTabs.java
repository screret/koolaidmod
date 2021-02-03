package koolaidmod.creativeTabs;

import koolaidmod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs extends CreativeTabs
{
    public ModCreativeTabs()
    {
        super("kool-aid mod");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.KOOL_AID);
    }
}

