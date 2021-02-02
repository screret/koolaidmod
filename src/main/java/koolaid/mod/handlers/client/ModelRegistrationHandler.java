package koolaid.mod.handlers.client;

import koolaid.mod.Base;
import koolaid.mod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.client.renderer.ItemMeshDefinition;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(value = Side.CLIENT, modid = Base.MODID)
public class ModelRegistrationHandler {

    @SubscribeEvent
    public void registerModels(final ModelRegistryEvent event) {
        registerModel(ModItems.KOOL_AID, 0);
        registerModel(ModItems.JUICE_HELMET, 0);
        registerModel(ModItems.JUICE_CHESTPLATE, 0);
        registerModel(ModItems.JUICE_LEGGINGS, 0);
        registerModel(ModItems.JUICE_BOOTS, 0);
    }

    private static void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        System.out.println(item.getRegistryName().toString());
    }
}