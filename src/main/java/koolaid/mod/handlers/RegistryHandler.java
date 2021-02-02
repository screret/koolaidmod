package koolaid.mod.handlers;

import koolaid.mod.Base;
import koolaid.mod.blocks.test_block;
import koolaid.mod.handlers.client.ModelRegistrationHandler;
import koolaid.mod.init.ModBlocks;
import koolaid.mod.init.ModItems;
import koolaid.mod.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(Base.MODID)
public class RegistryHandler {


    public static void newRegistry(final RegistryEvent.NewRegistry event){

    }

    @SubscribeEvent
    public static void registerItems(@NotNull final RegistryEvent.Register<Item> event){
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
    }

    @SubscribeEvent
    public static void registerBlocks(@NotNull final RegistryEvent.Register<Block> event){
        final Block[] blocks = {
                RegistryUtil.setBlockName(new test_block(Material.ROCK), "test_block").setCreativeTab(Base.MOD_TAB),
        };
        event.getRegistry().registerAll(blocks);
    }

    /*@SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerPots(RegistryEvent.Register<PotionType> event){
        event.getRegistry().registerAll(
                KoolAidPot,
                Long_KoolAidPot
        );
        PotionInit.AddMix();
    }*/

}
