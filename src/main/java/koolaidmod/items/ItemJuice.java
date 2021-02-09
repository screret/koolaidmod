package koolaidmod.items;

import koolaidmod.Base;
import koolaidmod.init.ModItems;
import koolaidmod.init.ModPotions;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.Integer.min;
import static java.util.Objects.requireNonNull;

public class ItemJuice extends Item implements IItemColor {

    public ItemJuice(){
        this.setMaxStackSize(1);
        this.setCreativeTab(Base.MOD_TAB);
    }
    static final int MAX_FOOD_LEVEL = 20;
    static final int FOOD_LEVEL_INCREASE = 4;

    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public int getMaxItemUseDuration(ItemStack p_getMaxItemUseDuration_1_) {
        return 32;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("cheap sugar water");
        tooltip.add("(wither 1:00)");
        tooltip.add("(kool-aid 1:00)");
        tooltip.add("(nausea 1:00)");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        for (int i = 0; i < ModPotions.KoolAidPot.getEffects().toArray().length; i++) {
            entityLiving.addPotionEffect(ModPotions.KoolAidPot.getEffects().get(i));
        }
        shaderHelper(new ResourceLocation("minecraft:shaders/post/invert.json"), new ResourceLocation("minecraft:shaders/post/blit.json"), entityLiving);

        Optional<EntityPlayer> entityPlayer = downcast(entityLiving);
        boolean shouldShrinkStack = entityPlayer.map(x -> !x.capabilities.isCreativeMode).orElse(true);
        if(shouldShrinkStack) {
            stack.shrink(1);
        }
        entityPlayer.flatMap(this::downcast)
                .ifPresent(mp -> CriteriaTriggers.CONSUME_ITEM.trigger(mp, stack));
        Function<FoodStats, Integer> newFoodLevel = food -> min(food.getFoodLevel()+FOOD_LEVEL_INCREASE, MAX_FOOD_LEVEL);
        Consumer<FoodStats> updateFoodLevel = food -> food.setFoodLevel(newFoodLevel.apply(food));
        if(!worldIn.isRemote) {
            entityPlayer.map(EntityPlayer::getFoodStats)
                    .ifPresent(updateFoodLevel);
        }
        entityPlayer.ifPresent(ep->ep.addStat(requireNonNull(StatList.getObjectUseStats(this))));
        if(shouldShrinkStack) {
            if(stack.isEmpty()) {
                return new ItemStack(ModItems.EMPTY_BOTTLE);
            }
            entityPlayer.ifPresent(ep->ep.inventory.addItemStackToInventory(new ItemStack(ModItems.EMPTY_BOTTLE)));
        }

        return stack;
    }

    Optional<EntityPlayer> downcast(EntityLivingBase entityLiving) {
        return entityLiving instanceof EntityPlayer ? Optional.of((EntityPlayer)entityLiving) : Optional.empty();
    }

    Optional<EntityPlayerMP> downcast(EntityPlayer entityPlayer) {
        return entityPlayer instanceof  EntityPlayerMP ? Optional.of((EntityPlayerMP)entityPlayer) : Optional.empty();
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public int colorMultiplier(ItemStack itemStack, int i) {
        return 3932107;
    }

    public void shaderHelper(ResourceLocation resourceLocation1, ResourceLocation resourceLocation2, EntityLivingBase entity) {
        Minecraft.getMinecraft().entityRenderer.loadShader(resourceLocation1);
        scheduler.scheduleAtFixedRate(() -> {
            do {
                Minecraft.getMinecraft().entityRenderer.loadShader(resourceLocation1);
            } while(!potionCheck(entity));
        }, 0, 50, TimeUnit.MILLISECONDS);
        Minecraft.getMinecraft().entityRenderer.loadShader(resourceLocation2);
    }

    public boolean potionCheck(EntityLivingBase entity){
        if (entity.isPotionActive(ModPotions.KoolAid)){
            return true;
        }else {
            return false;
        }
    }
}