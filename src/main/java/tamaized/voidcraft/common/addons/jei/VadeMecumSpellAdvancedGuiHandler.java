package tamaized.voidcraft.common.addons.jei;

import mezz.jei.api.gui.IAdvancedGuiHandler;
import tamaized.voidcraft.client.gui.VadeMecumSpellsGUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VadeMecumSpellAdvancedGuiHandler implements IAdvancedGuiHandler<VadeMecumSpellsGUI> {

	@Override
	public Class<VadeMecumSpellsGUI> getGuiContainerClass() {
		return VadeMecumSpellsGUI.class;
	}

	@Override
	public List<Rectangle> getGuiExtraAreas(VadeMecumSpellsGUI instance) {
		List<Rectangle> list = new ArrayList<Rectangle>();
		list.add(new Rectangle(0, 0, instance.width, instance.height - (instance.getYSize()) - 10));
		return list;
	}

	@Override
	public Object getIngredientUnderMouse(VadeMecumSpellsGUI guiContainer, int mouseX, int mouseY) {
		return null;
	}

}
