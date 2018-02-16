package tamaized.dalquor.common.armor;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.common.armors.TamArmor;
import tamaized.dalquor.VoidCraft;

import javax.annotation.Nullable;

public class ArmorCustomElytra extends TamArmor {

	public ArmorCustomElytra(CreativeTabs tab, ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(tab, armorMaterial, par3, par4, type, n);
		this.maxStackSize = 1;
		this.setMaxDamage(432);
		this.addPropertyOverride(new ResourceLocation("broken"), new IItemPropertyGetter() {

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return ItemElytra.isUsable(stack) ? 0.0F : 1.0F;
			}

		});
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
	}

	public static boolean isBroken(ItemStack stack) {
		return stack.getItemDamage() < stack.getMaxDamage() - 1;
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == VoidCraft.items.voidcrystal;
	}

}
