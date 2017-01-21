package Tamaized.Voidcraft.handlers;

import java.util.ArrayList;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class VadeMecumRitualHandler {

	public static void invokeRitual(EntityPlayer player, World world, BlockPos pos) {
		IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap == null || world.getBlockState(pos).getBlock() != VoidCraft.blocks.ritualBlock) return;
		switch (getRitual(cap, world, pos, true)) {
			case INTRO:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Intro"));
				cap.addCategory(IVadeMecumCapability.Category.INTRO);
				break;
			case TOME:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Words of Power"));
				cap.addCategory(IVadeMecumCapability.Category.TOME);
				break;
			case Flame:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Flame"));
				cap.addCategory(IVadeMecumCapability.Category.Flame);
				break;
			case Freeze:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Freeze"));
				cap.addCategory(IVadeMecumCapability.Category.Freeze);
				break;
			case Shock:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Shock"));
				cap.addCategory(IVadeMecumCapability.Category.Shock);
				break;
			case AcidSpray:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Acid"));
				cap.addCategory(IVadeMecumCapability.Category.AcidSpray);
				break;
			default:
				player.sendMessage(new TextComponentTranslation(TextFormatting.RED + "Unknown Ritual"));
				break;
		}
	}

	public static IVadeMecumCapability.Category getRitual(IVadeMecumCapability cap, World world, BlockPos pos, boolean clear) {
		for (IVadeMecumCapability.Category ritual : getAvailiableRituals(cap)) {
			if (doChecks(ritual, world, pos, clear)) return ritual;
		}
		return IVadeMecumCapability.Category.NULL;
	}

	public static ArrayList<IVadeMecumCapability.Category> getAvailiableRituals(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumCapability.Category> list = new ArrayList<IVadeMecumCapability.Category>();
		if (cap.getObtainedCategories().isEmpty()) {
			list.add(IVadeMecumCapability.Category.INTRO);
		} else {
			if (cap.getObtainedCategories().contains(IVadeMecumCapability.Category.TOME)) {
				if (cap.getObtainedCategories().contains(IVadeMecumCapability.Category.Flame)) {
					
				}else{
					list.add(IVadeMecumCapability.Category.Flame);
				}
				if (cap.getObtainedCategories().contains(IVadeMecumCapability.Category.Freeze)) {
					
				}else{
					list.add(IVadeMecumCapability.Category.Freeze);
				}
				if (cap.getObtainedCategories().contains(IVadeMecumCapability.Category.Shock)) {
					
				}else{
					list.add(IVadeMecumCapability.Category.Shock);
				}
				if (cap.getObtainedCategories().contains(IVadeMecumCapability.Category.AcidSpray)) {
					
				}else{
					list.add(IVadeMecumCapability.Category.AcidSpray);
				}
			} else {
				list.add(IVadeMecumCapability.Category.TOME);
			}
		}
		return list;
	}

	public static boolean doChecks(IVadeMecumCapability.Category ritual, World world, BlockPos pos, boolean clear) {
		int val = check(VoidCraft.ritualList.getRitual(ritual), world, pos);
		if (val > 0) {
			if (clear) clear(VoidCraft.ritualList.getRitual(ritual), val, world, pos);
			return true;
		}
		return false;
	}

	private static int check(ItemStack[] stackList, World world, BlockPos pos) {
		int i = -1;
		boolean flag = true;
		loop: for (int y = 0; y <= 2; y++) {
			for (int z = 1; z >= -1; z--) {
				for (int x = 1; x >= -1; x--) {
					i++;
					if (stackList[i].isEmpty()) continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					if (Item.getItemFromBlock(state.getBlock()) != stackList[i].getItem()) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag) return 1;

		i = -1;
		flag = true;
		loop: for (int y = 0; y <= 2; y++) {
			for (int x = -1; x <= 1; x++) {
				for (int z = 1; z >= -1; z--) {
					i++;
					if (stackList[i].isEmpty()) continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					if (Item.getItemFromBlock(state.getBlock()) != stackList[i].getItem()) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag) return 2;

		i = -1;
		flag = true;
		loop: for (int y = 0; y <= 2; y++) {
			for (int z = -1; z <= 1; z++) {
				for (int x = -1; x <= 1; x++) {
					i++;
					if (stackList[i].isEmpty()) continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					if (Item.getItemFromBlock(state.getBlock()) != stackList[i].getItem()) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag) return 3;

		i = -1;
		flag = true;
		loop: for (int y = 0; y <= 2; y++) {
			for (int x = 1; x >= -1; x--) {
				for (int z = -1; z <= 1; z++) {
					i++;
					if (stackList[i].isEmpty()) continue;
					IBlockState state = world.getBlockState(pos.add(x, y, z));
					if (Item.getItemFromBlock(state.getBlock()) != stackList[i].getItem()) {
						flag = false;
						break loop;
					}
				}
			}
		}
		if (flag) return 4;

		return 0;
	}

	private static void clear(ItemStack[] stackList, int face, World world, BlockPos pos) {
		int i;
		switch (face) {
			case 1:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int z = 1; z >= -1; z--) {
						for (int x = 1; x >= -1; x--) {
							i++;
							if (!stackList[i].isEmpty()) world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			case 2:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int x = -1; x <= 1; x++) {
						for (int z = 1; z >= -1; z--) {
							i++;
							if (!stackList[i].isEmpty()) world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			case 3:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int z = -1; z <= 1; z++) {
						for (int x = -1; x <= 1; x++) {
							i++;
							if (!stackList[i].isEmpty()) world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			case 4:
				i = -1;
				for (int y = 0; y <= 2; y++) {
					for (int x = 1; x >= -1; x--) {
						for (int z = -1; z <= 1; z++) {
							i++;
							if (!stackList[i].isEmpty()) world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			default:
				break;
		}
	}

}
