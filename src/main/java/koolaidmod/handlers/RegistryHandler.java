package koolaidmod.handlers;

import koolaidmod.Base;
import koolaidmod.blocks.test_block;
import koolaidmod.init.ModBlocks;
import koolaidmod.init.ModItems;
import koolaidmod.init.PotionInit;
import koolaidmod.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

@Mod.EventBusSubscriber
public class RegistryHandler {

    Logger logger = Logger.getLogger(Base.MODID);

    @SubscribeEvent
    public void registerItems(@NotNull final RegistryEvent.Register<Item> event){
        final Item[] items = {
                RegistryUtil.setItemName(ModItems.KOOL_AID, "kool_aid").setCreativeTab(Base.MOD_TAB),
                RegistryUtil.setItemName(ModItems.JUICE_HELMET, "juice_helmet").setCreativeTab(Base.MOD_TAB),
                RegistryUtil.setItemName(ModItems.JUICE_CHESTPLATE, "juice_chestplate").setCreativeTab(Base.MOD_TAB),
                RegistryUtil.setItemName(ModItems.JUICE_LEGGINGS, "juice_leggings").setCreativeTab(Base.MOD_TAB),
                RegistryUtil.setItemName(ModItems.JUICE_BOOTS, "juice_boots").setCreativeTab(Base.MOD_TAB),
        };
        final Item[] itemBlocks = {
                RegistryUtil.setItemName(new ItemBlock(ModBlocks.TEST_BLOCK), "test_block").setCreativeTab(Base.MOD_TAB)
        };
        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
        for(Item item : items)
            logger.warning("Registered item: " + item.getRegistryName());
        for(Item item : itemBlocks)
            logger.warning("Registered item: " + item.getRegistryName());
    }

    @SubscribeEvent
    public void registerBlocks(@NotNull final RegistryEvent.Register<Block> event){
        final Block[] blocks = {
                RegistryUtil.setBlockName(new test_block(Material.ROCK), "test_block").setCreativeTab(Base.MOD_TAB),
        };
        event.getRegistry().registerAll(blocks);
        for(Block block : blocks)
            logger.warning("Registered item: " + block.getRegistryName());
    }

}
