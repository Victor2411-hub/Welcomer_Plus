package com.gmail.arieldeleonhernandez123.welcomer_plus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import de.leonhard.storage.Config;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.ReloadSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Welcomer_Plus extends JavaPlugin {
    String name = this.getName();
    String latestversion;
    PluginDescriptionFile pdffile = this.getDescription();
    String Version;
    List<String> author;
    private static Welcomer_Plus INSTANCE = null;
    public Config config;

    public Welcomer_Plus() {
        this.Version = this.pdffile.getVersion();
        this.author = this.pdffile.getAuthors();
    }

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + this.name + ": The plugin has been activates " + ChatColor.BLUE + this.Version);
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "                 By: " + ChatColor.MAGIC + this.author);
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
        this.yamlfile();
        INSTANCE = this;
        this.updateChecker();
        this.registerEvents();
        this.resgistrarcomandos();
        int pluginId = 87644;
        new Metrics(this, pluginId);
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + this.name + ": The plugin has been disabled " + ChatColor.BLUE + this.Version);
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "                 By: " + ChatColor.MAGIC + this.author);
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "<-------------------------------------------------------------------->");
    }

    public static Welcomer_Plus getInstance() {
        return INSTANCE;
    }

    public Config getconfig() {
        return this.config;
    }

    public String getVersion() {
        return this.Version;
    }

    public String getLatestversion() {
        return this.latestversion;
    }

    public void resgistrarcomandos() {
        this.getCommand("welcomer").setExecutor(new ComandoPrincipal(this));
        this.getCommand("welcomer").setTabCompleter(new TabComplete(this));
    }

    public void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Entrar(this), this);
        pm.registerEvents(new Quit(this), this);
        pm.registerEvents(new Death(this), this);
    }

    public void yamlfile() {
        this.config = new Config("config.yml", "plugins/Welcomer_plus", this.getResource("config.yml"));
        this.config.setConfigSettings(ConfigSettings.PRESERVE_COMMENTS);
        this.config.setReloadSettings(ReloadSettings.INTELLIGENT);
    }

    public void updateChecker() {
        try {
            HttpURLConnection con = (HttpURLConnection)(new URL("https://api.spigotmc.org/legacy/update.php?resource=87644")).openConnection();
            int timed_out = 1250;
            con.setConnectTimeout(timed_out);
            con.setReadTimeout(timed_out);
            this.latestversion = (new BufferedReader(new InputStreamReader(con.getInputStream()))).readLine();
            if (this.latestversion.length() <= 7 && !this.Version.equals(this.latestversion)) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "There is a new version available. " + ChatColor.YELLOW + "(" + ChatColor.GRAY + this.latestversion + ChatColor.YELLOW + ")");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You can download it at: " + ChatColor.WHITE + "https://www.spigotmc.org/resources/87644/");
            }
        } catch (Exception var3) {
            Bukkit.getConsoleSender().sendMessage(this.name + ChatColor.RED + " Error while checking update.");
        }

    }
}