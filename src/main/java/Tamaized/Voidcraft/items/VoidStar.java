package Tamaized.Voidcraft.items;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class VoidStar extends Item{
	
	public boolean onEntityItemUpdate(EntityItem entityItem){
		if(entityItem.onGround && entityItem.dimension != voidCraft.dimensionIdVoid && entityItem.dimension != voidCraft.dimensionIdXia){
			boolean flag = true;
			for(int x=-1; x<2; x++){
				for(int z=-1; z<2; z++){
					for(int y=-1; y<1; y++){
						//if(x!=0 && z!=0){
							int xCoord = MathHelper.floor_double(entityItem.posX+x);
							int yCoord = MathHelper.floor_double(entityItem.posY+y);
							int zCoord = MathHelper.floor_double(entityItem.posZ+z);
							if(entityItem.worldObj.getBlock(xCoord, yCoord, zCoord) != voidCraft.blocks.blockFakeBedrock){
								if(!(x==0 && z==0 && y==0)) flag = false;
							}
						//}
					}
				}
			}
			if(flag){
				for(int x=-1; x<2; x++){
					for(int z=-1; z<2; z++){
						for(int y=-1; y<1; y++){
							int xCoord = MathHelper.floor_double(entityItem.posX+x);
							int yCoord = MathHelper.floor_double(entityItem.posY+y);
							int zCoord = MathHelper.floor_double(entityItem.posZ+z);
							if(x!=0 || z!=0 || y!=0){
								entityItem.worldObj.spawnEntityInWorld(new EntityLightningBolt(entityItem.worldObj, entityItem.posX+x, entityItem.posY, entityItem.posZ+z));
								entityItem.worldObj.setBlock(xCoord, yCoord, zCoord, voidCraft.blocks.blockNoBreak);
							}
						}
					}
				}
				int xCoord = MathHelper.floor_double(entityItem.posX);
				int yCoord = MathHelper.floor_double(entityItem.posY);
				int zCoord = MathHelper.floor_double(entityItem.posZ);
				entityItem.worldObj.setBlock(xCoord, yCoord, zCoord, voidCraft.blocks.blockPortalXia);
				if(entityItem.func_145800_j() != null){
					EntityPlayer entityplayer = entityItem.worldObj.getPlayerEntityByName(entityItem.func_145800_j());
					if(entityplayer != null) entityplayer.addStat(voidCraft.achievements.voidCraftAchMainLine_5, 1);
				}
				entityItem.setDead();
				return true;
			}
		}
        return false;
    }

}
