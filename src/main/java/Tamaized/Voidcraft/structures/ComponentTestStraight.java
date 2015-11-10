package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;

public class ComponentTestStraight extends ComponentTestPiece{
	
	public ComponentTestStraight() {}

    public ComponentTestStraight(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, int par4)
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
        this.getNextComponentNormal((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 1, 3, false);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestStraight createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, -3, 0, 5, 10, 19, par5);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestStraight(par6, par1Random, structureboundingbox, par5) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 0, 4, 4, 18, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 0, 3, 7, 18, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 0, 5, 18, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 5, 0, 4, 5, 18, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 4, 2, 5, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 13, 4, 2, 18, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 1, 3, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 15, 4, 1, 18, voidCraft.blocks.blockVoidbrick, voidCraft.blocks.blockVoidbrick, false);

        for (int i = 0; i <= 4; ++i)
        {
            for (int j = 0; j <= 2; ++j)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick, 0, i, -1, j, par3StructureBoundingBox);
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick, 0, i, -1, 18 - j, par3StructureBoundingBox);
            }
        }

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 4, 1, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 4, 0, 4, 4, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 14, 0, 4, 14, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 17, 0, 4, 17, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 1, 4, 4, 1, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 4, 4, 4, 4, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 14, 4, 4, 14, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 1, 17, 4, 4, 17, voidCraft.blocks.blockVoidfence, voidCraft.blocks.blockVoidfence, false);
        return true;
    }

}
