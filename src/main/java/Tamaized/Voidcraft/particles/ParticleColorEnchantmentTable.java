package Tamaized.Voidcraft.particles;

import net.minecraft.client.particle.ParticleEnchantmentTable;
import net.minecraft.world.World;

public class ParticleColorEnchantmentTable extends ParticleEnchantmentTable {

	public ParticleColorEnchantmentTable(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		particleBlue = particleGreen = particleRed = world.rand.nextFloat() * 255;
		//particleGreen = world.rand.nextFloat() * 255;
		//particleBlue = world.rand.nextFloat() * 255;
	}

}
