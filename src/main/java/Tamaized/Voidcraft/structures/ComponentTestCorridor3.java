package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;


public class ComponentTestCorridor3 extends ComponentTestPiece{
	
	 public ComponentTestCorridor3() {}

	    public ComponentTestCorridor3(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
	    {
	        super(par1);
	        this.coordBaseMode = par4;
	        this.boundingBox = par3StructureBoundingBox;
	    }

	    /**
	     * Initiates construction of the Structure Component picked, at the current Location of StructGen
	     */
	    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
	    {
	        this.getNextComponentNormal((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 1, 0, true);
	    }

	    /**
	     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
	     */
	    public static ComponentTestCorridor3 createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
	    {
	        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, -7, 0, 5, 14, 10, par5);
	        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestCorridor3(par6, par1Random, structureboundingbox, par5) : null;
	    }

	    /**
	     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
	     * the end, it adds Fences...
	     */
	    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
	    {
	        int i = this.getMetadataWithOffset(voidCraft.blocks.blockVoidstairs, 2);

	        for (int j = 0; j <= 9; ++j)
	        {
	            int k = Math.max(1, 7 - j);
	            int l = Math.min(Math.max(k + 5, 14 - j), 13);
	            int i1 = j;
	            this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, j, 4, k, j, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
	            this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, k + 1, j, 3, l - 1, j, Blocks.air, Blocks.air, false);

	            if (j <= 6)
	            {
	                this.placeBlockAtCurrentPosition(par1World, voidCraft.blocks.blockVoidstairs, i, 1, k + 1, j, par3StructureBoundingBox);
	                this.placeBlockAtCurrentPosition(par1World, voidCraft.blocks.blockVoidstairs, i, 2, k + 1, j, par3StructureBoundingBox);
	                this.placeBlockAtCurrentPosition(par1World, voidCraft.blocks.blockVoidstairs, i, 3, k + 1, j, par3StructureBoundingBox);
	            }

	            this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, l, j, 4, l, j, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
	            this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, k + 1, j, 0, l - 1, j, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
	            this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, k + 1, j, 4, l - 1, j, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);

	            if ((j & 1) == 0)
	            {
	                this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, k + 2, j, 0, k + 3, j, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
	                this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, k + 2, j, 4, k + 3, j, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
	            }

	            for (int j1 = 0; j1 <= 4; ++j1)
	            {
	                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getIdFromBlock(voidCraft.blocks.blockVoidbrick), 0, j1, -1, i1, par3StructureBoundingBox);
	            }
	        }

	        return true;
	    }

}
