package tamaized.voidcraft.client.entity.boss.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.client.entity.boss.bossbar.RenderBossHeathBar;
import tamaized.voidcraft.common.entity.boss.EntityBossCorruptedPawn;

@SideOnly(Side.CLIENT)
public class RenderCorruptedPawn<T extends EntityLiving> extends RenderLiving<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/entity/corruptedpawn.png");

	public RenderCorruptedPawn(RenderManager manager, ModelBase par1ModelBase, float par2) {
		super(manager, par1ModelBase, par2);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float ticks) {
		super.doRender(entity, x, y, z, yaw, ticks);
		this.renderLabel(entity, x, y, z);
		RenderBossHeathBar.setCurrentBoss((EntityBossCorruptedPawn) entity);
	}

	private void renderLabel(T yourentityLiving, double par2, double par4, double par6) {
		int distanceToEntity = 32;
		this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}
}