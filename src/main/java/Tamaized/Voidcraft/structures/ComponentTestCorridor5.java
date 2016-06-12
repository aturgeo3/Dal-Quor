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

public class ComponentTestCorridor5 extends ComponentTestPiece{
	
	public ComponentTestCorridor5() {}

    public ComponentTestCorridor5(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing par4)
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
        this.getNextComponentNormal((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 1, 0, true);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestCorridor5 createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, EnumFacing par5, int par6)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par2, par3, par4, -1, 0, 0, 5, 7, 5, par5);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestCorridor5(par6, par1Random, structureboundingbox, par5) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 1, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 5, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 4, 5, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 4, 1, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 3, 3, 0, 4, 3, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 1, 4, 4, 1, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 3, 4, 4, 3, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 0, 4, 6, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);

       /* for (int i = 0; i <= 4; ++i)
        {
            for (int j = 0; j <= 4; ++j)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, i, -1, j, par3StructureBoundingBox);
            }
        }
        */
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
