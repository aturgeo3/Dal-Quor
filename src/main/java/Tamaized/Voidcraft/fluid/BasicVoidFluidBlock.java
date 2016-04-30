package Tamaized.Voidcraft.fluid;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidFluidBlock extends BlockFluidClassic implements IBasicVoid{
	
	private final String name;

	public BasicVoidFluidBlock(Fluid fluid, Material material, String name) {
		super(fluid, material);
		this.name = name;
		setUnlocalizedName(voidCraft.modid+":"+name);
		setCreativeTab(voidCraft.tabs.tabVoid);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public String getName() {
		return name;
	} 
}
