package fr.tractopelle.foodeffects.base;

import fr.tractopelle.foodeffects.base.type.FoodType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Food {

    private String identifier;
    private ItemStack itemStack;
    private FoodType foodType;
    private PotionEffectType potionEffectType;
    private Integer level;
    private Integer duration;

    public Food(String identifier, ItemStack itemStack, FoodType foodType, PotionEffectType potionEffectType, Integer level, Integer duration) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.foodType = foodType;
        this.potionEffectType = potionEffectType;
        this.level = level;
        this.duration = duration;
    }

    public String getIdentifier() { return identifier; }

    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public ItemStack getItemStack() { return itemStack; }

    public void setItemStack(ItemStack itemStack) { this.itemStack = itemStack; }

    public FoodType getFoodType() { return foodType; }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public PotionEffectType getEffectType() { return potionEffectType; }

    public void setEffectType(PotionEffectType effectType) { this.potionEffectType = effectType; }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getDuration() { return duration; }

    public void setDuration(Integer duration) { this.duration = duration; }

}
