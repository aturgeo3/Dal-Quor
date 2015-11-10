package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;

public class ComponentTestCrossing extends ComponentTestPiece{
	
	public ComponentTestCrossing() {}

    public ComponentTestCrossing(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
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
        this.getNextComponentNormal((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 2, 0, false);
        this.getNextComponentX((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 0, 2, false);
        this.getNextComponentZ((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 0, 2, false);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestCrossing createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -2, 0, 0, 7, 9, 7, par5);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestCrossing(par6, par1Random, structureboundingbox, par5) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 6, 1, 6, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 6, 7, 6, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 1, 6, 0, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 6, 1, 6, 6, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 0, 6, 6, 0, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 6, 6, 6, 6, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 6, 1, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 5, 0, 6, 6, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 2, 0, 6, 6, 1, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 2, 5, 6, 6, 6, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 6, 0, 4, 6, 0, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 0, 4, 5, 0, voidCraft.blockVoidfence, voidCraft.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 6, 6, 4, 6, 6, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 6, 4, 5, 6, voidCraft.blockVoidfence, voidCraft.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 2, 0, 6, 4, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 2, 0, 5, 4, voidCraft.blockVoidfence, voidCraft.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 6, 2, 6, 6, 4, voidCraft.blockVoidbrick, voidCraft.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 5, 2, 6, 5, 4, voidCraft.blockVoidfence, voidCraft.blockVoidfence, false);

        for (int i = 0; i <= 6; ++i)
        {
            for (int j = 0; j <= 6; ++j)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blockVoidbrick, 0, i, -1, j, par3StructureBoundingBox);
            }
        }

        return true;
    }

}
