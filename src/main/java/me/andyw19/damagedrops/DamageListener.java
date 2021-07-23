package me.andyw19.damagedrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class DamageListener implements Listener {

    private DamageDrops damageDrops;

    public DamageListener(DamageDrops damageDrops) {this.damageDrops = damageDrops;}

    private long lastBoolTimeMillis = 0L;
    private final long dropChanceTime = DamageDrops.getSettings().getDropChanceInterval();
    private boolean golemCanPass = true;
    private boolean skeletonCanPass = true;
    private boolean zombieCanPass = true;
    private boolean creeperCanPass = true;
    private boolean beeCanPass = true;
    private boolean blazeCanPass = true;

    public final Material [] ironToNetheritePickaxe = {Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE, Material.GOLDEN_PICKAXE, Material.NETHERITE_PICKAXE};
    public final Material [] ironToNetheriteAxeSword = {Material.IRON_AXE, Material.IRON_SWORD, Material.GOLDEN_AXE, Material.GOLDEN_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_SWORD,
    Material.NETHERITE_AXE, Material.NETHERITE_SWORD};

    private final List<Material> creeperCommonLoot = DamageDrops.getSettings().creeperCommonMatList;
    private final List<Material> creeperMediumLoot = DamageDrops.getSettings().creeperMediumMatList;
    private final List<Material> creeperRareLoot = DamageDrops.getSettings().creeperRareMatList;

    private final List<Material> zombieCommonLoot = DamageDrops.getSettings().zombieCommonMatList;
    private final List<Material> zombieMediumLoot = DamageDrops.getSettings().zombieMediumMatList;
    private final List<Material> zombieRareLoot = DamageDrops.getSettings().zombieRareMatList;

    private final List<Material> skeletonCommonLoot = DamageDrops.getSettings().skeletonCommonMatList;
    private final List<Material> skeletonMediumLoot = DamageDrops.getSettings().skeletonMediumMatList;
    private final List<Material> skeletonRareLoot = DamageDrops.getSettings().skeletonRareMatList;

    private final List<Material> golemCommonLoot = DamageDrops.getSettings().golemCommonMatList;
    private final List<Material> golemMediumLoot = DamageDrops.getSettings().golemMediumMatList;
    private final List<Material> golemRareLoot = DamageDrops.getSettings().golemRareMatList;

    private final List<Material> blazeCommonLoot = DamageDrops.getSettings().blazeCommonMatList;
    private final List<Material> blazeMediumLoot = DamageDrops.getSettings().blazeMediumMatList;
    private final List<Material> blazeRareLoot = DamageDrops.getSettings().blazeRareMatList;

    private final List<Material> beeCommonLoot = DamageDrops.getSettings().beeCommonMatList;
    private final List<Material> beeMediumLoot = DamageDrops.getSettings().beeMediumMatList;
    private final List<Material> beeRareLoot = DamageDrops.getSettings().beeRareMatList;

    private final double [] creeperLootChance = DamageDrops.getSettings().getCreeperDropChance();
    private final double [] zombieLootChance = DamageDrops.getSettings().getZombieDropChance();
    private final double [] skeletonLootChance = DamageDrops.getSettings().getSkeletonDropChance();
    private final double [] golemLootChance = DamageDrops.getSettings().getGolemDropChance();
    private final double [] blazeLootChance = DamageDrops.getSettings().getBlazeDropChance();
    private final double [] beeLootChance = DamageDrops.getSettings().getBeeDropChance();

    private final int creeperLootLevel = DamageDrops.getSettings().getCreeperLootLevel();
    private final int zombieLootLevel = DamageDrops.getSettings().getZombieLootLevel();
    private final int skeletonLootLevel = DamageDrops.getSettings().getSkeletonLootLevel();
    private final int golemLootLevel = DamageDrops.getSettings().getGolemLootLevel();
    private final int blazeLootLevel = DamageDrops.getSettings().getBlazeLootLevel();
    private final int beeLootLevel = DamageDrops.getSettings().getBeeLootLevel();

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        switch (event.getEntity().getType()) {
            case CREEPER:
                creeperCanPass = mobCheck(event, creeperCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isCreeperDrops(),DamageDrops.getSettings().getCreeperHighLoot(), creeperLootLevel,
                        creeperCommonLoot, creeperMediumLoot, creeperRareLoot, true, creeperLootChance);
                break;
            case ZOMBIE: case ZOMBIE_VILLAGER: case ZOMBIE_HORSE: case DROWNED:
                zombieCanPass = mobCheck(event, zombieCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isZombieDrops(), DamageDrops.getSettings().getZombieHighLoot(), zombieLootLevel,
                        zombieCommonLoot, zombieMediumLoot, zombieRareLoot, true, zombieLootChance);
                break;
            case SKELETON: case SKELETON_HORSE: case WITHER_SKELETON:
                skeletonCanPass = mobCheck(event, skeletonCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isSkeletonDrops(), DamageDrops.getSettings().getSkeletonHighLoot(), skeletonLootLevel,
                        skeletonCommonLoot, skeletonMediumLoot, skeletonRareLoot, true, skeletonLootChance);
                break;
            case IRON_GOLEM:
                golemCanPass = mobCheck(event, golemCanPass, ironToNetheritePickaxe, DamageDrops.getSettings().isGolemDrops(), DamageDrops.getSettings().getGolemHighLoot(), golemLootLevel,
                        golemCommonLoot, golemMediumLoot, golemRareLoot, true, golemLootChance);
                break;
            case BEE:
                beeCanPass = mobCheck(event, beeCanPass, ironToNetheritePickaxe, DamageDrops.getSettings().isBeeDrops(), DamageDrops.getSettings().getBeeHighLoot(), beeLootLevel,
                        beeCommonLoot, beeMediumLoot, beeRareLoot, false, beeLootChance);
                break;
            case BLAZE:
                blazeCanPass = mobCheck(event, blazeCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isBlazeDrops(), DamageDrops.getSettings().getBlazeHighLoot(), blazeLootLevel,
                        blazeCommonLoot, blazeMediumLoot, blazeRareLoot, true, blazeLootChance);
                break;
        }
    }

    private boolean mobCheck(EntityDamageEvent event, boolean canPass, Material[] matArray, boolean isEnabled, int highestLootNum, int lootLevel,
                             List<Material> commonLoot, List<Material> mediumLoot, List<Material> rareLoot, boolean checkTool, double [] lootLevelArray) {
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (isEnabled) {
                if (!(event.getEntity() instanceof CreatureSpawner) || DamageDrops.getSettings().isSpawnerDrops()) {
                    EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                    canPass = checkTime(canPass);
                    if (e.getDamager() instanceof Player && (canPass)) {
                        canPass = startTime(canPass);
                        Player p = (Player) e.getDamager();

                        for (Material mat : matArray) {
                            if (p.getEquipment().getItemInMainHand().getType().equals(mat) || !(checkTool)) {
                                Location loc = event.getEntity().getLocation();

                                loot(event, loc, highestLootNum, lootLevel, lootLevelArray, commonLoot, mediumLoot, rareLoot);
                                break;
                            }
                        }

                    }
                    return canPass;

                }

                return canPass;
            }
        }
        return canPass;
    }

    private void loot(EntityDamageEvent event, Location loc, int highestLootNum, int lootLevel, double [] lootLevelArray, List<Material> commonLoot, List<Material> mediumLoot, List<Material> rareLoot) {
        double lootChance = Math.random();
        int finalLootNum;

        switch (lootLevel) {
            case 3:
                if (lootChance < lootLevelArray[2]) {
                    finalLootNum = getRandomNumber(1, highestLootNum);
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(getRandomElement(rareLoot), finalLootNum));
                    break;
                }
            case 2:
                if (lootChance < lootLevelArray[1]) {
                    finalLootNum = getRandomNumber(1, highestLootNum);
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(getRandomElement(mediumLoot), finalLootNum));
                    break;
                }
            case 1:
                if (lootChance < lootLevelArray[0]) {
                    finalLootNum = getRandomNumber(1, highestLootNum);
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(getRandomElement(commonLoot), finalLootNum));
                    break;
                }
                break;
        }
    }

    // Start timer to create delay
    private boolean startTime(boolean canPass) {
        canPass = false;
        lastBoolTimeMillis = System.currentTimeMillis();
        return canPass;
    }

    // Check timer to see if 1 second has passed
    private boolean checkTime(boolean canPass) {
        if (System.currentTimeMillis() - lastBoolTimeMillis > dropChanceTime) {
            return true;
        }
        return canPass;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private Material getRandomElement(List<Material> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

}
