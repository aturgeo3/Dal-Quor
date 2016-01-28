package Tamaized.Voidcraft.world.dim.Xia;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderXia implements IChunkProvider{
	
	private World worldObj;

	public ChunkProviderXia(World worldObj, long seed) {
		this.worldObj = worldObj;
	}

	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public Chunk provideChunk(int par1, int par2) {
		ChunkPrimer primer = new ChunkPrimer();
		Block[] ablock = new Block[32768];
        byte[] meta = new byte[ablock.length];
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16); //Forge Move up to allow for passing to replaceBiomeBlocks
        //this.generateTerrain(par1, par2, ablock);
        //this.replaceBlocksForBiome(par1, par2, ablock, meta, abiomegenbase);
        //this.genTest.func_151539_a(this, this.worldObj, par1, par2, ablock);
        Chunk chunk = new Chunk(this.worldObj, par1, par2);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)abiomegenbase[k].biomeID;
        }
        
        chunk.resetRelightChecks();
        return chunk;
	}

	public Chunk loadChunk(int par1, int par2) {
		return this.provideChunk(par1, par2);
	}

	@Override
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
		
	}

	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "Xia";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
		return new ArrayList();
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(Chunk chunk, int par1, int par2) {
		
	}

	@Override
	public void saveExtraData() {
		
	}

	@Override
	public Chunk provideChunk(BlockPos blockPosIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
		return false;
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
		return null;
	}

}
