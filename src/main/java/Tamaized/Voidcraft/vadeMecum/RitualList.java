package Tamaized.Voidcraft.vadeMecum;

import java.util.HashMap;
import java.util.Map;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * Ritual ItemStack arrays start from the bottom layer
 */
public class RitualList {

	private final Map<IVadeMecumCapability.Category, ItemStack[]> map;

	public ItemStack[] getRitual(IVadeMecumCapability.Category cat) {
		return map.get(cat);
	}

	public RitualList() {
		map = new HashMap<IVadeMecumCapability.Category, ItemStack[]>();
		map.put(IVadeMecumCapability.Category.INTRO, new ItemStack[] {

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

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.TOME, new ItemStack[] {

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

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.Flame, new ItemStack[] {

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				new ItemStack(Blocks.MAGMA), new ItemStack(voidCraft.blocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.Freeze, new ItemStack[] {

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.SNOW), new ItemStack(voidCraft.blocks.ritualBlock), new ItemStack(Blocks.SNOW),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.Shock, new ItemStack[] {

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_STONE), new ItemStack(voidCraft.blocks.ritualBlock), new ItemStack(Blocks.END_STONE),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
		map.put(IVadeMecumCapability.Category.AcidSpray, new ItemStack[] {

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				new ItemStack(Blocks.DIRT), new ItemStack(voidCraft.blocks.ritualBlock), new ItemStack(Blocks.DIRT),

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY });
	}

}
