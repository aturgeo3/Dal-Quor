package Tamaized.Voidcraft.world.dim.Xia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.world.SchematicLoader;
import Tamaized.Voidcraft.world.SchematicLoader.Schematic;

public class TeleporterXia extends Teleporter {

	private SchematicLoader sut;

	private final WorldServer worldServerInstance;
	private final Random random;

	boolean dospawn = false;

	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List destinationCoordinateKeys = new ArrayList();

	public TeleporterXia(WorldServer par1WorldServer) {
		super(par1WorldServer);

		this.worldServerInstance = par1WorldServer;
		this.random = new Random(par1WorldServer.getSeed());
		doStructure(par1WorldServer);
	}
	
	private void doStructure(WorldServer par1WorldServer){
		if(par1WorldServer == null) return;
		if (!this.worldServerInstance.isRemote && this.worldServerInstance.provider.dimensionId == voidCraft.dimensionIdXia) {
			sut = new SchematicLoader();
			Schematic spring = sut.get("VoidCastle-Redstone_WithTnT.schematic");
			int i = 0;
			for (int cy = 0; cy < spring.height; cy++){
				for (int cz = 0; cz < spring.length; cz++){
					for (int cx = 0; cx < spring.width; cx++) {

						Block b = Block.getBlockById(spring.blocks[i]);
						if (b != Blocks.air) {
							par1WorldServer.setBlockToAir(cx, cy + 160, cz);
							if (b != Blocks.stone)
								par1WorldServer.setBlock(cx, cy + 160, cz, b, spring.data[i], 2);
							else
								par1WorldServer.setBlock(cx, cy + 160, cz, voidCraft.blocks.blockNoBreak, spring.data[i], 2);
						}
						i++;
					}
				}
			}

			/*
			 * //Set the Bedrock Box this.worldServerInstance.setBlock(1, 140,
			 * 1, Blocks.bedrock); this.worldServerInstance.setBlock(1, 140, 0,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(1, 140, -1,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(1, 140, -2,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(0, 140, 1,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(0, 140, 0,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(0, 140, -1,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(0, 140, -2,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-1, 140, 1,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-1, 140, 0,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-1, 140, -1,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-1, 140, -2,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-2, 140, 1,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-2, 140, 0,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-2, 140, -1,
			 * Blocks.bedrock); this.worldServerInstance.setBlock(-2, 140, -2,
			 * Blocks.bedrock);
			 * 
			 * this.worldServerInstance.setBlock(2, 139, 2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(2, 139, 1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(2, 139, 0, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(2, 139, -1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(2, 139, -2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(2, 139, -3, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(1, 139, 2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(1, 139, -3, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(0, 139, 2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(0, 139, -3, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-1, 139, 2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-1, 139, -3, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-2, 139, 2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-2, 139, -3, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-3, 139, 2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-3, 139, 1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-3, 139, 0, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-3, 139, -1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-3, 139, -2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-3, 139, -3, Blocks.bedrock);
			 * 
			 * this.worldServerInstance.setBlock(1, 138, 1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(1, 138, 0, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(1, 138, -1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(1, 138, -2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(0, 138, 1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(0, 138, 0, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(0, 138, -1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(0, 138, -2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-1, 138, 1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-1, 138, 0, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-1, 138, -1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-1, 138, -2, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-2, 138, 1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-2, 138, 0, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-2, 138, -1, Blocks.bedrock);
			 * this.worldServerInstance.setBlock(-2, 138, -2, Blocks.bedrock);
			 * 
			 * //Set the Bricks (Border of bedrock)
			 * this.worldServerInstance.setBlock(2, 140, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(2,
			 * 140, 1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(2, 140, 0,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(2,
			 * 140, -1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(2, 140, -2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(2,
			 * 140, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 140, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 140, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 140, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(0,
			 * 140, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-1, 140, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 140, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 140, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-2,
			 * 140, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 140, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 140, 1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 140, 0,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 140, -1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 140, -2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 140, -3, voidCraft.blockVoidbrick);
			 * 
			 * //Torches this.worldServerInstance.setBlock(-2, 141, 1,
			 * Blocks.torch); this.worldServerInstance.setBlock(-2, 141, -2,
			 * Blocks.torch); this.worldServerInstance.setBlock(1, 141, -2,
			 * Blocks.torch); this.worldServerInstance.setBlock(1, 141, 1,
			 * Blocks.torch);
			 * 
			 * //Structures //1 this.worldServerInstance.setBlock(0, 140, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 140, -8, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 140, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 140, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 140, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 140, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 140, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 140, -4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 140, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 140, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 140, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 140, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 141, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 141, -8, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 141, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 141, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 141, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 141, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 141, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 141, -4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 141, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 141, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 141, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 141, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 142, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 142, -8, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 142, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 142, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 142, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 142, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 142, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 142, -4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 142, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 142, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 142, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 142, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, -8, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, -8,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 143, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 143, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 143, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, -4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, -4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 143, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 143, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 143, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, -5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, -5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, -5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(0,
			 * 143, -6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-1, 143, -6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-2,
			 * 143, -6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, -7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, -7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, -7,
			 * voidCraft.blockVoidbrick); //2
			 * this.worldServerInstance.setBlock(0, 140, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 140, 7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 140, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 140, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 140, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 140, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 140, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 140, 3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 140, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 140, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 140, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 140, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 141, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 141, 7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 141, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 141, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 141, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 141, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 141, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 141, 3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 141, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 141, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 141, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 141, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 142, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 142, 7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 142, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 142, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 142, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 142, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 142, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 142, 3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 142, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 142, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 142, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 142, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, 7, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, 7,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 143, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(1, 143, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(1,
			 * 143, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, 3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, 3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 143, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 143, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 143, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, 6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, 6, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, 6,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(0,
			 * 143, 5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-1, 143, 5,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-2,
			 * 143, 5, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(0, 143, 4,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-1,
			 * 143, 4, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-2, 143, 4,
			 * voidCraft.blockVoidbrick);
			 * 
			 * //Xia's Spot this.worldServerInstance.setBlock(-3, 141, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 142, 2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 143, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 141, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-3, 142, -3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-3,
			 * 143, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-4, 141, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-4,
			 * 142, 2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-4, 143, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-4,
			 * 141, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-4, 142, -3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-4,
			 * 143, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-5, 141, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-5,
			 * 142, 2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-5, 143, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-5,
			 * 141, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-5, 142, -3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-5,
			 * 143, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-6, 141, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-6,
			 * 142, 2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-6, 143, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-6,
			 * 141, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-6, 142, -3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-6,
			 * 143, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-7, 141, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-7,
			 * 142, 2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-7, 143, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-7,
			 * 141, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-7, 142, -3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-7,
			 * 143, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 141, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 142, 2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 143, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 141, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 142, -3,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 143, -3, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 141, -2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 142, -2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 143, -2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 141, -1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 142, -1,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 143, -1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 141, 0,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 142, 0, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 143, 0,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 141, 1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 142, 1,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 143, 1, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 141, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-8,
			 * 142, 2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-8, 143, 2,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-7,
			 * 143, -2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-7, 143, -1,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-7,
			 * 143, 0, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-7, 143, 1,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-6,
			 * 143, -2, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-6, 143, -1,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-6,
			 * 143, 0, voidCraft.blockVoidbrick);
			 * this.worldServerInstance.setBlock(-6, 143, 1,
			 * voidCraft.blockVoidbrick); this.worldServerInstance.setBlock(-5,
			 * 144, 2, voidCraft.blockVoidfence);
			 * this.worldServerInstance.setBlock(-6, 144, 2,
			 * voidCraft.blockVoidfence); this.worldServerInstance.setBlock(-7,
			 * 144, 2, voidCraft.blockVoidfence);
			 * this.worldServerInstance.setBlock(-8, 144, 2,
			 * voidCraft.blockVoidfence); this.worldServerInstance.setBlock(-8,
			 * 144, 1, voidCraft.blockVoidfence);
			 * this.worldServerInstance.setBlock(-8, 144, 0,
			 * voidCraft.blockVoidfence); this.worldServerInstance.setBlock(-8,
			 * 144, -1, voidCraft.blockVoidfence);
			 * this.worldServerInstance.setBlock(-8, 144, -2,
			 * voidCraft.blockVoidfence); this.worldServerInstance.setBlock(-8,
			 * 144, -3, voidCraft.blockVoidfence);
			 * this.worldServerInstance.setBlock(-7, 144, -3,
			 * voidCraft.blockVoidfence); this.worldServerInstance.setBlock(-6,
			 * 144, -3, voidCraft.blockVoidfence);
			 * this.worldServerInstance.setBlock(-5, 144, -3,
			 * voidCraft.blockVoidfence); this.worldServerInstance.setBlock(-5,
			 * 143, -2, voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-5, 143, -1,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-5, 143, 0,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-5, 143, 1,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-4, 142, -2,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-4, 142, -1,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-4, 142, 0,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-4, 142, 1,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-3, 141, -2,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-3, 141, -1,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-3, 141, 0,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * this.worldServerInstance.setBlock(-3, 141, 1,
			 * voidCraft.blockVoidstairs, 1, 3);
			 * 
			 * 
			 * 
			 * if(worldServerInstance.getLoadedEntityList() != null){ List l =
			 * worldServerInstance.getLoadedEntityList();
			 * 
			 * 
			 * 
			 * 
			 * 
			 * for (int i = 0; i < l.size(); ++i) { if(i < l.size()){
			 * EntityLivingBase x = (EntityLivingBase) l.get(i); if (x != null)
			 * { x.setDead(); } if(i == l.size() - 1) dospawn = true; }
			 * 
			 * 
			 * }
			 * 
			 * if(l.size() == 0) dospawn = true; }
			 * 
			 * if(dospawn == true){ dospawn = false;
			 * 
			 * EntityMobHerobrine hb = new
			 * EntityMobHerobrine(worldServerInstance); hb.setPosition(-1, 145,
			 * 4); worldServerInstance.spawnEntityInWorld(hb);
			 * 
			 * EntityMobDol ed = new EntityMobDol(worldServerInstance);
			 * ed.setPosition(-2, 145, -5);
			 * worldServerInstance.spawnEntityInWorld(ed);
			 * 
			 * EntityMobZol ez = new EntityMobZol(worldServerInstance);
			 * ez.setPosition(0, 145, -5);
			 * worldServerInstance.spawnEntityInWorld(ez); }
			 * 
			 * 
			 * this.worldServerInstance.setBlock(0, 139, 0, voidCraft.voidBox);
			 * TileEntityVoidBox tevb = (TileEntityVoidBox)
			 * this.worldServerInstance.getTileEntity(0, 139, 0);
			 * 
			 * if(tevb != null){ int i = 4; int xcoord = 0; int ycoord = 139;
			 * int zcoord = 0;
			 * 
			 * 
			 * ByteBufOutputStream bos = new
			 * ByteBufOutputStream(Unpooled.buffer()); DataOutputStream
			 * outputStream = new DataOutputStream(bos); try {
			 * outputStream.writeInt(i);
			 * outputStream.writeInt(voidCraft.voidDisc16
			 * .getIdFromItem(voidCraft.voidDisc16));
			 * outputStream.writeInt(xcoord); outputStream.writeInt(ycoord);
			 * outputStream.writeInt(zcoord);
			 * 
			 * 
			 * } catch (Exception ex) { ex.printStackTrace(); }
			 * 
			 * FMLProxyPacket packet = new FMLProxyPacket(bos.buffer(),
			 * voidCraft.networkChannelName);
			 * 
			 * 
			 * System.out.println("Server packet ID #4 has been Sent");
			 * //PacketDispatcher.sendPacketToServer(packet);
			 * voidCraft.channel.sendToServer(packet); try { bos.close(); }
			 * catch (IOException e) { e.printStackTrace(); }
			 * 
			 * 
			 * 
			 * 
			 * ByteBufOutputStream zbosz = new
			 * ByteBufOutputStream(Unpooled.buffer()); DataOutputStream
			 * zoutputStreamz = new DataOutputStream(zbosz);
			 * 
			 * 
			 * try { zoutputStreamz.writeInt(3);
			 * zoutputStreamz.writeInt(voidCraft
			 * .voidDisc16.getIdFromItem(voidCraft.voidDisc16));
			 * zoutputStreamz.writeInt(xcoord); zoutputStreamz.writeInt(ycoord);
			 * zoutputStreamz.writeInt(zcoord);
			 * zoutputStreamz.writeBoolean(true);
			 * 
			 * } catch (Exception ex) { ex.printStackTrace(); }
			 * 
			 * FMLProxyPacket zpacket = new FMLProxyPacket(zbosz.buffer(),
			 * voidCraft.networkChannelName);
			 * 
			 * 
			 * System.out.println("Server packet ID #4 has been Sent");
			 * //PacketDispatcher.sendPacketToServer(packet);
			 * voidCraft.channel.sendToServer(zpacket); try { zbosz.close(); }
			 * catch (IOException e) { e.printStackTrace(); }
			 * 
			 * }else{ System.out.println("Null!"); }
			 */
		}
	}

