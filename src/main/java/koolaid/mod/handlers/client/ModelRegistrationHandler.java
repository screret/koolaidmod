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

    protected List<ModelRegistryObj> modelsToReg = new ArrayList<ModelRegistryObj>();
    protected List<ModelBakeObj> modelsToBake = new ArrayList<ModelBakeObj>();
    protected List<StateMapObj> statesToMap = new ArrayList<StateMapObj>();


    public void registerItemModels(Item... items){
        for(Item item : items){
            registerItemModel(item);
        }
    }

    public void registerItemModel(Item item) {
        registerItemModel(item, item.getRegistryName().toString());
    }

    private void registerItemModel(Item item, String modelLocation) {
        final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
        registerItemModel(item, fullModelLocation);
    }

    private void registerItemModel(Item item, final ModelResourceLocation fullModelLocation) {
        modelsToBake.add(new ModelBakeObj(item, fullModelLocation, stack -> fullModelLocation));
    }
    /*@SubscribeEvent
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
    }*/

    @SubscribeEvent
    public void registerItems(ModelRegistryEvent event) {
        for(StateMapObj obj : statesToMap) {
            ModelLoader.setCustomStateMapper(obj.block, obj.map);
        }
        for(ModelBakeObj obj : modelsToBake) {
            ModelLoader.setCustomMeshDefinition(obj.item, obj.meshDefinition);
            if(obj.resource == null) {
                NonNullList<ItemStack> subItems = NonNullList.create();
                obj.item.getSubItems(CreativeTabs.SEARCH, subItems);
                for(ItemStack stack : subItems) {
                    ModelBakery.registerItemVariants(obj.item, obj.meshDefinition.getModelLocation(stack));
                }
            }
            else {
                ModelBakery.registerItemVariants(obj.item, obj.resource); // Ensure the custom model is loaded and prevent the default model from being loaded
            }
        }
        for(ModelRegistryObj obj : modelsToReg) {
            ModelLoader.setCustomModelResourceLocation(obj.item, obj.meta, obj.resource);
        }
    }

    protected static class ModelRegistryObj {
        final Item item;
        final int meta;
        final ModelResourceLocation resource;

        public ModelRegistryObj(Item i, int m, ModelResourceLocation loc) {
            item = i;
            meta = m;
            resource = loc;
        }
    }

    protected static class ModelBakeObj {
        final Item item;
        final ModelResourceLocation resource;
        final ItemMeshDefinition meshDefinition;
        public ModelBakeObj(Item i, @Nullable ModelResourceLocation location, ItemMeshDefinition itemMeshDefinition) {
            item = i;
            resource = location;
            meshDefinition = itemMeshDefinition;
        }
    }

    protected static class StateMapObj {
        final Block block;
        final StateMapperBase map;
        public StateMapObj(Block b, StateMapperBase mapper) {
            block = b;
            map = mapper;
        }

    }

}