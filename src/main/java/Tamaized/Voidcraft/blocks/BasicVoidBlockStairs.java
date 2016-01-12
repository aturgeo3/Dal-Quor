package Tamaized.Voidcraft.blocks;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BasicVoidBlockStairs extends BlockStairs implements IBasicVoidBlock{
	
	private final String name;

	protected BasicVoidBlockStairs(IBlockState modelState, String n) {
		super(modelState);
		name = n;
		setUnlocalizedName(voidCraft.modid+":"+name);
		GameRegistry.registerBlock(this, getName());
	}
	
	@Override
	public String getName() {
		return name;
	}

}
