package com.gmail.arieldeleonhernandez123.welcomer_plus;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager extends YamlConfiguration {
    private static ConfigManager config;

    public static ConfigManager obtenerconfig() {
        if (config == null) {
            config = new ConfigManager();
        }
        return config;
    }


    private Welcomer_Plus plugin;
    private File configFile;

    public ConfigManager() {
        plugin = Welcomer_Plus.getPlugin(Welcomer_Plus.class);
        configFile = new File(plugin.getDataFolder(), "config.yml");
        saveDefault();
        config.options.copyDefaults();
        reload();
    }


    public void reload() {
        try {
            super.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            super.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefault() {
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
    }
}