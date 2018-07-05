package tamaized.dalquor.common.structures.xia;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.Random;

public class MapGenXiaCastle extends MapGenStructure {

	private final IChunkGenerator chunkGenerator;
	private ChunkPrimer primer;

	public MapGenXiaCastle(IChunkGenerator generator) {
		chunkGenerator = generator;
	}

	public MapGenXiaCastle setPrimer(ChunkPrimer p) {
		primer = p;
		return this;
	}

	@Override
	public String getStructureName() {
		return "xiacastle";
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return findNearestStructurePosBySpacing(worldIn, this, pos, 20, 11, 10387313, true, 100, findUnexplored);
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		return chunkX == 3 && chunkZ == 0;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenXiaCastle.Start(world, chunkGenerator, rand, chunkX, chunkZ, primer);
	}

	public static class Start extends StructureStart {

		private boolean sizeable;

		public Start() {

		}

		public Start(World worldIn, IChunkGenerator chunkProvider, Random random, int chunkX, int chunkZ, ChunkPrimer primer) {
			super(chunkX, chunkZ);
			this.create(worldIn, chunkProvider, random, chunkX, chunkZ, primer);
		}

		private void create(World worldIn, IChunkGenerator chunkProvider, Random rnd, int chunkX, int chunkZ, ChunkPrimer primer) {
			Random random = new Random((long) (chunkX + chunkZ * 10387313));
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			if (primer == null)
				sizeable = false;
			else {
				BlockPos blockpos = new BlockPos(chunkX * 16 + 8, 60, chunkZ * 16 + 8);
				StructureXiaCastlePieces.start(worldIn.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, this.components, rnd);
				this.updateBoundingBox();
				sizeable = true;
			}
		}

		@Override
		public boolean isSizeableStructure() {
			return sizeable;
		}
	}
}
