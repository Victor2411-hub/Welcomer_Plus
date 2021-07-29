package com.gmail.arieldeleonhernandez123.welcomer_plus;

import de.leonhard.storage.Yaml;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.List;

public class Entrar implements Listener {

    private final Welcomer_Plus plugin;

    public Entrar(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void AlEntrar(PlayerJoinEvent event) {


        //--------------------------Necesario--------------------------\\
        event.getPlayer().getUniqueId();
        event.getPlayer().getName();
        Player jugador = event.getPlayer();
        String jugador1 = jugador.getName();
        OfflinePlayer offlinePlayer = (OfflinePlayer) event.getPlayer();
        Yaml config = Welcomer_Plus.getInstance().getconfig();
        //--------------------------Necesario--------------------------\\

        //--------------------------Variables--------------------------\\
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
        //--------------------------Variables--------------------------\\

        //--------------------------IFS--------------------------\\

        if (config.getString(textotrue).equals("true")) {
            if (jugador.hasPermission("Screen_Welcomer.allmesage")) {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    player.sendTitle(ChatColor.translateAlternateColorCodes('&', jugador1 + config.getString(texto2)), "");
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 10, (float) 1.8);
                }

            }
        }


        if (config.getString(first).equals("true")) {
            if (!offlinePlayer.hasPlayedBefore())
                plugin.getServer().broadcastMessage(ChatColor.BOLD + "" + ChatColor.GOLD + offlinePlayer.getName() + ChatColor.RESET
                        + ChatColor.YELLOW + firstmessage);
        }
        if (config.getString(path).equals("true")) {
            jugador.sendTitle(ChatColor.translateAlternateColorCodes('&', config.getString(texto)), config.getString(sub).replaceAll("%player%", jugador.getName()));

        }
        if (config.getString(jointrue).equals("true")) {
            event.setJoinMessage(null);
            for (int i = 0; i < mensaje.size(); i++) {
                String joinchatmessage = mensaje.get(i);
                jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', joinchatmessage.replaceAll("%player%", jugador.getName())));
            }
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString(joinmessage).replaceAll("%player%", jugador.getName())));

        }
        if (jugador.isOp() && !(plugin.getVersion().equals(plugin.getLatestversion()))) {
            jugador.sendMessage(ChatColor.RED + available + ChatColor.YELLOW +
                    "(" + ChatColor.GRAY + plugin.latestversion + ChatColor.YELLOW + ")");
            jugador.sendMessage(ChatColor.RED + download + ChatColor.WHITE + "https://www.spigotmc.org/resources/87644/");
        }
        if (config.getString(particle).equals("true")) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                jugador.spawnParticle(Particle.valueOf(particles), jugador.getLocation(), 2000);
            }, Long.parseLong(ticks));

        }
        if (config.getString(sound).equals("true")) {
            jugador.playSound(jugador.getLocation(), Sound.valueOf(soundsel), 10, (float) 1.8);
        }
        //--------------------------IFS--------------------------\\
        if (jugador.hasPermission("Screen_Welcomer.joincomands")) {

            if (jugador.isOp()){
                for (int i = 0; i < comandos.size(); i++) {
                    String joincommands = comandos.get(i);
                    Bukkit.dispatchCommand(jugador, joincommands);
                }
            }
            else {

                for (int i = 0; i < comandos.size(); i++) {
                    jugador.setOp(true);
                    String joincommands = comandos.get(i);
                    Bukkit.dispatchCommand(jugador, joincommands);
                    jugador.setOp(false);
                }
            }
        }
    }
}