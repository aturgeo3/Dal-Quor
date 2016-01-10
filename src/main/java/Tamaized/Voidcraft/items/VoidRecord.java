package Tamaized.Voidcraft.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.machina.VoidBox;

public class VoidRecord extends ItemRecord {

	public static VoidBox daBox;

	/** List of all record items and their names. */
	private static final Map records = new HashMap();

	/** The name of the record. */
	public final String recordName;

	private int time = 0;

	public VoidRecord(String par2Str, int t) {
		super(par2Str);
		time = t;
		this.recordName = par2Str;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabMisc);
		records.put(par2Str, this);
		records.put("records." + par2Str, this); // Forge Bug Fix: RenderGlobal
													// adds a "records." when
													// looking up below.
	}

	public int getTime() {
		return time;
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(this.getRecordNameLocal());
	}

	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal() {
		// return StatCollector.translateToLocal("item.record." +
		// this.recordName + ".desc");
		String s = this.recordName;
		s = s.replace("voidCraft:", "");
		return s;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Return the title for this record.
	 */
	public String getRecordTitle() {
		return "VoidCraft - " + this.recordName.substring(10);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Return an item rarity from EnumRarity
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.RARE;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Return the record item corresponding to the given name.
	 */
	public static ItemRecord getRecord(String par0Str) {
		return (ItemRecord) records.get(par0Str);
	}

	@Override
	public ResourceLocation getRecordResource(String name) {
		return new ResourceLocation(name);
	}

}
