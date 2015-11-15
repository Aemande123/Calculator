package sonar.calculator.mod.common.item.modules;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import sonar.calculator.mod.Calculator;
import sonar.calculator.mod.CalculatorConfig;
import sonar.calculator.mod.common.item.calculators.SonarCalculator;
import sonar.core.utils.helpers.FontHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TerrainModule extends BaseTerrainModule {

	public TerrainModule() {
		super.replacable = new Block[]{ Blocks.grass, Blocks.dirt, Blocks.stone};
		capacity = CalculatorConfig.getInteger("Terrain Module");
		maxReceive = CalculatorConfig.getInteger("Terrain Module");
		maxExtract = CalculatorConfig.getInteger("Terrain Module");
		maxTransfer = CalculatorConfig.getInteger("Terrain Module");
	}
}
