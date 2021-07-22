package me.andyw19.damagedrops.config;

import me.andyw19.damagedrops.DamageDrops;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

    private final DamageDrops plugin;

    private long dropChanceInterval;

    private boolean spawnerDrops;
    private boolean golemDrops;
    private boolean skeletonDrops;
    private boolean zombieDrops;
    private boolean creeperDrops;
    private boolean beeDrops;
    private boolean blazeDrops;

    private final double [] creeperDropChance = new double[3];
    private final double [] zombieDropChance = new double[3];
    private final double [] skeletonDropChance = new double[3];
    private final double [] golemDropChance = new double[3];
    private final double [] blazeDropChance = new double[3];
    private final double [] beeDropChance = new double[3];

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

            ConfigurationSection creeper = config.getConfigurationSection("mobs.creeper");
            this.creeperDrops = creeper.getBoolean("creeperdrops", true);
            this.creeperDropChance[0] = creeper.getDouble("commonlootchance", 0.35);
            this.creeperDropChance[1] = creeper.getDouble("mediumlootchance", 0.15);
            this.creeperDropChance[2] = creeper.getDouble("rarelootchance", 0.02);

            ConfigurationSection zombie = config.getConfigurationSection("mobs.zombie");
            this.zombieDrops = zombie.getBoolean("zombiedrops", true);
            this.zombieDropChance[0] = zombie.getDouble("commonlootchance", 0.35);
            this.zombieDropChance[1] = zombie.getDouble("mediumlootchance", 0.15);
            this.zombieDropChance[2] = zombie.getDouble("rarelootchance", 0.02);

            ConfigurationSection skeleton = config.getConfigurationSection("mobs.skeleton");
            this.skeletonDrops = skeleton.getBoolean("skeletondrops", true);
            this.skeletonDropChance[0] = skeleton.getDouble("commonlootchance", 0.35);
            this.skeletonDropChance[1] = skeleton.getDouble("mediumlootchance", 0.15);
            this.skeletonDropChance[2] = skeleton.getDouble("rarelootchance", 0.02);

            ConfigurationSection irongolem = config.getConfigurationSection("mobs.irongolem");
            this.golemDrops = irongolem.getBoolean("irongolemdrops", true);
            this.golemDropChance[0] = irongolem.getDouble("commonlootchance", 0.35);
            this.golemDropChance[1] = irongolem.getDouble("mediumlootchance", 0.15);
            this.golemDropChance[2] = irongolem.getDouble("rarelootchance", 0.02);

            ConfigurationSection blaze = config.getConfigurationSection("mobs.blaze");
            this.blazeDrops = blaze.getBoolean("blazedrops", true);
            this.blazeDropChance[0] = blaze.getDouble("commonlootchance", 0.35);
            this.blazeDropChance[1] = blaze.getDouble("mediumlootchance", 0.15);
            this.blazeDropChance[2] = blaze.getDouble("rarelootchance", 0.02);

            ConfigurationSection bee = config.getConfigurationSection("mobs.bee");
            this.beeDrops = bee.getBoolean("beedrops", true);
            this.beeDropChance[0] = bee.getDouble("commonlootchance", 0.35);
            this.beeDropChance[1] = bee.getDouble("mediumlootchance", 0.15);
            this.beeDropChance[2] = bee.getDouble("rarelootchance", 0.02);

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

    public boolean isCreeperDrops() {
        return creeperDrops;
    }

    public boolean isBeeDrops() {
        return beeDrops;
    }

    public boolean isBlazeDrops() {
        return blazeDrops;
    }

    public double [] getCreeperDropChance(){ return creeperDropChance; }
    public double [] getZombieDropChance(){ return zombieDropChance; }
    public double [] getSkeletonDropChance(){ return skeletonDropChance; }
    public double [] getGolemDropChance(){ return golemDropChance; }
    public double [] getBlazeDropChance(){ return blazeDropChance; }
    public double [] getBeeDropChance(){ return beeDropChance; }
}
