package Tamaized.Voidcraft.GUI.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import Tamaized.Voidcraft.GUI.server.VoidicPowerGenContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;

public class VoidicPowerGenGUI extends GuiContainer {
	
	public TileEntityVoidicPowerGen te;
	
	private static final ResourceLocation daTexture = new ResourceLocation(voidCraft.modid, "textures/gui/voidGen.png");
	
	public VoidicPowerGenGUI (InventoryPlayer inventoryPlayer, TileEntityVoidicPowerGen tileEntity) {
		super(new VoidicPowerGenContainer(inventoryPlayer, tileEntity));
		this.te = tileEntity;
		this.xSize = 347;
		this.ySize = 320;
	}
	
	public void updateScreen(){
		
		{
			float scale = 50;
			int k = (int) (((float)te.getFluidAmount()/(float)te.getMaxFluidAmount())*scale);
			drawTexturedModalRect(guiLeft+93, guiTop+134 - k, 0, 470-(k), 12, k+1); 
		}
		
		{
			float scale = 47;
			int k = (int) (((float)te.getPowerAmount()/(float)te.getMaxPower())*scale);
			drawTexturedModalRect(guiLeft+196, guiTop+133 - k, 12, 470-(k), 12, k+1); 
		}
			
		super.updateScreen();
	}
	
	public void actionPerformed(GuiButton button) {
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String text = "Voidic Power Generator";
		this.fontRendererObj.drawString(text, this.xSize/2 - this.fontRendererObj.getStringWidth(text) / 2, this.ySize-260, 0xAAAAAA);
		text = "Fluid:";
		this.fontRendererObj.drawString(text, (this.xSize/2 - this.fontRendererObj.getStringWidth(text) / 2) - 100, this.ySize/2 - 65, 0x7700FF);
		text = te.getFluidAmount()+"/";
		this.fontRendererObj.drawString(text, (this.xSize/2 - this.fontRendererObj.getStringWidth(text) / 1) - 85, this.ySize/2 - 55, 0x7700FF);
		text = ""+te.getMaxFluidAmount();
		this.fontRendererObj.drawString(text, (this.xSize/2 - this.fontRendererObj.getStringWidth(text) / 1) - 85, this.ySize/2 - 45, 0x7700FF);
		text = "Voidic Power:";
		this.fontRendererObj.drawString(text, (this.xSize/2 - this.fontRendererObj.getStringWidth(text) / 2) + 70, this.ySize/2 - 65, 0xFF0000);
		text = te.getPowerAmount()+"/";
		this.fontRendererObj.drawString(text, (this.xSize/2) + 38, this.ySize/2 - 55, 0xFF0000);
		text = ""+te.getMaxPower();
		this.fontRendererObj.drawString(text, (this.xSize/2) + 38, this.ySize/2 - 45, 0xFF0000);
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
