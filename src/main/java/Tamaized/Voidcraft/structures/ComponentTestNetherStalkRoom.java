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

public class ComponentTestNetherStalkRoom extends ComponentTestPiece{
	
	public ComponentTestNetherStalkRoom() {}

    public ComponentTestNetherStalkRoom(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing par4)
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
        this.getNextComponentNormal((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 5, 11, true);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestNetherStalkRoom createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, EnumFacing par5, int par6)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -5, -3, 0, 13, 14, 13, par5);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestNetherStalkRoom(par6, par1Random, structureboundingbox, par5) : null;
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

        i = this.getMetadataWithOffset(voidCraft.blocks.blockVoidbrick, 3);
        int j;
        int k;
        int l;

        for (j = 0; j <= 6; ++j)
        {
            k = j + 4;

            for (l = 5; l <= 7; ++l)
            {
                this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(i), l, 5 + j, k, par3StructureBoundingBox);
            }

            if (k >= 5 && k <= 8)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 5, k, 7, j + 4, k, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
            }
            else if (k >= 9 && k <= 10)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 8, k, 7, j + 4, k, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
            }

            if (j >= 1)
            {
                this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 6 + j, k, 7, 9 + j, k, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
        }

        for (j = 5; j <= 7; ++j)
        {
            this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(i), j, 12, 11, par3StructureBoundingBox);
        }

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 6, 7, 5, 7, 7, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 7, 6, 7, 7, 7, 7, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 13, 12, 7, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 2, 3, 5, 3, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 9, 3, 5, 10, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 5, 4, 2, 5, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 5, 2, 10, 5, 3, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 5, 9, 10, 5, 10, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 5, 4, 10, 5, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        j = this.getMetadataWithOffset(voidCraft.blocks.blockVoidbrick, 0);
        k = this.getMetadataWithOffset(voidCraft.blocks.blockVoidbrick, 1);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(k), 4, 5, 2, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(k), 4, 5, 3, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(k), 4, 5, 9, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(k), 4, 5, 10, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(j), 8, 5, 2, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(j), 8, 5, 3, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(j), 8, 5, 9, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidbrick.getStateFromMeta(j), 8, 5, 10, par3StructureBoundingBox);
        //this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 4, 4, 4, 4, 8, Block.slowSand, Block.slowSand, false);
        //this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 4, 4, 9, 4, 8, Block.slowSand, Block.slowSand, false);
        //this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 5, 4, 4, 5, 8, Block.netherStalk, Block.netherStalk, false);
        //this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 4, 9, 5, 8, Block.netherStalk, Block.netherStalk, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 8, 2, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 4, 12, 2, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 0, 8, 1, 3, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 0, 9, 8, 1, 12, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 4, 3, 1, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 9, 0, 4, 12, 1, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        int i1;

        for (l = 4; l <= 8; ++l)
        {
            for (i1 = 0; i1 <= 2; ++i1)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, l, -1, i1, par3StructureBoundingBox);
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, l, -1, 12 - i1, par3StructureBoundingBox);
            }
        }

        for (l = 0; l <= 2; ++l)
        {
            for (i1 = 4; i1 <= 8; ++i1)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, l, -1, i1, par3StructureBoundingBox);
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, 12 - l, -1, i1, par3StructureBoundingBox);
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
