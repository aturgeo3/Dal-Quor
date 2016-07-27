package Tamaized.Voidcraft.world.dim.Xia;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import Tamaized.Voidcraft.voidCraft;
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
		Schematic spring = sut.get("XiaCastle.schematic");
		int i = 0;
		for (int cy = 0; cy < spring.height; cy++){
			for (int cz = 0; cz < spring.length; cz++){
				for (int cx = 0; cx < spring.width; cx++) {
					int id = spring.blocks[i];
					int meta = spring.data[i];
					world.setBlockToAir(new BlockPos(cx+x+1, cy+y, cz+z+1));
					
					switch (id){
					case 727:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.fluids.voidFluidBlock.getStateFromMeta(meta), 2);
						break;
					case 729:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.blocks.blockFakeBedrock.getStateFromMeta(meta), 2);
						break;
					case 730:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.blocks.blockNoBreak.getStateFromMeta(meta), 2);
						break;
					case 732:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.blocks.blockVoidbrick.getStateFromMeta(meta), 2);
						break;
					case 731:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.blocks.blockVoidcrystal.getStateFromMeta(meta), 2);
						break;
					case 735:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.blocks.blockVoidfence.getStateFromMeta(meta), 2);
						break;
					case 734:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.blocks.blockVoidBrickHalfSlab.getStateFromMeta(meta), 2);
						break;
					case 733:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), voidCraft.blocks.blockVoidstairs.getStateFromMeta(meta), 2);
						break;
					default:
						world.setBlockState(new BlockPos(cx+x+1, cy+y, cz+z+1), Block.getBlockById(id).getStateFromMeta(meta), 2);
						break;
					}
					
					i++;
				}
			}
		}
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw){
		if(entityIn.dimension == 0){
			BlockPos bedPos = entityIn instanceof EntityPlayer && ((EntityPlayer)entityIn).getBedLocation(0) != null ? ((EntityPlayer)entityIn).getBedLocation(0) : worldServerInstance.getSpawnPoint();
            entityIn.setLocationAndAngles((double)bedPos.getX(), (double)bedPos.getY(), (double)bedPos.getZ(), entityIn.rotationYaw, entityIn.rotationPitch);
			return;
		}
		if(entityIn instanceof EntityPlayer) ((EntityPlayer)entityIn).addStat(voidCraft.achievements.voidCraftAchMainLine_6, 1);
		entityIn.setPosition(0.5, 60, 0.5);
		if (!this.placeInExistingPortal(entityIn)){
			this.makePortal(entityIn);
			this.placeInExistingPortal(entityIn);
		}
		entityIn.setLocationAndAngles(47.5, 61, 4.5, 0, 0);
	}

    /**
	 * Place an entity in a nearby portal which already exists.
	 */
	public boolean placeInExistingPortal(Entity e){
		if(worldServerInstance.getBlockState(new BlockPos(0, 59, 0)).getBlock() == voidCraft.blocks.blockNoBreak) return true;
		else return false;
	}
	
	@Override
	public boolean makePortal(Entity e) {
		byte b0 = 16;
		double d0 = -1.0D;
		int i = MathHelper.floor_double(e.posX);
		int j = MathHelper.floor_double(e.posY);
		int k = MathHelper.floor_double(e.posZ);
		
		if(e.dimension == voidCraft.dimensionIdXia){
			doStructure(this.worldServerInstance, -11, 59, -4);
			worldServerInstance.setBlockState(new BlockPos(0, 0, 58), voidCraft.blocks.xiaBlock.getDefaultState());
		}else{
			
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long par1) {
		
	}
}