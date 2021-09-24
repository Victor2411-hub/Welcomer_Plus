package com.gmail.arieldeleonhernandez123.welcomer_plus;

import de.leonhard.storage.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class ComandoPrincipal implements CommandExecutor {
    private final Welcomer_Plus plugin;

    public ComandoPrincipal(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + this.plugin.name + ChatColor.RED + ": You cannot run this command from the console");
            return false;
        } else {
            Player jugador = (Player)sender;
            String version;
            if (args.length <= 0) {
                FileConfiguration config = this.plugin.getConfig();
                version = config.getString("config.help2");
                jugador.sendMessage(ChatColor.BLUE + this.plugin.name + ChatColor.WHITE + version);
                return true;
            } else {
                Yaml config = Welcomer_Plus.getInstance().getconfig();
                version = config.getString("config.version");
                if (args[0].equalsIgnoreCase("version")) {
                    jugador.sendMessage(ChatColor.BLUE + this.plugin.name + ChatColor.WHITE + version + ChatColor.YELLOW + this.plugin.Version);
                    return true;
                } else if (args.length < 3) {
                    String error;
                    if (args[0].equalsIgnoreCase("help")) {
                        error = config.getString("config.help");
                        jugador.sendMessage(ChatColor.BLUE + this.plugin.name + ChatColor.WHITE + error);
                        jugador.sendMessage(ChatColor.WHITE + "1./welcomer\n2./welcomer version\n3./welcomer help\n4./welcomer msg");
                        return true;
                    } else {
                        error = config.getString("config.error");
                        jugador.sendMessage(ChatColor.BLUE + this.plugin.name + ChatColor.RED + error);
                        return true;
                    }
                }
            }
            return true;
        }
    }
}