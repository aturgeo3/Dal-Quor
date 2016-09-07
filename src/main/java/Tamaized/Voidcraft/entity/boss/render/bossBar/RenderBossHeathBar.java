/**
 * Original Class file: BossBarHandler
 * Mod: Botania
 * Author: Vazkii
 * 
 * This is a custom version of it therefore I wont claim this file truly as my own.
 */
package Tamaized.Voidcraft.entity.boss.render.bossBar;

import java.awt.Rectangle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.voidCraft;

public class RenderBossHeathBar {
	
	private static IVoidBossData voidBoss;
	
	private static float flashTick = 0;
	private static boolean flashTickFlag = false;
	private static int flashTickWait = 0;
	
	public static void setCurrentBoss(IVoidBossData b){
		voidBoss = b;
	}
	
	public static void render(ScaledResolution res) {
		if(voidBoss == null) return;
		
		Minecraft mc = Minecraft.getMinecraft();
		Rectangle bgRect = new Rectangle(0, 0, 185, 16);
		Rectangle fgRect = new Rectangle(0, bgRect.y + bgRect.height, 181, 10);
		String name = voidBoss.getDisplayName().getFormattedText();
		int c = res.getScaledWidth() / 2;
		int x = c - bgRect.width / 2;
		int y = 20;
		int xf = x + (bgRect.width - fgRect.width) / 2;
		int yf = y + (bgRect.height - fgRect.height) / 2;
		int bossHpPerc = (int) ((double) fgRect.width * voidBoss.getPercentHP());
		int tx = c - mc.getRenderManager().getFontRenderer().getStringWidth(name) / 2;
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		ResourceLocation rl = new ResourceLocation(voidCraft.modid+":textures/gui/WhiteSpace.png");
		if(rl != null) mc.renderEngine.bindTexture(rl);
		
		GL11.glColor4f(0F, 0F, 1F, flashTick);
		drawBar(x, y, 0, bgRect.width, bgRect.height);
		GL11.glColor4f(1F, 0F, 0F, 1F);
		drawBar(xf, yf, 0, fgRect.width, fgRect.height);
		GL11.glColor4f(0F, 1F, 0F, 1F);
		drawBar(xf, yf, 0, bossHpPerc, fgRect.height);
		mc.getRenderManager().getFontRenderer().drawString((int)voidBoss.getHealth()+"/"+(int)voidBoss.getMaxHealth(), xf+1, y + 5, 0x000000);
		
		mc.getRenderManager().getFontRenderer().drawStringWithShadow(name, tx, y - 10, 0xAA00FF);
		
		GL11.glColor4f(1F, 1F, 1F, 1F);//CleanUp
		GL11.glEnable(GL11.GL_BLEND);

		Entity e = (Entity) voidBoss;
		EntityPlayer p = mc.thePlayer;
		if(e.isDead || !p.worldObj.loadedEntityList.contains(e) || pointDistanceSpace(e.posX, e.posY, e.posZ, p.posX, p.posY, p.posZ) > 32) voidBoss = null;
		
		if(flashTickWait <= 0){
			if(flashTickFlag){
				if(flashTick > 0.15){
					flashTick-=0.1;
				}else{
					flashTickFlag = !flashTickFlag;
				}
			}else{
				if(flashTick < 1){
					flashTick+=0.1;
				}else{
					flashTickFlag = !flashTickFlag;
				}
			}
			flashTickWait = 1;
		}else{
			flashTickWait--;
		}
	}
	
	public static float pointDistanceSpace(double x1, double y1, double z1, double x2, double y2, double z2) {
		return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	}
	
	public static void drawBar(int x, int y, int z, int w, int h) {
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldRender = tessellator.getBuffer();
		worldRender.begin(7, DefaultVertexFormats.POSITION);
		worldRender.pos(x+w, y, z).endVertex();
		worldRender.pos(x, y, z).endVertex();
		worldRender.pos(x, y+h, z).endVertex();
		worldRender.pos(x+w, y+h, z).endVertex();
		tessellator.draw();
	}

}
