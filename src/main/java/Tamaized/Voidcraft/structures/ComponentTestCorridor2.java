package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;

public class ComponentTestCorridor2 extends ComponentTestPiece{
	
	 private boolean field_111020_b;

	    public ComponentTestCorridor2() {}

	    public ComponentTestCorridor2(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing par4)
	    {
	        super(par1);
	        this.coordBaseMode = par4;
	        this.boundingBox = par3StructureBoundingBox;
	        this.field_111020_b = par2Random.nextInt(3) == 0;
	    }

	    protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
	    {
	        super.func_143011_b(par1NBTTagCompound);
	        this.field_111020_b = par1NBTTagCompound.getBoolean("Chest");
	    }

	    protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
	    {
	        super.func_143012_a(par1NBTTagCompound);
	        par1NBTTagCompound.setBoolean("Chest", this.field_111020_b);
	    }

	    /**
	     * Initiates construction of the Structure Component picked, at the current Location of StructGen
	     */
	    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random)
	    {
	        this.getNextComponentZ((ComponentTestStartPiece)par1StructureComponent, par2List, par3Random, 0, 1, true);
	    }

	    /**
	     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
	     */
	    public static ComponentTestCorridor2 createValidComponent(List par0List, Random par1Random, int par2, int par3, int par4, EnumFacing par5, int par6)
	    {
	        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(par2, par3, par4, -1, 0, 0, 5, 7, 5, par5);
	        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestCorridor2(par6, par1Random, structureboundingbox, par5) : null;
	    }

	    /**
	     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
	     * the end, it adds Fences...
	     */
	    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
	    {
	        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 1, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 5, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 3, 1, 0, 4, 1, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 3, 3, 0, 4, 3, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 4, 2, 0, 4, 5, 0, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 1, 2, 4, 4, 5, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 1, 3, 4, 1, 4, 4, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
	        this.func_175804_a(par1World, par3StructureBoundingBox, 3, 3, 4, 3, 4, 4, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
	        int i;
	        int j;

	        if (this.field_111020_b)
	        {
	            i = this.getYWithOffset(2);
	            j = this.getXWithOffset(1, 3);
	            int k = this.getZWithOffset(1, 3);

	            if (par3StructureBoundingBox.func_175898_b(new Vec3i(j, i, k)))
	            {
	                this.field_111020_b = false;
	                this.func_180778_a(par1World, par3StructureBoundingBox, par2Random, 1, 2, 3, field_111019_a, 2 + par2Random.nextInt(4));
	            }
	        }

	        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 6, 0, 4, 6, 4, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);

	        for (i = 0; i <= 4; ++i)
	        {
	            for (j = 0; j <= 4; ++j)
	            {
	                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState().blockID, 0, i, -1, j, par3StructureBoundingBox);
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
