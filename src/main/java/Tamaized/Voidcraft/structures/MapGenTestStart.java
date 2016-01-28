package Tamaized.Voidcraft.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import Tamaized.Voidcraft.mobs.entity.EntityMobVoidWrath;
import Tamaized.Voidcraft.mobs.entity.EntityMobWraith;
import Tamaized.Voidcraft.world.dim.TheVoid.ChunkProviderVoid;

public class MapGenTestStart extends MapGenStructure {
	
	private List spawnList = new ArrayList();

    public MapGenTestStart()
    {       
    	this.spawnList.add(new SpawnListEntry(EntityMobVoidWrath.class, 1, 0, 1));
    }

    @Override
    public String getStructureName(){
        return "VoidFortress";
    }

    public List getSpawnList(){
        return this.spawnList;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int par1, int par2){
        int k = par1 >> 4;
        int l = par2 >> 4;
        this.rand.setSeed((long)(k ^ l << 4) ^ this.worldObj.getSeed());
        this.rand.nextInt();
        return this.rand.nextInt(3) != 0 ? false : (par1 != (k << 4) + 4 + this.rand.nextInt(8) ? false : par2 == (l << 4) + 4 + this.rand.nextInt(8));
    }

    @Override
    protected StructureStart getStructureStart(int par1, int par2){
        return new StructureTestStart(this.worldObj, this.rand, par1, par2);
    }

}
