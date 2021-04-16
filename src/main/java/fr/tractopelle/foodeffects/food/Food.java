package fr.tractopelle.foodeffects.food;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Food {

    private final String identifier;
    private final ItemStack itemStack;
    private final PotionEffectType potionEffectType;
    private final Integer level;
    private final Integer duration;

    public Food(String identifier, ItemStack itemStack, PotionEffectType potionEffectType, Integer level, Integer duration) {

        this.identifier = identifier;
        this.itemStack = itemStack;
        this.potionEffectType = potionEffectType;
        this.level = level;
        this.duration = duration;

    }

    public String getIdentifier() { return identifier; }

    public ItemStack getItemStack() { return itemStack; }

    public PotionEffectType getEffectType() { return potionEffectType; }

    public Integer getLevel() {
        return level;
    }

    public Integer getDuration() { return duration; }

}
