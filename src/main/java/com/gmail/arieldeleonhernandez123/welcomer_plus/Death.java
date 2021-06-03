package com.gmail.arieldeleonhernandez123.welcomer_plus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class Death implements Listener {
    private final Welcomer_Plus plugin;

    public Death(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void Almorir (PlayerDeathEvent event){
        //--------------------------Necesario--------------------------\\
        Player jugador = event.getEntity();
        YamlConfiguration config = ConfigManager.obtenerconfig();
        //--------------------------Necesario--------------------------\\

        //--------------------------Variables--------------------------\\
        String Deahttrue = "config.Death";
        String Deaht = "config.Death-message";
        //--------------------------Variables--------------------------\\

        //--------------------------IFS--------------------------\\
        if(config.getString(Deahttrue).equals("true")) {
            event.setDeathMessage(null);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString(Deaht).replaceAll("%player%", jugador.getName())));
        }
        //--------------------------IFS--------------------------\\

    }

}

