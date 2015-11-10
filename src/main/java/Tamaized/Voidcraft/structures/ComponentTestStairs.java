package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;

public class ComponentTestStairs extends ComponentTestPiece{
	
	public ComponentTestStairs() {}

    public ComponentTestStairs(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
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
        this.getNextComponentZ((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 6, 2, false);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestStairs createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -2, 0, 0, 7, 11, 7, par5);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestStairs(par6, par1Random, structureboundingbox, par5) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 6, 1, 6, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 6, 10, 6, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 1, 8, 0, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 0, 6, 8, 0, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 1, 0, 8, 6, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 2, 1, 6, 8, 6, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 6, 5, 8, 6, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 2, 0, 5, 4, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 3, 2, 6, 5, 2, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 3, 4, 6, 5, 4, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.placeBlockAtCurrentPosition(par1World, voidCraft.blocks.blockVoidbrick, 0, 5, 2, 5, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 5, 4, 3, 5, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 2, 5, 3, 4, 5, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 2, 5, 2, 5, 5, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 5, 1, 6, 5, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 7, 1, 5, 7, 4, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 8, 2, 6, 8, 4, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 6, 0, 4, 8, 0, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 0, 4, 5, 0, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);

        for (int i = 0; i <= 6; ++i)
        {
            for (int j = 0; j <= 6; ++j)
            {
               // this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick, 0, i, -1, j, par3StructureBoundingBox);
            }
        }

        return true;
    }

}
