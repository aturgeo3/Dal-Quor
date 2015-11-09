package Tamaized.Voidcraft.world.dim.TheVoid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import Tamaized.Voidcraft.common.voidCraft;
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
		this.spawnableMonsterList.add(new SpawnListEntry(EntityMobWraith.class, 1, 1, 1));
		}
	
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1)
	{
		 return 0;
	}

}
