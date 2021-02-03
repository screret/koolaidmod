package koolaidmod.init;

import koolaidmod.Base;
import koolaidmod.potions.CustomPotion;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Base.MODID)
public class PotionInit {
    public PotionInit(){super();}
    public static final Potion KoolAid = new CustomPotion("Kool_Aid", false, 5, 0, 0).registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, MathHelper.getRandomUUID().toString(), 2d, 0);
    public static final PotionType KoolAidPot = new PotionType("KoolAid", new PotionEffect(new PotionEffect(KoolAid, 2400)));
    public static final PotionType Long_KoolAidPot = new PotionType("long_KoolAid", new PotionEffect(new PotionEffect(KoolAid, 4800)));

    public static void registerPotion(){
        registerPotions(KoolAidPot, Long_KoolAidPot, KoolAid);

        AddMix();
    }
    public static void registerPotions(PotionType defaultPotion, PotionType longPotion, Potion effect) {
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }

    public static void AddMix(){
        PotionHelper.addMix(KoolAidPot, Items.REDSTONE, Long_KoolAidPot);
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.KOOL_AID, KoolAidPot);
    }
}
