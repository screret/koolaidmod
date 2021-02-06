package koolaidmod.client;

import com.google.gson.JsonSyntaxException;
import koolaidmod.Base;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.client.shader.ShaderLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class ShaderEnable extends RenderGlobal{
    public ShaderEnable() {
        super(Minecraft.getMinecraft());
        this.mc = Minecraft.getMinecraft();
    }

    private ShaderGroup invertShader;
    private Framebuffer invertFramebuffer;

    private final Minecraft mc;

    private static final Logger LOGGER = LogManager.getLogger();

    public void enableInvertShader(){
        this.invertShader.render(1);
    }


    public void MakeInvertShader() {
        if (OpenGlHelper.shadersSupported) {
            if (ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
                ShaderLinkHelper.setNewStaticShaderLinkHelper();
            }

            ResourceLocation resourcelocation = new ResourceLocation("shaders/post/invert.json");

            try {
                this.invertShader = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), resourcelocation);
                this.invertShader.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
                this.invertFramebuffer = this.invertShader.getFramebufferRaw("final");
            } catch (IOException var3) {
                LOGGER.warn("Failed to load shader: {}", resourcelocation, var3);
                this.invertShader = null;
                this.invertFramebuffer = null;
            } catch (JsonSyntaxException var4) {
                LOGGER.warn("Failed to load shader: {}", resourcelocation, var4);
                this.invertShader = null;
                this.invertFramebuffer = null;
            }
        }
    }

    public void renderInvertFramebuffer() {
        if (this.isRenderInvert()) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
            this.invertFramebuffer.framebufferRenderExt(this.mc.displayWidth, this.mc.displayHeight, false);
            GlStateManager.disableBlend();
        }
    }

    protected boolean isRenderInvert() {
        return this.invertFramebuffer != null && this.invertShader != null && this.mc.player != null;
    }
}
