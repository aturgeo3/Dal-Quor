package Tamaized.Voidcraft.structures;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

abstract public class ComponentTestPiece extends StructureComponent{
	
	protected static final WeightedRandomChestContent[] field_111019_a = new WeightedRandomChestContent[] {
		new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 5),
		new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 5),
		new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 15),
		new WeightedRandomChestContent(voidCraft.voidCloth, 0, 1, 3, 5),
		new WeightedRandomChestContent(voidCraft.voidcrystal, 0, 2, 5, 15),
		new WeightedRandomChestContent(voidCraft.ectoplasm, 0, 1, 3, 5),
		new WeightedRandomChestContent(voidCraft.MoltenvoidChain, 0, 1, 1, 2),
		new WeightedRandomChestContent(voidCraft.demonBoots, 0, 0, 1, 1),
		new WeightedRandomChestContent(voidCraft.demonLegs, 0, 0, 1, 1),
		new WeightedRandomChestContent(voidCraft.demonHelmet, 0, 0, 1, 1),
		new WeightedRandomChestContent(voidCraft.demonChest, 0, 0, 1, 1)
		};

    public ComponentTestPiece() {}

    protected ComponentTestPiece(int par1)
    {
        super(par1);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {}

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {}

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

    private ComponentTestPiece getNextComponent(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, List par3List, Random par4Random, int par5, int par6, int par7, int par8, int par9)
    {
        int j1 = this.getTotalWeight(par2List);
        boolean flag = j1 > 0 && par9 <= 30;
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
                    if (!structurenetherbridgepieceweight.func_78822_a(par9) || structurenetherbridgepieceweight == par1ComponentNetherBridgeStartPiece.theNetherBridgePieceWeight && !structurenetherbridgepieceweight.field_78825_e)
                    {
                        break;
                    }

                    ComponentTestPiece componentnetherbridgepiece = StructureTestPieces.createNextComponent(structurenetherbridgepieceweight, par3List, par4Random, par5, par6, par7, par8, par9);

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

        return ComponentTestEnd.func_74971_a(par3List, par4Random, par5, par6, par7, par8, par9);
    }

    /**
     * Finds a random component to tack on to the bridge. Or builds the end.
     */
    private StructureComponent getNextComponent(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, int par6, int par7, int par8, boolean par9)
    {
        if (Math.abs(par4 - par1ComponentNetherBridgeStartPiece.getBoundingBox().minX) <= 112 && Math.abs(par6 - par1ComponentNetherBridgeStartPiece.getBoundingBox().minZ) <= 112)
        {
            List list1 = par1ComponentNetherBridgeStartPiece.primaryWeights;

            if (par9)
            {
                list1 = par1ComponentNetherBridgeStartPiece.secondaryWeights;
            }

            ComponentTestPiece componentnetherbridgepiece = this.getNextComponent(par1ComponentNetherBridgeStartPiece, list1, par2List, par3Random, par4, par5, par6, par7, par8 + 1);

            if (componentnetherbridgepiece != null)
            {
                par2List.add(componentnetherbridgepiece);
                par1ComponentNetherBridgeStartPiece.field_74967_d.add(componentnetherbridgepiece);
            }

            return componentnetherbridgepiece;
        }
        else
        {
            return ComponentTestEnd.func_74971_a(par2List, par3Random, par4, par5, par6, par7, par8);
        }
    }

    /**
     * Gets the next component in any cardinal direction
     */
    protected StructureComponent getNextComponentNormal(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.maxZ + 1, this.coordBaseMode, this.getComponentType(), par6);
            case 1:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.coordBaseMode, this.getComponentType(), par6);
            case 2:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par4, this.boundingBox.minY + par5, this.boundingBox.minZ - 1, this.coordBaseMode, this.getComponentType(), par6);
            case 3:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par5, this.boundingBox.minZ + par4, this.coordBaseMode, this.getComponentType(), par6);
            default:
                return null;
        }
    }

    /**
     * Gets the next component in the +/- X direction
     */
    protected StructureComponent getNextComponentX(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType(), par6);
            case 1:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType(), par6);
            case 2:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 1, this.getComponentType(), par6);
            case 3:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, 2, this.getComponentType(), par6);
            default:
                return null;
        }
    }

    /**
     * Gets the next component in the +/- Z direction
     */
    protected StructureComponent getNextComponentZ(ComponentTestStartPiece par1ComponentNetherBridgeStartPiece, List par2List, Random par3Random, int par4, int par5, boolean par6)
    {
        switch (this.coordBaseMode)
        {
            case 0:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType(), par6);
            case 1:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType(), par6);
            case 2:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, 3, this.getComponentType(), par6);
            case 3:
                return this.getNextComponent(par1ComponentNetherBridgeStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, 0, this.getComponentType(), par6);
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
