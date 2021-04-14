package fr.tractopelle.foodeffects.manager;

import fr.tractopelle.foodeffects.CorePlugin;
import fr.tractopelle.foodeffects.base.Food;
import fr.tractopelle.foodeffects.base.type.FoodType;
import fr.tractopelle.foodeffects.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FoodsManager {

    private CorePlugin corePlugin;
    private List<Food> foods = new ArrayList<>();

    public FoodsManager(CorePlugin corePlugin) {

        this.corePlugin = corePlugin;
        this.loadFoods();

    }

    public List<Food> getFoodsList() {
        return foods;
    }

    private void addFoodList(String identifier, ItemStack itemStack, FoodType foodType, PotionEffectType potionEffectType, Integer level, Integer duration) {

        this.foods.add(new Food(identifier, itemStack, foodType, potionEffectType, level, duration));

    }

    private void loadFoods(){

        int i = 1;

        for (String s : corePlugin.getConfiguration().getConfigurationSection("ITEMS").getKeys(false)) {

            String foodName = corePlugin.getConfiguration().getString("ITEMS." + i + ".MATERIAL");

            if (FoodType.isFood(foodName.toLowerCase(Locale.ROOT))) {

                FoodType assetType = FoodType.getAssetFromString(foodName);

                addFoodList(corePlugin.getConfiguration().getString("ITEMS." + i + ".IDENTIFIER"),
                        new ItemBuilder(Material.getMaterial(corePlugin.getConfiguration().getString("ITEMS." + i + ".MATERIAL")))
                                .setName(corePlugin.getConfiguration().getString("ITEMS." + i + ".NAME"))
                                .setListLore(corePlugin.getConfiguration().getStringList("ITEMS." + i + ".LORE"))
                                .addGlow(corePlugin.getConfiguration().getBoolean("ITEMS." + i + ".GLOW"))
                                .toItemStack(),

                        assetType,
                        PotionEffectType.getByName(corePlugin.getConfiguration().getString("ITEMS." + i + ".EFFECT")),
                        corePlugin.getConfiguration().getInt("ITEMS." + i + ".LEVEL"),
                        corePlugin.getConfiguration().getInt("ITEMS." + i + ".DURATION")
                );

            }

            i++;

        }
    }


}
