package koolaidmod.handlers;

import koolaidmod.Base;
import koolaidmod.blocks.test_block;
import koolaidmod.init.ModBlocks;
import koolaidmod.init.ModItems;
import koolaidmod.init.PotionInit;
import koolaidmod.items.armor.ModArmor;
import koolaidmod.materials.ModMaterials;
import koolaidmod.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.logging.Logger;

@Mod.EventBusSubscriber(modid = Base.MODID)
public class RegistryHandler {

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event){
        Logger logger = Logger.getLogger(Base.MODID);

        final Block[] blocks = {
                RegistryUtil.setBlockName(new test_block(Material.ROCK), "test_block").setCreativeTab(Base.MOD_TAB).setCreativeTab(CreativeTabs.SEARCH),
        };
        event.getRegistry().registerAll(blocks);
        for(Block block : blocks)
            logger.warning("Registered item: " + block.getRegistryName().toString());
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event){
        Logger logger = Logger.getLogger(Base.MODID);
        final Item[] items = {
                RegistryUtil.setItemName(new Item(), "kool_aid").setCreativeTab(Base.MOD_TAB),

                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.HEAD), "juice_helmet").setCreativeTab(Base.MOD_TAB).setCreativeTab(CreativeTabs.SEARCH),
                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.CHEST), "juice_chestplate").setCreativeTab(Base.MOD_TAB).setCreativeTab(CreativeTabs.SEARCH),
                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.LEGS), "juice_leggings").setCreativeTab(Base.MOD_TAB).setCreativeTab(CreativeTabs.SEARCH),
                RegistryUtil.setItemName(new ModArmor(ModMaterials.JUICE_ARMOR, EntityEquipmentSlot.FEET), "juice_boots").setCreativeTab(Base.MOD_TAB).setCreativeTab(CreativeTabs.SEARCH),
        };
        final Item[] itemBlocks = {
                new ItemBlock(ModBlocks.TEST_BLOCK).setRegistryName(ModBlocks.TEST_BLOCK.getRegistryName())
        };
        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
        for(Item item : items)
            logger.warning("Registered item: " + item.getRegistryName().toString());
        for(Item item : itemBlocks)
            logger.warning("Registered item: " + item.getRegistryName().toString());
    }
}
