package Tamaized.Voidcraft.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import Tamaized.Voidcraft.projectiles.AcidBall;
import Tamaized.Voidcraft.projectiles.VoidChain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Debugger extends Item {
	
	TileEntity te;
	
	public Debugger(){
		
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10){	
		if(par3World.isRemote) return true;
		AcidBall entityarrow = new AcidBall(par3World, par2EntityPlayer, 2.0F);
		par3World.spawnEntityInWorld(entityarrow);
        return true;
    }
	
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World par3World, EntityPlayer par2EntityPlayer){
		if(par3World.isRemote) return p_77659_1_;
		AcidBall entityarrow = new AcidBall(par3World, par2EntityPlayer, 2.0F);
		par3World.spawnEntityInWorld(entityarrow);
		return p_77659_1_;
		
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + "Debug Tool");
	}

}
