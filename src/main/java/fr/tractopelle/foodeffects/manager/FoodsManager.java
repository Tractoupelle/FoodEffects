package fr.tractopelle.foodeffects.manager;

import fr.tractopelle.foodeffects.CorePlugin;
import fr.tractopelle.foodeffects.food.Food;
import fr.tractopelle.foodeffects.food.type.FoodType;
import fr.tractopelle.foodeffects.item.ItemBuilder;
import fr.tractopelle.foodeffects.item.RNBItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class FoodsManager {

    private final CorePlugin corePlugin;
    private List<Food> foods = new ArrayList<>();

    public FoodsManager(CorePlugin corePlugin) {

        this.corePlugin = corePlugin;
        this.loadFoods();

    }

    public List<Food> getFoodsList() {
        return foods;
    }

    private void addFoodList(String identifier, ItemStack itemStack, PotionEffectType potionEffectType, Integer level, Integer duration) {

        this.foods.add(new Food(identifier, itemStack, potionEffectType, level, duration));

    }

    private void loadFoods(){

        for (int i = 1; i < corePlugin.getConfiguration().getConfigurationSection("ITEMS").getKeys(false).size() + 1;  i++) {

            if (FoodType.isFoodFromString(corePlugin.getConfiguration().getString("ITEMS." + i + ".MATERIAL").toLowerCase(Locale.ROOT))) {

                addFoodList(corePlugin.getConfiguration().getString("ITEMS." + i + ".IDENTIFIER"),
                        new ItemBuilder(Material.getMaterial(corePlugin.getConfiguration().getString("ITEMS." + i + ".MATERIAL")))
                                .setName(corePlugin.getConfiguration().getString("ITEMS." + i + ".NAME"))
                                .setListLore(corePlugin.getConfiguration().getStringList("ITEMS." + i + ".LORE"))
                                .addGlow(corePlugin.getConfiguration().getBoolean("ITEMS." + i + ".GLOW"))
                                .toItemStack(),
                        PotionEffectType.getByName(corePlugin.getConfiguration().getString("ITEMS." + i + ".EFFECT")),
                        corePlugin.getConfiguration().getInt("ITEMS." + i + ".LEVEL"),
                        corePlugin.getConfiguration().getInt("ITEMS." + i + ".DURATION")

                );
            }
        }
    }

    public Optional<Food> getFood (ItemStack itemStack) {

        return corePlugin.getFoodsManager().getFoodsList().stream().filter(food -> food.getIdentifier().equals(new RNBItem(itemStack).getString("identifier"))).findFirst();

    }
}
