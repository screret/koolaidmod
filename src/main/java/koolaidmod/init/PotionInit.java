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
    public static final PotionType KoolAidPot = new PotionType("koolaid", new PotionEffect(Potion.getPotionById(9), 2400), new PotionEffect(Potion.getPotionById(15), 2400));
    public static final PotionType Long_KoolAidPot = new PotionType("long_koolaid", new PotionEffect(Potion.getPotionById(9), 4800), new PotionEffect(Potion.getPotionById(15),2400);

    public static void registerPotion(){
        registerPotions(KoolAidPot, Long_KoolAidPot);

        AddMix();
    }
    public static void registerPotions(PotionType defaultPotion, PotionType longPotion) {
        PotionInit.KoolAidPot.setRegistryName("potion_koolaid");
        PotionInit.Long_KoolAidPot.setRegistryName("potion_long_koolaid");
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }

    public static void AddMix(){
        PotionHelper.addMix(KoolAidPot, Items.REDSTONE, Long_KoolAidPot);
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.KOOL_AID, KoolAidPot);
    }
}
