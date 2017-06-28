package tamaized.voidcraft.common.world.dim.thevoid;

import tamaized.voidcraft.common.entity.companion.EntityVoidParrot;
import tamaized.voidcraft.common.entity.mob.EntityMobSpectreChain;
import tamaized.voidcraft.common.entity.mob.EntityMobWraith;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenVoid extends Biome {

	public BiomeGenVoid(BiomeProperties prop) {
		super(prop);

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();

		this.spawnableCreatureList.add(new SpawnListEntry(EntityMobWraith.class, 1, 0, 1));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityMobSpectreChain.class, 1, 0, 1));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityVoidParrot.class, 1, 0, 1));

	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return 0;
	}

	@Override
	public boolean canRain() {
		return false;
	}

}
