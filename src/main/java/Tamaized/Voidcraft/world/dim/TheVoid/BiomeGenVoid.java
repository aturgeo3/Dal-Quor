package Tamaized.Voidcraft.world.dim.TheVoid;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.mobs.entity.EntityMobSpectreChain;
import Tamaized.Voidcraft.mobs.entity.EntityMobWraith;

public class BiomeGenVoid extends BiomeGenBase {

	public BiomeGenVoid(int id) {
		super(id);
		
		this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        //this.topBlock = voidCraft.blockFakeBedrock;
		//this.fillerBlock = voidCraft.blockFakeBedrock;
		this.spawnableCreatureList.add(new SpawnListEntry(EntityMobWraith.class, 1, 0, 1));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityMobSpectreChain.class, 1, 0, 1));
		}
	
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1)
	{
		 return 0;
	}

}
