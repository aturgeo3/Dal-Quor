package Tamaized.Voidcraft.entity.boss.xia.render;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderBossHeathBar;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderXia<T extends EntityBossXia> extends RenderLiving<T>{
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(voidCraft.modid+":textures/entity/Xia.png");
	 
    public RenderXia(ModelBase par1ModelBase, float par2){
        super(Minecraft.getMinecraft().getRenderManager(), par1ModelBase, par2);
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float yaw, float ticks){
    	super.doRender(entity, x, y, z, yaw, ticks);
		this.renderLabel(entity, x, y, z);
        RenderBossHeathBar.setCurrentBoss(entity);
    }

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}
	
	protected void renderLabel(T entity, double x, double y, double z){
		y += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * z);
		this.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z, 32);
	}
}

