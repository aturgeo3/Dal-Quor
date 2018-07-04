package tamaized.dalquor.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import tamaized.tammodized.common.items.TamItem;
import tamaized.dalquor.common.handlers.ConfigHandler;
import tamaized.dalquor.registry.ModBlocks;

public class VoidStar extends TamItem {

	public VoidStar(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		// Make sure we're even on top of a fakeBedrockBlock before we begin a loop
		if (entityItem.onGround && entityItem.dimension != ConfigHandler.dimensionIdVoid && entityItem.dimension != ConfigHandler.dimensionIdXia && entityItem.dimension != ConfigHandler.dimensionIdDalQuor && entityItem.world.getBlockState(new BlockPos(MathHelper.floor(entityItem.posX), MathHelper.floor(entityItem.posY - 1), MathHelper.floor(entityItem.posZ))).getBlock() == ModBlocks.ethericPlatform) {
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					for (int y = -1; y < 1; y++) {
						// if(x!=0 && z!=0){
						int xCoord = MathHelper.floor(entityItem.posX + x);
						int yCoord = MathHelper.floor(entityItem.posY + y);
						int zCoord = MathHelper.floor(entityItem.posZ + z);
						if (entityItem.world.getBlockState(new BlockPos(xCoord, yCoord, zCoord)).getBlock() != ModBlocks.ethericPlatform) {
							if (!(x == 0 && z == 0 && y == 0)) {
								return false; // No reason to continue checking
							}
						}
						// }
					}
				}
			}
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					for (int y = -1; y < 1; y++) {
						int xCoord = MathHelper.floor(entityItem.posX + x);
						int yCoord = MathHelper.floor(entityItem.posY + y);
						int zCoord = MathHelper.floor(entityItem.posZ + z);
						if (x != 0 || z != 0 || y != 0) {
							entityItem.world.spawnEntity(new EntityLightningBolt(entityItem.world, entityItem.posX + x, entityItem.posY, entityItem.posZ + z, false));
							entityItem.world.setBlockState(new BlockPos(xCoord, yCoord, zCoord), ModBlocks.noBreak.getDefaultState());
						}
					}
				}
			}
			int xCoord = MathHelper.floor(entityItem.posX);
			int yCoord = MathHelper.floor(entityItem.posY);
			int zCoord = MathHelper.floor(entityItem.posZ);
			entityItem.world.setBlockState(new BlockPos(xCoord, yCoord, zCoord), ModBlocks.portalXia.getDefaultState());
			if (entityItem.getThrower() != null) {
				EntityPlayer entityplayer = entityItem.world.getPlayerEntityByName(entityItem.getThrower());
				//				if (entityplayer != null) entityplayer.addStat(VoidCraft.achievements.godsSleep, 1); TODO
			}
			entityItem.setDead();
			return true;
		}
		return false;
	}

}
