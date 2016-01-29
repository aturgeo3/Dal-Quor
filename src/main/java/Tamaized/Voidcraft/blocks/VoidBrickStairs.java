package Tamaized.Voidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class VoidBrickStairs extends BasicVoidBlockStairs{

	public VoidBrickStairs(Block block, int meta, String string) {
		super(block.getStateFromMeta(meta), string);
		this.useNeighborBrightness = true;
	}
}
