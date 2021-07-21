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

    private final Material [] zombieLoot = {Material.ROTTEN_FLESH};
    private final Material [] skeletonLoot = {Material.BONE, Material.BONE_MEAL};
    private final Material [] creeperLoot = {Material.GUNPOWDER, Material.FIRE_CHARGE, Material.TNT};
    private final Material [] golemLoot = {Material.RAW_IRON, Material.POPPY};
    private final Material [] beeLoot = {Material.HONEYCOMB};
    private final Material [] blazeLoot = {Material.BLAZE_ROD};

    private double [] lootLevelArray = {0.25, 0.1, 0.03};
    private double [] blazeRareLoot = {0.05};


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        switch (event.getEntity().getType()) {
            case CREEPER:
                creeperCanPass = mobCheck(event, creeperCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isCreeperDrops(), 1, 2, 3, creeperLoot, true, lootLevelArray);
                break;
            case ZOMBIE: case ZOMBIE_VILLAGER: case ZOMBIE_HORSE: case DROWNED:
                zombieCanPass = mobCheck(event, zombieCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isZombieDrops(), 1, 2, 1, zombieLoot, true, lootLevelArray);
                break;
            case SKELETON: case SKELETON_HORSE: case WITHER_SKELETON:
                skeletonCanPass = mobCheck(event, skeletonCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isSkeletonDrops(), 1, 2, 2, skeletonLoot, true, lootLevelArray);
                break;
            case IRON_GOLEM:
                golemCanPass = mobCheck(event, golemCanPass, ironToNetheritePickaxe, DamageDrops.getSettings().isGolemDrops(), 1, 2, 2, golemLoot, true, lootLevelArray);
                break;
            case BEE:
                beeCanPass = mobCheck(event, beeCanPass, ironToNetheritePickaxe, DamageDrops.getSettings().isBeeDrops(), 1, 1, 1, beeLoot, false, lootLevelArray);
                break;
            case BLAZE:
                blazeCanPass = mobCheck(event, blazeCanPass, ironToNetheriteAxeSword, DamageDrops.getSettings().isBlazeDrops(), 1, 1, 1, blazeLoot, true, blazeRareLoot);
                break;
        }
    }

    private boolean mobCheck(EntityDamageEvent event, boolean canPass, Material[] matArray, boolean isEnabled, int lootNum, int highestLootNum, int lootLevel,
                             Material [] lootList, boolean checkTool, double [] lootLevelArray) {
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

                                loot(event, loc, lootNum, highestLootNum, lootLevel, lootList, lootLevelArray);
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

    private void loot(EntityDamageEvent event, Location loc, int lootNum, int highestLootNum, int lootLevel, Material [] lootList, double [] lootLevelArray) {
        double lootChance = Math.random();
        double lootNumChance = Math.random();
        int finalLootNum = lootNum;
        switch (lootLevel) {
            case 1:
                if (lootChance < lootLevelArray[0]) {
                    if (lootNumChance < 0.2) {finalLootNum = highestLootNum;}
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(lootList[0], finalLootNum));
                }
                break;
            case 2:
                if (lootChance < lootLevelArray[1]) {
                    if (lootNumChance < 0.2) {finalLootNum = highestLootNum;}
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(lootList[1], finalLootNum));
                } else if (lootChance < lootLevelArray[0]) {
                    if (lootNumChance < 0.2) {finalLootNum = highestLootNum;}
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(lootList[0], finalLootNum));
                }
                break;
            case 3:
                if (lootChance < lootLevelArray[2]) {
                    if (lootNumChance < 0.2) {finalLootNum = highestLootNum;}
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(lootList[2], finalLootNum));
                } else if (lootChance < lootLevelArray[1]) {
                    if (lootNumChance < 0.2) {finalLootNum = highestLootNum;}
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(lootList[1], finalLootNum));
                } else if (lootChance < lootLevelArray[0]) {
                    if (lootNumChance < 0.2) {finalLootNum = highestLootNum;}
                    event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(lootList[0], finalLootNum));
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

}
