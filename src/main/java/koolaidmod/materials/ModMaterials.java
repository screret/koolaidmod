package koolaidmod.materials;

import koolaidmod.Base;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {

    public static final ToolMaterial TUTORIAL_TOOL = EnumHelper.addToolMaterial(Base.MODID + ":" + "mod_tool", 2, 625, 7.0F, 2.5F, 10);

    public static final ArmorMaterial JUICE_ARMOR = EnumHelper.addArmorMaterial(Base.MODID + ":" + "juice_armor", Base.MODID + ":juice", 1, new int[]{20, 50, 60, 30}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2048);

}