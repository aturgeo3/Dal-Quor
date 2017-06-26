package Tamaized.Voidcraft.client.helper;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

public class GUIElementList extends GuiScrollingList {

	private Minecraft mc;
	private GuiContainer parent;
	private ArrayList<GUIListElement> list;
	private GUIListElement selected;

	public GUIElementList(GuiContainer gui, ArrayList<GUIListElement> elements, int x, int y, int listWidth, int listHeight, int slotHeight) {
		super(gui.mc, listWidth, gui.height, y, listHeight, x, slotHeight, gui.width, gui.height);
		mc = gui.mc;
		parent = gui;
		list = elements;
	}

	public GUIListElement getSelected() {
		return selected;
	}

	public int getSelectedIndex() {
		return list.indexOf(selected);
	}

	@Override
	protected int getSize() {
		return list.size();
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		selected = list.get(index);
	}

	@Override
	protected boolean isSelected(int index) {
		return list.get(index) == selected;
	}

	@Override
	protected void drawBackground() {
		// parent.drawDefaultBackground();
	}

	@Override
	protected void drawSlot(int index, int right, int top, int height, Tessellator tess) {
		list.get(index).draw(parent, mc, right, top, height, tess);

	}

	public ArrayList<GUIListElement> getElements() {
		return list;
	}

}
