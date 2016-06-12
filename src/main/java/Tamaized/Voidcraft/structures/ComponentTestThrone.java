package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import Tamaized.Voidcraft.common.voidCraft;

public class ComponentTestThrone extends ComponentTestPiece{
	
	private boolean hasSpawner;

    public ComponentTestThrone() {}

    public ComponentTestThrone(int par1, Random par2Random, StructureBoundingBox par3StructureBoundingBox, EnumFacing par4)
    {
        super(par1);
        this.setCoordBaseMode(par4);
        this.boundingBox = par3StructureBoundingBox;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeStructureToNBT(par1NBTTagCompound);
        this.hasSpawner = par1NBTTagCompound.getBoolean("Mob");
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readStructureFromNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("Mob", this.hasSpawner);
    }

    /**
     * Creates and returns a new component piece. Or null if it could not find enough room to place it.
     */
    public static ComponentTestThrone createValidComponent(List par0List, Random par1Random, int x, int y, int z, EnumFacing face, int id)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -2, 0, 0, 7, 8, 9, face);
        return isAboveGround(structureboundingbox) && StructureComponent.findIntersecting(par0List, structureboundingbox) == null ? new ComponentTestThrone(id, par1Random, structureboundingbox, face) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 6, 7, 7, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 0, 0, 5, 1, 7, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 1, 5, 2, 7, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 2, 5, 3, 7, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 4, 3, 5, 4, 7, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 2, 0, 1, 4, 2, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 2, 0, 5, 4, 2, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 2, 1, 5, 3, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 5, 5, 2, 5, 5, 3, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 5, 3, 0, 5, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 5, 3, 6, 5, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 5, 8, 5, 5, 8, voidCraft.blocks.blockVoidbrick.getDefaultState(), voidCraft.blocks.blockVoidbrick.getDefaultState(), false);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 1, 6, 3, par3StructureBoundingBox);
        this.setBlockState(par1World, voidCraft.blocks.blockVoidfence.getDefaultState(), 5, 6, 3, par3StructureBoundingBox);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 3, 0, 6, 8, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 6, 6, 3, 6, 6, 8, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 6, 8, 5, 7, 8, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 8, 8, 4, 8, 8, voidCraft.blocks.blockVoidfence.getDefaultState(), voidCraft.blocks.blockVoidfence.getDefaultState(), false);
        int i;
        int j;

        if (!this.hasSpawner)
        {
            i = this.getYWithOffset(5);
            j = this.getXWithOffset(3, 5);
            int k = this.getZWithOffset(3, 5);

            if (par3StructureBoundingBox.isVecInside(new Vec3i(j, i, k)))
            {
                this.hasSpawner = true;
                //par1World.setBlock(j, i, k, Block.mobSpawner, 0, 2);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)par1World.getTileEntity(new BlockPos(j, i, k));

                if (tileentitymobspawner != null)
                {
                    //tileentitymobspawner.getSpawnerLogic().setMobID("Blaze");
                }
            }
        }

        for (i = 0; i <= 6; ++i)
        {
            for (j = 0; j <= 6; ++j)
            {
                //this.fillCurrentPositionBlocksDownwards(par1World, voidCraft.blocks.blockVoidbrick.getDefaultState(), 0, i, -1, j, par3StructureBoundingBox);
            }
        }

        return true;
    }

}
