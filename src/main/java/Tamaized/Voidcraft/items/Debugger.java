package Tamaized.Voidcraft.items;

import java.util.List;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.world.SchematicLoader;
import Tamaized.Voidcraft.world.SchematicLoader.Schematic;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Debugger extends Item {
	
	TileEntity te;
	
	public Debugger(){
		
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
		/*SchematicLoader sut = new SchematicLoader();
		Schematic spring = sut.get("VoidCastle-Redstone_WithTnT.schematic");
		int i = 0;
		for (int cy = 0; cy < spring.height; cy++){
			for (int cz = 0; cz < spring.length; cz++){
				for (int cx = 0; cx < spring.width; cx++) {

					Block b = Block.getBlockById(spring.blocks[i]);
					if (b != Blocks.air) {
						par3World.setBlockToAir(cx+x, cy+y, cz+z);
						if (b != Blocks.stone)
							par3World.setBlock(cx+x, cy+y, cz+z, b, spring.data[i], 2);
						else
							par3World.setBlock(cx+x, cy+y, cz+z, voidCraft.blockNoBreak, spring.data[i], 2);
					}
					i++;
				}
			}
		}
		*/
		/*
		te = par3World.getTileEntity(x, y, z);

		
		if(te instanceof TileEntityVoidMacerator){
			TileEntityVoidMacerator instance = (TileEntityVoidMacerator) te;
			instance.fill(ForgeDirection.NORTH, new FluidStack(voidCraft.fluidVoid, instance.voidTank.getCapacity()), true);
			instance.burnTime = instance.voidTank.getFluidAmount();
		}
		
		if(te instanceof TileEntityVoidInfuser){
			TileEntityVoidInfuser instance = (TileEntityVoidInfuser) te;
			instance.fill(ForgeDirection.NORTH, new FluidStack(voidCraft.fluidVoid, instance.voidTank.getCapacity()), true);
			instance.burnTime = instance.voidTank.getFluidAmount();
		}
		
		if(te instanceof TileEntityHeimdall){
			TileEntityHeimdall instance = (TileEntityHeimdall) te;
			instance.fill(ForgeDirection.NORTH, new FluidStack(voidCraft.fluidVoid, instance.voidTank.getCapacity()), true);
			instance.burnTime = instance.voidTank.getFluidAmount();
		}
		*/
		
		//VoidChain entityarrow = new VoidChain(par3World, par2EntityPlayer, par2EntityPlayer, 1.6F, (float)(14 - par3World.difficultySetting.getDifficultyId() * 4));
		//par3World.spawnEntityInWorld(entityarrow);
        return true;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + "Debug Tool");
	}

}
