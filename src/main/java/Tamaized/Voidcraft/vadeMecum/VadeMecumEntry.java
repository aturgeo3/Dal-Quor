package Tamaized.Voidcraft.vadeMecum;

import java.util.ArrayList;

public class VadeMecumEntry {

	private ArrayList<VadeMecumPage> pages;

	public VadeMecumEntry(ArrayList<VadeMecumPage> pageList) {
		pages = pageList;
	}

	public int getPages() {
		return pages.size();
	}

	public VadeMecumPage getPage(int pageNumber) {
		return getPages() > 0 ? pageNumber > getPages() - 1 ? pages.get(0) : pages.get(pageNumber) : null;
	}

	public void render() {

	}

}
