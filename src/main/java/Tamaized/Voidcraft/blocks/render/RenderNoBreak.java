package Tamaized.Voidcraft.blocks.render;

import java.nio.FloatBuffer;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderNoBreak extends TileEntitySpecialRenderer {
	
	private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
    private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
    private static final Random field_147527_e = new Random(31100L);
    FloatBuffer field_147528_b = GLAllocation.createDirectFloatBuffer(16);
    private static final String __OBFID = "CL_00000972";

    private FloatBuffer func_147525_a(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_){
        this.field_147528_b.clear();
        this.field_147528_b.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
        this.field_147528_b.flip();
        return this.field_147528_b;
    }

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float p_180535_8_, int p_180535_9_){
    	float f1 = (float)this.rendererDispatcher.entityX;
        float f2 = (float)this.rendererDispatcher.entityY;
        float f3 = (float)this.rendererDispatcher.entityZ;
        GlStateManager.disableLighting();
        field_147527_e.setSeed(31100L);
        float f4 = 0.75F;

        for (int j = 0; j < 16; ++j)
        {
            GlStateManager.pushMatrix();
            float f5 = (float)(16 - j);
            float f6 = 0.0625F;
            float f7 = 1.0F / (f5 + 1.0F);

            if (j == 0)
            {
                this.bindTexture(field_147529_c);
                f7 = 0.1F;
                f5 = 65.0F;
                f6 = 0.125F;
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(770, 771);
            }

            if (j >= 1)
            {
                this.bindTexture(field_147526_d);
            }

            if (j == 1)
            {
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(1, 1);
                f6 = 0.5F;
            }

            float f8 = (float)(-(y + (double)f4));
            float f9 = f8 + (float)ActiveRenderInfo.getPosition().yCoord;
            float f10 = f8 + f5 + (float)ActiveRenderInfo.getPosition().yCoord;
            float f11 = f9 / f10;
            f11 += (float)(y + (double)f4);
            GlStateManager.translate(f1, f11, f3);
            GlStateManager.texGen(GlStateManager.TexGen.S, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.T, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.R, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.Q, 9216);
            GlStateManager.texGen(GlStateManager.TexGen.S, 9473, this.func_147525_a(1.0F, 0.0F, 0.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.T, 9473, this.func_147525_a(0.0F, 0.0F, 1.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.R, 9473, this.func_147525_a(0.0F, 0.0F, 0.0F, 1.0F));
            GlStateManager.texGen(GlStateManager.TexGen.Q, 9474, this.func_147525_a(0.0F, 1.0F, 0.0F, 0.0F));
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.S);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.T);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.R);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.Q);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, (float)(Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
            GlStateManager.scale(f6, f6, f6);
            GlStateManager.translate(0.5F, 0.5F, 0.0F);
            GlStateManager.rotate((float)(j * j * 4321 + j * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-0.5F, -0.5F, 0.0F);
            GlStateManager.translate(-f1, -f3, -f2);
            f9 = f8 + (float)ActiveRenderInfo.getPosition().yCoord;
            GlStateManager.translate((float)ActiveRenderInfo.getPosition().xCoord * f5 / f9, (float)ActiveRenderInfo.getPosition().zCoord * f5 / f9, -f2);
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer worldrenderer = tessellator.getBuffer();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            float f12 = field_147527_e.nextFloat() * 0.5F + 0.1F;
            float f13 = field_147527_e.nextFloat() * 0.5F + 0.4F;
            float f14 = field_147527_e.nextFloat() * 0.5F + 0.5F;

            if (j == 0)
            {
                f14 = 1.0F;
                f13 = 1.0F;
                f12 = 1.0F;
            }
            
            //Top
            worldrenderer.pos(x + 0, y + 1, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 1, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 1, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 1, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            //East
            worldrenderer.pos(x + 1, y + 1, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 0, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 0, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 1, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            //West
            worldrenderer.pos(x + 0, y + 1, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 0, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 0, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 1, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            //South
            worldrenderer.pos(x + 0, y + 1, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 0, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 0, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 1, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            //North
            worldrenderer.pos(x + 1, y + 1, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 0, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 0, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 1, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            //Bottom
            worldrenderer.pos(x + 0, y + 0, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 0, z + 0).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 1, y + 0, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            worldrenderer.pos(x + 0, y + 0, z + 1).color(f11, f12, f13, 1.0F).endVertex();
            
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

        GlStateManager.disableBlend();
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.S);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.T);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.R);
        GlStateManager.disableTexGenCoord(GlStateManager.TexGen.Q);
        GlStateManager.enableLighting();
    }
    
    
}
