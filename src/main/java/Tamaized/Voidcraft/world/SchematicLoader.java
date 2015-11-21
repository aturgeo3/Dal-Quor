package Tamaized.Voidcraft.world;

import java.io.InputStream;
import java.util.ArrayList;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class SchematicLoader {
	
	public Schematic get(String schemname){
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/voidcraft/Schematics/"+schemname);
            NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);
            short width = nbtdata.getShort("Width");
            short height = nbtdata.getShort("Height");
            short length = nbtdata.getShort("Length");
            
            byte[] addBlocks = nbtdata.getByteArray("AddBlocks");
            byte[] localBlocks = nbtdata.getByteArray("Blocks");
            byte[] data = nbtdata.getByteArray("Data");
            
            boolean extra = false;
            byte extraBlocks[] = null;
            byte extraBlocksNibble[] = null;
            if (nbtdata.hasKey("AddBlocks")) {
                extra = true;
                extraBlocksNibble = nbtdata.getByteArray("AddBlocks");
                extraBlocks = new byte[extraBlocksNibble.length * 2];
                for (int i = 0; i < extraBlocksNibble.length; i++) {
                    extraBlocks[i * 2 + 0] = (byte) ((extraBlocksNibble[i] >> 4) & 0xF);
                    extraBlocks[i * 2 + 1] = (byte) (extraBlocksNibble[i] & 0xF);
                }
            }
            
            int[] blocks = new int[localBlocks.length];
            
            for(int i=0; i<blocks.length; i++){
            	blocks[i] = (localBlocks[i] & 0xFF) | (extra ? ((extraBlocks[i] & 0xFF) << 8) : 0);
            }

            System.out.println("schem size:" + width + " x " + height + " x " + length);
            NBTTagList tileentities = nbtdata.getTagList("TileEntities", 10);
            is.close();

            return new Schematic(tileentities, width, height, length, addBlocks, blocks, data);
        } catch (Exception e) {
            System.out.println("I can't load schematic, because " + e.toString());
            return null;
        }
    }

    public class Schematic{
        public  NBTTagList tileentities;
        public  short width;
        public  short height;
        public short length;
        public int[] blocks;
        public byte[] data;
        public Schematic(NBTTagList tileentities, short width, short height, short length, byte[] addBlocks, int[] blocks, byte[] data){
            this.tileentities = tileentities;
            this.width = width;
            this.height = height;
            this.length = length;
            this.blocks = blocks;
            this.data = data;
        }

    }

}
