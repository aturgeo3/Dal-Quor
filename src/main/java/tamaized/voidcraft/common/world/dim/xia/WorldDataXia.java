package tamaized.voidcraft.common.world.dim.xia;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import tamaized.voidcraft.VoidCraft;

public class WorldDataXia extends WorldSavedData {

	private static final String NAME = VoidCraft.modid + "_XiaCastle";
	private WorldProviderXia xia;
	private NBTTagCompound nbt;

	public WorldDataXia(String name) {
		super(name);
	}

	public final void setProvider(WorldProviderXia provider) {
		xia = provider;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.nbt = nbt == null ? new NBTTagCompound() : nbt;
	}

	public NBTTagCompound getNBT(){
		return nbt;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		return xia == null ? nbt : xia.getXiaCastleHandler().writeNBT(nbt);
	}

	public static WorldDataXia get(World world) {
		MapStorage storage = world.getMapStorage();
		WorldDataXia instance = (WorldDataXia) storage.getOrLoadData(WorldDataXia.class, NAME);
		if (instance == null) {
			instance = new WorldDataXia(NAME);
			storage.setData(NAME, instance);
		}
		return instance;
	}
}
