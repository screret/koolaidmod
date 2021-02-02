package koolaid.mod.init;

import koolaid.mod.Base;
import koolaid.mod.items.armor.ModArmor;
import koolaid.mod.materials.ModMaterials;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Base.MODID)
public class ModItems {

        //normies
        public static final Item KOOL_AID              = new Item();

        //armor
        public static final ModArmor JUICE_HELMET      = new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.HEAD);
        public static final ModArmor JUICE_CHESTPLATE  = new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.CHEST);
        public static final ModArmor JUICE_LEGGINGS    = new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.LEGS);
        public static final ModArmor JUICE_BOOTS       = new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.FEET);
}