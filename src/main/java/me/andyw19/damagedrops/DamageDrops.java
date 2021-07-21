package me.andyw19.damagedrops;

import me.andyw19.damagedrops.commands.ReloadPlugin;
import me.andyw19.damagedrops.config.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public final class DamageDrops extends JavaPlugin {

    private static Settings settings;
    public static Settings getSettings() {
        return settings;
    }


    @Override
    public void onLoad() {
        // Set instance for easy cross-class referencing
        settings = new Settings(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerConfig();
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        this.getCommand("damagedropsreload").setExecutor(new ReloadPlugin(this));
        System.out.println("DamageDrops has loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("DamageDrops has unloaded");
    }

    private void registerConfig() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        settings.reload();
    }


}
