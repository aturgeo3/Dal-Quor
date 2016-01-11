package Tamaized.Voidcraft.mobs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;
import Tamaized.Voidcraft.mobs.entity.boss.bar.RenderBossHeathBar;

@SideOnly(Side.CLIENT)
public class RenderHerobrine extends RenderLiving
{
	private static final ResourceLocation Herobrine_Texture = new ResourceLocation("VoidCraft:textures/entity/Herobrine.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png
	 
    public RenderHerobrine(ModelBase par1ModelBase, float par2)
    {
        super(Minecraft.getMinecraft().getRenderManager(), par1ModelBase, par2);
    }

    public void renderHerobrine(EntityMobHerobrine par1EntityWraith, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1EntityWraith, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderHerobrine((EntityMobHerobrine)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderHerobrine((EntityMobHerobrine)par1Entity, par2, par4, par6, par8, par9);
        RenderBossHeathBar.setCurrentBoss((EntityMobHerobrine)par1Entity);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Herobrine_Texture;
	}
	
	protected void renderLabel(EntityMobHerobrine yourentityLiving, double par2, double par4, double par6)
	{
	int distanceToEntity = 32;//if you're less then 32 blocks x-y-z away from this entity,it will display the entity's name.
	this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
	par4 += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par6);
	}

	public void passSpecialRender(EntityLivingBase par1EntityLiving, double par2, double par4, double par6)
	{
		this.renderLabel((EntityMobHerobrine)par1EntityLiving, par2, par4, par6);
	}
}