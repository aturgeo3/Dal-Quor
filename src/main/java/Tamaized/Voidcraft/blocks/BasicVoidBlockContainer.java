package Tamaized.Voidcraft.blocks;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BasicVoidBlockContainer extends BlockContainer implements IBasicVoid{
	
	private final String name;

	protected BasicVoidBlockContainer(Material materialIn, String n) {
		super(materialIn);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, "blocks/"+n);
		this.setCreativeTab(voidCraft.tabs.tabVoid);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

}
