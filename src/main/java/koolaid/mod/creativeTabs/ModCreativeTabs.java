package koolaid.mod.creativeTabs;

import koolaid.mod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
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

