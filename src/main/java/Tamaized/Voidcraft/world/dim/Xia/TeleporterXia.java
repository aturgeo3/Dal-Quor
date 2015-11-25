package Tamaized.Voidcraft.world.dim.Xia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.Teleporter.PortalPosition;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.world.SchematicLoader;
import Tamaized.Voidcraft.world.SchematicLoader.Schematic;

public class TeleporterXia extends Teleporter {

	private SchematicLoader sut = new SchematicLoader();

	private final WorldServer worldServerInstance;
	private final Random random;

	boolean dospawn = false;

	public TeleporterXia(WorldServer par1WorldServer) {
		super(par1WorldServer);

		this.worldServerInstance = par1WorldServer;
		this.random = new Random(par1WorldServer.getSeed());
	}
	
	private void doStructure(WorldServer world, int x, int y, int z){
		if(world == null) return;
		Schematic spring = sut.get("voidcraft.schematic");
		int i = 0;
		for (int cy = 0; cy < spring.height; cy++){
			for (int cz = 0; cz < spring.length; cz++){
				for (int cx = 0; cx < spring.width; cx++) {
					int id = spring.blocks[i];
					int meta = spring.data[i];
					world.setBlockToAir(cx+x+1, cy+y, cz+z+1);
					
					switch (id){
					case 849:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.fluids.blockVoidFluid, meta, 2);
						break;
					case 851:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.blocks.blockFakeBedrock, meta, 2);
						break;
					case 852:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.blocks.blockNoBreak, meta, 2);
						break;
					case 854:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.blocks.blockVoidbrick, meta, 2);
						break;
					case 853:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.blocks.blockVoidcrystal, meta, 2);
						break;
					case 857:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.blocks.blockVoidfence, meta, 2);
						break;
					case 856:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.blocks.blockVoidBrickSlab, meta, 2);
						break;
					case 855:
						world.setBlock(cx+x+1, cy+y, cz+z+1, voidCraft.blocks.blockVoidstairs, meta, 2);
						break;
					default:
						world.setBlock(cx+x+1, cy+y, cz+z+1, Block.getBlockById(id), meta, 2);
						break;
					}
					
					i++;
				}
			}
		}
	}
	
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8){
		//if(par1Entity instanceof EntityPlayer) ((EntityPlayer) par1Entity).addStat(voidCraft.achievements.voidCraftAchMainLine_2, 1);
		if(par1Entity.dimension == 0){
			ChunkCoordinates chunkcoordinates = par1Entity instanceof EntityPlayer && ((EntityPlayer)par1Entity).getBedLocation(0) != null ? ((EntityPlayer)par1Entity).getBedLocation(0) : worldServerInstance.getSpawnPoint();
            //chunkcoordinates.posY = par1Entity.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
            par1Entity.setLocationAndAngles((double)chunkcoordinates.posX, (double)chunkcoordinates.posY, (double)chunkcoordinates.posZ, par1Entity.rotationYaw, par1Entity.rotationPitch);
			return;
		}
		par1Entity.setPosition(0.5, 60, 0.5);
		if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8)){
			this.makePortal(par1Entity);
			this.placeInExistingPortal(par1Entity, par2, par4, par6, par8);
		}
		par1Entity.setLocationAndAngles(0.5, 60, 0.5, 0, 0);
	}

    /**
	 * Place an entity in a nearby portal which already exists.
	 */
	public boolean placeInExistingPortal(Entity e, double x, double y, double z, float par8){
		if(worldServerInstance.getBlock(0, 59, 0) == voidCraft.blocks.blockNoBreak) return true;
		else return false;
	}
	
	public boolean makePortal(Entity e) {
		byte b0 = 16;
		double d0 = -1.0D;
		int i = MathHelper.floor_double(e.posX);
		int j = MathHelper.floor_double(e.posY);
		int k = MathHelper.floor_double(e.posZ);
		
		if(e.dimension == voidCraft.dimensionIdXia){
			doStructure(this.worldServerInstance, -11, 59, -4);
		}else{
			
		}

		return true;
	}

	public void removeStalePortalLocations(long par1) {
		
	}
}