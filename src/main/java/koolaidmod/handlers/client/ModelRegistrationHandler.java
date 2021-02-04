package koolaidmod.handlers.client;

import koolaidmod.Base;
import koolaidmod.init.ModBlocks;
import koolaidmod.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@EventBusSubscriber(value = Side.CLIENT, modid = Base.MODID)
public class ModelRegistrationHandler {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerModels(@NotNull final ModelRegistryEvent event) {
        registerModel(ModItems.KOOL_AID, 0);

        registerModel(ModItems.JUICE_HELMET, 0);
        registerModel(ModItems.JUICE_CHESTPLATE, 0);
        registerModel(ModItems.JUICE_LEGGINGS, 0);
        registerModel(ModItems.JUICE_BOOTS, 0);

        registerModel(Item.getItemFromBlock(ModBlocks.TEST_BLOCK), 0);
    }

    private static void registerModel(@NotNull Item item, int meta) {
        if (item != null)
            ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
        else
            throw new IllegalArgumentException("object not registered");
    }
}