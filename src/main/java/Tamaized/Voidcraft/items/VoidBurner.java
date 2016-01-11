package Tamaized.Voidcraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidBurner extends Item{

	public VoidBurner() {
		super();

		this.maxStackSize =1;
		this.setMaxDamage(26);
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float x, float y, float z){
		if(side.getIndex() == 0){
		y--;
		}
		if(side.getIndex() == 1){
		y++;
		}
		if(side.getIndex() == 2){
		z--;
		}
		if(side.getIndex() == 3){
		z++;
		}
		if(side.getIndex() == 4){
		x--;
		}
		if(side.getIndex() == 5){
		x++;
		}
		if(!player.canPlayerEdit(new BlockPos(x, y, z), side, itemstack)){
		return false;
		}else{
		if(world.isAirBlock(new BlockPos(x, y, z))){
		world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "fire.ignite", 1F, itemRand.nextFloat()*0.4F + 0.8F);
		world.setBlockState(new BlockPos(x, y, z), voidCraft.blocks.fireVoid.getDefaultState());
		}

		itemstack.damageItem(1, player);
		return true;
		}
		}

}
