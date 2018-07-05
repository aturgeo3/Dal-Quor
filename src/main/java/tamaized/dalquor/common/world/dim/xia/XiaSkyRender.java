package tamaized.dalquor.common.world.dim.xia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.IRenderHandler;

import java.nio.FloatBuffer;
import java.util.Random;

public class XiaSkyRender extends IRenderHandler {

	private static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
	private static final Random RANDOM = new Random(31100L);
	private static final FloatBuffer MODELVIEW = GLAllocation.createDirectFloatBuffer(16);
	private static final FloatBuffer PROJECTION = GLAllocation.createDirectFloatBuffer(16);
	private FloatBuffer buffer = GLAllocation.createDirectFloatBuffer(16);

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		double scale = 200.0D;
		double offset = scale / 2D;

		double x = -offset;
		double y = -offset;
		double z = -offset;
		GlStateManager.disableLighting();
		RANDOM.setSeed(31100L);
		GlStateManager.getFloat(2982, MODELVIEW);
		GlStateManager.getFloat(2983, PROJECTION);

		float phase = MathHelper.cos((float) Math.toRadians(((float) Minecraft.getSystemTime() % 40000.0F / 40000.0F) * 360F)) * 0.5F + 0.5F;
		float maxPhases = 8F;
		int minPhases = 3;
		int phases = (int) Math.ceil((phase * maxPhases) / 1F);

		for (int j = 0; j < phases + minPhases; ++j) {
			GlStateManager.pushMatrix();
			float f1 = 2.0F / (float) (18 - j);

			if (j == 0) {
				this.bindTexture(END_SKY_TEXTURE);
				f1 = 0.15F;
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			}

			if (j >= 1) {
				this.bindTexture(END_PORTAL_TEXTURE);
			}

			if (j == 1) {
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			}

			GlStateManager.texGen(GlStateManager.TexGen.S, 9216);
			GlStateManager.texGen(GlStateManager.TexGen.T, 9216);
			GlStateManager.texGen(GlStateManager.TexGen.R, 9216);
			GlStateManager.texGen(GlStateManager.TexGen.S, 9474, this.getBuffer(1.0F, 0.0F, 0.0F));
			GlStateManager.texGen(GlStateManager.TexGen.T, 9474, this.getBuffer(0.0F, 1.0F, 0.0F));
			GlStateManager.texGen(GlStateManager.TexGen.R, 9474, this.getBuffer(0.0F, 0.0F, 1.0F));
			GlStateManager.enableTexGenCoord(GlStateManager.TexGen.S);
			GlStateManager.enableTexGenCoord(GlStateManager.TexGen.T);
			GlStateManager.enableTexGenCoord(GlStateManager.TexGen.R);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.loadIdentity();
			GlStateManager.translate(0.5F, 0.5F, 0.0F);
			GlStateManager.scale(0.5F, 0.5F, 1.0F);
			float f2 = (float) (j + 1);
			GlStateManager.translate(17.0F / f2, (2.0F + f2 / 1.5F) * ((float) Minecraft.getSystemTime() % 800000.0F / 800000.0F), 0.0F);
			GlStateManager.rotate((f2 * f2 * 4321.0F + f2 * 9.0F) * 2.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.scale(4.5F - f2 / 4.0F, 4.5F - f2 / 4.0F, 1.0F);
			GlStateManager.multMatrix(PROJECTION);
			GlStateManager.multMatrix(MODELVIEW);
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder vertexbuffer = tessellator.getBuffer();
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);

			float fade = j < (phases + (minPhases - 1)) ? 1.0F : (phase % (1F / maxPhases) * maxPhases);

			float f3 = (RANDOM.nextFloat() * 0.5F + 0.1F) * f1 * fade;
			float f4 = (RANDOM.nextFloat() * 0.5F + 0.4F) * f1 * fade;
			float f5 = (RANDOM.nextFloat() * 0.5F + 0.5F) * f1 * fade;

			float alpha = 1.0F;

			vertexbuffer.pos(x, y, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y + scale, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x, y + scale, z).color(f3, f4, f5, alpha).endVertex();

			vertexbuffer.pos(x, y + scale, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y + scale, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x, y, z + scale).color(f3, f4, f5, alpha).endVertex();

			vertexbuffer.pos(x, y + scale, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x, y + scale, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x, y, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x, y, z).color(f3, f4, f5, alpha).endVertex();

			vertexbuffer.pos(x + scale, y, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y + scale, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y + scale, z).color(f3, f4, f5, alpha).endVertex();

			vertexbuffer.pos(x, y + scale, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y + scale, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y + scale, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x, y + scale, z + scale).color(f3, f4, f5, alpha).endVertex();

			vertexbuffer.pos(x, y, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y, z + scale).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x + scale, y, z).color(f3, f4, f5, alpha).endVertex();
			vertexbuffer.pos(x, y, z).color(f3, f4, f5, alpha).endVertex();

			GlStateManager.disableFog();
			tessellator.draw();
			GlStateManager.enableFog();
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
			this.bindTexture(END_SKY_TEXTURE);
		}

		GlStateManager.disableBlend();
		GlStateManager.disableTexGenCoord(GlStateManager.TexGen.S);
		GlStateManager.disableTexGenCoord(GlStateManager.TexGen.T);
		GlStateManager.disableTexGenCoord(GlStateManager.TexGen.R);
		GlStateManager.enableLighting();
	}

	private FloatBuffer getBuffer(float p_188193_1_, float p_188193_2_, float p_188193_3_) {
		this.buffer.clear();
		this.buffer.put(p_188193_1_).put(p_188193_2_).put(p_188193_3_).put(0.0F);
		this.buffer.flip();
		return this.buffer;
	}

	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().renderEngine.bindTexture(location);
	}

}
