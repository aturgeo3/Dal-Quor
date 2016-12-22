package Tamaized.Voidcraft.vadeMecum.contents.documentation.starforge;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.client.resources.I18n;

public class VadeMecumPageListStarForge implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge", voidCraft.modid + ".VadeMecum.docs.desc.starforge.pg1"),

				new VadeMecumPage("", voidCraft.modid + ".VadeMecum.docs.desc.starforge.pg2"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.blindingFear", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.blindingFear"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.firstDegreeBurns", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.firstDegreeBurns"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidTouch", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidTouch"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.xiaBlessing", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.xiaBlessing"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.secondDegreeBurns", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.secondDegreeBurns"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidWrath", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidWrath"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.mortalFear", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.mortalFear"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.thirdDegreeBurns", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.thirdDegreeBurns"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.voidCripple", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.voidCripple"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.vorp", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.vorp"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.haste", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.haste"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.silkTouch", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.silkTouch"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.fortune", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.fortune"),

				new VadeMecumPage(voidCraft.modid + ".VadeMecum.docs.title.starforge.effect.threeByThree", voidCraft.modid + ".VadeMecum.docs.desc.starforge.effect.threeByThree")

		};
	}

}
