package tamaized.voidcraft.common.world;

import tamaized.voidcraft.common.handlers.ConfigHandler;
import tamaized.voidcraft.registry.VoidCraftBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorVoid implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int id = world.provider.getDimension();
		if (id == 1)
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		if (id == ConfigHandler.dimensionIdVoid)
			generateVoid(world, random, chunkX * 16, chunkZ * 16);
		if (id == ConfigHandler.dimensionIdDalQuor)
			generateDalQuor(world, random, chunkX * 16, chunkZ * 16);
	}

	private void generateEnd(World world, Random random, int BlockX, int BlockZ) {
		if (ConfigHandler.generate_VoidOre) {
			for (int i = 0; i < 6; i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(100);
				new WorldGenMinable(VoidCraftBlocks.oreVoidcrystal.getStateVoidFalse(), 5, input -> input == Blocks.END_STONE.getDefaultState()).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
		if (ConfigHandler.generate_CosmicOre) {
			for (int i = 0; i < 6; i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(100);
				new WorldGenMinable(VoidCraftBlocks.cosmicMaterial.getDefaultState(), 3, input -> input == Blocks.END_STONE.getDefaultState()).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
	}

	private void generateVoid(World world, Random random, int BlockX, int BlockZ) {
		if (ConfigHandler.generate_CosmicOre) {
			for (int i = 0; i < 10; i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(255);
				new WorldGenMinable(VoidCraftBlocks.cosmicMaterial.getDefaultState(), 3, input -> input == VoidCraftBlocks.blockFakeBedrock.getDefaultState()).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
	}

	private void generateDalQuor(World world, Random random, int BlockX, int BlockZ) {
		if (ConfigHandler.generate_CosmicOre) {
			for (int i = 0; i < 16; i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(255);
				new WorldGenMinable(VoidCraftBlocks.cosmicMaterial.getDefaultState(), 7, input -> input != Blocks.AIR.getDefaultState()).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
		if (ConfigHandler.generate_VoidOre) {
			for (int i = 0; i < 10; i++) {
				int Xcoord = BlockX + random.nextInt(16);
				int Zcoord = BlockZ + random.nextInt(16);
				int Ycoord = random.nextInt(255);
				new WorldGenMinable(VoidCraftBlocks.oreVoidcrystal.getDefaultState(), 5, input -> input == VoidCraftBlocks.blockFakeBedrock.getDefaultState()).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
			}
		}
	}
}