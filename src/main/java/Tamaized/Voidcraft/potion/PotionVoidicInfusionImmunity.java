package Tamaized.Voidcraft.potion;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionVoidicInfusionImmunity extends Potion {

	private final ResourceLocation iconTexture;

	@SideOnly(Side.CLIENT)
	private static final MethodHandle Z_LEVEL = findFieldGetter(net.minecraft.client.gui.Gui.class, "zLevel", "field_73735_i");

	public PotionVoidicInfusionImmunity(String name) {
		super(false, 0x7700FF);
		iconTexture = new ResourceLocation(voidCraft.modid, "textures/potions/" + name + ".png");
		setRegistryName(voidCraft.modid, name);
		setPotionName("effect." + getRegistryName().toString());
		GameRegistry.register(this);
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {
	}

	@Override
	public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
		super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) {
		mc.getTextureManager().bindTexture(iconTexture);
		net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 7, y + 8, 0, 0, 16, 16, 16, 16);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha) {
		mc.getTextureManager().bindTexture(iconTexture);
		net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
	}

	public static MethodHandle findFieldGetter(Class<?> clazz, String... fieldNames) {
		final Field field = ReflectionHelper.findField(clazz, fieldNames);

		try {
			return MethodHandles.lookup().unreflectGetter(field);
		} catch (IllegalAccessException e) {
			throw new ReflectionHelper.UnableToAccessFieldException(fieldNames, e);
		}
	}

}
