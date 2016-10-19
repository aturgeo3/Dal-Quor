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

				null, null, null,

				null, new ItemStack(voidCraft.blocks.ritualBlock), null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null };
		PowerIntro = new ItemStack[] {

				null, new ItemStack(Blocks.MAGMA), null,

				new ItemStack(Blocks.COAL_BLOCK), new ItemStack(voidCraft.blocks.ritualBlock), new ItemStack(Blocks.GLOWSTONE),

				null, new ItemStack(Blocks.PRISMARINE, 1, 1), null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null,

				//////////////////////////////////////////////////////////////////////////////////////////

				null, null, null,

				null, null, null,

				null, null, null };
	}

}
