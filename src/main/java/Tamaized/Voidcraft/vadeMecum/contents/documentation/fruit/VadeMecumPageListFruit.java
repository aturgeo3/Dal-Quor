package Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingAlchemy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;

public class VadeMecumPageListFruit {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Ethereal Fruit", "Ethereal Fruits are harvested from Ethereal Plants. These plants can be found naturally in the void next to a source of Liquid Void. Harvesting via Right Click may yield fruit and seeds between 0 and 3. Yes, there is a chance to not gain any seeds or fruit at all. To plant these seeds you'll need to use a Void Hoe on Soft Bedrock and have a nearby source of liquid void as normal water will not work. Before you actually plant the seeds you can alter the soil, this"),
				new VadeMecumPage("", "cannot be done if the seeds have already been planted. To alter the soil simply right click it with one of the following: Redstone dust, Lapis dust, Gold dust, Emerald dust, or Diamond dust. Eating these fruits will yield various benefits based on the alterations. A normal fruit grants Voidic Immunity which will prevent and decay any Voidic Infusion on the player. A redstone fruit gives a buff to strength. Gold gives Absorb and Resistance."),
				new VadeMecumPage("", "Lapis provides a speed buff. Emerald gives a jump buff. And diamond gives a health boost buff. The plants will only grow in light levels 2 and below. This means the plants need darkness."),
				new VadeMecumPage("Potion of Plenum", "One can make a Voidic Alchemy Table and combine all the types of Ethereal Fruit into a potion. What this potion does is it gives the user immunity to void damage allowing one to venture down into the void below bedrock in the Overworld."),
				new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy("Alchemy Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.etherealFruit),
						new ItemStack(voidCraft.items.etherealFruit_redstone),
						new ItemStack(voidCraft.items.etherealFruit_lapis),
						new ItemStack(voidCraft.items.etherealFruit_gold),
						new ItemStack(voidCraft.items.etherealFruit_emerald),
						new ItemStack(voidCraft.items.etherealFruit_diamond) }, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), voidCraft.potions.type_voidImmunity))) };
	}

}
