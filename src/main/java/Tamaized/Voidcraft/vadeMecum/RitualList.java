package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Ritual ItemStack arrays start from the bottom layer
 */
public class RitualList {

	public final ItemStack[] Intro;
	public final ItemStack[] PowerIntro;

	public RitualList() {
		Intro = new ItemStack[] {

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(voidCraft.blocks.ritualBlock), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY };
		PowerIntro = new ItemStack[] {

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				new ItemStack(Blocks.COAL_BLOCK), new ItemStack(voidCraft.blocks.ritualBlock), new ItemStack(Blocks.GLOWSTONE),

				ItemStack.EMPTY, new ItemStack(Blocks.PRISMARINE, 1, 1), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY };
	}

}
