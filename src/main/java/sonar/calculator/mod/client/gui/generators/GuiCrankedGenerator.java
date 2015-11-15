package sonar.calculator.mod.client.gui.generators;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import sonar.calculator.mod.CalculatorConfig;
import sonar.calculator.mod.common.containers.ContainerCrankedGenerator;
import sonar.calculator.mod.common.tileentity.generators.TileEntityCrankedGenerator;
import sonar.core.utils.helpers.FontHelper;

public class GuiCrankedGenerator extends GuiContainer {
	public static final ResourceLocation bground = new ResourceLocation("Calculator:textures/gui/guicrank.png");

	public TileEntityCrankedGenerator entity;

	public GuiCrankedGenerator(InventoryPlayer inventoryPlayer, TileEntityCrankedGenerator entity) {
		super(new ContainerCrankedGenerator(inventoryPlayer, entity));

		this.entity = entity;

		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		FontHelper.textCentre(FontHelper.translate("tile.CrankedGenerator.name"), this.xSize, 6, 0);
		if (this.entity.cranked()) {
			FontHelper.textCentre(FontHelper.translate(FontHelper.translate("crank.cranked") + ": " + FontHelper.translate("locator.true")), this.xSize, 25, 0);
		}
		if (!this.entity.cranked()) {
			FontHelper.textCentre(FontHelper.translate(FontHelper.translate("crank.cranked") + ": " + FontHelper.translate("locator.false")), this.xSize, 25, 0);

		}
		FontHelper.textCentre(FontHelper.formatStorage(entity.storage.getEnergyStored()), this.xSize, 64, 2);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int k = this.entity.storage.getEnergyStored() * 78 / 1000;
		int j = 78 - k;
		drawTexturedModalRect(this.guiLeft + 49, this.guiTop + 63, 176, 0, k, 10);
	}
}
