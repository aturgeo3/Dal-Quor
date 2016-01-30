package Tamaized.Voidcraft.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobVoidBoss;

public class ChainedSkull extends BasicVoidItems{

	private World worldObj;

	public ChainedSkull(String name) {
		super(name);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	int x = pos.getX();
        	int y = pos.getY();
        	int z = pos.getZ();
            Block i1 = world.getBlockState(pos).getBlock();
            x += side.getFrontOffsetX();
            y += side.getFrontOffsetY();
            z += side.getFrontOffsetZ();
            double d0 = 0.0D;

            if (side.getIndex() == 1)
            {
                d0 = 0.5D;
            }

            EntityMobVoidBoss entity = new EntityMobVoidBoss(world);
            
            if (entity != null)
            {
            	
            	entity.setCustomNameTag("Corrupted Pawn");
                
                int yaw = (int)player.rotationYaw;

                if (yaw<0) yaw+=360;  //creates the full radial circle of the yaw
                       

                yaw+=22;     //centers coordinates you may want to drop this line
                yaw%=360;  //and this one if you want a strict interpretation of the zones

                int facing = yaw/45;   //  360degrees divided by 45 == 8 zones
                //System.out.println("Yaw is " + yaw + "facing is " + facing);
                
                
                if(facing==0){
                	entity.setPosition(player.posX, player.posY+4, player.posZ+2);
                	}
                if(facing==1){
                    entity.setPosition(player.posX-2, player.posY+4, player.posZ+2);
                    }
                if(facing==2){
                    entity.setPosition(player.posX-2, player.posY+4, player.posZ);
                    }
                if(facing==3){
                    entity.setPosition(player.posX-2, player.posY+4, player.posZ-2);
                    }
                if(facing==4){
                    entity.setPosition(player.posX, player.posY+4, player.posZ-2);
                    }
                if(facing==5){
                    entity.setPosition(player.posX+2, player.posY+4, player.posZ-2);
                    }
                if(facing==6){
                    entity.setPosition(player.posX+2, player.posY+4, player.posZ);
                    }
                if(facing==7){
                    entity.setPosition(player.posX+2, player.posY+4, player.posZ+2);
                    }
                
                
                world.spawnEntityInWorld(entity);
                
                		player.addChatMessage(new ChatComponentText("???: Go forth, my pawn.")); 
                	
                    --stack.stackSize;
                
            }

            return true;
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_RED + "WARNING: EXPLOSIONS");
	}

}
