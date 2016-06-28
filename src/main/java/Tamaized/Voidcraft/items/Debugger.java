package Tamaized.Voidcraft.items;

import java.util.List;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.power.IVoidicPower;
import Tamaized.Voidcraft.projectiles.VoidChain;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.world.SchematicLoader;

public class Debugger extends BasicVoidItems {

	SchematicLoader sut = new SchematicLoader();
	
	public Debugger(String n) {
		super(n);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
    	VoidChain entityarrow = new VoidChain(worldIn, playerIn, playerIn, 1);
    	worldIn.spawnEntityInWorld(entityarrow);
		return EnumActionResult.PASS;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.DARK_PURPLE + "Debug Tool");
	}

}
