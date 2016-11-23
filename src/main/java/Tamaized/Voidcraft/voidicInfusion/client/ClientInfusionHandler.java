package Tamaized.Voidcraft.voidicInfusion.client;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ClientInfusionHandler {

	public int amount = 0;
	public int maxAmount = 300;
	private int tick = 0;

	private static ResourceLocation texture = new ResourceLocation(voidCraft.modid, "textures/gui/voidicInfusion.png");

	@SubscribeEvent
	public void update(ClientTickEvent e) {
		if (e.phase == Phase.END && !Minecraft.getMinecraft().isGamePaused()) {
			World world = Minecraft.getMinecraft().world;
			EntityPlayer player = Minecraft.getMinecraft().player;
			if (world == null || player == null) return;
			// if(player.hasCapability(CapabilityList.VOIDICINFUSION, null)){
			// amount = player.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusion();
			// maxAmount = player.getCapability(CapabilityList.VOIDICINFUSION, null).getMaxInfusion();
			// }
			// DebugEvent.textR = tick+" "+amount+" "+maxAmount;
			tick++;
			if (tick % 20 == 0) {
				if (world != null && world.provider != null) {
					boolean flag = true;
					if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == voidCraft.items.voidicSuppressor) {
						IVoidicPowerCapability cap = player.getHeldItemMainhand().getCapability(CapabilityList.VOIDICPOWER, null);
						if (cap != null && cap.getCurrentPower() > 0) {
							//cap.drain(1);
							flag = false;
						}
					} else if (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == voidCraft.items.voidicSuppressor) {
						IVoidicPowerCapability cap = player.getHeldItemOffhand().getCapability(CapabilityList.VOIDICPOWER, null);
						if (cap != null && cap.getCurrentPower() > 0) {
							//cap.drain(1);
							flag = false;
						}
					}
					if (world.provider.getDimension() == voidCraft.config.getDimensionIDvoid() && flag) {
						amount++;
						if (amount > maxAmount) amount = maxAmount;
					} else {
						amount -= 10;
						if (amount < 0) amount = 0;
					}
					float perc = ((float) amount / (float) maxAmount);
					// if(perc>=0.75f) player.capabilities.allowFlying = true;
					// DebugEvent.textL = perc+"";
				}
				tick = 0;
			}
		}
	}

	@SubscribeEvent
	public void InGameOverlay(RenderGameOverlayEvent e) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.player;

		if (e.getType() == e.getType().PORTAL) {
			ScaledResolution scaledRes = new ScaledResolution(mc);
			GlStateManager.disableAlpha();
			GlStateManager.disableDepth();
			GlStateManager.depthMask(false);
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.color(1.0F, 1.0F, 1.0F, (float) amount / (float) maxAmount);
			mc.getTextureManager().bindTexture(texture);

			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer worldrenderer = tessellator.getBuffer();
			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
			worldrenderer.pos(0.0D, (double) scaledRes.getScaledHeight(), -90.0D).tex(0, 1).endVertex();
			worldrenderer.pos((double) scaledRes.getScaledWidth(), (double) scaledRes.getScaledHeight(), -90.0D).tex(1, 1).endVertex();
			worldrenderer.pos((double) scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1, 0).endVertex();
			worldrenderer.pos(0.0D, 0.0D, -90.0D).tex(0, 0).endVertex();
			tessellator.draw();
			GlStateManager.depthMask(true);
			GlStateManager.enableDepth();
			GlStateManager.enableAlpha();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		}

	}

}
