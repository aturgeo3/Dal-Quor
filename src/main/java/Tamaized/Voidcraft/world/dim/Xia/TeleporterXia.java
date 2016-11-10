package Tamaized.Voidcraft.world.dim.Xia;

import java.util.Random;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityXiaCastle;
import Tamaized.Voidcraft.world.SchematicLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterXia extends Teleporter {

	private SchematicLoader sut = new SchematicLoader();

	private final WorldServer worldServerInstance;
	private final Random random;

	boolean dospawn = false;

	public TeleporterXia(WorldServer par1WorldServer) {
		super(par1WorldServer);

		worldServerInstance = par1WorldServer;
		random = new Random(par1WorldServer.getSeed());
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (entityIn.dimension == 0) {
			BlockPos bedPos = entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getBedLocation(0) != null ? ((EntityPlayer) entityIn).getBedLocation(0) : worldServerInstance.getSpawnPoint();
			entityIn.setLocationAndAngles((double) bedPos.getX(), (double) bedPos.getY(), (double) bedPos.getZ(), entityIn.rotationYaw, entityIn.rotationPitch);
			return;
		}
		if (entityIn instanceof EntityPlayer) ((EntityPlayer) entityIn).addStat(voidCraft.achievements.voidCraftAchMainLine_6, 1);
		entityIn.setPositionAndUpdate(52.5, 62, 4.5);
		if (!isActive(entityIn)) makePortal(entityIn);
		entityIn.setPositionAndUpdate(52.5, 62, 4.5);
	}

	public boolean isActive(Entity e) {
		return worldServerInstance.getTileEntity(new BlockPos(0, 55, 0)) instanceof TileEntityXiaCastle;
	}

	@Override
	public boolean makePortal(Entity e) {
		byte b0 = 16;
		double d0 = -1.0D;
		int i = MathHelper.floor_double(e.posX);
		int j = MathHelper.floor_double(e.posY);
		int k = MathHelper.floor_double(e.posZ);

		if (e.dimension == voidCraft.config.getDimensionIDxia()) {
			// doStructure(sut, worldServerInstance, new BlockPos(-11, 59, -4));
			// worldServerInstance.setBlockState(new BlockPos(0, 0, 58), voidCraft.blocks.xiaBlock.getDefaultState());
			SchematicLoader loader = new SchematicLoader();
			SchematicLoader.buildSchematic("xiaCastle_new_2.schematic", loader, worldServerInstance, new BlockPos(0, 60, 0));
			worldServerInstance.setBlockState(new BlockPos(52, 55, 4), voidCraft.blocks.xiaBlock.getDefaultState());
			TileEntity te = worldServerInstance.getTileEntity(new BlockPos(52, 55, 4));
			if (te instanceof TileEntityXiaCastle) ((TileEntityXiaCastle) te).start();
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long par1) {

	}
}