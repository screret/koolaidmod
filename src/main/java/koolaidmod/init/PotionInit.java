package koolaidmod.init;

import koolaidmod.Base;
import koolaidmod.potions.CustomPotion;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.*;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.asm.transformers.PotionEffectTransformer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jetbrains.annotations.Nullable;

@GameRegistry.ObjectHolder(Base.MODID)
public class PotionInit {
    public PotionInit(){super();}
    public static final Potion KoolAid = new CustomPotion("potion_koolaid", true, 6316287, 0, 0);
    public static final PotionType KoolAidPot = new PotionType("potion_koolaid_item", new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 4800), new PotionEffect(Potion.getPotionFromResourceLocation("wither"), 4800));
    public static final PotionType Long_KoolAidPot = new PotionType("potion_long_koolaid_item", new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 8600), new PotionEffect(Potion.getPotionFromResourceLocation("wither"), 8600));
    public static void registerPotion(){
        registerPotions(KoolAid, KoolAidPot, Long_KoolAidPot);

        AddMix();
    }
    public static void registerPotions(Potion potion, PotionType defaultPotion, PotionType longPotion) {
        //potion.setRegistryName(Base.MODID + ":" + potion.getName());
        defaultPotion.setRegistryName(defaultPotion.getNamePrefixed(Base.MODID));
        longPotion.setRegistryName(longPotion.getNamePrefixed(Base.MODID));
        //ForgeRegistries.POTIONS.register(potion);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }

    public static void AddMix(){
        PotionHelper.addMix(KoolAidPot, Items.REDSTONE, Long_KoolAidPot);
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.KOOL_AID, KoolAidPot);
    }
}
