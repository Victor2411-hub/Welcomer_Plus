package com.gmail.arieldeleonhernandez123.welcomer_plus;

import de.leonhard.storage.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    public void Almorir(PlayerDeathEvent event) {
        Player jugador = event.getEntity();
        Yaml config = Welcomer_Plus.getInstance().getconfig();
        String Deahttrue = "config.Death";
        String Deaht = "config.Death-message";
        if (config.getString(Deahttrue).equals("true")) {
            event.setDeathMessage((String)null);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString(Deaht).replaceAll("%player%", jugador.getName())));
        }

    }
}