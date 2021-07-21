package me.andyw19.damagedrops.commands;

import me.andyw19.damagedrops.DamageDrops;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadPlugin implements CommandExecutor {

    private final DamageDrops plugin;

    public ReloadPlugin(DamageDrops damageDrops) {
        this.plugin = damageDrops;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            DamageDrops.getSettings().reload();
            p.sendMessage(ChatColor.GOLD + "DamageDrops " + ChatColor.YELLOW + "reloaded");
        }
        return true;
    }
}
