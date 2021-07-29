package me.andyw19.damagedrops.config;

import me.andyw19.damagedrops.DamageDrops;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Settings {

    private final DamageDrops plugin;

    private long dropChanceInterval;
    private boolean spawnerDrops;

    public HashMap<String,Boolean> mobEnabled = new HashMap<String, Boolean>();
    public HashMap<String,Integer> lootHighNum = new HashMap<String, Integer>();
    public HashMap<String,Integer> lootLevel = new HashMap<String, Integer>();

    public final List<Material> creeperCommonMatList = new ArrayList<>();
    public final List<Material> creeperMediumMatList = new ArrayList<>();
    public final List<Material> creeperRareMatList = new ArrayList<>();

    public final List<Material> zombieCommonMatList = new ArrayList<>();
    public final List<Material> zombieMediumMatList = new ArrayList<>();
    public final List<Material> zombieRareMatList = new ArrayList<>();

    public final List<Material> skeletonCommonMatList = new ArrayList<>();
    public final List<Material> skeletonMediumMatList = new ArrayList<>();
    public final List<Material> skeletonRareMatList = new ArrayList<>();

    public final List<Material> golemCommonMatList = new ArrayList<>();
    public final List<Material> golemMediumMatList = new ArrayList<>();
    public final List<Material> golemRareMatList = new ArrayList<>();

    public final List<Material> blazeCommonMatList = new ArrayList<>();
    public final List<Material> blazeMediumMatList = new ArrayList<>();
    public final List<Material> blazeRareMatList = new ArrayList<>();

    public final List<Material> beeCommonMatList = new ArrayList<>();
    public final List<Material> beeMediumMatList = new ArrayList<>();
    public final List<Material> beeRareMatList = new ArrayList<>();

    public final List<Material> piglinCommonMatList = new ArrayList<>();
    public final List<Material> piglinMediumMatList = new ArrayList<>();
    public final List<Material> piglinRareMatList = new ArrayList<>();

    private final double [] creeperDropChance = new double[3];
    private final double [] zombieDropChance = new double[3];
    private final double [] skeletonDropChance = new double[3];
    private final double [] golemDropChance = new double[3];
    private final double [] blazeDropChance = new double[3];
    private final double [] beeDropChance = new double[3];
    private final double [] piglinDropChance = new double[3];

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
            this.mobEnabled.put("creeper", creeper.getBoolean("creeperdrops", true));
            this.lootHighNum.put("creeper", creeper.getInt("highestloot", 2));
            this.lootLevel.put("creeper", creeper.getInt("loot.lootlevel", 3));
            this.creeperDropChance[0] = creeper.getDouble("commonlootchance", 0.35);
            this.creeperDropChance[1] = creeper.getDouble("mediumlootchance", 0.15);
            this.creeperDropChance[2] = creeper.getDouble("rarelootchance", 0.02);
            List<String> creeperCommonList = config.getStringList("mobs.creeper.loot.commonloot");
            List<String> creeperMediumList = config.getStringList("mobs.creeper.loot.mediumloot");
            List<String> creeperRareList = config.getStringList("mobs.creeper.loot.rareloot");
            checkMat(creeperCommonList, creeperCommonMatList);
            checkMat(creeperMediumList, creeperMediumMatList);
            checkMat(creeperRareList, creeperRareMatList);


            ConfigurationSection zombie = config.getConfigurationSection("mobs.zombie");
            this.mobEnabled.put("zombie", zombie.getBoolean("zombiedrops", true));
            this.lootHighNum.put("zombie", zombie.getInt("highestloot", 2));
            this.lootLevel.put("zombie", zombie.getInt("loot.lootlevel", 3));
            this.zombieDropChance[0] = zombie.getDouble("commonlootchance", 0.35);
            this.zombieDropChance[1] = zombie.getDouble("mediumlootchance", 0.15);
            this.zombieDropChance[2] = zombie.getDouble("rarelootchance", 0.02);
            List<String> zombieCommonList = config.getStringList("mobs.zombie.loot.commonloot");
            List<String> zombieMediumList = config.getStringList("mobs.zombie.loot.mediumloot");
            List<String> zombieRareList = config.getStringList("mobs.zombie.loot.rareloot");
            checkMat(zombieCommonList, zombieCommonMatList);
            checkMat(zombieMediumList, zombieMediumMatList);
            checkMat(zombieRareList, zombieRareMatList);

            ConfigurationSection skeleton = config.getConfigurationSection("mobs.skeleton");
            this.mobEnabled.put("skeleton", skeleton.getBoolean("skeletondrops", true));
            this.lootHighNum.put("skeleton", skeleton.getInt("highestloot", 2));
            this.lootLevel.put("skeleton", skeleton.getInt("loot.lootlevel", 3));
            this.skeletonDropChance[0] = skeleton.getDouble("commonlootchance", 0.35);
            this.skeletonDropChance[1] = skeleton.getDouble("mediumlootchance", 0.15);
            this.skeletonDropChance[2] = skeleton.getDouble("rarelootchance", 0.02);
            List<String> skeletonCommonList = config.getStringList("mobs.skeleton.loot.commonloot");
            List<String> skeletonMediumList = config.getStringList("mobs.skeleton.loot.mediumloot");
            List<String> skeletonRareList = config.getStringList("mobs.skeleton.loot.rareloot");
            checkMat(skeletonCommonList, skeletonCommonMatList);
            checkMat(skeletonMediumList, skeletonMediumMatList);
            checkMat(skeletonRareList, skeletonRareMatList);

            ConfigurationSection irongolem = config.getConfigurationSection("mobs.irongolem");
            this.mobEnabled.put("irongolem", irongolem.getBoolean("golemdrops", true));
            this.lootHighNum.put("irongolem", irongolem.getInt("highestloot", 2));
            this.lootLevel.put("irongolem", irongolem.getInt("loot.lootlevel", 3));
            this.golemDropChance[0] = irongolem.getDouble("commonlootchance", 0.35);
            this.golemDropChance[1] = irongolem.getDouble("mediumlootchance", 0.15);
            this.golemDropChance[2] = irongolem.getDouble("rarelootchance", 0.02);
            List<String> golemCommonList = config.getStringList("mobs.golem.loot.commonloot");
            List<String> golemMediumList = config.getStringList("mobs.golem.loot.mediumloot");
            List<String> golemRareList = config.getStringList("mobs.golem.loot.rareloot");
            checkMat(golemCommonList, golemCommonMatList);
            checkMat(golemMediumList, golemMediumMatList);
            checkMat(golemRareList, golemRareMatList);

            ConfigurationSection blaze = config.getConfigurationSection("mobs.blaze");
            this.mobEnabled.put("blaze", blaze.getBoolean("blazedrops", true));
            this.lootHighNum.put("blaze", blaze.getInt("highestloot", 2));
            this.lootLevel.put("blaze", blaze.getInt("loot.lootlevel", 3));
            this.blazeDropChance[0] = blaze.getDouble("commonlootchance", 0.35);
            this.blazeDropChance[1] = blaze.getDouble("mediumlootchance", 0.15);
            this.blazeDropChance[2] = blaze.getDouble("rarelootchance", 0.02);
            List<String> blazeCommonList = config.getStringList("mobs.blaze.loot.commonloot");
            List<String> blazeMediumList = config.getStringList("mobs.blaze.loot.mediumloot");
            List<String> blazeRareList = config.getStringList("mobs.blaze.loot.rareloot");
            checkMat(blazeCommonList, blazeCommonMatList);
            checkMat(blazeMediumList, blazeMediumMatList);
            checkMat(blazeRareList, blazeRareMatList);

            ConfigurationSection bee = config.getConfigurationSection("mobs.bee");
            this.mobEnabled.put("bee", bee.getBoolean("beedrops", true));
            this.lootHighNum.put("bee", bee.getInt("highestloot", 1));
            this.lootLevel.put("bee", bee.getInt("loot.lootlevel", 3));
            this.beeDropChance[0] = bee.getDouble("commonlootchance", 0.35);
            this.beeDropChance[1] = bee.getDouble("mediumlootchance", 0.15);
            this.beeDropChance[2] = bee.getDouble("rarelootchance", 0.02);
            List<String> beeCommonList = config.getStringList("mobs.bee.loot.commonloot");
            List<String> beeMediumList = config.getStringList("mobs.bee.loot.mediumloot");
            List<String> beeRareList = config.getStringList("mobs.bee.loot.rareloot");
            checkMat(beeCommonList, beeCommonMatList);
            checkMat(beeMediumList, beeMediumMatList);
            checkMat(beeRareList, beeRareMatList);

            ConfigurationSection piglin = config.getConfigurationSection("mobs.piglin");
            this.mobEnabled.put("piglin", piglin.getBoolean("piglindrops", true));
            this.lootHighNum.put("piglin", piglin.getInt("highestloot", 1));
            this.lootLevel.put("piglin", piglin.getInt("loot.lootlevel", 3));
            this.piglinDropChance[0] = piglin.getDouble("commonlootchance", 0.35);
            this.piglinDropChance[1] = piglin.getDouble("mediumlootchance", 0.15);
            this.piglinDropChance[2] = piglin.getDouble("rarelootchance", 0.02);
            List<String> piglinCommonList = config.getStringList("mobs.piglin.loot.commonloot");
            List<String> piglinMediumList = config.getStringList("mobs.piglin.loot.mediumloot");
            List<String> piglinRareList = config.getStringList("mobs.piglin.loot.rareloot");
            checkMat(piglinCommonList, piglinCommonMatList);
            checkMat(piglinMediumList, piglinMediumMatList);
            checkMat(piglinRareList, piglinRareMatList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkMat(List<String> stringList, List<Material> matList) {
        for(String material : stringList) {
            Material mat = Material.getMaterial(material);
            if (material == null) continue;
            matList.add(mat);
        }
    }

    public long getDropChanceInterval() {
        return dropChanceInterval;
    }

    public boolean isSpawnerDrops() { return spawnerDrops; }

    public double [] getCreeperDropChance(){ return creeperDropChance; }
    public double [] getZombieDropChance(){ return zombieDropChance; }
    public double [] getSkeletonDropChance(){ return skeletonDropChance; }
    public double [] getGolemDropChance(){ return golemDropChance; }
    public double [] getBlazeDropChance(){ return blazeDropChance; }
    public double [] getBeeDropChance(){ return beeDropChance; }
    public double [] getPiglinDropChance(){ return piglinDropChance;}
}
