package Tamaized.Voidcraft.mobs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobVoidBoss;
import Tamaized.Voidcraft.mobs.model.ModelVoidBoss;

@SideOnly(Side.CLIENT)
public class RenderVoidBoss extends RenderLiving
{
	
	 private static final ResourceLocation VoidBoss_Texture = new ResourceLocation("VoidCraft:textures/entity/zVoidBoss.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png
	    
	 private int field_82419_a;

	    public RenderVoidBoss()
	    {
	        super(Minecraft.getMinecraft().getRenderManager(), new ModelVoidBoss(), 1.0F);
	        
	    }
	    
	    public void func_82418_a(EntityMobVoidBoss par1EntityWither, double par2, double par4, double par6, float par8, float par9)
	    {
	        BossStatus.setBossStatus(par1EntityWither, true);
	       

	        super.doRender(par1EntityWither, par2, par4, par6, par8, par9);
	    }
	    
	    
    public RenderVoidBoss(ModelBase par1ModelBase, float par2)
    {
        super(Minecraft.getMinecraft().getRenderManager(), par1ModelBase, par2);
    }

    public void renderVoidBoss(EntityMobVoidBoss par1EntityMobSpectreChain, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityMobSpectreChain, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderVoidBoss((EntityMobVoidBoss)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
    	this.func_82418_a((EntityMobVoidBoss)par1Entity, par2, par4, par6, par8, par9);
        this.renderVoidBoss((EntityMobVoidBoss)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return VoidBoss_Texture;
	}
}