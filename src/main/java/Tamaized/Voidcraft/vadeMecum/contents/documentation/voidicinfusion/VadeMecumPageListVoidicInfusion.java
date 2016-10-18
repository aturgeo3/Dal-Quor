package Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;

public class VadeMecumPageListVoidicInfusion implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Infusion", "While in the void, without a Voidic Suppressor in hand or the effects of Voidic Immunity, you will slowly gain Voidic Infusion. While under the effects of Voidic Infusion your vision becomes slightly obscured. Your maximum health will decrease. Void Crystal like spikes will begin to form and stick out of your arms and back. At a certian point you will gain the ability to fly. Your attacks will deal an addition 1 damage which goes through all forms of protection, this damage"),
				new VadeMecumPage("", "is known as Voidic Damage. You become incorporeal in which certian physical attacks have a chance of passing right through you, dealing no damage. If the amount of Voidic Infusion becomes too great, death will occur. As Voidic Infusion decays, all of these effects begin to wear off and your health returns back to normal.") };
	}

}
