package tamaized.dalquor.common.structures.voidcity;

import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.common.entity.mob.EntityMobEtherealGuardian;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class MapGenVoidCity extends MapGenStructure {
	private final int citySpacing = 20;
	private final int minCitySeparation = 11;
	private final IChunkGenerator worldProvider;

	private final List<Biome.SpawnListEntry> spawnList = Lists.newArrayList();

	public MapGenVoidCity(IChunkGenerator p_i46665_1_) {
		this.worldProvider = p_i46665_1_;

		spawnList.add(new Biome.SpawnListEntry(EntityMobEtherealGuardian.class, 1, 1, 3));
	}

	private static int getYPosForStructure(int p_191070_0_, int p_191070_1_, IChunkGenerator p_191070_2_) {
		return 128;
	}

	@Override
	public void generate(World worldIn, int x, int z, ChunkPrimer primer) {
		super.generate(worldIn, x, z, primer);
	}

	@Override
	public String getStructureName() {
		return VoidCraft.modid + "EndCity";
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		this.world = worldIn;
		return findNearestStructurePosBySpacing(worldIn, this, pos, 20, 11, 10387313, true, 100, findUnexplored);
	}

	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.spawnList;
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		int i = chunkX;
		int j = chunkZ;

		if (chunkX < 0) {
			chunkX -= 19;
		}

		if (chunkZ < 0) {
			chunkZ -= 19;
		}

		int k = chunkX / 20;
		int l = chunkZ / 20;
		Random random = this.world.setRandomSeed(k, l, 10387313);
		k = k * 20;
		l = l * 20;
		k = k + (random.nextInt(5) + random.nextInt(5)) / 2;
		l = l + (random.nextInt(5) + random.nextInt(5)) / 2;

		if (i == k && j == l) {
			int i1 = getYPosForStructure(i, j, this.worldProvider);
			return i1 >= 60;
		} else {
			return false;
		}
	}

	private ChunkPrimer primer;

	public void setPrimer(ChunkPrimer p) {
		primer = p;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenVoidCity.Start(this.world, this.worldProvider, this.rand, chunkX, chunkZ, primer);
	}

	public BlockPos getClosestStrongholdPos(World worldIn, BlockPos pos, boolean p_180706_3_) {
		this.world = worldIn;
		return findNearestStructurePosBySpacing(worldIn, this, pos, 20, 11, 10387313, true, 100, p_180706_3_);
	}

	public static class Start extends StructureStart {
		private boolean isSizeable;

		public Start() {
		}

		public Start(World worldIn, IChunkGenerator chunkProvider, Random random, int chunkX, int chunkZ, ChunkPrimer primer) {
			super(chunkX, chunkZ);
			this.create(worldIn, chunkProvider, random, chunkX, chunkZ, primer);
		}

		private void create(World worldIn, IChunkGenerator chunkProvider, Random rnd, int chunkX, int chunkZ, ChunkPrimer primer) {
			Random random = new Random((long) (chunkX + chunkZ * 10387313));
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			int i = MapGenVoidCity.getYPosForStructure(chunkX, chunkZ, chunkProvider);

			if (i < 60 || (primer != null && primer.getBlockState(0, i - 1, 0).getBlock() == Blocks.AIR)) {
				this.isSizeable = false;
			} else {
				BlockPos blockpos = new BlockPos(chunkX * 16 + 8, i, chunkZ * 16 + 8);
				StructureVoidCityPieces.startHouseTower(worldIn.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, this.components, rnd);
				this.updateBoundingBox();
				this.isSizeable = true;
			}
		}

		/**
		 * currently only defined for Villages, returns true if Village has more than 2 non-road components
		 */
		@Override
		public boolean isSizeableStructure() {
			return this.isSizeable;
		}
	}

}