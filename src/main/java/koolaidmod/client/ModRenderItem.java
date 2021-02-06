package koolaidmod.client;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.PotionColorCalculationEvent;

import java.util.Collection;

public class ModRenderItem extends PotionColorCalculationEvent{

    PotionColorCalculationEvent event;

    public ModRenderItem(EntityLivingBase entity, int color, boolean hideParticle, Collection<PotionEffect> effectList) {
        super(entity, color, hideParticle, effectList);
        color = 3932107;
    }
}
