package Tamaized.Voidcraft.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.storage.loot.LootTable;
import Tamaized.Voidcraft.common.voidCraft;

abstract public class ComponentTestPiece extends StructureComponent{
	
    public ComponentTestPiece() {}

    protected ComponentTestPiece(int par1)
    {
        super(par1);
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound par1NBTTagCompound) {}

    @Override
    protected void readStructureFromNBT(NBTTagCompound par1NBTTagCompound) {}

    
    private int getTotalWeight(List par1List)
    {
        boolean flag = false;
        int i = 0;
        StructureTestPieceWeight structurenetherbridgepieceweight;

        for (Iterator iterator = par1List.iterator(); iterator.hasNext(); i += structurenetherbridgepieceweight.field_78826_b)
        {
            structurenetherbridgepieceweight = (StructureTestPieceWeight)iterator.next();

            if (structurenetherbridgepieceweight.field_78824_d > 0 && structurenetherbridgepieceweight.field_78827_c < structurenetherbridgepieceweight.field_78824_d)
            {
                flag = true;
            }
        }

        return flag ? i : -1;
    }

    private ComponentTestPiece getNextComponent(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, List par3List, Random par4Random, int x, int y, int z, EnumFacing face, int id)
    {
        int j1 = this.getTotalWeight(par2List);
        boolean flag = j1 > 0 && id <= 30;
        int k1 = 0;

        while (k1 < 5 && flag)
        {
            ++k1;
            int l1 = par4Random.nextInt(j1);
            Iterator iterator = par2List.iterator();

            while (iterator.hasNext())
            {
            	StructureTestPieceWeight structurenetherbridgepieceweight = (StructureTestPieceWeight)iterator.next();
                l1 -= structurenetherbridgepieceweight.field_78826_b;

                if (l1 < 0)
                {
                    if (!structurenetherbridgepieceweight.func_78822_a(id) || structurenetherbridgepieceweight == par1ComponentNetherBridgeStartPiece.theNetherBridgePieceWeight && !structurenetherbridgepieceweight.field_78825_e)
                    {
                        break;
                    }

                    ComponentTestPiece componentnetherbridgepiece = StructureTestPieces.createNextComponent(structurenetherbridgepieceweight, par3List, par4Random, x, y, z, face, id);

                    if (componentnetherbridgepiece != null)
                    {
                        ++structurenetherbridgepieceweight.field_78827_c;
                        par1ComponentNetherBridgeStartPiece.theNetherBridgePieceWeight = structurenetherbridgepieceweight;

                        if (!structurenetherbridgepieceweight.func_78823_a())
                        {
                            par2List.remove(structurenetherbridgepieceweight);
                        }

                        return componentnetherbridgepiece;
                    }
                }
            }
        }

        return ComponentTestEnd.func_74971_a(par3List, par4Random, x, y, z, face, id);
    }

    /**
     * Finds a random component to tack on to the bridge. Or builds the end.
     */
    private StructureComponent getNextComponent(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int x, int y, int z, EnumFacing face, int id, boolean par9)
    {
        if (Math.abs(x - par1ComponentNetherBridgeStartPiece.getBoundingBox().minX) <= 112 && Math.abs(z - par1ComponentNetherBridgeStartPiece.getBoundingBox().minZ) <= 112)
        {
            List list1 = par1ComponentNetherBridgeStartPiece.primaryWeights;

            if (par9)
            {
                list1 = par1ComponentNetherBridgeStartPiece.secondaryWeights;
            }

            ComponentTestPiece componentnetherbridgepiece = this.getNextComponent(par1ComponentNetherBridgeStartPiece, list1, par2List, par3Random, x, y, z, face, id + 1);

            if (componentnetherbridgepiece != null)
            {
                par2List.add(componentnetherbridgepiece);
                par1ComponentNetherBridgeStartPiece.field_74967_d.add(componentnetherbridgepiece);
            }
            System.out.println(componentnetherbridgepiece);
            return componentnetherbridgepiece;
        }
        else
        {
        	StructureComponent sc = ComponentTestEnd.func_74971_a(par2List, par3Random, x, y, z, face, id);
            System.out.println(sc);
            return sc;
        }
    }

    /**
     * Gets the next component in any cardinal direction
     */
    protected StructureComponent getNextComponentNormal(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
    {
    	System.out.println(boundingBox);
        switch (this.getCoordBaseMode().getIndex())
        {
            case 0:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.maxZ + 1, this.getCoordBaseMode(), this.getComponentType(), par6);
            case 1:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.getCoordBaseMode(), this.getComponentType(), par6);
            case 2:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.minZ - 1, this.getCoordBaseMode(), this.getComponentType(), par6);
            case 3:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.getCoordBaseMode(), this.getComponentType(), par6);
            default:
                return null;
        }
    }

    /**
     * Gets the next component in the +/- X direction
     */
    protected StructureComponent getNextComponentX(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
    {
        switch (this.getCoordBaseMode().getIndex())
        {
            case 0:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.UP, this.getComponentType(), par6);
            case 1:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType(), par6);
            case 2:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.UP, this.getComponentType(), par6);
            case 3:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType(), par6);
            default:
                return null;
        }
    }

    /**
     * Gets the next component in the +/- Z direction
     */
    protected StructureComponent getNextComponentZ(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
    {
        switch (this.getCoordBaseMode().getIndex())
        {
            case 0:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.SOUTH, this.getComponentType(), par6);
            case 1:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, EnumFacing.DOWN, this.getComponentType(), par6);
            case 2:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, EnumFacing.SOUTH, this.getComponentType(), par6);
            case 3:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, EnumFacing.DOWN, this.getComponentType(), par6);
            default:
                return null;
        }
    }

    /**
     * Checks if the bounding box's minY is > 10
     */
    protected static boolean isAboveGround(StructureBoundingBox par0StructureBoundingBox)
    {
        return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
    }

}
