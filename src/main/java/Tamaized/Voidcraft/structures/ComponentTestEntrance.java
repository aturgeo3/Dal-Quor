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

public class ComponentTestEntrance extends ComponentTestPiece{
	
	public ComponentTestEntrance() {}

    public ComponentTestEntrance(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing par4)
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
        this.getNextComponentNormal((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 5, 3, true);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestEntrance createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, EnumFacing par5, int par6)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -5, -3, 0, 13, 14, 13, par5);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestEntrance(par6, par1Random, structureboundingbox, par5) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 0, 12, 4, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 0, 1, 12, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 5, 0, 12, 12, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 11, 4, 12, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 11, 10, 12, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 9, 11, 7, 12, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 0, 4, 12, 1, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 0, 10, 12, 1, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 9, 0, 7, 12, 1, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 11, 2, 10, 12, 10, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 8, 0, 7, 8, 0, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        int i;

        for (i = 1; i <= 11; i += 2)
        {
            this.fillWithBlocks(par1World, par3StructureBoundingBox, i, 10, 0, i, 11, 0, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, i, 10, 12, i, 11, 12, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 10, i, 0, 11, i, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 12, 10, i, 12, 11, i, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), i, 13, 0, par3StructureBoundingBox);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), i, 13, 12, par3StructureBoundingBox);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, 13, i, par3StructureBoundingBox);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 12, 13, i, par3StructureBoundingBox);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), i + 1, 13, 0, par3StructureBoundingBox);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), i + 1, 13, 12, par3StructureBoundingBox);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 0, 13, i + 1, par3StructureBoundingBox);
            this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 12, 13, i + 1, par3StructureBoundingBox);
        }

        this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 0, 13, 0, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 0, 13, 12, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 0, 13, 0, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 12, 13, 0, par3StructureBoundingBox);

        for (i = 3; i <= 9; i += 2)
        {
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 7, i, 1, 8, i, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
            this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 7, i, 11, 8, i, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        }

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 8, 2, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 4, 12, 2, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 0, 8, 1, 3, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 9, 8, 1, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 4, 3, 1, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 0, 4, 12, 1, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        int j;

        for (i = 4; i <= 8; ++i)
        {
            for (j = 0; j <= 2; ++j)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), i, -1, j, par3StructureBoundingBox);
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), i, -1, 12 - j, par3StructureBoundingBox);
            }
        }

        for (i = 0; i <= 2; ++i)
        {
            for (j = 4; j <= 8; ++j)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), i, -1, j, par3StructureBoundingBox);
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 12 - i, -1, j, par3StructureBoundingBox);
            }
        }

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 5, 5, 7, 5, 7, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 1, 6, 6, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 6, 0, 6, par3StructureBoundingBox);
        //this.setBlockState(par1World, Block.lavaMoving, 0, 6, 5, 6, par3StructureBoundingBox);
        i = this.getXWithOffset(6, 6);
        j = this.getYWithOffset(5);
        int k = this.getZWithOffset(6, 6);

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
