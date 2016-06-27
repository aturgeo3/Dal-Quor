package Tamaized.Voidcraft.GUI.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.GUI.server.HeimdallContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;

@SideOnly(Side.CLIENT)
public class HeimdallGUI extends GuiContainer {
	
	public static int gleft;
	public static int gtop;
	
	public TileEntityHeimdall te;
	
	private static final ResourceLocation daTexture = new ResourceLocation(voidCraft.modid, "textures/gui/heimdall.png");

	public HeimdallGUI (InventoryPlayer inventoryPlayer, TileEntityHeimdall tileEntity) {
		super(new HeimdallContainer(inventoryPlayer, tileEntity));
		te = tileEntity;
		xSize = 347;
		ySize = 320;
		gleft = guiLeft;
		gtop = guiTop;
	}

	@Override
	public void updateScreen(){
		
		{
			float scale = 46;
			int k = (int) (((float)te.getFluidAmount()/(float)te.getMaxFluidAmount())*scale);
			drawTexturedModalRect(guiLeft+93, guiTop+131 - k, 0, 497-(k), 12, k+1); 
		}
			
		super.updateScreen();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = "Heimdall has opened a Gate";
		fontRendererObj.drawString(text, xSize/2 - fontRendererObj.getStringWidth(text) / 2, ySize-260, 4210752);
		text = te.getFluidAmount()+"/"+te.getMaxFluidAmount()+"mb";
		fontRendererObj.drawString(text, (xSize/12 - fontRendererObj.getStringWidth(text) / 12)-5, ySize-220, 4210752);
		text = "Generated";
		fontRendererObj.drawString(text, (xSize/12 - fontRendererObj.getStringWidth(text) / 12)-5, ySize-200, 4210752);
		}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();{
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
			drawTexturedModalRect(guiLeft+78, guiTop+66, 0, 0, xSize/2, ySize/2);
			this.updateScreen();
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}
	
	


}
