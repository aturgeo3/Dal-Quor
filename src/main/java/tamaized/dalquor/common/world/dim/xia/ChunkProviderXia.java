package tamaized.dalquor.common.world.dim.xia;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import tamaized.dalquor.common.structures.xia.MapGenXiaCastle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChunkProviderXia implements IChunkGenerator {

	private World world;
	private MapGenXiaCastle xiaCastle = new MapGenXiaCastle();
	private Random rand = new Random();

	public ChunkProviderXia(World worldIn, boolean p_i45637_2_, long seed) {
		this.world = worldIn;
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();

		xiaCastle.generate(world, x, z, chunkprimer);

		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		Biome[] abiome = this.world.getBiomeProvider().getBiomesForGeneration(null, x * 16, z * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(abiome[i]);
		}

		chunk.resetRelightChecks();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		BlockPos blockpos = new BlockPos(x * 16, 0, z * 16);
		ChunkPos chunkpos = new ChunkPos(x, z);

		xiaCastle.generateStructure(world, rand, chunkpos);

		BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return new ArrayList();
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {
		xiaCastle.generate(world, x, z, null);
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos position) {
		return structureName.equals(xiaCastle.getStructureName()) && xiaCastle.isInsideStructure(position);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return structureName.equals(xiaCastle.getStructureName()) ? xiaCastle.getNearestStructurePos(world, position, findUnexplored) : null;
	}

}
