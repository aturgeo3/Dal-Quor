package Tamaized.Voidcraft.handlers;

import java.util.ArrayList;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.RitualList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class VadeMecumRitualHandler {

	public static enum Ritual {
		NULL, INTRO, POWERINTRO
	}

	public static void invokeRitual(EntityPlayer player, World world, BlockPos pos) {
		IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap == null || world.getBlockState(pos).getBlock() != voidCraft.blocks.ritualBlock) return;
		switch (getRitual(cap, world, pos, true)) {
			case INTRO:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Intro"));
				cap.addCategory(IVadeMecumCapability.Category.INTRO);
				break;
			case POWERINTRO:
				player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GREEN + "Words of Power"));
				cap.addCategory(IVadeMecumCapability.Category.TOME);
				break;
			default:
				player.sendMessage(new TextComponentTranslation(TextFormatting.RED + "Unknown Ritual"));
				break;
		}
	}

	public static Ritual getRitual(IVadeMecumCapability cap, World world, BlockPos pos, boolean clear) {
		for (Ritual ritual : getAvailiableRituals(cap)) {
			if (doChecks(ritual, world, pos, clear)) return ritual;
		}
		return Ritual.NULL;
	}

	public static ArrayList<Ritual> getAvailiableRituals(IVadeMecumCapability cap) {
		ArrayList<Ritual> list = new ArrayList<Ritual>();
		if (cap.getObtainedCategories().isEmpty()) {
			list.add(Ritual.INTRO);
		} else {
			if (cap.getObtainedCategories().contains(Ritual.POWERINTRO)) {

			} else {
				list.add(Ritual.POWERINTRO);
			}
		}
		return list;
	}

	public static boolean doChecks(Ritual ritual, World world, BlockPos pos, boolean clear) {
		int val = 0;
		switch (ritual) {
			case INTRO:
				val = check(voidCraft.ritualList.Intro, world, pos);
				if (val > 0) {
					if (clear) clear(voidCraft.ritualList.Intro, val, world, pos);
					return true;
				}
				break;
			case POWERINTRO:
				val = check(voidCraft.ritualList.PowerIntro, world, pos);
				if (val > 0) {
					if (clear) clear(voidCraft.ritualList.PowerIntro, val, world, pos);
					return true;
				}
				break;
			default:
				break;
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
					if (stackList[i] == null) continue;
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
					if (stackList[i] == null) continue;
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
					if (stackList[i] == null) continue;
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
					if (stackList[i] == null) continue;
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
							if (stackList[i] != null) world.setBlockToAir(pos.add(x, y, z));
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
							if (stackList[i] != null) world.setBlockToAir(pos.add(x, y, z));
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
							if (stackList[i] != null) world.setBlockToAir(pos.add(x, y, z));
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
							if (stackList[i] != null) world.setBlockToAir(pos.add(x, y, z));
						}
					}
				}
				break;
			default:
				break;
		}
	}

}
