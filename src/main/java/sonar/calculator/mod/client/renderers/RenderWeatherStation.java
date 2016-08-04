package sonar.calculator.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sonar.calculator.mod.client.models.ModelWeatherStationBase;
import sonar.calculator.mod.client.models.ModelWeatherStationDish;
import sonar.calculator.mod.common.tileentity.machines.TileEntityWeatherStation;

public class RenderWeatherStation extends TileEntitySpecialRenderer<TileEntityWeatherStation> {
	private static final ResourceLocation baseTex = new ResourceLocation("Calculator:textures/model/weatherstation_base.png");
	private static final ResourceLocation dishTex = new ResourceLocation("Calculator:textures/model/weatherstation_dish.png");

	private ModelWeatherStationBase base;
	private ModelWeatherStationDish dish;

	public RenderWeatherStation() {
		this.base = new ModelWeatherStationBase();
		this.dish = new ModelWeatherStationDish();
	}

	@Override
	public void renderTileEntityAt(TileEntityWeatherStation te, double x, double y, double z, float partialTicks, int destroyStage) {
		int i;

		if (te.getWorld() == null) {
			i = 0;
		} else {
			Block block = te.getBlockType();
			i = te.getBlockMetadata();
			if ((block != null) && (i == 0)) {
				i = te.getBlockMetadata();
			}
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(baseTex);
		GL11.glPushMatrix();
		GL11.glRotatef(180.0F, 180.0F, 0.0F, 1.0F);

		GL11.glRotated(-0.625, 0, 1, 0);
		if (te.getWorld() != null) {
			if (te.angle == 1000) {
				int j = 0;
				if (i == 3) {
					j = 0;
				}
				if (i == 2) {
					j = 180;
				}
				if (i == 4) {
					j = 90;
				}
				if (i == 5) {
					j = 270;
				}
				GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
				this.base.renderBase((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
				this.base.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
				GL11.glTranslatef(0.0F, -0.9F, -0.84F);
				GL11.glRotated(45, 1.0F, 0.0F, 0.0F);
				Minecraft.getMinecraft().renderEngine.bindTexture(dishTex);
				this.dish.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			} else {
				this.base.renderBase((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
				GL11.glRotated(te.angle, 0.0F, 1.0F, 0.0F);
				this.base.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
				GL11.glTranslatef(0.0F, -0.9F, -0.84F);
				GL11.glRotated(45, 1.0F, 0.0F, 0.0F);
				Minecraft.getMinecraft().renderEngine.bindTexture(dishTex);
				this.dish.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			}

		} else {
			int j = 0;
			if (i == 3) {
				j = 0;
			}
			if (i == 2) {
				j = 180;
			}
			if (i == 4) {
				j = 90;
			}
			if (i == 5) {
				j = 270;
			}
			GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
			this.base.renderBase((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			this.base.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glTranslatef(0.0F, -0.9F, -0.84F);
			GL11.glRotatef(45, 1.0F, 0.0F, 0.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(dishTex);
			this.dish.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		}

		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
