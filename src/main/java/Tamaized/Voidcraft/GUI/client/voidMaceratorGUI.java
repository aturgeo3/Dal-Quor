package Tamaized.Voidcraft.GUI.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.I18n;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.GUI.server.VoidMaceratorContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

public class voidMaceratorGUI extends GuiContainer {
	
	public TileEntityVoidMacerator voidMacerator;
	
	private static final ResourceLocation daTexture = new ResourceLocation(voidCraft.modid, "textures/gui/voidMacerator.png");

	public voidMaceratorGUI (InventoryPlayer inventoryPlayer, TileEntityVoidMacerator tileEntity) {
		super(new VoidMaceratorContainer(inventoryPlayer, tileEntity));
		
		this.voidMacerator = tileEntity;
		
		this.xSize = 347;
		this.ySize = 320;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		
		String name = "Void Infused Macerator";//this.voidMacerator.isInvNameLocalized() ? this.voidMacerator.getInvName() : this.voidMacerator.getInvName();
		
		this.fontRendererObj.drawString("Void Infused Macerator", this.xSize/2 - this.fontRendererObj.getStringWidth(name) / 2, this.ySize-260, 4210752);//this.xSize/2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		//this.fontRendererObj.drawString("container.inventory", 8, this.ySize-96 + 2, 4210752);
		
		this.fontRendererObj.drawString(this.voidMacerator.burnTime+"/3000mB", (this.xSize/12 - this.fontRendererObj.getStringWidth(name) / 12)-5, this.ySize-220, 4210752);
		}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(daTexture);
		drawTexturedModalRect(guiLeft+78, guiTop+66, 0, 0, xSize/2, ySize/2);
		
		if(this.voidMacerator.isBurning()){
			int k = this.voidMacerator.getBurnTimeRemainingScaled(50); //Use this as height
			drawTexturedModalRect(guiLeft+93, guiTop+134 - k, 0, 500-(k), 20, k+1); //PosX, PosY, Texture Real PosX, Texture Real PosY, width, height (Width/Height: if has 'k+1' or 'k' = do not touch! [Leave it as K+1 or K!!!])
		}
		
		int k = this.voidMacerator.getCookProgressScaled(26);
		drawTexturedModalRect(guiLeft+188, guiTop+99, 0, 434, k+1, 16);
		
		
	}


}
