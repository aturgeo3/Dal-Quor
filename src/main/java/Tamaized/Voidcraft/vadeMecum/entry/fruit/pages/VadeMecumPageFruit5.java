package Tamaized.Voidcraft.vadeMecum.entry.fruit.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingAlchemy;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;

public class VadeMecumPageFruit5 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingAlchemy("Alchemy Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.etherealFruit),
			new ItemStack(voidCraft.items.etherealFruit_redstone),
			new ItemStack(voidCraft.items.etherealFruit_lapis),
			new ItemStack(voidCraft.items.etherealFruit_gold),
			new ItemStack(voidCraft.items.etherealFruit_emerald),
			new ItemStack(voidCraft.items.etherealFruit_diamond) }, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), voidCraft.potions.type_voidImmunity));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x + craftXoffset, y);
	}

}
