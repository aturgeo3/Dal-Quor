package tamaized.dalquor.common.world.dim.thevoid;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tamaized.dalquor.common.handlers.ConfigHandler;
import tamaized.dalquor.common.structures.voidcity.MapGenVoidCity;
import tamaized.dalquor.common.structures.voidfortress.MapGenVoidFortress;
import tamaized.dalquor.registry.ModBlocks;
import tamaized.dalquor.registry.ModFluids;

import java.util.List;
import java.util.Random;

public class ChunkProviderVoid implements IChunkGenerator {

	public final MapGenVoidFortress genFortress = new MapGenVoidFortress();
	public final MapGenVoidCity genCity = new MapGenVoidCity(this);
	private final Random rand;
	private final World world;
	private final WorldType terrainType;
	private final double[] heightMap;
	private final float[] biomeWeights;
	private NoiseGeneratorOctaves depthNoise;
	private NoiseGeneratorOctaves realityHoleNoiseGen;
	private double[] mainNoiseRegion;
	private double[] minLimitRegion;
	private double[] maxLimitRegion;
	private double[] depthRegion;
	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private Biome[] biomesForGeneration;
	private double[] realityHoleNoise;

	public ChunkProviderVoid(World worldIn, boolean mapFeaturesEnabledIn, long seed) {
		world = worldIn;
		terrainType = worldIn.getWorldInfo().getTerrainType();
		rand = new Random(seed);
		minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
		depthNoise = new NoiseGeneratorOctaves(rand, 10);
		realityHoleNoiseGen = new NoiseGeneratorOctaves(rand, 4);
		heightMap = new double[825];
		realityHoleNoise = new double[16];
		biomeWeights = new float[25];

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
				biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}

	}

	private void generateHeightmap(int xOffset, int yOffset, int zOffset) {
		final double coordinateScale = 684.412F;
		final double heightScale = 400;
		final double mainNoiseScaleX = 80.0F;
		final double mainNoiseScaleY = 1;
		final double mainNoiseScaleZ = 80.0F;
		final double baseSize = 8.5F;
		final double stretchY = 0.5F;
		final float biomeDepthWeight = 0.0F;
		final float biomeDepthOffset = 0.0F;
		final float biomeScaleWeight = 0.0F;
		final float biomeScaleOffset = 0.0F;
		final double upperLimitScale = 64;
		final double lowerLimitScale = 64;
		final double depthNoiseScaleX = 200.0F;
		final double depthNoiseScaleZ = 200.0F;
		final double depthNoiseScaleExponent = 0.5F;
		depthRegion = depthNoise.generateNoiseOctaves(depthRegion, xOffset, zOffset, 5, 5, depthNoiseScaleX, depthNoiseScaleZ, depthNoiseScaleExponent);
		mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, xOffset, yOffset, zOffset, 5, 33, 5, coordinateScale / mainNoiseScaleX, heightScale / mainNoiseScaleY, coordinateScale / mainNoiseScaleZ);
		minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, xOffset, yOffset, zOffset, 5, 33, 5, coordinateScale, heightScale, coordinateScale);
		maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, xOffset, yOffset, zOffset, 5, 33, 5, coordinateScale, heightScale, coordinateScale);
		realityHoleNoise = realityHoleNoiseGen.generateNoiseOctaves(realityHoleNoise, xOffset * 4, zOffset * 4, 0, 4, 4, 1, 0.03125D, 0.03125D, 1.0D);

		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				int i1 = 2;
				Biome biome = biomesForGeneration[k + 2 + (l + 2) * 10];

				for (int j1 = -2; j1 <= 2; ++j1) {
					for (int k1 = -2; k1 <= 2; ++k1) {
						Biome biome1 = biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
						float f5 = biomeDepthOffset + biome1.getBaseHeight() * biomeDepthWeight;
						float f6 = biomeScaleOffset + biome1.getHeightVariation() * biomeScaleWeight;

						if (terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
							f5 = 1.0F + f5 * 2.0F;
							f6 = 1.0F + f6 * 4.0F;
						}

						float f7 = biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

						if (biome1.getBaseHeight() > biome.getBaseHeight()) {
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = depthRegion[j] / 8000.0D;

				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}

				d7 = d7 * 3.0D - 2.0D;

				if (d7 < 0.0D) {
					d7 = d7 / 2.0D;

					if (d7 < -1.0D) {
						d7 = -1.0D;
					}

					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				} else {
					if (d7 > 1.0D) {
						d7 = 1.0D;
					}

					d7 = d7 / 8.0D;
				}

				++j;
				double d8 = (double) f3;
				double d9 = (double) f2;
				d8 = d8 + d7 * 0.2D;
				d8 = d8 * baseSize / 8.0D;
				double d0 = baseSize + d8 * 4.0D;
				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = ((double) l1 - d0) * stretchY * 128.0D / 256.0D / d9;

					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}

					double d2 = minLimitRegion[i] / lowerLimitScale;
					double d3 = maxLimitRegion[i] / upperLimitScale;
					double d4 = (mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (l1 > 29) {
						double d6 = (double) ((float) (l1 - 29) / 3.0F);
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					heightMap[i] = d5;
					++i;
				}
			}
		}
	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
		final int seaLevel = 63;
		biomesForGeneration = world.getBiomeProvider().getBiomesForGeneration(biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		generateHeightmap(x * 4, 0, z * 4);

		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				boolean flag = false;//realityHoleNoise[l + i * 4] + this.rand.nextDouble() * 0.2D > 0.0D;
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {
					double d0 = 0.125D;
					double d1 = heightMap[i1 + i2];
					double d2 = heightMap[j1 + i2];
					double d3 = heightMap[k1 + i2];
					double d4 = heightMap[l1 + i2];
					double d5 = (heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								if ((lvt_45_1_ += d16) > 0.0D && i2 * 8 + j2 < 128) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, (flag) ? ModBlocks.realityHole.getDefaultState() : ModBlocks.ethericPlatform.getDefaultState());
								} else if (i2 * 8 + j2 < seaLevel) {
									//primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.AIR.getDefaultState());
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		setBlocksInChunk(x, z, chunkprimer);
		genCity.setPrimer(chunkprimer);
		genCity.generate(world, x, z, chunkprimer);
		genFortress.generate(world, x, z, chunkprimer);
		Chunk chunk = new Chunk(world, chunkprimer, x, z);
		double d0 = 0.03125D;
		Biome[] abiome = world.getBiomeProvider().getBiomesForGeneration(null, x * 16, z * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();
		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(abiome[i]);
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		BlockPos blockpos = new BlockPos(x * 16, 0, z * 16);
		ChunkPos chunkpos = new ChunkPos(x, z);
		genFortress.generateStructure(world, rand, chunkpos);
		genCity.generateStructure(world, rand, chunkpos);
		int k1;
		WorldGenMinable genCrystalOre = new WorldGenMinable(ModBlocks.oreVoidcrystal.getDefaultState(), 5, input -> (input == ModBlocks.ethericPlatform.getDefaultState()));
		if (ConfigHandler.generate_VoidOre) {
			for (k1 = 0; k1 < 16; ++k1) {
				genCrystalOre.generate(world, rand, blockpos.add(rand.nextInt(16), rand.nextInt(200) + 50, rand.nextInt(16)));
			}
		}
		new WorldGenLakes(ModFluids.arcaneSludgeFluidBlock).generate(world, rand, blockpos.add(rand.nextInt(16) + 8, rand.nextInt(200) + 50, rand.nextInt(16) + 8));
		new WorldGenLakes(ModBlocks.realityHole).generate(world, rand, blockpos.add(rand.nextInt(16) + 8, rand.nextInt(100) + 20, rand.nextInt(16) + 8));
		{
			BlockPos pos = blockpos.add(rand.nextInt(16) + 8, rand.nextInt(100) + 20, rand.nextInt(16) + 8);
			if (world.getBlockState(pos).getBlock() == ModBlocks.ethericPlatform)
				world.setBlockState(pos, ModFluids.arcaneSludgeFluidBlock.getDefaultState(), 2);
		}
		BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		if (genFortress != null) {
			if (genFortress.isInsideStructure(pos)) {
				if (Math.floor(Math.random() * 10) == 0)
					return genFortress.getSpawnList();
				// if(par1EnumCreatureType == EnumCreatureType.creature) return genTest.getSpawnList();
			}
		}
		if (genFortress.isPositionInStructure(world, pos) && world.getBlockState(pos.down()).getBlock() == ModBlocks.voidbrick) {
			if (Math.floor(Math.random() * 40) == 0)
				return genFortress.getSpawnList();
			// if(par1EnumCreatureType == EnumCreatureType.creature) return genTest.getSpawnList();
		}
		if (pos.getY() >= 128) {
			IBlockState state = world.getBlockState(pos.down());
			if (state != null && state.getBlock() == ModBlocks.voidbrick && world.rand.nextInt(5) == 0) {
				return genCity.getSpawnList();
			}
		}
		return world.getBiomeForCoordsBody(pos).getSpawnableList(creatureType);// voidCraft.biomes.biomeVoid.getSpawnableList(creatureType);//new ArrayList();//world.getBiomeGenForCoords(pos).getSpawnableList(creatureType);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return structureName.equals(genFortress.getStructureName()) ?

				genFortress.getNearestStructurePos(worldIn, position, findUnexplored) :

				structureName.equals(genCity.getStructureName()) ?

						genCity.getClosestStrongholdPos(worldIn, position, findUnexplored) :

						null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos position) {
		return structureName.equals(genFortress.getStructureName()) ?

				genFortress.isInsideStructure(position) :

				structureName.equals(genCity.getStructureName()) &&

						genCity.isInsideStructure(position);
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		genFortress.generate(world, x, z, null);
		genCity.generate(world, x, z, null);
	}
}
