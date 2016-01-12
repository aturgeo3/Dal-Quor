package Tamaized.Voidcraft.blocks;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BasicVoidBlockContainer extends BlockContainer implements IBasicVoidBlock{
	
	private final String name;

	protected BasicVoidBlockContainer(Material materialIn, String n) {
		super(materialIn);
		name = n;
		setUnlocalizedName(voidCraft.modid+":"+name);
		GameRegistry.registerBlock(this, getName());
	}
	
	@Override
	public String getName() {
		return name;
	}

}
