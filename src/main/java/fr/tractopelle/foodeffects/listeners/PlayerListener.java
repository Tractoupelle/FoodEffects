package fr.tractopelle.foodeffects.listeners;

import fr.tractopelle.foodeffects.CorePlugin;
import fr.tractopelle.foodeffects.food.Food;
import fr.tractopelle.foodeffects.item.RNBItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class PlayerListener implements Listener {

    private final CorePlugin corePlugin;

    public PlayerListener(CorePlugin corePlugin) {
        this.corePlugin = corePlugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;

        if (!(event.getItem().hasItemMeta()) && !(event.getItem().getItemMeta().hasLore())) return;

        ItemStack itemStack = event.getItem();

        Optional<Food> foodOptional = corePlugin.getFoodsManager().getFood(itemStack);

        if (foodOptional.isPresent()) {

            if (event.getPlayer().getFoodLevel() >= 20) {
                event.getPlayer().setFoodLevel(19);

            }
        }
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {

        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;

        if (!(event.getItem().hasItemMeta()) && !(event.getItem().getItemMeta().hasLore())) return;

        ItemStack itemStack = event.getItem();

        Optional<Food> foodOptional = corePlugin.getFoodsManager().getFood(itemStack);

        if (foodOptional.isPresent()) {

            Player player = event.getPlayer();
            PotionEffectType potionEffectType = foodOptional.get().getEffectType();

            if(player.hasPotionEffect(potionEffectType)){

                player.removePotionEffect(potionEffectType);

            }

            player.addPotionEffect(new PotionEffect(potionEffectType, foodOptional.get().getDuration() * 20, foodOptional.get().getLevel() - 1));

        }
    }
}
