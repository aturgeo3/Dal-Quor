package Tamaized.Voidcraft.machina;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Tamaized.Voidcraft.blocks.BasicVoidBlockContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerCable;

public class VoidicPowerCable extends BasicVoidBlockContainer {

	public VoidicPowerCable(Material materialIn, String n) {
		super(materialIn, n);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVoidicPowerCable();
	}

}
