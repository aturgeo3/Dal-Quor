package Tamaized.Voidcraft.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;



public class ComponentTestStartPiece extends ComponentTestCrossing3{
	
	/** Instance of StructureNetherBridgePieceWeight. */
    public StructureTestPieceWeight theNetherBridgePieceWeight;

    /**
     * Contains the list of valid piece weights for the set of nether bridge structure pieces.
     */
    public List primaryWeights;

    /**
     * Contains the list of valid piece weights for the secondary set of nether bridge structure pieces.
     */
    public List secondaryWeights;
    public ArrayList field_74967_d = new ArrayList();

    public ComponentTestStartPiece() {}

    public ComponentTestStartPiece(Random par1Random, int par2, int par3)
    {
        super(par1Random, par2, par3);
        this.primaryWeights = new ArrayList();
        StructureTestPieceWeight[] astructurenetherbridgepieceweight = StructureTestPieces.getPrimaryComponents();
        int k = astructurenetherbridgepieceweight.length;
        int l;
        StructureTestPieceWeight structurenetherbridgepieceweight;

        for (l = 0; l < k; ++l)
        {
            structurenetherbridgepieceweight = astructurenetherbridgepieceweight[l];
            structurenetherbridgepieceweight.field_78827_c = 0;
            this.primaryWeights.add(structurenetherbridgepieceweight);
        }

        this.secondaryWeights = new ArrayList();
        astructurenetherbridgepieceweight = StructureTestPieces.getSecondaryComponents();
        k = astructurenetherbridgepieceweight.length;

        for (l = 0; l < k; ++l)
        {
            structurenetherbridgepieceweight = astructurenetherbridgepieceweight[l];
            structurenetherbridgepieceweight.field_78827_c = 0;
            this.secondaryWeights.add(structurenetherbridgepieceweight);
        }
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeStructureToNBT(par1NBTTagCompound);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readStructureFromNBT(par1NBTTagCompound);
    }

}
