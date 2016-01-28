package Tamaized.Voidcraft.structures;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.MapGenStructureIO;


public class StructureTestPieces {
	
	private static final StructureTestPieceWeight[] primaryComponents = new StructureTestPieceWeight[] {
		new StructureTestPieceWeight(ComponentTestStraight.class, 30, 0, true),
		new StructureTestPieceWeight(ComponentTestCrossing3.class, 10, 4),
		new StructureTestPieceWeight(ComponentTestCrossing.class, 10, 4),
		new StructureTestPieceWeight(ComponentTestStairs.class, 10, 3),
		new StructureTestPieceWeight(ComponentTestThrone.class, 5, 2),
		new StructureTestPieceWeight(ComponentTestEntrance.class, 5, 1)
		};
    private static final StructureTestPieceWeight[] secondaryComponents = new StructureTestPieceWeight[] {
    	new StructureTestPieceWeight(ComponentTestCorridor5.class, 25, 0, true),
    	new StructureTestPieceWeight(ComponentTestCrossing2.class, 15, 5),
    	new StructureTestPieceWeight(ComponentTestCorridor2.class, 5, 10),
    	new StructureTestPieceWeight(ComponentTestCorridor.class, 5, 10),
    	new StructureTestPieceWeight(ComponentTestCorridor3.class, 10, 3, true),
    	new StructureTestPieceWeight(ComponentTestCorridor4.class, 7, 2),
    	new StructureTestPieceWeight(ComponentTestNetherStalkRoom.class, 5, 2)
    	};

    public static void func_143049_a()
    {
        MapGenStructureIO.registerStructureComponent(ComponentTestCrossing3.class, "VoidCraft:NeVBCr");
        MapGenStructureIO.registerStructureComponent(ComponentTestEnd.class, "VoidCraft:NeVBEF");
        MapGenStructureIO.registerStructureComponent(ComponentTestStraight.class, "VoidCraft:NeVBS");
        MapGenStructureIO.registerStructureComponent(ComponentTestCorridor3.class, "VoidCraft:NeVCCS");
        MapGenStructureIO.registerStructureComponent(ComponentTestCorridor4.class, "VoidCraft:NeVCTB");
        MapGenStructureIO.registerStructureComponent(ComponentTestEntrance.class, "VoidCraft:NeVCE");
        MapGenStructureIO.registerStructureComponent(ComponentTestCrossing2.class, "VoidCraft:NeVSCSC");
        MapGenStructureIO.registerStructureComponent(ComponentTestCorridor.class, "VoidCraft:NeVSCLT");
        MapGenStructureIO.registerStructureComponent(ComponentTestCorridor5.class, "VoidCraft:NeVSC");
        MapGenStructureIO.registerStructureComponent(ComponentTestCorridor2.class, "VoidCraft:NeVSCRT");
        MapGenStructureIO.registerStructureComponent(ComponentTestNetherStalkRoom.class, "VoidCraft:NeVCSR");
        MapGenStructureIO.registerStructureComponent(ComponentTestThrone.class, "VoidCraft:NeVMT");
        MapGenStructureIO.registerStructureComponent(ComponentTestCrossing.class, "VoidCraft:NeVRC");
        MapGenStructureIO.registerStructureComponent(ComponentTestStairs.class, "VoidCraft:NeVSR");
        MapGenStructureIO.registerStructureComponent(ComponentTestStartPiece.class, "VoidCraft:NeVStart");
    }

    private static ComponentTestPiece createNextComponentRandom(StructureTestPieceWeight par0StructureNetherBridgePieceWeight, List par1List, Random par2Random, int x, int y, int z, EnumFacing face, int id)
    {
        Class oclass = par0StructureNetherBridgePieceWeight.weightClass;
        Object object = null;

        if (oclass == ComponentTestStraight.class)
        {
            object = ComponentTestStraight.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCrossing3.class)
        {
            object = ComponentTestCrossing3.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCrossing.class)
        {
            object = ComponentTestCrossing.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestStairs.class)
        {
            object = ComponentTestStairs.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestThrone.class)
        {
            object = ComponentTestThrone.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestEntrance.class)
        {
            object = ComponentTestEntrance.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCorridor5.class)
        {
            object = ComponentTestCorridor5.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCorridor2.class)
        {
            object = ComponentTestCorridor2.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCorridor.class)
        {
            object = ComponentTestCorridor.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCorridor3.class)
        {
            object = ComponentTestCorridor3.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCorridor4.class)
        {
            object = ComponentTestCorridor4.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestCrossing2.class)
        {
            object = ComponentTestCrossing2.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }
        else if (oclass == ComponentTestNetherStalkRoom.class)
        {
            object = ComponentTestNetherStalkRoom.createValidComponent(par1List, par2Random, x, y, z, face, id);
        }

        return (ComponentTestPiece)object;
    }

    static ComponentTestPiece createNextComponent(StructureTestPieceWeight par0StructureNetherBridgePieceWeight, List par1List, Random par2Random, int x, int y, int z, EnumFacing face, int id)
    {
        return createNextComponentRandom(par0StructureNetherBridgePieceWeight, par1List, par2Random, x, y, z, face, id);
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
