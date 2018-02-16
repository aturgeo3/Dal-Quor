package tamaized.dalquor.common.vademecum;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.client.gui.VadeMecumGUI;
import tamaized.dalquor.proxy.ClientProxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class VadeMecumEntry {

	private final static Map<String, VadeMecumEntry> map = new HashMap<>();

	public static VadeMecumEntry getEntry(String entry) {
		return map.get(entry);
	}

	public static String getEntry(VadeMecumEntry entry) {
		for (Entry<String, VadeMecumEntry> e : map.entrySet()) {
			if (e.getValue() == entry)
				return e.getKey();
		}
		return null;
	}

	private final String title;
	private final IVadeMecumPageProvider pages;
	private final Map<Integer, List<VadeMecumButton>> buttons = new HashMap<>();
	private final VadeMecumEntry backPage;

	public VadeMecumEntry(String registryName, String title, VadeMecumEntry back, IVadeMecumPageProvider pageList) {
		map.put(registryName, this);
		this.title = I18n.format(title);
		pages = pageList;
		backPage = back;
		initObjects();
	}

	public void initObjects() {

	}

	public void init(VadeMecumGUI gui) {

	}

	protected final void clearButtons() {
		buttons.clear();
	}

	protected final void addButton(VadeMecumGUI gui, int buttonId, String buttonText, ItemStack stack) {
		final int w = 100;
		final int h = 20;
		final int xPad = gui.getX() + 48;
		final int yPad = gui.getY() + 35;
		final int xMulti = 170;
		final int yMulti = 25;
		int page = (buttons.size() - 1);
		if (page >= 0) {
			if (!buttons.containsKey(page)) {
				// Something when very wrong, spit out map contents and don't add the button.
				VoidCraft.instance.logger.error("A null page was recieved from a Vade Mecum Button List (" + page + "): " + buttons.entrySet());
				return;
			}
			if (buttons.get(page).size() >= 6) {
				buttons.put(page + 1, new ArrayList<>());
				buttons.get(page + 1).add(new VadeMecumButton(gui, buttonId, xPad + (xMulti * ((page + 1) % 2)), yPad, 100, 20, buttonText, stack));
			} else {
				int size = buttons.get(page).size();
				buttons.get(page).add(new VadeMecumButton(gui, buttonId, xPad + (xMulti * (page % 2)), yPad + (yMulti * (size % 6)), 100, 20, buttonText, stack));
			}
		} else {
			buttons.put(0, new ArrayList<>());
			buttons.get(0).add(new VadeMecumButton(gui, buttonId, xPad, yPad, 100, 20, buttonText, stack));
		}
	}

	public final void mouseClicked(VadeMecumGUI gui, int pageNumber, int mouseX, int mouseY, int mouseButton) throws IOException {
		if (mouseButton == 0) {
			if (buttons.containsKey(pageNumber)) {
				for (VadeMecumButton button : buttons.get(pageNumber)) {
					if (button.mousePressed(gui.mc, mouseX, mouseY)) {
						button.playPressSound(gui.mc.getSoundHandler());
						actionPerformed(gui, button.id, mouseButton);
					}
				}
			}
			if (buttons.containsKey(pageNumber + 1)) {
				for (VadeMecumButton button : buttons.get(pageNumber + 1)) {
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

	public final void goBack(VadeMecumGUI gui) {
		gui.changeEntry(backPage == null ? ClientProxy.vadeMecumEntryList : backPage);
	}

	protected void actionPerformed(VadeMecumGUI gui, int id, int mouseButton) {

	}

	public final void render(VadeMecumGUI gui, FontRenderer render, int mX, int mY, int x, int y, int page) {
		VadeMecumGUI.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 198, y + 15, 0x000000);
		renderPages(gui, render, mX, mY, x, y, page);
		renderButtons(gui, render, mX, mY, x, y, page);
	}

	private void renderPages(VadeMecumGUI gui, FontRenderer render, int mX, int mY, int x, int y, int page) {
		IVadeMecumPage[] pageList = pages == null ? null : pages.getPageList(gui.getPlayerStats());
		if (pageList != null && pageList.length > page) {
			pageList[page].render(gui, render, x + 50, y + 20, mX, mY, 0);
			if (page + 1 < getPageLength(gui))
				pageList[page + 1].render(gui, render, x + 285, y + 20, mX, mY, -70);
		}
	}

	private void renderButtons(VadeMecumGUI gui, FontRenderer render, int mX, int mY, int x, int y, int page) {
		if (buttons.containsKey(page)) {
			for (VadeMecumButton button : buttons.get(page)) {
				button.drawButton(gui.mc, mX, mY, 0);
			}
		}
		if (buttons.containsKey(page + 1)) {
			for (VadeMecumButton button : buttons.get(page + 1)) {
				button.drawButton(gui.mc, mX, mY, 0);
			}
		}
	}

	public final int getPageLength(VadeMecumGUI gui) {
		return pages == null ? buttons.isEmpty() ? 0 : buttons.size() : pages.getPageList(gui.getPlayerStats()).length;
	}

}
