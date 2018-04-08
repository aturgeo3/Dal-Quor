package tamaized.dalquor.client.entity.boss.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.client.entity.boss.bossbar.RenderBossHeathBar;
import tamaized.dalquor.common.entity.boss.herobrine.EntityBossHerobrine;

@SideOnly(Side.CLIENT)
public class RenderHerobrine<T extends EntityBossHerobrine> extends RenderLiving<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(DalQuor.modid, "textures/entity/herobrine.png");

	public RenderHerobrine(RenderManager manager, ModelBase par1ModelBase, float par2) {
		super(manager, par1ModelBase, par2);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float ticks) {
		super.doRender(entity, x, y, z, yaw, ticks);
		this.renderLabel(entity, x, y, z);
		RenderBossHeathBar.setCurrentBoss(entity);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

	private void renderLabel(T yourentityLiving, double par2, double par4, double par6) {
		int distanceToEntity = 32;
		this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
		par4 += (float) this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par6;
	}
}