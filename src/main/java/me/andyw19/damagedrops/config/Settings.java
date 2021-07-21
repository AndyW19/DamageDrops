package me.andyw19.damagedrops.config;

import me.andyw19.damagedrops.DamageDrops;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

    private final DamageDrops plugin;

    private long dropChanceInterval;

    private boolean spawnerDrops;
    private boolean golemDrops;
    private boolean skeletonDrops;
    private boolean zombieDrops;

    public Settings(DamageDrops damageDrops) {
        this.plugin = damageDrops;
    }

    public void reload() {
        plugin.reloadConfig();
        reloadFromFile(plugin.getConfig());
    }

    public void reloadFromFile(FileConfiguration config) {
        try {
            this.dropChanceInterval = config.getLong("drop_chance_interval", 1000);
            this.spawnerDrops = config.getBoolean("spawnerdrops", false);
            this.golemDrops = config.getBoolean("golemdrops", true);
            this.skeletonDrops = config.getBoolean("skeletondrops", true);
            this.zombieDrops = config.getBoolean("zombiedrops", true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean isGolemDrops() {
        return golemDrops;
    }

    public long getDropChanceInterval() {
        return dropChanceInterval;
    }

    public boolean isSkeletonDrops() {
        return skeletonDrops;
    }

    public boolean isZombieDrops() { return zombieDrops; }

    public boolean isSpawnerDrops() { return spawnerDrops; }
}