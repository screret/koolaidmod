package koolaidmod.handlers;

import koolaidmod.Base;
import koolaidmod.blocks.test_block;
import koolaidmod.init.ModBlocks;
import koolaidmod.init.ModItems;
import koolaidmod.items.armor.ModArmor;
import koolaidmod.materials.ModMaterials;
import koolaidmod.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Base.MODID)
public class RegistryHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerBlocks(@NotNull final RegistryEvent.Register<Block> event){
        final Block[] blocks = {
                RegistryUtil.setBlockName(new test_block(Material.ROCK), "test_block").setCreativeTab(Base.MOD_TAB),
        };
        event.getRegistry().registerAll(blocks);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerItems(@NotNull final RegistryEvent.Register<Item> event){
        Logger logger = LogManager.getLogger(Base.MODID);
        final Item[] items = {
                RegistryUtil.setItemName(new Item(), "kool_aid").setCreativeTab(Base.MOD_TAB),

                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.HEAD), "juice_helmet").setCreativeTab(Base.MOD_TAB),
                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.CHEST), "juice_chestplate").setCreativeTab(Base.MOD_TAB),
                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.LEGS), "juice_leggings").setCreativeTab(Base.MOD_TAB),
                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.FEET), "juice_boots").setCreativeTab(Base.MOD_TAB),
        };
        final Item[] itemBlocks = {
                new ItemBlock(ModBlocks.TEST_BLOCK).setRegistryName(ModBlocks.TEST_BLOCK.getRegistryName())
        };
        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
        logger.warn(items[0].getRegistryName().toString() + " " +  ModItems.KOOL_AID.getRegistryName().toString());
    }
}
