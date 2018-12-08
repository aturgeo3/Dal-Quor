package tamaized.dalquor.client.entity.boss.extra;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import tamaized.dalquor.common.entity.boss.twins.EntityTwinsTransform;

import javax.annotation.Nullable;
import java.nio.FloatBuffer;
import java.util.Random;

public class RenderTwinsTransform<T extends EntityTwinsTransform> extends Render<T> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private static final FloatBuffer MODELVIEW = GLAllocation.createDirectFloatBuffer(16);
	private static final FloatBuffer PROJECTION = GLAllocation.createDirectFloatBuffer(16);
	private FloatBuffer buffer = GLAllocation.createDirectFloatBuffer(16);

	public RenderTwinsTransform(RenderManager renderManager) {
		super(renderManager);
	}

	public static void drawBoltSegment(Tessellator tessellator, Vec3d p1, Vec3d p2, float scale, int color) {
		BufferBuilder buffer = tessellator.getBuffer();

		GlStateManager.pushMatrix();
		GlStateManager.translate(p1.x, p1.y, p1.z);

		double dist = p1.distanceTo(p2);
		float xd = (float) (p1.x - p2.x);
		float yd = (float) (p1.y - p2.y);
		float zd = (float) (p1.z - p2.z);
		double var7 = (double) MathHelper.sqrt((double) (xd * xd + zd * zd));
		float rotYaw = (float) (Math.atan2((double) xd, (double) zd) * 180.0D / 3.141592653589793D);
		float rotPitch = (float) (Math.atan2((double) yd, var7) * 180.0D / 3.141592653589793D);

		GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F + rotYaw, 0.0F, 0.0F, -1.0F);
		GlStateManager.rotate(rotPitch, 1.0F, 0.0F, 0.0F);
		GlStateManager.disableCull();

		buffer.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
		for (int i = 0; i <= 9; i++) {
			float f = (i + 1F) / 9F;
			float verX = MathHelper.sin((float) (i % 3) * (float) Math.PI * 2F / (float) 3) * f * scale;
			float verZ = MathHelper.cos((float) (i % 3) * (float) Math.PI * 2F / (float) 3) * f * scale;

			float r = ((color >> 24) & 0xFF) / 255F;
			float g = ((color >> 16) & 0xFF) / 255F;
			float b = ((color >> 8) & 0xFF) / 255F;
			float a = ((color) & 0xFF) / 255F;
			buffer.pos(verX, dist, verZ).color(r, g, b, a).endVertex();
			buffer.pos(verX, 0, verZ).color(r, g, b, a).endVertex();
		}
		tessellator.draw();

		GlStateManager.enableCull();
		GlStateManager.popMatrix();
	}

	public static void renderBoltBetween(Vec3d point1, Vec3d point2, double scale, double maxDeflection, int maxSegments, int color) {
		Tessellator tessellator = Tessellator.getInstance();
		Random random = new Random();

		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 200, 200);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

		double distance = point1.distanceTo(point2);
		Vec3d dirVec = new Vec3d(point2.x - point1.x, point2.y - point1.y, point2.z - point1.z);
		Vec3d invDir = new Vec3d(1, 1, 1).subtract(dirVec);

		Vec3d[] vectors = new Vec3d[maxSegments / 2 + random.nextInt(maxSegments / 2)];

		vectors[0] = point1;
		vectors[vectors.length - 1] = point2;

		for (int i = 1; i < vectors.length - 1; i++) {
			double pos = (i / (double) vectors.length) * distance;

			Vec3d point = new Vec3d(point1.x, point1.y, point1.z);
			point = point.add(new Vec3d(dirVec.x, dirVec.y, dirVec.z).scale(pos));

			double randX = (-0.5 + random.nextDouble()) * maxDeflection * invDir.x;
			double randY = (-0.5 + random.nextDouble()) * maxDeflection * invDir.y;
			double randZ = (-0.5 + random.nextDouble()) * maxDeflection * invDir.z;

			point = point.add(randX, randY, randZ);

			vectors[i] = point;
		}

		double rScale = scale * (0.5 + (random.nextDouble() * 0.5));
		for (int i = 1; i < vectors.length - 1; i++) {
			drawBoltSegment(tessellator, vectors[i - 1], vectors[i], (float) rScale, color);
		}

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);

		renderCatalyst(entity, x, y, z, partialTicks);
		renderWind(entity, x, y, z, partialTicks);

		GlStateManager.popMatrix();

		if (!Minecraft.getMinecraft().isGamePaused() && entity.ticksExisted % 2 == 0 && entity.world.rand.nextInt(3) == 0) {
			int bolts = entity.world.rand.nextInt(3) + 1;
			Vec3d pos = new Vec3d(x, y + 1.0F, z);
			for (int i = 0; i <= bolts; i++) {
				Vec3d rot = new Vec3d(0, 1.5D, 0).rotatePitch((float) Math.toRadians(entity.world.rand.nextInt(360))).rotateYaw((float) Math.toRadians(entity.world.rand.nextInt(360)));
				renderBoltBetween(pos, pos.add(rot), 0.02F, 0.35F, 9, 0x84F0FF);
			}
		}
	}

	private void renderCatalyst(T entity, double x, double y, double z, float partialTicks) {
		GlStateManager.pushMatrix();

		float f = (float) entity.ticksExisted + partialTicks;

		GlStateManager.rotate(f * 4.0F, 0, 1, 0);

		f *= 2.5F;

		bindEntityTexture(entity);
		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.rotate(f, 0, 0, 1);
		GlStateManager.matrixMode(5888);
		GlStateManager.enableBlend();

		Tessellator tess = Tessellator.getInstance();
		BufferBuilder buffer = tess.getBuffer();
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

		GlStateManager.disableLighting();
		GlStateManager.disableCull();

		drawBody(entity, 0, 0, 0, buffer, partialTicks);

		tess.draw();

		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		GlStateManager.matrixMode(5888);

		GlStateManager.disableBlend();
		GlStateManager.enableCull();
		GlStateManager.enableLighting();

		GlStateManager.popMatrix();
	}

	private void renderWind(T entity, double x, double y, double z, float partialTicks) {
		if(!Minecraft.getMinecraft().isGamePaused() && entity.ticksExisted % 5 == 0) {
			ParticleManager effectRenderer = Minecraft.getMinecraft().effectRenderer;
			Vec3d radial = new Vec3d(1, 0, 0).rotateYaw((float) Math.toRadians(entity.world.rand.nextInt(360)));
			Vec3d pos = entity.getPositionVector().add(radial);
			Vec3d dir = radial.rotateYaw((float) Math.toRadians(45));
			effectRenderer.spawnEffectParticle(EnumParticleTypes.CLOUD.getParticleID(), pos.x, pos.y, pos.z, dir.x * 0.05F, 0.05F, dir.z * 0.05F);
			effectRenderer.spawnEffectParticle(EnumParticleTypes.CLOUD.getParticleID(), entity.posX + entity.world.rand.nextFloat() - 0.5F, entity.posY, entity.posZ + entity.world.rand.nextFloat() - 0.5F, 0, 0.005F, 0);
		}
	}

	private void drawBody(T entity, double x, double y, double z, BufferBuilder buffer, float partialTicks) {
		double size = 0.25D;
		double half = (size / 2D);
		double yOffset = 1.0D;

		buffer.pos(x, y + yOffset + size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z - half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z - half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset + size, z).tex(0, 1).color(1F, 1F, 1F, 1F).endVertex();

		buffer.pos(x, y + yOffset + size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z + half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z + half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset + size, z).tex(0, 1).color(1F, 1F, 1F, 1F).endVertex();

		buffer.pos(x, y + yOffset + size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z + half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z - half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset + size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();

		buffer.pos(x, y + yOffset + size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z - half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z + half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset + size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();

		//////////////////////////////////////////////////////////////////////////////

		buffer.pos(x, y + yOffset - size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z - half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z - half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset - size, z).tex(0, 1).color(1F, 1F, 1F, 1F).endVertex();

		buffer.pos(x, y + yOffset - size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z + half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z + half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset - size, z).tex(0, 1).color(1F, 1F, 1F, 1F).endVertex();

		buffer.pos(x, y + yOffset - size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z + half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x + half, y + yOffset, z - half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset - size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();

		buffer.pos(x, y + yOffset - size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z - half).tex(0, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x - half, y + yOffset, z + half).tex(1, 0).color(1F, 1F, 1F, 1F).endVertex();
		buffer.pos(x, y + yOffset - size, z).tex(1, 1).color(1F, 1F, 1F, 1F).endVertex();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}

	private FloatBuffer getBuffer(float p_188193_1_, float p_188193_2_, float p_188193_3_, float p_188193_4_) {
		this.buffer.clear();
		this.buffer.put(p_188193_1_).put(p_188193_2_).put(p_188193_3_).put(p_188193_4_);
		this.buffer.flip();
		return this.buffer;
	}
}