	public void placeInPortal(Entity par1Entity, double par2, double par4,
			double par6, float par8) {
		par1Entity.setPosition(10, 161, 3);
	}

}

/**
 * Place an entity in a nearby portal, creating one if necessary.
 * 
 * public void placeInPortal(Entity par1Entity, double par2, double par4, double
 * par6, float par8) { if (this.worldServerInstance.provider.dimensionId != 1) {
 * if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8)) {
 * this.makePortal(par1Entity); this.placeInExistingPortal(par1Entity, par2,
 * par4, par6, par8); } } else { int i =
 * MathHelper.floor_double(par1Entity.posX); int j =
 * MathHelper.floor_double(par1Entity.posY) - 1; int k =
 * MathHelper.floor_double(par1Entity.posZ); byte b0 = 1; byte b1 = 0; for (int
 * l = -2; l <= 2; ++l) { for (int i1 = -2; i1 <= 2; ++i1) { for (int j1 = -1;
 * j1 < 3; ++j1) { int k1 = i + i1 * b0 + l * b1; int l1 = j + j1; int i2 = k +
 * i1 * b1 - l * b0; boolean flag = j1 < 0;
 * 
 * /** change this Blocks this.worldServerInstance.setBlock(k1, l1, i2, flag ?
 * voidCraft.BlocksFakeBedrock : 0); } } }
 * par1Entity.setLocationAndAngles((double)i, (double)j, (double)k,
 * par1Entity.rotationYaw, 0.0F); par1Entity.motionX = par1Entity.motionY =
 * par1Entity.motionZ = 0.0D; } }
 * 
 * /** Place an entity in a nearby portal which already exists.
 * 
 * public boolean placeInExistingPortal(Entity par1Entity, double par2, double
 * par4, double par6, float par8) { short short1 = 128; double d3 = -1.0D; int i
 * = 0; int j = 0; int k = 0; int l = MathHelper.floor_double(par1Entity.posX);
 * int i1 = MathHelper.floor_double(par1Entity.posZ); long j1 =
 * ChunkCoordIntPair.chunkXZ2Int(l, i1); boolean flag = true; double d4; int k1;
 * 
 * if (this.destinationCoordinateCache.containsItem(j1)) { PortalPosition
 * portalposition =
 * (PortalPosition)this.destinationCoordinateCache.getValueByKey(j1); d3 = 0.0D;
 * i = portalposition.posX; j = portalposition.posY; k = portalposition.posZ;
 * portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
 * flag = false; } else { for (k1 = l - short1; k1 <= l + short1; ++k1) { double
 * d5 = (double)k1 + 0.5D - par1Entity.posX;
 * 
 * for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1) { double d6 = (double)l1
 * + 0.5D - par1Entity.posZ;
 * 
 * for (int i2 = this.worldServerInstance.getActualHeight() - 1; i2 >= 0; --i2)
 * { if (this.worldServerInstance.getBlocksId(k1, i2, l1) ==
 * voidCraft.BlocksTeleporter) { while (this.worldServerInstance.getBlocksId(k1,
 * i2 - 1, l1) == voidCraft.BlocksTeleporter) { --i2; }
 * 
 * d4 = (double)i2 + 0.5D - par1Entity.posY; double d7 = d5 * d5 + d4 * d4 + d6
 * * d6;
 * 
 * if (d3 < 0.0D || d7 < d3) { d3 = d7; i = k1; j = i2; k = l1; } } } } } }
 * 
 * if (d3 >= 0.0D) { if (flag) { this.destinationCoordinateCache.add(j1, new
 * PortalPosition(this, i, j, k, this.worldServerInstance.getTotalWorldTime()));
 * this.destinationCoordinateKeys.add(Long.valueOf(j1)); }
 * 
 * double d8 = (double)i + 0.5D; double d9 = (double)j + 0.5D; d4 = (double)k +
 * 0.5D; int j2 = -1;
 * 
 * if (this.worldServerInstance.getBlocksId(i - 1, j, k) ==
 * voidCraft.BlocksTeleporter) { j2 = 2; }
 * 
 * if (this.worldServerInstance.getBlocksId(i + 1, j, k) ==
 * voidCraft.BlocksTeleporter) { j2 = 0; }
 * 
 * if (this.worldServerInstance.getBlocksId(i, j, k - 1) ==
 * voidCraft.BlocksTeleporter) { j2 = 3; }
 * 
 * if (this.worldServerInstance.getBlocksId(i, j, k + 1) ==
 * voidCraft.BlocksTeleporter) { j2 = 1; }
 * 
 * int k2 = par1Entity.getTeleportDirection();
 * 
 * if (j2 > -1) { int l2 = Direction.rotateLeft[j2]; int i3 =
 * Direction.offsetX[j2]; int j3 = Direction.offsetZ[j2]; int k3 =
 * Direction.offsetX[l2]; int l3 = Direction.offsetZ[l2]; boolean flag1 =
 * !this.worldServerInstance.isAirBlocks(i + i3 + k3, j, k + j3 + l3) ||
 * !this.worldServerInstance.isAirBlocks(i + i3 + k3, j + 1, k + j3 + l3);
 * boolean flag2 = !this.worldServerInstance.isAirBlocks(i + i3, j, k + j3) ||
 * !this.worldServerInstance.isAirBlocks(i + i3, j + 1, k + j3);
 * 
 * if (flag1 && flag2) { j2 = Direction.rotateOpposite[j2]; l2 =
 * Direction.rotateOpposite[l2]; i3 = Direction.offsetX[j2]; j3 =
 * Direction.offsetZ[j2]; k3 = Direction.offsetX[l2]; l3 =
 * Direction.offsetZ[l2]; k1 = i - k3; d8 -= (double)k3; int i4 = k - l3; d4 -=
 * (double)l3; flag1 = !this.worldServerInstance.isAirBlocks(k1 + i3 + k3, j, i4
 * + j3 + l3) || !this.worldServerInstance.isAirBlocks(k1 + i3 + k3, j + 1, i4 +
 * j3 + l3); flag2 = !this.worldServerInstance.isAirBlocks(k1 + i3, j, i4 + j3)
 * || !this.worldServerInstance.isAirBlocks(k1 + i3, j + 1, i4 + j3); }
 * 
 * float f1 = 0.5F; float f2 = 0.5F;
 * 
 * if (!flag1 && flag2) { f1 = 1.0F; } else if (flag1 && !flag2) { f1 = 0.0F; }
 * else if (flag1 && flag2) { f2 = 0.0F; }
 * 
 * d8 += (double)((float)k3 * f1 + f2 * (float)i3); d4 += (double)((float)l3 *
 * f1 + f2 * (float)j3); float f3 = 0.0F; float f4 = 0.0F; float f5 = 0.0F;
 * float f6 = 0.0F;
 * 
 * if (j2 == k2) { f3 = 1.0F; f4 = 1.0F; } else if (j2 ==
 * Direction.rotateOpposite[k2]) { f3 = -1.0F; f4 = -1.0F; } else if (j2 ==
 * Direction.rotateRight[k2]) { f5 = 1.0F; f6 = -1.0F; } else { f5 = -1.0F; f6 =
 * 1.0F; }
 * 
 * double d10 = par1Entity.motionX; double d11 = par1Entity.motionZ;
 * par1Entity.motionX = d10 * (double)f3 + d11 * (double)f6; par1Entity.motionZ
 * = d10 * (double)f5 + d11 * (double)f4; par1Entity.rotationYaw = par8 -
 * (float)(k2 * 90) + (float)(j2 * 90); } else { par1Entity.motionX =
 * par1Entity.motionY = par1Entity.motionZ = 0.0D; }
 * 
 * par1Entity.setLocationAndAngles(d8, d9, d4, par1Entity.rotationYaw,
 * par1Entity.rotationPitch); return true; } else { return false; } }
 * 
 * public boolean makePortal(Entity par1Entity) { byte b0 = 16; double d0 =
 * -1.0D; int i = MathHelper.floor_double(par1Entity.posX); int j =
 * MathHelper.floor_double(par1Entity.posY); int k =
 * MathHelper.floor_double(par1Entity.posZ); int l = i; int i1 = j; int j1 = k;
 * int k1 = 0; int l1 = this.random.nextInt(4); int i2; double d1; double d2;
 * int j2; int k2; int l2; int i3; int j3; int k3; int l3; int i4; int j4; int
 * k4; double d3; double d4;
 * 
 * for (i2 = i - b0; i2 <= i + b0; ++i2) { d1 = (double) i2 + 0.5D -
 * par1Entity.posX;
 * 
 * for (j2 = k - b0; j2 <= k + b0; ++j2) { d2 = (double) j2 + 0.5D -
 * par1Entity.posZ; label274:
 * 
 * for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2) { if
 * (this.worldServerInstance.isAirBlocks(i2, k2, j2)) { while (k2 > 0 &&
 * this.worldServerInstance.isAirBlocks(i2, k2 - 1, j2)) { --k2; }
 * 
 * for (i3 = l1; i3 < l1 + 4; ++i3) { l2 = i3 % 2; k3 = 1 - l2;
 * 
 * if (i3 % 4 >= 2) { l2 = -l2; k3 = -k3; }
 * 
 * for (j3 = 0; j3 < 3; ++j3) { for (i4 = 0; i4 < 4; ++i4) { for (l3 = -1; l3 <
 * 4; ++l3) { k4 = i2 + (i4 - 1) * l2 + j3 * k3; j4 = k2 + l3; int l4 = j2 + (i4
 * - 1) * k3 - j3 * l2;
 * 
 * if (l3 < 0 && !this.worldServerInstance .getBlocksMaterial(k4, j4, l4)
 * .isSolid() || l3 >= 0 && !this.worldServerInstance .isAirBlocks(k4, j4, l4))
 * { continue label274; } } } }
 * 
 * d4 = (double) k2 + 0.5D - par1Entity.posY; d3 = d1 * d1 + d4 * d4 + d2 * d2;
 * 
 * if (d0 < 0.0D || d3 < d0) { d0 = d3; l = i2; i1 = k2; j1 = j2; k1 = i3 % 4; }
 * } } } } }
 * 
 * if (d0 < 0.0D) { for (i2 = i - b0; i2 <= i + b0; ++i2) { d1 = (double) i2 +
 * 0.5D - par1Entity.posX;
 * 
 * for (j2 = k - b0; j2 <= k + b0; ++j2) { d2 = (double) j2 + 0.5D -
 * par1Entity.posZ; label222:
 * 
 * for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2) { if
 * (this.worldServerInstance.isAirBlocks(i2, k2, j2)) { while (k2 > 0 &&
 * this.worldServerInstance.isAirBlocks(i2, k2 - 1, j2)) { --k2; }
 * 
 * for (i3 = l1; i3 < l1 + 2; ++i3) { l2 = i3 % 2; k3 = 1 - l2;
 * 
 * for (j3 = 0; j3 < 4; ++j3) { for (i4 = -1; i4 < 4; ++i4) { l3 = i2 + (j3 - 1)
 * * l2; k4 = k2 + i4; j4 = j2 + (j3 - 1) * k3;
 * 
 * if (i4 < 0 && !this.worldServerInstance .getBlocksMaterial(l3, k4, j4)
 * .isSolid() || i4 >= 0 && !this.worldServerInstance .isAirBlocks(l3, k4, j4))
 * { continue label222; } } }
 * 
 * d4 = (double) k2 + 0.5D - par1Entity.posY; d3 = d1 * d1 + d4 * d4 + d2 * d2;
 * 
 * if (d0 < 0.0D || d3 < d0) { d0 = d3; l = i2; i1 = k2; j1 = j2; k1 = i3 % 2; }
 * } } } } } }
 * 
 * int i5 = l; int j5 = i1; j2 = j1; int k5 = k1 % 2; int l5 = 1 - k5;
 * 
 * if (k1 % 4 >= 2) { k5 = -k5; l5 = -l5; }
 * 
 * boolean flag;
 * 
 * if (d0 < 0.0D) { if (i1 < 70) { i1 = 70; }
 * 
 * if (i1 > this.worldServerInstance.getActualHeight() - 10) { i1 =
 * this.worldServerInstance.getActualHeight() - 10; }
 * 
 * j5 = i1;
 * 
 * for (k2 = -1; k2 <= 1; ++k2) { for (i3 = 1; i3 < 3; ++i3) { for (l2 = -1; l2
 * < 3; ++l2) { k3 = i5 + (i3 - 1) * k5 + k2 * l5; j3 = j5 + l2; i4 = j2 + (i3 -
 * 1) * l5 - k2 * k5; flag = l2 < 0; this.worldServerInstance.setBlock(k3, j3,
 * i4, flag ? voidCraft.BlocksFakeBedrock : 0); } } } }
 * 
 * for (k2 = 0; k2 < 4; ++k2) { for (i3 = 0; i3 < 4; ++i3) { for (l2 = -1; l2 <
 * 4; ++l2) { k3 = i5 + (i3 - 1) * k5; j3 = j5 + l2; i4 = j2 + (i3 - 1) * l5;
 * flag = i3 == 0 || i3 == 3 || l2 == -1 || l2 == 3;
 * this.worldServerInstance.setBlock(k3, j3, i4, flag ?
 * voidCraft.BlocksFakeBedrock : voidCraft.BlocksTeleporter, 0, 2); } }
 * 
 * for (i3 = 0; i3 < 4; ++i3) { for (l2 = -1; l2 < 4; ++l2) { k3 = i5 + (i3 - 1)
 * * k5; j3 = j5 + l2; i4 = j2 + (i3 - 1) * l5;
 * this.worldServerInstance.notifyBlockssOfNeighborChange(k3, j3, i4,
 * this.worldServerInstance.getBlocksId(k3, j3, i4)); } } }
 * 
 * return true; }
 * 
 * public void removeStalePortalLocations(long par1) {
 * 
 * }
 * 
 * }
 */
