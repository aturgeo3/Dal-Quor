package tamaized.dalquor.common.world.dim.xia;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.common.handlers.ConfigHandler;
import tamaized.dalquor.common.xiacastle.logic.XiaCastleLogicHandler;
import tamaized.dalquor.registry.ModBiomes;

public class WorldProviderXia extends WorldProvider {

	private static final XiaSkyRender skyRender = new XiaSkyRender();

	private XiaCastleLogicHandler xiaCastleHandler;
	private WorldDataXia data;
	private boolean hasLoaded = false;

	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	@Override
	protected void init() {
		biomeProvider = new BiomeProviderSingle(ModBiomes.biomeXia);
		doesWaterVaporize = false;
		nether = true;
		if (world instanceof WorldServer) {
			if (xiaCastleHandler == null)
				xiaCastleHandler = new XiaCastleLogicHandler(world);
			hasLoaded = xiaCastleHandler.hasFinished();
		}
	}

	@Override
	public void onWorldSave() {
		if (data != null)
			data.markDirty();
	}

	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(net.minecraft.world.chunk.Chunk chunk) {
		return false;
	}

	@Override
	public void onWorldUpdateEntities() {
		super.onWorldUpdateEntities();
		if (xiaCastleHandler != null) {
			if (!hasLoaded) {
				data = WorldDataXia.get(world);
				data.setProvider(this);
				xiaCastleHandler.start();
				if (data.getNBT() != null)
					xiaCastleHandler.readNBT(data.getNBT());
				hasLoaded = true;
			}
			xiaCastleHandler.onUpdate();
		}
	}

	public final XiaCastleLogicHandler getXiaCastleHandler() {
		return xiaCastleHandler;
	}

	@Override
	public IRenderHandler getSkyRenderer() {
		return skyRender;
	}

	/**
	 * Return Vec3D with biome specific fog color
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(0.0D, 0.0D, 0.0D);
	}

	/**
	 * Creates the light to brightness table
	 */
	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
	}

	/**
	 * Returns a new chunk provider which generates chunks for this world
	 */
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderXia(world, world.getWorldInfo().isMapFeaturesEnabled(), world.getSeed());
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
	 */
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	/**
	 * Will check if the x, z position specified is alright to be set as the map spawn point
	 */
	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
	 */
	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 0.5F;
	}

	/**
	 * True if the player can respawn in this dimension (true = overworld, false = nether).
	 */
	@Override
	public boolean canRespawnHere() {
		return false;
	}

	/**
	 * Returns true if the given X,Z coordinate should show environmental fog.
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}

	@Override
	public int getAverageGroundLevel() {
		return 50;
	}

	@Override
	public WorldBorder createWorldBorder() {
		return new WorldBorder() {

			@Override
			public double getCenterX() {
				return super.getCenterX() / 8.0D;
			}

			@Override
			public double getCenterZ() {
				return super.getCenterZ() / 8.0D;
			}

		};
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(ConfigHandler.dimensionIdXia);
	}

}
