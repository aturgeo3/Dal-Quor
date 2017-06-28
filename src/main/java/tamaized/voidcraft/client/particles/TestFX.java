package tamaized.voidcraft.client.particles;


public class TestFX{/* extends EntityFX{

	private static final ResourceLocation texture = new ResourceLocation("VoidCraft:textures/particle/Portal.png");
	
	public TestFX(World par1World, double x, double y, double z) {
		super(par1World, x, y, z);
		
		double newrand =  (float) Math.random();
		
		setGravity(.005F);
		//setScale(1F);
		setMaxAge(10);
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
	
	public int getFXLayer(){
		return 3;
	}
	
	public TestFX setMaxAge(int maxAge){
		particleMaxAge = maxAge;
		return this;
	}
	
	public TestFX setGravity(float gravity){
		particleGravity = gravity;
		return this;
	}
	
	public TestFX setScale(float scale){
		particleScale = scale;
		return this;
	}

*/
}
