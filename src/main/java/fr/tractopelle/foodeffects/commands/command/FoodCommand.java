package fr.tractopelle.foodeffects.commands.command;

import fr.tractopelle.foodeffects.CorePlugin;
import fr.tractopelle.foodeffects.food.Food;
import fr.tractopelle.foodeffects.commands.FCommand;
import fr.tractopelle.foodeffects.item.ItemBuilder;
import fr.tractopelle.foodeffects.item.RItemUnsafe;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodCommand extends FCommand {

    private final CorePlugin corePlugin;

    public FoodCommand(CorePlugin corePlugin) {
        super(corePlugin, "foodeffects", true, "FOODEFFECTS.ADMIN");
        this.corePlugin = corePlugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {

        String prefix = corePlugin.getConfiguration().getString("PREFIX");

        if (args.length == 1 && args[0].equalsIgnoreCase("info")) {

            List<String> foodName = corePlugin.getFoodsManager().getFoodsList().stream()
                    .map(Food::getIdentifier).collect(Collectors.toList());

            commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("FOOD-LIST")
                    .replace("%foods%", StringUtils.join(foodName, ", ")));

        } else if (args.length != 4) {

            corePlugin.getConfiguration().getStringList("USAGE-ADMIN").forEach(commandSender::sendMessage);
            return false;

        } else {

            if (!(args[0].equalsIgnoreCase("give"))) {
                corePlugin.getConfiguration().getStringList("USAGE-ADMIN").forEach(commandSender::sendMessage);
                return false;

            }

            if (Bukkit.getPlayer(args[1]) == null) {
                commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("UNKNOW-PLAYER"));
                return false;
            }

            if (!(isInt(args[2]))) {

                commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("NOT-AN-INT"));
                return false;

            }

            Player target = Bukkit.getPlayer(args[1]);
            int amount = Integer.parseInt(args[2]);

            Optional<Food> foodOptional = corePlugin.getFoodsManager().getFoodsList().stream().filter(food -> food.getIdentifier().equalsIgnoreCase(args[3])).findFirst();

            if (foodOptional.isPresent()) {

                RItemUnsafe rItemUnsafe = new RItemUnsafe(new ItemBuilder(foodOptional.get().getItemStack()));
                rItemUnsafe.setString("identifier", foodOptional.get().getIdentifier());
                rItemUnsafe.toItemBuilder().toItemStack().setAmount(amount);

                if (target.getInventory().firstEmpty() == -1) {

                    target.getLocation().getWorld().dropItemNaturally(target.getLocation(), rItemUnsafe.toItemBuilder().toItemStack());

                } else {

                    target.getInventory().addItem(rItemUnsafe.toItemBuilder().toItemStack());

                }

                commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("GIVE-FOOD")
                        .replace("%amount%", String.valueOf(amount))
                        .replace("%food%", foodOptional.get().getIdentifier())
                        .replace("%player%", target.getName()));

            } else {

                List<String> foodName = corePlugin.getFoodsManager().getFoodsList().stream()
                        .map(Food::getIdentifier).collect(Collectors.toList());

                commandSender.sendMessage(prefix + corePlugin.getConfiguration().getString("NOT-AN-FOOD")
                        .replace("%foods%", StringUtils.join(foodName, ", ")));

            }

        }

        return false;

    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (Throwable e) {
            return false;
        }
        return true;
    }
}
