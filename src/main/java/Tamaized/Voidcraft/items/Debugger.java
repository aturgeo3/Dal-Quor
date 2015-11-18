package Tamaized.Voidcraft.items;

import java.util.List;
import java.util.Random;

import Tamaized.Voidcraft.structures.ComponentTestCorridor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Debugger extends Item {
	
	public Debugger(){
		
	}
	
	/**
	 * On Block
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){	
		Random rand = new Random();
    	world.setBlock(x, y+1, z, Blocks.chest, 0, 2);
        TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(x, y+1, z);

        if (tileentitychest != null)
        {
            WeightedRandomChestContent.generateChestContents(rand, ComponentTestCorridor.field_111019_a, tileentitychest, 1 + rand.nextInt(5));
        }
    	return true;
    }
	
	/**
	 * In Air
	 */
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World par3World, EntityPlayer par2EntityPlayer){
		return p_77659_1_;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + "Debug Tool");
	}

}
