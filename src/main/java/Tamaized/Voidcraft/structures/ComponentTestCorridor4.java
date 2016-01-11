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

public class ComponentTestCorridor4 extends ComponentTestPiece{
	
	public ComponentTestCorridor4() {}

    public ComponentTestCorridor4(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing par4){
        super(par1);
        this.coordBaseMode = par4;
        this.boundingBox = par3StructureBoundingBox;
    }

    /**
     * Initiates construction of the Structure Component picked, at the current Location of StructGen
     */
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
    {
        byte b0 = 1;

        if (this.coordBaseMode.getIndex() == 1 || this.coordBaseMode.getIndex() == 2)
        {
            b0 = 5;
        }

        this.getNextComponentX((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 0, b0, par3Random.nextInt(8) > 0);
        this.getNextComponentZ((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 0, b0, par3Random.nextInt(8) > 0);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestCorridor4 createValidComponent(List par0List, Random par1Random, int x, int y, int z, EnumFacing face, int id)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(x, y, z, -3, 0, 0, 9, 7, 9, face);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestCorridor4(id, par1Random, structureboundingbox, face) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 0, 0, 8, 1, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 2, 0, 8, 5, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 6, 0, 8, 6, 5, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 2, 0, 2, 5, 0, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 6, 2, 0, 8, 5, 0, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 1, 3, 0, 1, 4, 0, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 7, 3, 0, 7, 4, 0, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 2, 4, 8, 2, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 1, 1, 4, 2, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 6, 1, 4, 7, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 3, 8, 8, 3, 8, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 3, 6, 0, 3, 7, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 8, 3, 6, 8, 3, 7, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 3, 4, 0, 5, 5, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 8, 3, 4, 8, 5, 5, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 1, 3, 5, 2, 5, 5, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 6, 3, 5, 7, 5, 5, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 1, 4, 5, 1, 5, 5, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.func_175804_a(par1World, par3StructureBoundingBox, 7, 4, 5, 7, 5, 5, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);

        for (int i = 0; i <= 5; ++i)
        {
            for (int j = 0; j <= 8; ++j)
            {
               // this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, j, -1, i, par3StructureBoundingBox);
            }
        }

        return true;
    }

	@Override
	protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
		
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
		
	}

}
