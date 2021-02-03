package koolaidmod.items.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class ModArmor extends ItemArmor{
    public ModArmor(ArmorMaterial armorMaterial, EntityEquipmentSlot equipmentSlot){
        super(armorMaterial, 0, equipmentSlot);
    }
}
