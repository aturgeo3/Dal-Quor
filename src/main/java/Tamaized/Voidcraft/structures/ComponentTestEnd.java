package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;

public class ComponentTestEnd extends ComponentTestPiece{
	
	private int fillSeed;

    public ComponentTestEnd() {}

    public ComponentTestEnd(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing face)
    {
        super(par1);
        this.coordBaseMode = face;
        this.boundingBox = par3StructureBoundingBox;
        this.fillSeed = par2Random.nextInt();
    }

    public static ComponentTestEnd func_74971_a(List par0List, Random par1Random, int x, int y, int z, EnumFacing face, int id)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(x, y, z, -1, -3, 0, 5, 10, 8, face);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestEnd(id, par1Random, structureboundingbox, face) : null;
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
    {
        super.func_143011_b(par1NBTTagCompound);
        this.fillSeed = par1NBTTagCompound.getInteger("Seed");
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
    {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Seed", this.fillSeed);
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        Random random1 = new Random((long)this.fillSeed);
        int i;
        int j;
        int k;

        for (i = 0; i <= 4; ++i)
        {
            for (j = 3; j <= 4; ++j)
            {
                k = random1.nextInt(8);
                this.func_175804_a(par1World, par3StructureBoundingBox, i, j, 0, i, j, k, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
            }
        }

        i = random1.nextInt(8);
        this.func_175804_a(par1World, par3StructureBoundingBox, 0, 5, 0, 0, 5, i, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        i = random1.nextInt(8);
        this.func_175804_a(par1World, par3StructureBoundingBox, 4, 5, 0, 4, 5, i, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);

        for (i = 0; i <= 4; ++i)
        {
            j = random1.nextInt(5);
            this.func_175804_a(par1World, par3StructureBoundingBox, i, 2, 0, i, 2, j, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        }

        for (i = 0; i <= 4; ++i)
        {
            for (j = 0; j <= 1; ++j)
            {
                k = random1.nextInt(3);
                this.func_175804_a(par1World, par3StructureBoundingBox, i, j, 0, i, j, k, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
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
