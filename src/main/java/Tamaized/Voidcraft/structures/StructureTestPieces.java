package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.MapGenStructureIO;


public class StructureTestPieces {
	
	private static final StructureTestPieceWeight[] primaryComponents = new StructureTestPieceWeight[] {new StructureTestPieceWeight(ComponentTestStraight.class, 30, 0, true), new StructureTestPieceWeight(ComponentTestCrossing3.class, 10, 4), new StructureTestPieceWeight(ComponentTestCrossing.class, 10, 4), new StructureTestPieceWeight(ComponentTestStairs.class, 10, 3), new StructureTestPieceWeight(ComponentTestThrone.class, 5, 2), new StructureTestPieceWeight(ComponentTestEntrance.class, 5, 1)};
    private static final StructureTestPieceWeight[] secondaryComponents = new StructureTestPieceWeight[] {new StructureTestPieceWeight(ComponentTestCorridor5.class, 25, 0, true), new StructureTestPieceWeight(ComponentTestCrossing2.class, 15, 5), new StructureTestPieceWeight(ComponentTestCorridor2.class, 5, 10), new StructureTestPieceWeight(ComponentTestCorridor.class, 5, 10), new StructureTestPieceWeight(ComponentTestCorridor3.class, 10, 3, true), new StructureTestPieceWeight(ComponentTestCorridor4.class, 7, 2), new StructureTestPieceWeight(ComponentTestNetherStalkRoom.class, 5, 2)};

    public static void func_143049_a()
    {
        MapGenStructureIO.func_143031_a(ComponentTestCrossing3.class, "NeVBCr");
        MapGenStructureIO.func_143031_a(ComponentTestEnd.class, "NeVBEF");
        MapGenStructureIO.func_143031_a(ComponentTestStraight.class, "NeVBS");
        MapGenStructureIO.func_143031_a(ComponentTestCorridor3.class, "NeVCCS");
        MapGenStructureIO.func_143031_a(ComponentTestCorridor4.class, "NeVCTB");
        MapGenStructureIO.func_143031_a(ComponentTestEntrance.class, "NeVCE");
        MapGenStructureIO.func_143031_a(ComponentTestCrossing2.class, "NeVSCSC");
        MapGenStructureIO.func_143031_a(ComponentTestCorridor.class, "NeVSCLT");
        MapGenStructureIO.func_143031_a(ComponentTestCorridor5.class, "NeVSC");
        MapGenStructureIO.func_143031_a(ComponentTestCorridor2.class, "NeVSCRT");
        MapGenStructureIO.func_143031_a(ComponentTestNetherStalkRoom.class, "NeVCSR");
        MapGenStructureIO.func_143031_a(ComponentTestThrone.class, "NeVMT");
        MapGenStructureIO.func_143031_a(ComponentTestCrossing.class, "NeVRC");
        MapGenStructureIO.func_143031_a(ComponentTestStairs.class, "NeVSR");
        MapGenStructureIO.func_143031_a(ComponentTestStartPiece.class, "NeVStart");
    }

    private static ComponentTestPiece createNextComponentRandom(StructureTestPieceWeight par0StructureNetherBridgePieceWeight, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        Class oclass = par0StructureNetherBridgePieceWeight.weightClass;
        Object object = null;

        if (oclass == ComponentTestStraight.class)
        {
            object = ComponentTestStraight.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCrossing3.class)
        {
            object = ComponentTestCrossing3.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCrossing.class)
        {
            object = ComponentTestCrossing.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestStairs.class)
        {
            object = ComponentTestStairs.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestThrone.class)
        {
            object = ComponentTestThrone.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestEntrance.class)
        {
            object = ComponentTestEntrance.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCorridor5.class)
        {
            object = ComponentTestCorridor5.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCorridor2.class)
        {
            object = ComponentTestCorridor2.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCorridor.class)
        {
            object = ComponentTestCorridor.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCorridor3.class)
        {
            object = ComponentTestCorridor3.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCorridor4.class)
        {
            object = ComponentTestCorridor4.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestCrossing2.class)
        {
            object = ComponentTestCrossing2.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }
        else if (oclass == ComponentTestNetherStalkRoom.class)
        {
            object = ComponentTestNetherStalkRoom.createValidComponent(par1List, par2Random, par3, par4, par5, par6, par7);
        }

        return (ComponentTestPiece)object;
    }

    static ComponentTestPiece createNextComponent(StructureTestPieceWeight par0StructureNetherBridgePieceWeight, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        return createNextComponentRandom(par0StructureNetherBridgePieceWeight, par1List, par2Random, par3, par4, par5, par6, par7);
    }

    static StructureTestPieceWeight[] getPrimaryComponents()
    {
        return primaryComponents;
    }

    static StructureTestPieceWeight[] getSecondaryComponents()
    {
        return secondaryComponents;
    }

}
