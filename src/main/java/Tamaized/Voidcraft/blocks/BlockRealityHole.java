package Tamaized.Voidcraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import Tamaized.TamModized.blocks.TamBlock;
import Tamaized.Voidcraft.voidCraft;

public class BlockRealityHole extends TamBlock {

    public BlockRealityHole(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
		setLightLevel(1.0F);
		setResistance(100);
	}

	/**
     * Triggered whenever an entity collides with this block (enters into the block)
     */
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn){
    /*	if(!worldIn.isRemote){
    		if(entityIn instanceof EntityPlayerMP){
        		EntityPlayerMP player = ((EntityPlayerMP) entityIn);
        		player.mcServer.getPlayerList().transferPlayerToDimension(player, 0, new RealityTeleporter(player.mcServer.worldServerForDimension(0), player.getPosition()));
    		}
        	else entityIn.setDead();
    	}*/
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
    	if(!worldIn.isRemote){
    		if(entityIn instanceof EntityPlayerMP){
        		EntityPlayerMP player = ((EntityPlayerMP) entityIn);
        		if(player.worldObj.provider.getDimension() != 0) player.mcServer.getPlayerList().transferPlayerToDimension(player, 0, new RealityTeleporter(player.mcServer.worldServerForDimension(0), player.getPosition()));
        		//else new RealityTeleporter(player.getServerWorld(), player.getPosition()).placeInPortal(player, player.rotationYaw);
    		}
        	else entityIn.setDead();
    	}
    }

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return null;
	}
	
	private class RealityTeleporter extends Teleporter{
		
		private int x;
		private int y;
		private int z;
		
		public RealityTeleporter(WorldServer worldIn, BlockPos pos) {
			super(worldIn);
			x = pos.getX();
			y = pos.getY();
			z = pos.getZ();
		}
		
		public void placeInPortal(Entity entityIn, float rotationYaw){
			World w = entityIn.worldObj;
    		int rX = (int) Math.floor(Math.random()*200);
    		int rZ = (int) Math.floor(Math.random()*200);
    		if(rand()) rX = 0-rX;
    		if(rand()) rZ = 0-rZ;
    		x = x+rX;
    		z = z+rZ;
    		while(w.getBlockState(new BlockPos(x, y, z)).getMaterial() != Material.AIR) y++;
    		entityIn.setPosition(x, y, z);
    		System.out.println(entityIn.getPosition());
		}
		
		private boolean rand(){
			switch((int)Math.floor(Math.random())){
    			case 0:
    				return false;
    			case 1:
    				return true;
    			default:
    				return false;
    		}
		}
		
	}

}
