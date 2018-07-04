package tamaized.dalquor.common.vademecum.progression;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.registry.ModBlocks;

import java.util.HashMap;
import java.util.Map;

/**
 * Ritual ItemStack arrays start from the bottom layer
 */
public class RitualList {

	private final Map<IVadeMecumCapability.Category, ItemStack[]> map;

	public ItemStack[] getRitual(IVadeMecumCapability.Category cat) {
		return map.get(cat);
	}

	public RitualList() {
		map = new HashMap<>();
		map.put(IVadeMecumCapability.Category.INTRO, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(ModBlocks.ritualBlock), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.TOME, new ItemStack[]{

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				new ItemStack(Blocks.COAL_BLOCK), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.GLOWSTONE),

				ItemStack.EMPTY, new ItemStack(Blocks.PRISMARINE, 1, 1), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.Flame, new ItemStack[]{

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				new ItemStack(Blocks.MAGMA), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.NETHERRACK),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.FireSheathe, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA)});
		map.put(IVadeMecumCapability.Category.Fireball, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(ModBlocks.ritualBlock), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.NETHERRACK), ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.TNT), new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, new ItemStack(Blocks.NETHERRACK), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.NETHERRACK), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.FireTrap, new ItemStack[]{

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.ExplosionFire, new ItemStack[]{

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA)});
		map.put(IVadeMecumCapability.Category.RingOfFire, new ItemStack[]{

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.MAGMA),

				new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA), new ItemStack(Blocks.MAGMA),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY,

				new ItemStack(Blocks.MAGMA), ItemStack.EMPTY, new ItemStack(Blocks.MAGMA),

				ItemStack.EMPTY, new ItemStack(Blocks.MAGMA), ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.Freeze, new ItemStack[]{

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.SNOW), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.SNOW),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.FrostSheathe, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.SNOW), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.SNOW), ItemStack.EMPTY,

				new ItemStack(Blocks.SNOW), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.SNOW),

				ItemStack.EMPTY, new ItemStack(Blocks.SNOW), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.SNOW), ItemStack.EMPTY, new ItemStack(Blocks.SNOW),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.SNOW), ItemStack.EMPTY, new ItemStack(Blocks.SNOW)});
		map.put(IVadeMecumCapability.Category.IceSpike, new ItemStack[]{

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(ModBlocks.ritualBlock),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY,

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.FrostTrap, new ItemStack[]{

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.ExplosionFrost, new ItemStack[]{

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), ItemStack.EMPTY, new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.ICE), ItemStack.EMPTY, new ItemStack(Blocks.ICE),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.ICE), ItemStack.EMPTY, new ItemStack(Blocks.ICE)});
		map.put(IVadeMecumCapability.Category.RingOfFrost, new ItemStack[]{

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.ICE),

				new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE), new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.ICE), ItemStack.EMPTY, new ItemStack(Blocks.ICE),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.ICE), ItemStack.EMPTY, new ItemStack(Blocks.ICE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY,

				new ItemStack(Blocks.ICE), ItemStack.EMPTY, new ItemStack(Blocks.ICE),

				ItemStack.EMPTY, new ItemStack(Blocks.ICE), ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.Shock, new ItemStack[]{

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_STONE), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.END_STONE),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_STONE), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.ShockSheathe, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS)});
		map.put(IVadeMecumCapability.Category.LitStrike, new ItemStack[]{

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_ROD), ItemStack.EMPTY, new ItemStack(Blocks.END_ROD),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.END_ROD), ItemStack.EMPTY, new ItemStack(Blocks.END_ROD)});
		map.put(IVadeMecumCapability.Category.LitTrap, new ItemStack[]{

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.END_ROD), ItemStack.EMPTY,

				new ItemStack(Blocks.END_ROD), ItemStack.EMPTY, new ItemStack(Blocks.END_ROD),

				ItemStack.EMPTY, new ItemStack(Blocks.END_ROD), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.ExplosionLit, new ItemStack[]{

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS)});
		map.put(IVadeMecumCapability.Category.RingOfLit, new ItemStack[]{

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.END_BRICKS),

				new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS), new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY,

				new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS),

				ItemStack.EMPTY, new ItemStack(Blocks.END_BRICKS), ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.AcidSpray, new ItemStack[]{

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				new ItemStack(Blocks.DIRT), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.DIRT),

				new ItemStack(Blocks.STONE), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.STONE),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.AcidSheathe, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.STONE), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.STONE), ItemStack.EMPTY,

				new ItemStack(Blocks.STONE), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.STONE),

				ItemStack.EMPTY, new ItemStack(Blocks.STONE), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.STONE), ItemStack.EMPTY, new ItemStack(Blocks.STONE),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.STONE), ItemStack.EMPTY, new ItemStack(Blocks.STONE)});
		map.put(IVadeMecumCapability.Category.Disint, new ItemStack[]{

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				ItemStack.EMPTY, new ItemStack(ModBlocks.ritualBlock), ItemStack.EMPTY,

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY,

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.AcidTrap, new ItemStack[]{

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.ExplosionAcid, new ItemStack[]{

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK)});
		map.put(IVadeMecumCapability.Category.RingOfAcid, new ItemStack[]{

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(ModBlocks.ritualBlock), new ItemStack(Blocks.SLIME_BLOCK),

				new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK), new ItemStack(Blocks.SLIME_BLOCK),

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY,

				new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK),

				ItemStack.EMPTY, new ItemStack(Blocks.SLIME_BLOCK), ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.VoidicTouch, new ItemStack[]{

				ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY,

				new ItemStack(ModBlocks.ethericPlatform), new ItemStack(ModBlocks.ritualBlock), new ItemStack(ModBlocks.ethericPlatform),

				ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.VoidicSheathe, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY,

				new ItemStack(ModBlocks.ethericPlatform), new ItemStack(ModBlocks.ritualBlock), new ItemStack(ModBlocks.ethericPlatform),

				ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform),

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform)});
		map.put(IVadeMecumCapability.Category.Implosion, new ItemStack[]{

				ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY,

				new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole),

				ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole),

				ItemStack.EMPTY, new ItemStack(ModBlocks.ritualBlock), ItemStack.EMPTY,

				new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole),

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY,

				new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole),

				ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY});
		map.put(IVadeMecumCapability.Category.SummonFireElemental, new ItemStack[]{

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(ModBlocks.ritualBlock), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY,

				new ItemStack(ModBlocks.realityHole), new ItemStack(Items.LAVA_BUCKET), new ItemStack(ModBlocks.realityHole),

				ItemStack.EMPTY, new ItemStack(ModBlocks.realityHole), ItemStack.EMPTY,

				//////////////////////////////////////////////////////////////////////////////////////////

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,

				ItemStack.EMPTY, new ItemStack(ModBlocks.ethericPlatform), ItemStack.EMPTY,

				ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY});
	}

}
