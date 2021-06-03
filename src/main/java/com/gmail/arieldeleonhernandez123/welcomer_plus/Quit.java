package com.gmail.arieldeleonhernandez123.welcomer_plus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {
    private final Welcomer_Plus plugin;

    public Quit(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void Alsalir(PlayerQuitEvent event) {
        //--------------------------Necesario--------------------------\\
        Player jugador = event.getPlayer();
        YamlConfiguration config = ConfigManager.obtenerconfig();
        //--------------------------Necesario--------------------------\\

        //--------------------------Variables--------------------------\\
        String leave = "config.Leave-message";
        String leavemessage = "config.Leave-chat-message";
        //--------------------------Variables--------------------------\\

        //--------------------------IFS--------------------------\\
        if (config.getString(leave).equals("true")) {
            event.setQuitMessage(null);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString(leavemessage).replaceAll("%player%", jugador.getName())));
        }
        //--------------------------IFS--------------------------\\
    }
}



