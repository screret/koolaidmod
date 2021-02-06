package koolaidmod.init;

import koolaidmod.Base;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class CustomPotion extends ItemPotion {
    public CustomPotion() {
        this.setMaxStackSize(1);
        this.setCreativeTab(Base.MOD_TAB);
    }

    Item item;

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
        EntityPlayer lvt_4_1_ = entity instanceof EntityPlayer ? (EntityPlayer)entity : null;
        if (lvt_4_1_ == null || !lvt_4_1_.capabilities.isCreativeMode) {
            stack.shrink(1);
        }

        if (lvt_4_1_ instanceof EntityPlayerMP) {
            CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)lvt_4_1_, stack);
        }

        if (!world.isRemote) {
            List<PotionEffect> lvt_5_1_ = PotionUtils.getEffectsFromStack(stack);
            Iterator var6 = lvt_5_1_.iterator();

            while(var6.hasNext()) {
                PotionEffect effect = (PotionEffect)var6.next();
                if (effect.getPotion().isInstant()) {
                    effect.getPotion().affectEntity(lvt_4_1_, lvt_4_1_, entity, effect.getAmplifier(), 1.0D);
                } else {
                    entity.addPotionEffect(new PotionEffect(effect));
                }
            }
        }

        if (lvt_4_1_ != null) {
            lvt_4_1_.addStat(StatList.getObjectUseStats(this));
        }

        if (lvt_4_1_ == null || !lvt_4_1_.capabilities.isCreativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(this.item);
            }

            if (lvt_4_1_ != null) {
                lvt_4_1_.inventory.addItemStackToInventory(new ItemStack(this.item));
            }
        }



        return stack;
    }

}
