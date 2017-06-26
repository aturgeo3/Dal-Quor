package Tamaized.Voidcraft.common.structures.voidfortress;

import Tamaized.Voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.common.entity.mob.EntityMobVoidWrath;
import com.google.common.collect.Lists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class MapGenVoidFortress extends MapGenStructure {

	private final List<Biome.SpawnListEntry> spawnList = Lists.<Biome.SpawnListEntry>newArrayList();

	public MapGenVoidFortress() {
		this.spawnList.add(new SpawnListEntry(EntityMobVoidWrath.class, 3, 0, 1));
		this.spawnList.add(new SpawnListEntry(EntityHerobrineCreeper.class, 1, 0, 1));
	}

	@Override
	public String getStructureName() {
		return "Fortress";
	}

	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		int i = 1000;
		int j = pos.getX() >> 4;
		int k = pos.getZ() >> 4;

		for (int l = 0; l <= 1000; ++l) {
			for (int i1 = -l; i1 <= l; ++i1) {
				boolean flag = i1 == -l || i1 == l;

				for (int j1 = -l; j1 <= l; ++j1) {
					boolean flag1 = j1 == -l || j1 == l;

					if (flag || flag1) {
						int k1 = j + i1;
						int l1 = k + j1;

						if (this.canSpawnStructureAtCoords(k1, l1) && (!findUnexplored || !worldIn.isChunkGeneratedAt(k1, l1))) {
							return new BlockPos((k1 << 4) + 8, 64, (l1 << 4) + 8);
						}
					}
				}
			}
		}

		return null;
	}

	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.spawnList;
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		int i = chunkX >> 4;
		int j = chunkZ >> 4;
		this.rand.setSeed((long) (i ^ j << 4) ^ this.world.getSeed());
		this.rand.nextInt();
		return this.rand.nextInt(3) == 0 && (chunkX != (i << 4) + 4 + this.rand.nextInt(8) ? false : chunkZ == (j << 4) + 4 + this.rand.nextInt(8));
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenVoidFortress.Start(this.world, this.rand, chunkX, chunkZ);
	}

	public static class Start extends StructureStart {

		public Start() {

		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			super(chunkX, chunkZ);
			StructureVoidFortressPieces.Start structurenetherbridgepieces$start = new StructureVoidFortressPieces.Start(random, (chunkX << 4) + 2, (chunkZ << 4) + 2);
			this.components.add(structurenetherbridgepieces$start);
			structurenetherbridgepieces$start.buildComponent(structurenetherbridgepieces$start, this.components, random);
			List<StructureComponent> list = structurenetherbridgepieces$start.pendingChildren;

			while (!list.isEmpty()) {
				int i = random.nextInt(list.size());
				StructureComponent structurecomponent = (StructureComponent) list.remove(i);
				structurecomponent.buildComponent(structurenetherbridgepieces$start, this.components, random);
			}

			this.updateBoundingBox();
			this.setRandomHeight(worldIn, random, 48, 70);
		}
	}

}
