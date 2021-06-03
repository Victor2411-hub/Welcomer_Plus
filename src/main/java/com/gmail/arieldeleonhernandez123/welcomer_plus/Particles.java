package com.gmail.arieldeleonhernandez123.welcomer_plus;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Particles implements Listener {
    private final Welcomer_Plus plugin;

    public Particles(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }

    public void crearInventario(Player jugador) {
        Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&2&lJoin Particles"));
        ItemStack item = new ItemStack(Material.DRAGON_EGG, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lDragon Breath"));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "Set Dragon Breath as join Particle"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20, item);

        item = new ItemStack(Material.TORCH, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lExplosion"));
        lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "Set Explosion as join Particle"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(24, item);

        item = new ItemStack(Material.DIAMOND, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lFire"));
        lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "Set Fire as join Particle"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(30, item);

        item = new ItemStack(Material.IRON_INGOT, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lCrit"));
        lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "Set Crit as join Particle"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(32, item);

        item = new ItemStack(Material.GOLD_INGOT, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lBUBBLE_COLUMN_UP"));
        lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "Set BUBBLE_COLUMN_UP as join Particle"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(22, item);

        ItemStack decoracion = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        meta = decoracion.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a"));
        lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7"));
        meta.setLore(lore);
        decoracion.setItemMeta(meta);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, decoracion);
        }
        for (int i = 36; i < 45; i++) {
            inv.setItem(i, decoracion);
        }
        inv.setItem(9, decoracion);
        inv.setItem(18, decoracion);
        inv.setItem(27, decoracion);
        inv.setItem(36, decoracion);
        inv.setItem(17, decoracion);
        inv.setItem(26, decoracion);
        inv.setItem(35, decoracion);

        jugador.openInventory(inv);
        return;
    }

    @EventHandler
    public void clickearInventario(InventoryClickEvent event) {
        String nombre = ChatColor.translateAlternateColorCodes('&', "&2&lJoin Particles");
        String nombreM = ChatColor.stripColor(nombre);


        if (!ChatColor.stripColor(event.getView().getTitle()).equals(nombreM)) return;

        event.setCancelled(true);

        if (event.getCurrentItem() != null && event.getSlot() == event.getRawSlot()) {
            Player jugador = (Player) event.getWhoClicked();
            event.setCancelled(true);
            YamlConfiguration config = ConfigManager.obtenerconfig();
            String paht ="config.Join-particle";
            switch (event.getSlot()) {
                case 20:
                    if (config.getString(paht).equals("true")) {
                        config.set("config.Join-particle-select", "DRAGON_BREATH");
                        jugador.sendMessage(ChatColor.YELLOW + "Join Particles set has DRAGON BREAHT");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }
                    return;

                case 24:
                    if (config.getString(paht).equals("true")) {
                        config.set("config.Join-particle-select", "EXPLOSION_HUGE");
                        jugador.sendMessage(ChatColor.YELLOW + "Join Particles set has Explotion");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }
                    return;

                case 30:
                    if (config.getString(paht).equals("true")) {
                        config.set("config.Join-particle-select", "FIRE");
                        jugador.sendMessage(ChatColor.YELLOW + "Join Particles set has FIRE");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }
                    return;

                case 32:
                    if (config.getString(paht).equals("true")) {
                        config.set("config.Join-particle-select", "CRIT");
                        jugador.sendMessage(ChatColor.YELLOW + "Join Particles set has CRIT");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }
                    return;

                case 22:
                    if (config.getString(paht).equals("true")) {
                        config.set("config.Join-particle-select", "BUBBLE_COLUMN_UP");
                        jugador.sendMessage(ChatColor.YELLOW + "Join Particles set has bubble column up");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }
                    return;
            }
        }
        event.setCancelled(true);
    }

}
