package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;

public class ComponentTestCrossing extends ComponentTestPiece{
	
	public ComponentTestCrossing() {}

    public ComponentTestCrossing(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing par4)
    {
        super(par1);
        this.setCoordBaseMode(par4);
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
    public static ComponentTestCrossing createValidComponent(List par0List, Random par1Random, int x, int y, int z, EnumFacing face, int id)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -2, 0, 0, 7, 9, 7, face);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestCrossing(id, par1Random, structureboundingbox, face) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 6, 1, 6, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 6, 7, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 1, 6, 0, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 6, 1, 6, 6, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 0, 6, 6, 0, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 6, 6, 6, 6, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 6, 1, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 5, 0, 6, 6, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 2, 0, 6, 6, 1, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 2, 5, 6, 6, 6, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 6, 0, 4, 6, 0, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 0, 4, 5, 0, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 6, 6, 4, 6, 6, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 6, 4, 5, 6, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 2, 0, 6, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 2, 0, 5, 4, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 6, 2, 6, 6, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 5, 2, 6, 5, 4, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);

        for (int i = 0; i <= 6; ++i)
        {
            for (int j = 0; j <= 6; ++j)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, i, -1, j, par3StructureBoundingBox);
            }
        }

        return true;
    }

	@Override
	protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
		// TODO Auto-generated method stub
		
	}

}
