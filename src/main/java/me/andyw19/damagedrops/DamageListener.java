package me.andyw19.damagedrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
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

    public final Material [] golemArray = {Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE, Material.GOLDEN_PICKAXE, Material.NETHERITE_PICKAXE};
    public final Material [] skeletonArray = {Material.IRON_AXE, Material.IRON_SWORD, Material.GOLDEN_AXE, Material.GOLDEN_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_SWORD,
    Material.NETHERITE_AXE, Material.NETHERITE_SWORD};


    @EventHandler
    public void onGolem(EntityDamageEvent event) {

        if (event.getEntity().getType() == EntityType.IRON_GOLEM) {
            if (!(event.getEntity() instanceof CreatureSpawner)) {
                if (DamageDrops.getSettings().isGolemDrops()) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                        golemCanPass = checkTime(golemCanPass);
                        if (e.getDamager() instanceof Player && (golemCanPass)) {
                            golemCanPass = startTime(golemCanPass);
                            Player p = (Player) e.getDamager();

                            for (Material mat : golemArray) {
                                if (p.getEquipment().getItemInMainHand().getType().equals(mat)) {
                                    Location loc = event.getEntity().getLocation();

                                    double d = Math.random();
                                    if (d < 0.15) {
                                        event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(Material.RAW_IRON, 2));
                                    } else if (d < 0.35) {
                                        event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(Material.RAW_IRON, 1));
                                    }

                                }
                            }

                        }

                    }

                }
            }

        }
    }

    @EventHandler
    public void onSkeleton(EntityDamageEvent event) {

        if ((event.getEntity().getType() == EntityType.SKELETON) || (event.getEntity().getType() == EntityType.WITHER_SKELETON)) {
            if (!(event.getEntity() instanceof CreatureSpawner)) {
                if (DamageDrops.getSettings().isSkeletonDrops()) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                        skeletonCanPass = checkTime(skeletonCanPass);
                        if (e.getDamager() instanceof Player && (skeletonCanPass)) {
                            skeletonCanPass = startTime(skeletonCanPass);
                            Player p = (Player) e.getDamager();

                            for (Material mat : skeletonArray) {
                                if (p.getEquipment().getItemInMainHand().getType().equals(mat)) {
                                    Location loc = event.getEntity().getLocation();

                                    double d = Math.random();
                                    if (d <= 0.15) {
                                        event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(Material.BONE, 2));
                                    } else if (d <= 0.35) {
                                        event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(Material.BONE, 1));
                                    }

                                }
                            }

                        }

                    }

                }
            }

        }
    }

    @EventHandler
    public void onZombie(EntityDamageEvent event) {

        if ((event.getEntity().getType() == EntityType.ZOMBIE) || (event.getEntity().getType() == EntityType.HUSK)
                || (event.getEntity().getType() == EntityType.ZOMBIE_VILLAGER)) {
            if (!(event.getEntity() instanceof CreatureSpawner)) {
                if (DamageDrops.getSettings().isZombieDrops()) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                        zombieCanPass = checkTime(zombieCanPass);
                        if (e.getDamager() instanceof Player && (zombieCanPass)) {
                            zombieCanPass = startTime(zombieCanPass);
                            Player p = (Player) e.getDamager();

                            for (Material mat : skeletonArray) {
                                if (p.getEquipment().getItemInMainHand().getType().equals(mat)) {
                                    Location loc = event.getEntity().getLocation();

                                    double d = Math.random();
                                    if (d <= 0.15) {
                                        event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(Material.ROTTEN_FLESH, 2));
                                    } else if (d <= 0.35) {
                                        event.getEntity().getWorld().dropItemNaturally(loc, new ItemStack(Material.ROTTEN_FLESH, 1));
                                    }

                                }
                            }

                        }

                    }

                }
            }


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
