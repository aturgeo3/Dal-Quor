package Tamaized.Voidcraft.blocks;

import Tamaized.Voidcraft.common.voidCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class VoidSlab extends BlockSlab{

	public VoidSlab(boolean p_i45410_1_, Material p_i45410_2_) {
		super(p_i45410_1_, p_i45410_2_);
	}

	@Override
	public String func_150002_b(int p_150002_1_) {
		return null;
	}
	
	/**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(voidCraft.blocks.blockVoidBrickSlab);
    }

}
