package Tamaized.Voidcraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidBurner extends BasicVoidItems{

	public VoidBurner(String name) {
		super(name);

		this.maxStackSize =1;
		this.setMaxDamage(26);
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float x, float y, float z){
		pos = pos.offset(side);

        if (!player.canPlayerEdit(pos, side, itemstack)){
            return false;
        }else{
        	if(world.isAirBlock(pos)){
            	world.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            	world.setBlockState(pos, voidCraft.blocks.fireVoid.getDefaultState());
            }

            itemstack.damageItem(1, player);
            return true;
        }
	}
}
