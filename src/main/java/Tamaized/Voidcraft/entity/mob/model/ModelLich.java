package Tamaized.Voidcraft.entity.mob.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelLich extends ModelBase{
	//fields
	private ModelRenderer Helm;
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightarm;
	private ModelRenderer leftarm;
	private ModelRenderer RightHand;
	private ModelRenderer LeftHand;
	
	private int movement = 0;
	private boolean moveup;
  
  public ModelLich()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Helm = new ModelRenderer(this, 77, 35);
      Helm.addBox(-5F, -10F, -5F, 10, 10, 10);
      Helm.setRotationPoint(0F, -8F, 0F);
      Helm.setTextureSize(128, 64);
      Helm.mirror = true;
      setRotation(Helm, 0F, 0F, 0F);
      head = new ModelRenderer(this, 80, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, -9F, 0F);
      head.setTextureSize(128, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 32);
      body.addBox(-4F, 0F, -2F, 16, 27, 5);
      body.setRotationPoint(-4F, -7F, 2F);
      body.setTextureSize(128, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-2F, 0F, -2F, 4, 10, 4);
      rightarm.setRotationPoint(-10F, 0F, -1F);
      rightarm.setTextureSize(128, 64);
      rightarm.mirror = true;
      setRotation(rightarm, -2.156359F, 1.003822F, 0F);
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(-2F, 0F, -2F, 4, 10, 4);
      leftarm.setRotationPoint(10F, 0F, -1F);
      leftarm.setTextureSize(128, 64);
      leftarm.mirror = true;
      setRotation(leftarm, -1.319841F, 0F, 0F);
      RightHand = new ModelRenderer(this, 0, 0);
      RightHand.addBox(-2F, -7F, -2F, 4, 7, 4);
      RightHand.setRotationPoint(-17F, -5F, -6F);
      RightHand.setTextureSize(128, 64);
      RightHand.mirror = true;
      setRotation(RightHand, 0F, -0.5205006F, -0.3717861F);
      LeftHand = new ModelRenderer(this, 0, 0);
      LeftHand.addBox(-2F, -7F, -2F, 4, 7, 4);
      LeftHand.setRotationPoint(10F, 3F, -10F);
      LeftHand.setTextureSize(128, 64);
      LeftHand.mirror = true;
      setRotation(LeftHand, 1.301251F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Helm.render(f5);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    RightHand.render(f5);
    LeftHand.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    this.Helm.rotateAngleX = this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
    this.Helm.rotateAngleY = this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
    
  }
  
  /**
   * Used for easily adding entity-dependent animations. The second and third float params here are the same second
   * and third as in the setRotationAngles method.
   */
  public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_){
	  
	 /* EntityMobLich lich = (EntityMobLich) p_78086_1_;
	  
	  //Animation
	  if(!moveup) {
	    	this.body.rotationPointY -= 0.01;
	    	this.head.rotationPointY -= 0.01;
	    	this.Helm.rotationPointY -= 0.01;
	    	this.rightarm.rotationPointY -= 0.02;
	    	this.RightHand.rotationPointY -= 0.02;
	    	
	    }else{
	    	this.body.rotationPointY += 0.01;
	    	this.head.rotationPointY += 0.01;
	    	this.Helm.rotationPointY += 0.01;
	    	this.rightarm.rotationPointY += 0.02;
	    	this.RightHand.rotationPointY += 0.02;
	    }
	    
	    if(this.body.rotationPointY < -7) moveup = true;
	    if(this.body.rotationPointY > -5) moveup = false;
	    
	    //Particles
	    double d0 = (double) ((float) lich.posX + this.RightHand.offsetX);
        double d1 = (double)((float) lich.posY + this.RightHand.offsetY);
        double d2 = (double) ((float) lich.posZ + this.RightHand.offsetZ);
        double d3 = 0.3D;
        double d4 = -0.5D;
        double d5 = -0.3D;
        
        DebugEvent.textR = d0+" : "+d1+" : "+d2;
        
        lich.worldObj.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
        */
  }

}
