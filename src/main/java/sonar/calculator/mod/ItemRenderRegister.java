package sonar.calculator.mod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import sonar.core.common.block.properties.IMetaRenderer;

public class ItemRenderRegister {
	public static void register() {
		for (Item item : CalculatorItems.registeredItems) {
			if (item.getHasSubtypes()) {
				List<ItemStack> stacks = new ArrayList();
				item.getSubItems(item, Calculator.Calculator, stacks);
				for (ItemStack stack : stacks) {
					String variant = "variant=meta" + stack.getItemDamage();
					if (item instanceof IMetaRenderer) {
						IMetaRenderer meta = (IMetaRenderer) item;
						variant = "variant=" + meta.getVariant(stack.getItemDamage()).getName();
					}
					ModelLoader.setCustomModelResourceLocation(item, stack.getItemDamage(), new ModelResourceLocation(Calculator.modid + ":" + "items/" + item.getUnlocalizedName().substring(5), variant));
				}
			} else {
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Calculator.modid, item.getUnlocalizedName().substring(5)), "inventory"));
			}
		}
	}
}
