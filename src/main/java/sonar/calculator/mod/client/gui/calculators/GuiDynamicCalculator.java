package sonar.calculator.mod.client.gui.calculators;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sonar.calculator.mod.common.containers.ContainerDynamicCalculator;
import sonar.calculator.mod.common.tileentity.misc.TileEntityCalculator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDynamicCalculator
  extends GuiContainer
{
  private ResourceLocation texture = new ResourceLocation("Calculator:textures/gui/dynamiccalculator.png");
  
  public GuiDynamicCalculator(EntityPlayer player, TileEntityCalculator.Dynamic dynamic) { 
	  super(new ContainerDynamicCalculator(player, dynamic));
    
    this.xSize = 176;
    this.ySize = 166;
  }

  
  @Override
protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
    
    drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
  }
}
