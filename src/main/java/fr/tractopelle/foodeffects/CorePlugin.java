package fr.tractopelle.foodeffects;

import fr.tractopelle.foodeffects.food.Food;
import fr.tractopelle.foodeffects.commands.command.FoodCommand;
import fr.tractopelle.foodeffects.config.Config;
import fr.tractopelle.foodeffects.listeners.PlayerListener;
import fr.tractopelle.foodeffects.manager.FoodsManager;
import fr.tractopelle.foodeffects.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CorePlugin extends JavaPlugin {

    private Config configuration;
    private final Logger log = new Logger(this.getDescription().getFullName());
    private FoodsManager foodsManager;

    @Override
    public void onEnable() {

        this.init();

    }

    private void init() {

        log.info("=======================================", Logger.LogType.SUCCESS);
        log.info(" Plugin initialization in progress...", Logger.LogType.SUCCESS);
        log.info(" Author: Tractopelle#4020", Logger.LogType.SUCCESS);
        log.info("=======================================", Logger.LogType.SUCCESS);

        registerListeners();

        registerCommands();

        this.configuration = new Config(this, "config");

        this.foodsManager = new FoodsManager(this);

    }

    private void registerCommands() {

        new FoodCommand(this);

    }

    private void registerListeners() {

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);

    }

    public Config getConfiguration() { return configuration; }

    public FoodsManager getFoodsManager() { return foodsManager; }

}
