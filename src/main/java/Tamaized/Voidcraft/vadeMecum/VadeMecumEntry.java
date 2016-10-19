package Tamaized.Voidcraft.vadeMecum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.events.client.DebugEvent;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumEntry {
	
	private static Map<String, VadeMecumEntry> map = new HashMap<String, VadeMecumEntry>();
	
	public static VadeMecumEntry getEntry(String entry){
		return map.get(entry);
	}
	
	public static String getEntry(VadeMecumEntry entry){
		for(Entry<String, VadeMecumEntry> e : map.entrySet()){
			if(e.getValue() == entry) return e.getKey();
		}
		return null;
	}

	private final String title;
	private final IVadeMecumPageProvider pages;
	private List<VadeMecumButton> buttons = new ArrayList<VadeMecumButton>();
	private List<VadeMecumButton> buttons_2 = new ArrayList<VadeMecumButton>();
	private final VadeMecumEntry backPage;

	public VadeMecumEntry(String registryName, String title, VadeMecumEntry back, IVadeMecumPageProvider pageList) {
		map.put(registryName, this);
		this.title = title;
		pages = pageList;
		backPage = back;
		initObjects();
	}

	public void initObjects() {

	}

	public void init(VadeMecumGUI gui) {

	}

	protected void clearButtons() {
		buttons.clear();
		buttons_2.clear();
	}

	protected void addButton(VadeMecumButton b) {
		buttons.add(b);
	}

	protected void addButton2(VadeMecumButton b) {
		buttons_2.add(b);
	}

	public void mouseClicked(VadeMecumGUI gui, int pageNumber, int mouseX, int mouseY, int mouseButton) throws IOException {
		if (mouseButton == 0) {
			if (pageNumber < 2) {
				Iterator<VadeMecumButton> iter = buttons.iterator();
				while (iter.hasNext()) {
					VadeMecumButton button = iter.next();
					if (button.mousePressed(gui.mc, mouseX, mouseY)) {
						button.playPressSound(gui.mc.getSoundHandler());
						actionPerformed(gui, button.id, mouseButton);
					}
				}
			} else {
				Iterator<VadeMecumButton> iter = buttons_2.iterator();
				while (iter.hasNext()) {
					VadeMecumButton button = iter.next();
					if (button.mousePressed(gui.mc, mouseX, mouseY)) {
						button.playPressSound(gui.mc.getSoundHandler());
						actionPerformed(gui, button.id, mouseButton);
					}
				}
			}
		} else if (mouseButton == 1) {
			goBack(gui);
		}
	}

	public void goBack(VadeMecumGUI gui) {
		if (backPage != null) gui.changeEntry(backPage);
	}

	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {

	}

	public void render(VadeMecumGUI gui, FontRenderer render, int mX, int mY, int x, int y, int page) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 198, y + 15, 0x000000);
		renderPages(gui, render, mX, mY, x, y, page);
		renderButtons(gui, render, mX, mY, x, y, page);
	}

	protected void renderPages(VadeMecumGUI gui, FontRenderer render, int mX, int mY, int x, int y, int page) {
		IVadeMecumPage[] pageList = pages == null ? null : pages.getPageList(gui.getPlayerStats());
		if (pageList != null) {
			pageList[page].render(gui, render, x + 50, y + 20, mX, mY, 0);
			if (page + 1 < getPageLength(gui)) pageList[page + 1].render(gui, render, x + 285, y + 20, mX, mY, -70);
		}
	}

	protected void renderButtons(VadeMecumGUI gui, FontRenderer render, int mX, int mY, int x, int y, int page) {
		if (page < 2) {
			for (VadeMecumButton button : buttons) {
				button.drawButton(gui.mc, mX, mY);
			}
		} else {
			for (VadeMecumButton button : buttons_2) {
				button.drawButton(gui.mc, mX, mY);
			}
		}
	}

	public int getPageLength(VadeMecumGUI gui) {
		return pages == null ? 0 : pages.getPageList(gui.getPlayerStats()).length;
	}

}
