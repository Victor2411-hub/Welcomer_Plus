package com.gmail.arieldeleonhernandez123.welcomer_plus;

import java.util.Iterator;
import java.util.List;

import de.leonhard.storage.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Entrar implements Listener {
    private final Welcomer_Plus plugin;

    public Entrar(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void AlEntrar(PlayerJoinEvent event) {
        event.getPlayer().getUniqueId();
        event.getPlayer().getName();
        Player jugador = event.getPlayer();
        String jugador1 = jugador.getName();
        OfflinePlayer offlinePlayer = event.getPlayer();
        Yaml config = Welcomer_Plus.getInstance().getconfig();
        String sound = "config.Join-Sound";
        String soundsel = config.getString("config.Join-sound-select");
        String path = "config.Message-Welcome";
        String jointrue = "config.Join-message";
        String texto = "config.Welcome-screen-message";
        String textotrue = "config.Join-messageall";
        String texto2 = "config.Join-messageall-message";
        String sub = "config.Welcome-screen-sub-message";
        String particle = "config.Join-particle";
        String first = "config.First-join";
        String joinmessage = "config.Join-player-message";
        String particles = config.getString("config.Join-particle-select");
        String firstmessage = config.getString("config.First-join-message");
        List<String> mensaje = config.getStringList("config.Join-chat-message");
        String available = config.getString("config.available");
        String download = config.getString("config.download");
        List<String> comandos = config.getStringList("config.Join-Commands");
        String ticks = config.getString("config.Particle-ticks");
        if (config.getString(textotrue).equals("true") && jugador.hasPermission("Screen_Welcomer.allmesage")) {
            Iterator var24 = Bukkit.getOnlinePlayers().iterator();

            while(var24.hasNext()) {
                Player player = (Player)var24.next();
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', jugador1 + config.getString(texto2)), "");
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10.0F, 1.8F);
            }
        }

        if (config.getString(first).equals("true") && !offlinePlayer.hasPlayedBefore()) {
            this.plugin.getServer().broadcastMessage(ChatColor.BOLD + "" + ChatColor.GOLD + offlinePlayer.getName() + ChatColor.RESET + ChatColor.YELLOW + firstmessage);
        }

        if (config.getString(path).equals("true")) {
            jugador.sendTitle(ChatColor.translateAlternateColorCodes('&', config.getString(texto)), config.getString(sub).replaceAll("%player%", jugador.getName()));
        }

        int i;
        String joincommands;
        if (config.getString(jointrue).equals("true")) {
            event.setJoinMessage((String)null);

            for(i = 0; i < mensaje.size(); ++i) {
                joincommands = (String)mensaje.get(i);
                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', joincommands.replaceAll("%player%", jugador.getName())));
            }

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString(joinmessage).replaceAll("%player%", jugador.getName())));
        }

        if (jugador.isOp() && !this.plugin.getVersion().equals(this.plugin.getLatestversion())) {
            jugador.sendMessage(ChatColor.RED + available + ChatColor.YELLOW + "(" + ChatColor.GRAY + this.plugin.latestversion + ChatColor.YELLOW + ")");
            jugador.sendMessage(ChatColor.RED + download + ChatColor.WHITE + "https://www.spigotmc.org/resources/87644/");
        }

        if (config.getString(particle).equals("true")) {
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                jugador.spawnParticle(Particle.valueOf(particles), jugador.getLocation(), 2000);
            }, Long.parseLong(ticks));
        }

        if (config.getString(sound).equals("true")) {
            jugador.playSound(jugador.getLocation(), Sound.valueOf(soundsel), 10.0F, 1.8F);
        }

        if (jugador.hasPermission("Screen_Welcomer.joincomands")) {
            if (jugador.isOp()) {
                for(i = 0; i < comandos.size(); ++i) {
                    joincommands = (String)comandos.get(i);
                    Bukkit.dispatchCommand(jugador, joincommands);
                }
            } else {
                for(i = 0; i < comandos.size(); ++i) {
                    jugador.setOp(true);
                    joincommands = (String)comandos.get(i);
                    Bukkit.dispatchCommand(jugador, joincommands);
                    jugador.setOp(false);
                }
            }
        }

    }
}