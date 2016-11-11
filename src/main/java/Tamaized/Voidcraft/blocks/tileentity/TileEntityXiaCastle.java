package Tamaized.Voidcraft.blocks.tileentity;

import java.util.List;

import Tamaized.TamModized.tileentity.TamTileEntity;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.events.client.DebugEvent;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.XiaBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.HerobrineBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.twins.TwinsBattleHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityXiaCastle extends TamTileEntity implements ITickable {

	private boolean running = false;
	private boolean xiaDoorOpen = false;

	private final IBattleHandler twins = new TwinsBattleHandler();
	private final IBattleHandler herobrine = new HerobrineBattleHandler();
	private final IBattleHandler xia = new XiaBattleHandler();

	private BlockPos twinsLoc;
	private BlockPos herobrineLoc;
	private BlockPos xiaLoc;

	@Override
	public void onUpdate() {
		if (!worldObj.isRemote) {
			validateInstance();
			if (running) {
				doHandlerStartChecks();
				if (!xiaDoorOpen && twins.isDone() && herobrine.isDone()) {
				//if (!xiaDoorOpen) {
					BlockPos doorPos = new BlockPos(54, 76, 82);
					for (int x = 0; x > -5; x--) {
						for (int y = 0; y < 4; y++) {
							worldObj.setBlockToAir(doorPos.add(x, y, 0));
						}
					}
					xiaDoorOpen = true;
				}
				if (twins.isRunning()) twins.update();
				if (herobrine.isRunning()) herobrine.update();
				if (xia.isRunning()) xia.update();
			}
		}
	}

	private void doHandlerStartChecks() {
		if (twinsLoc != null && herobrineLoc != null && xiaLoc != null) {
			AxisAlignedBB twinsBB = new AxisAlignedBB(twinsLoc.add(-5, 0, 0), twinsLoc.add(5, 2, -6));
			AxisAlignedBB herobrineBB = new AxisAlignedBB(herobrineLoc.add(0, 0, -10), herobrineLoc.add(8, 2, 10));
			AxisAlignedBB xiaBB = new AxisAlignedBB(xiaLoc.add(-18, 0, 7), xiaLoc.add(18, 10, 15));
			List<EntityPlayer> list;
			if (!twins.isRunning() && !twins.isDone()) {
				list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, twinsBB);
				if (!list.isEmpty()) twins.start(worldObj, twinsLoc);
			}
			if (!herobrine.isRunning() && !herobrine.isDone()) {
				list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, herobrineBB);
				if (!list.isEmpty()) herobrine.start(worldObj, herobrineLoc);
			}
			if (!xia.isRunning() && !xia.isDone() && xiaDoorOpen) {
				list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, xiaBB);
				if (!list.isEmpty()) xia.start(worldObj, xiaLoc);
			}
		}
	}

	public boolean isActive() {
		validateInstance();
		return running;
	}

	public void validateInstance() {
		if (worldObj == null || worldObj.playerEntities.isEmpty() || xiaLoc == null || twinsLoc == null | herobrineLoc == null) stop();
		if (!running && worldObj != null && !worldObj.playerEntities.isEmpty()) start();
	}

	public void start() {
		stop();
		twinsLoc = getPos().add(93 - 52, 71 - 55, 70 - 4);
		herobrineLoc = getPos().add(12 - 52, 71 - 55, 70 - 4);
		xiaLoc = getPos().add(52 - 52, 75 - 55, 85 - 4);
		running = true;
	}

	public void stop() {
		twins.stop();
		herobrine.stop();
		xia.stop();
		BlockPos doorPos = new BlockPos(54, 76, 82);
		for (int x = 0; x > -5; x--) {
			for (int y = 0; y < 4; y++) {
				worldObj.setBlockState(doorPos.add(x, y, 0), (x == 0 || x == -4 || y == 0 || y == 3) ? voidCraft.blocks.realityHole.getDefaultState() : voidCraft.blocks.blockNoBreak.getDefaultState());
			}
		}
		xiaDoorOpen = false;
		running = false;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 2, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager netManager, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		running = nbt.hasKey("running") ? nbt.getBoolean("running") : false;
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setBoolean("running", running);
		return nbt;
	}

}
