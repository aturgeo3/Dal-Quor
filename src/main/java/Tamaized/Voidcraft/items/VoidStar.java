package Tamaized.Voidcraft.items;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidStar extends BasicVoidItems{
	
	public VoidStar(String n) {
		super(n);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem){
		if(entityItem.onGround && entityItem.dimension != voidCraft.dimensionIdVoid &&
				entityItem.dimension != voidCraft.dimensionIdXia &&
				entityItem.worldObj.getBlockState(
						new BlockPos(MathHelper.floor_double(entityItem.posX),
								MathHelper.floor_double(entityItem.posY-1),
								MathHelper.floor_double(entityItem.posZ))).getBlock() == voidCraft.blocks.blockFakeBedrock //Make sure we're even ontop of a fakeBedrockBlock before we begin a loop
								){
			for(int x=-1; x<2; x++){
				for(int z=-1; z<2; z++){
					for(int y=-1; y<1; y++){
						//if(x!=0 && z!=0){
							int xCoord = MathHelper.floor_double(entityItem.posX+x);
							int yCoord = MathHelper.floor_double(entityItem.posY+y);
							int zCoord = MathHelper.floor_double(entityItem.posZ+z);
							if(entityItem.worldObj.getBlockState(new BlockPos(xCoord, yCoord, zCoord)).getBlock() != voidCraft.blocks.blockFakeBedrock){
								if(!(x==0 && z==0 && y==0)){
									return false; //No reason to continue checking
								}
							}
						//}
					}
				}
			}
			for(int x=-1; x<2; x++){
				for(int z=-1; z<2; z++){
					for(int y=-1; y<1; y++){
						int xCoord = MathHelper.floor_double(entityItem.posX+x);
						int yCoord = MathHelper.floor_double(entityItem.posY+y);
						int zCoord = MathHelper.floor_double(entityItem.posZ+z);
						if(x!=0 || z!=0 || y!=0){
							entityItem.worldObj.spawnEntityInWorld(new EntityLightningBolt(entityItem.worldObj, entityItem.posX+x, entityItem.posY, entityItem.posZ+z, false));
							entityItem.worldObj.setBlockState(new BlockPos(xCoord, yCoord, zCoord), voidCraft.blocks.blockNoBreak.getDefaultState());
						}
					}
				}
			}
			int xCoord = MathHelper.floor_double(entityItem.posX);
			int yCoord = MathHelper.floor_double(entityItem.posY);
			int zCoord = MathHelper.floor_double(entityItem.posZ);
			entityItem.worldObj.setBlockState(new BlockPos(xCoord, yCoord, zCoord), voidCraft.blocks.blockPortalXia.getDefaultState());
			if(entityItem.getThrower() != null){
				EntityPlayer entityplayer = entityItem.worldObj.getPlayerEntityByName(entityItem.getThrower());
				if(entityplayer != null) entityplayer.addStat(voidCraft.achievements.voidCraftAchMainLine_5, 1);
			}
			entityItem.setDead();
			return true;	
		}
		return false;
    }

}
