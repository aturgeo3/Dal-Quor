package Tamaized.Voidcraft.particles;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_GREATER;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glAlphaFunc;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class AcidFX extends Particle {

	private static final ResourceLocation texture = new ResourceLocation("VoidCraft:textures/particle/AcidFX.png");
	/*
	public AcidFX(World p_i1209_1_, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_){
		 super(p_i1209_1_, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
	        this.motionX = this.motionX * 0.009999999776482582D + p_i1209_8_;
	        this.motionY = this.motionY * 0.009999999776482582D + p_i1209_10_;
	        this.motionZ = this.motionZ * 0.009999999776482582D + p_i1209_12_;
	        double d6 = p_i1209_2_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
	        d6 = p_i1209_4_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
	        d6 = p_i1209_6_ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
	        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
	        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
	        this.noClip = true;
	    }
	*/
	
	public AcidFX(World par1World, double x, double y, double z) {
		super(par1World, x, y, z);
		prevPosX = posX = x;
		prevPosY = posY = y;
		prevPosZ = posZ = z;
		
		double newrand =  (float) Math.random();
		this.motionY = this.motionY * 0.009999999776482582D + 1;
		this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
		this.particleAlpha = 0.5F;
		setGravity(.005F);
		setMaxAge(50);
	}

	public void renderParticle(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7){
		WorldRenderer worldRenderer = tess.getWorldRenderer();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		glDepthMask(false);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glAlphaFunc(GL_GREATER, 0.003921569F);
		worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		//worldRenderer.setBrightness(getBrightnessForRender(partialTicks));
		float scale = 0.1F*particleScale;
		float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		worldRenderer.pos(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale).tex(0, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		worldRenderer.pos(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale).tex(1, 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		worldRenderer.pos(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale).tex(1, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		worldRenderer.pos(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale).tex(0, 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).endVertex();
		tess.draw();
		glDisable(GL_BLEND);
		glDepthMask(true);
		glAlphaFunc(GL_GREATER, 0.1F);
	}

	    public int getBrightnessForRender(float p_70070_1_)
	    {
	        float f1 = ((float)this.particleAge + p_70070_1_) / (float)this.particleMaxAge;

	        if (f1 < 0.0F)
	        {
	            f1 = 0.0F;
	        }

	        if (f1 > 1.0F)
	        {
	            f1 = 1.0F;
	        }

	        int i = super.getBrightnessForRender(p_70070_1_);
	        int j = i & 255;
	        int k = i >> 16 & 255;
	        j += (int)(f1 * 15.0F * 16.0F);

	        if (j > 240)
	        {
	            j = 240;
	        }

	        return j | k << 16;
	    }

	    /**
	     * Gets how bright this entity is.
	     */
	    public float getBrightness(float p_70013_1_)
	    {
	        float f1 = ((float)this.particleAge + p_70013_1_) / (float)this.particleMaxAge;

	        if (f1 < 0.0F)
	        {
	            f1 = 0.0F;
	        }

	        if (f1 > 1.0F)
	        {
	            f1 = 1.0F;
	        }

	        float f2 = super.getBrightness(p_70013_1_);
	        return f2 * f1 + (1.0F - f1);
	    }

	    /**
	     * Called to update the entity's position/logic.
	     */
	    public void onUpdate()
	    {
	    	this.particleScale+=this.particleScale/15;
	    	//this.particleAlpha = (this.particleAge / this.particleMaxAge);
	    	
	        if (this.particleAge++ >= 15)//this.particleMaxAge)
	        {
	            this.setDead();
	        }
	    }
	    
	    public int getFXLayer(){
			return 3;
		}
		
		public AcidFX setMaxAge(int maxAge){
			particleMaxAge = maxAge;
			return this;
		}
		
		public AcidFX setGravity(float gravity){
			particleGravity = gravity;
			return this;
		}
		
		public AcidFX setScale(float scale){
			particleScale = scale;
			return this;
		}
}
