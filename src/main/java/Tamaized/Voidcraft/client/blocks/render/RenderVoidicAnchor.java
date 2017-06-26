package Tamaized.Voidcraft.client.blocks.render;

import Tamaized.TamModized.particles.FX.ParticleFluff;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.client.blocks.model.ModelVoidicCharger;
import Tamaized.Voidcraft.client.event.ClientRenderTicker;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicAnchor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderVoidicAnchor extends TileEntitySpecialRenderer<TileEntityVoidicAnchor> {

	private static final ResourceLocation texture = new ResourceLocation(VoidCraft.modid, "textures/models/blocks/voidiccharger.png");

	private final ModelVoidicCharger model;

	public RenderVoidicAnchor() {
		this.model = new ModelVoidicCharger();
	}

	@Override
	public void render(TileEntityVoidicAnchor te, double x, double y, double z, float partialTicks, int destroyStage, float p_192841_10_) {
		GL11.glPushMatrix();
		{
			GlStateManager.disableLighting();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			GL11.glPushMatrix();
			{
				GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
				float f = (float) ClientRenderTicker.tick + partialTicks;
				this.model.render((Entity) null, 0.0F, f * 1.0f, -0.1F, 0.0F, 0.0F, 0.0625F, 0, -1.0D, 0);
			}
			GL11.glPopMatrix();
			GlStateManager.enableLighting();
		}
		GL11.glPopMatrix();
		World world = te.getWorld();
		if (world == null || Minecraft.getMinecraft().isGamePaused()) return;
		Random rand = te.getWorld().rand;
		double distance = 0.35D;
			Vec3d vec = new Vec3d(distance, distance, distance).rotatePitch(rand.nextInt(360)).rotateYaw(rand.nextInt(360));
			float speed = 0.04F;
			Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFluff(world, new Vec3d(te.getPos()).addVector(0.5, 1.35F, 0.5).add(vec), new Vec3d(-vec.x * speed, -vec.y * speed, -vec.z * speed), 7, 0, rand.nextFloat() * 0.90F + 0.10F, 0x7700FFFF));
		if(rand.nextInt(10) == 0)Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleFluff(world, new Vec3d(te.getPos()).addVector(0.5, 0.99F, 0.5), new Vec3d(0, 0, 0), 100, 0.01F, rand.nextFloat() * 0.50F + 0.10F, 0x7700FFFF));
	}

}
