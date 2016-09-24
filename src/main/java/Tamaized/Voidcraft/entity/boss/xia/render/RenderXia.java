package Tamaized.Voidcraft.entity.boss.xia.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.render.bossBar.RenderBossHeathBar;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.model.ModelXia;

@SideOnly(Side.CLIENT)
public class RenderXia<T extends EntityBossXia> extends RenderLiving<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(voidCraft.modid + ":textures/entity/Xia.png");

	public RenderXia(ModelBase par1ModelBase, float par2) {
		super(Minecraft.getMinecraft().getRenderManager(), par1ModelBase, par2);
		this.addLayer(new LayerBipedArmor(this));
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float ticks) {
		super.doRender(entity, x, y, z, yaw, ticks);
		renderSpecials(entity);
		this.renderLabel(entity, x, y, z);
		RenderBossHeathBar.setCurrentBoss(entity);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

	@Override
	public ModelXia getMainModel() {
		return (ModelXia) super.getMainModel();
	}

	protected void renderLabel(T entity, double x, double y, double z) {
		// y += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * z);
		this.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z, 32);
	}

	private void renderSpecials(EntityBossXia xia) {
		switch (xia.getAction()) {
			case IDLE:
				break;
			case SWORD_PROJECTION_RIGHT:
				renderRightSwordProjection(xia);
				break;
			default:
				break;
		}
	}

	private void renderRightSwordProjection(EntityBossXia xia) {
		double swordLength = 30;

		double xCoord = xia.posX + swordLength * -Math.cos(Math.toRadians(xia.rightArmYaw)) * Math.sin(Math.toRadians(90));
		double zCoord = xia.posY + swordLength * -Math.sin(Math.toRadians(xia.rightArmYaw)) * Math.sin(Math.toRadians(90));
		double yCoord = xia.posZ + swordLength * Math.cos(Math.toRadians(90));

		Vec3d vecResult = new Vec3d(xCoord, yCoord, zCoord);
		Vec3d vecOrigin = new Vec3d(xia.posX, xia.posY, xia.posZ);

		double xAngle = -Math.cos(Math.toRadians(90.0D + xia.rightArmYaw)) * Math.sin(xia.rightArmPitch);
		double yAngle = 1;//Math.sin(xia.rightArmPitch);
		double zAngle = -Math.sin(Math.toRadians(90.0D + xia.rightArmYaw)) * Math.sin(xia.rightArmPitch);

		GlStateManager.pushAttrib();
		{
			GlStateManager.pushMatrix();
			{
				Tessellator tessellator = Tessellator.getInstance();
				VertexBuffer vertexbuffer = tessellator.getBuffer();
				vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
				{
					drawRect(vertexbuffer, vecOrigin, vecOrigin, Face.SIDE, false, xAngle, yAngle, zAngle, 2);
					drawRect(vertexbuffer, vecOrigin, vecResult, Face.SIDE, false, xAngle, yAngle, zAngle, 2);
					drawRect(vertexbuffer, vecOrigin, vecResult, Face.SIDE, true, xAngle, yAngle, zAngle, 2);
					drawRect(vertexbuffer, vecResult, vecResult, Face.SIDE, false, xAngle, yAngle, zAngle, 2);
					drawRect(vertexbuffer, vecOrigin, vecResult, Face.BOTTOM, false, xAngle, yAngle, zAngle, 2);
					drawRect(vertexbuffer, vecOrigin, vecResult, Face.TOP, false, xAngle, yAngle, zAngle, 2);
					
				}
				tessellator.draw();
			}
			GlStateManager.popMatrix();
		}
		GlStateManager.popAttrib();
	}
	
	private void drawSword(VertexBuffer buffer){
		
	}

	private enum Face {
		SIDE, TOP, BOTTOM
	}

	private void drawRect(VertexBuffer vertexbuffer, Vec3d vec1, Vec3d vec2, Face face, boolean flip, double xAngle, double yAngle, double zAngle, double range) {
		if (vec1.equals(vec2)) {
			drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, false, true);
			drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, false, false);
			drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, true, false);
			drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, true, true);
		} else {
			switch (face) {
				case SIDE:
					drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, flip, true);
					drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, flip, false);
					drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, flip, false);
					drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, flip, true);
					break;
				case TOP:
					drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, false, false);
					drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, true, false);
					drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, true, false);
					drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, false, false);
					break;
				case BOTTOM:
					drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, false, true);
					drawVertex(vertexbuffer, vec1, 20, xAngle, yAngle, zAngle, true, true);
					drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, true, true);
					drawVertex(vertexbuffer, vec2, 20, xAngle, yAngle, zAngle, false, true);
					break;
				default:
					break;
			}
		}
	}

	private void drawVertex(VertexBuffer vertexbuffer, Vec3d vec, double range, double xAngle, double yAngle, double zAngle, boolean xzPos, boolean yPos) {
		double x = (vec.xCoord + ((xzPos ? 1 : -1) * (range * xAngle)));
		double y = (vec.yCoord + ((yPos ? 1 : -1) * (range * yAngle)));
		double z = (vec.zCoord + ((xzPos ? 1 : -1) * (range * zAngle)));
		vertexbuffer.pos(x, y, z).color(0.0f, 0.0f, 1.0f, 1.0F).endVertex();
	}
}
