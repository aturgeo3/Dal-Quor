package Tamaized.Voidcraft.structures;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureTestStart extends StructureStart{
	
	public StructureTestStart() {}
	
	public StructureTestStart(World par1World, Random par2Random, int par3, int par4)
    {
        super(par3, par4);
        ComponentTestStartPiece componentnetherbridgestartpiece = new ComponentTestStartPiece(par2Random, (par3 << 4) + 2, (par4 << 4) + 2);
        this.components.add(componentnetherbridgestartpiece);
        componentnetherbridgestartpiece.buildComponent(componentnetherbridgestartpiece, this.components, par2Random);
        ArrayList arraylist = componentnetherbridgestartpiece.field_74967_d;

        while (!arraylist.isEmpty())
        {
            int k = par2Random.nextInt(arraylist.size());
            StructureComponent structurecomponent = (StructureComponent)arraylist.remove(k);
            structurecomponent.buildComponent(componentnetherbridgestartpiece, this.components, par2Random);
        }

        this.updateBoundingBox();
        this.setRandomHeight(par1World, par2Random, 48, 70);
    }

}
