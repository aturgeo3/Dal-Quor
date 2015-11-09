package Tamaized.Voidcraft.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobVoidBoss;

public class ChainedSkull extends Item{

	private World worldObj;

	public ChainedSkull() {
		super();
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else
        {
            Block i1 = par3World.getBlock(x, y, z);
            x += Facing.offsetsXForSide[par7];
            y += Facing.offsetsYForSide[par7];
            z += Facing.offsetsZForSide[par7];
            double d0 = 0.0D;

            if (par7 == 1)
            {
                d0 = 0.5D;
            }

            EntityMobVoidBoss entity = new EntityMobVoidBoss(par3World);
            
            if (entity != null)
            {
            	
            	entity.setCustomNameTag("Corrupted Pawn");
                
                int yaw = (int)par2EntityPlayer.rotationYaw;

                if (yaw<0) yaw+=360;  //creates the full radial circle of the yaw
                       

                yaw+=22;     //centers coordinates you may want to drop this line
                yaw%=360;  //and this one if you want a strict interpretation of the zones

                int facing = yaw/45;   //  360degrees divided by 45 == 8 zones
                //System.out.println("Yaw is " + yaw + "facing is " + facing);
                
                
                if(facing==0){
                	entity.setPosition(par2EntityPlayer.posX, par2EntityPlayer.posY+4, par2EntityPlayer.posZ+2);
                	}
                if(facing==1){
                    entity.setPosition(par2EntityPlayer.posX-2, par2EntityPlayer.posY+4, par2EntityPlayer.posZ+2);
                    }
                if(facing==2){
                    entity.setPosition(par2EntityPlayer.posX-2, par2EntityPlayer.posY+4, par2EntityPlayer.posZ);
                    }
                if(facing==3){
                    entity.setPosition(par2EntityPlayer.posX-2, par2EntityPlayer.posY+4, par2EntityPlayer.posZ-2);
                    }
                if(facing==4){
                    entity.setPosition(par2EntityPlayer.posX, par2EntityPlayer.posY+4, par2EntityPlayer.posZ-2);
                    }
                if(facing==5){
                    entity.setPosition(par2EntityPlayer.posX+2, par2EntityPlayer.posY+4, par2EntityPlayer.posZ-2);
                    }
                if(facing==6){
                    entity.setPosition(par2EntityPlayer.posX+2, par2EntityPlayer.posY+4, par2EntityPlayer.posZ);
                    }
                if(facing==7){
                    entity.setPosition(par2EntityPlayer.posX+2, par2EntityPlayer.posY+4, par2EntityPlayer.posZ+2);
                    }
                
                
                par3World.spawnEntityInWorld(entity);
                
                		par2EntityPlayer.addChatMessage(new ChatComponentText("???: Go forth, my pawn.")); 
                	
                    --par1ItemStack.stackSize;
                
            }

            return true;
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.DARK_RED + "WARNING: EXPLOSIONS");
	}

}
