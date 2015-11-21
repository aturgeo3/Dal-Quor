package Tamaized.Voidcraft.blocks.render;

import java.nio.FloatBuffer;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderNoBreak extends TileEntitySpecialRenderer {
	
	private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
    private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
    private static final Random field_147527_e = new Random(31100L);
    FloatBuffer field_147528_b = GLAllocation.createDirectFloatBuffer(16);
    private static final String __OBFID = "CL_00000972";

    private FloatBuffer func_147525_a(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_)
    {
        this.field_147528_b.clear();
        this.field_147528_b.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
        this.field_147528_b.flip();
        return this.field_147528_b;
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float p_147500_8_){
    	//this.renderTop(p_147500_1_, x, y, z, p_147500_8_);
    	//this.renderBottom(p_147500_1_, x, y, z, p_147500_8_);
    	//this.renderNorth(p_147500_1_, x, y, z, p_147500_8_);
    	//this.renderSouth(p_147500_1_, x, y, z, p_147500_8_);
    	//this.renderEast(p_147500_1_, x, y, z, p_147500_8_);
    	//this.renderWest(p_147500_1_, x, y, z, p_147500_8_);
    	
    	float f1 = (float)this.field_147501_a.field_147560_j;
        float f2 = (float)this.field_147501_a.field_147561_k;
        float f3 = (float)this.field_147501_a.field_147558_l;
        GL11.glDisable(GL11.GL_LIGHTING);
        field_147527_e.setSeed(31100L);
        float f4 = 1.00F; //Block Size

        for (int i = 0; i < 16; ++i)
        {
            GL11.glPushMatrix();
            float f5 = (float)(16 - i); //Current Image
            float f6 = 0.0625F; // Distance, Lower = Closer
            float f7 = 1.0F / (f5 + 1.0F); //Brightness

            if (i == 0)
            {
                this.bindTexture(field_147529_c);
                f7 = 0.1F; 
                f5 = 65.0F;
                f6 = 0.125F;
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            }

            if (i == 1)
            {
                this.bindTexture(field_147526_d);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                f6 = 0.5F;
            }

            float f8 = (float)(-(y + (double)f4));
            float f9 = f8 + ActiveRenderInfo.objectY;
            float f10 = f8 + f5 + ActiveRenderInfo.objectY;
            float f11 = f9 / f10;
            f11 += (float)(y + (double)f4);
            GL11.glTranslatef(f1, f11, f3);
            GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
            GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
            GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, this.func_147525_a(1.0F, 0.0F, 0.0F, 0.0F)); //XScale (Lower = Scaled Furthur), XPosP, StretchR, XPosN
            GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, this.func_147525_a(0.0F, 0.0F, 1.0F, 0.0F)); //StretchL, ZPosP, ZScale (Lower = Scaled Furthur), ZPosN
            GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, this.func_147525_a(0.0F, 0.0F, 0.0F, 1.0F)); // No idea
            GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_147525_a(0.0F, 1.0F, 0.0F, 0.0F)); // Y Axis?
            GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
            GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, (float)(Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
            GL11.glScalef(f6, f6, f6);
            GL11.glTranslatef(0.5F, 0.5F, 0.0F);
            GL11.glRotatef((float)(i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
            GL11.glTranslatef(-f1, -f3, -f2);
            f9 = f8 + ActiveRenderInfo.objectY;
            GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, -f2);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            f11 = field_147527_e.nextFloat() * 0.5F + 0.1F;
            float f12 = field_147527_e.nextFloat() * 0.5F + 0.4F;
            float f13 = field_147527_e.nextFloat() * 0.5F + 0.5F;

            if (i == 0)
            {
                f13 = 1.0F;
                f12 = 1.0F;
                f11 = 1.0F;
            }

            tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 1.0F);
            
            //Top
            tessellator.addVertex(x + 0, y + 1, z + 0);
            tessellator.addVertex(x + 0, y + 1, z + 1);
            tessellator.addVertex(x + 1, y + 1, z + 1);
            tessellator.addVertex(x + 1, y + 1, z + 0);/*
            //East
            tessellator.addVertex(x + 1, y + 1, z + 1);
            tessellator.addVertex(x + 1, y + 0, z + 1);
            tessellator.addVertex(x + 1, y + 0, z + 0);
            tessellator.addVertex(x + 1, y + 1, z + 0);
            //West
            tessellator.addVertex(x + 0, y + 1, z + 0);
            tessellator.addVertex(x + 0, y + 0, z + 0);
            tessellator.addVertex(x + 0, y + 0, z + 1);
            tessellator.addVertex(x + 0, y + 1, z + 1);
            //South
            tessellator.addVertex(x + 0, y + 1, z + 1);
            tessellator.addVertex(x + 0, y + 0, z + 1);
            tessellator.addVertex(x + 1, y + 0, z + 1);
            tessellator.addVertex(x + 1, y + 1, z + 1);
            //North
            tessellator.addVertex(x + 1, y + 1, z + 0);
            tessellator.addVertex(x + 1, y + 0, z + 0);
            tessellator.addVertex(x + 0, y + 0, z + 0);
            tessellator.addVertex(x + 0, y + 1, z + 0);
            //Bottom
            tessellator.addVertex(x + 0, y + 0, z + 0);
            tessellator.addVertex(x + 1, y + 0, z + 0);
            tessellator.addVertex(x + 1, y + 0, z + 1);
            tessellator.addVertex(x + 0, y + 0, z + 1);
            */
            tessellator.draw();
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        }

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
        GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
        GL11.glEnable(GL11.GL_LIGHTING);
    }
    
    
}
