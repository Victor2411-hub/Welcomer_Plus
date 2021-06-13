package com.gmail.arieldeleonhernandez123.welcomer_plus;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public final class Welcomer_Plus extends JavaPlugin {

    //--------------------------Variables--------------------------\\
    String name = getName();
    String latestversion;
    PluginDescriptionFile pdffile = getDescription();
    String Version = pdffile.getVersion();
    List<String> author = pdffile.getAuthors();
    FileConfiguration config = getConfig();
    //--------------------------Variables--------------------------\\

    @Override
    public void onEnable() {
        // Inicio
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + name + ": The plugin has been activates " + ChatColor.BLUE + Version);
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "                 By: " + ChatColor.MAGIC + author);
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        updateChecker();
        registerEvents();
        resgistrarcomandos();
        int pluginId = 9967;
        new Metrics(this, pluginId);


    }

    @Override
    public void onDisable() {
        // Apagado
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + name + ": The plugin has been disabled " + ChatColor.BLUE + Version);
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "                 By: Elcheems ");
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
    }

    public String getVersion(){
        return this.Version;
    }
    public String getLatestversion(){
        return this.latestversion;
    }

    public void resgistrarcomandos(){
        (this.getCommand("welcomer")).setExecutor(new ComandoPrincipal(this));

    }

    public void registerEvents(){

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Entrar(this), this);
        pm.registerEvents(new Quit(this),this);
        pm.registerEvents(new Inventario(this),this);
        pm.registerEvents(new Particles(this),this);
        pm.registerEvents(new Death(this),this);
    }
    public void updateChecker(){
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=87644").openConnection();
            int timed_out = 1250;
            con.setConnectTimeout(timed_out);
            con.setReadTimeout(timed_out);
            latestversion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (latestversion.length() <= 7) {
                if(!Version.equals(latestversion)){
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED +"There is a new version available. "+ChatColor.YELLOW+
                            "("+ChatColor.GRAY+latestversion+ChatColor.YELLOW+")");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"You can download it at: "+ChatColor.WHITE+"https://www.spigotmc.org/resources/87644/");
                }
            }
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED +" Error while checking update.");
        }
    }

}
