package com.gmail.arieldeleonhernandez123.welcomer_plus;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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

public class Inventario implements Listener {
    private final Welcomer_Plus plugin;

    public Inventario(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }

    Player jugador;
    @SuppressWarnings("deprecation")
    public void crearInventario(Player jugador) {

        Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&2&lWelcomer Plus+"));
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lMessage-Welcome"));
        List<String> lore = new ArrayList<String>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20, item);

        item = new ItemStack(Material.GOLD_BLOCK, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lJoin-message"));
        lore = new ArrayList<String>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(22, item);

        item = new ItemStack(Material.IRON_BLOCK, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lLeave-message"));
        lore = new ArrayList<String>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(24, item);

        item = new ItemStack(Material.DIAMOND, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lDeath-message"));
        lore = new ArrayList<String>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(30, item);

        item = new ItemStack(Material.NETHER_STAR, 1);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lJoin-particle"));
        lore = new ArrayList<String>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(32, item);

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

    @SuppressWarnings("deprecation")
    @EventHandler
    public void clickearInventario(InventoryClickEvent event) {
        String nombre = ChatColor.translateAlternateColorCodes('&', "&2&lWelcomer Plus+");
        String nombreM = ChatColor.stripColor(nombre);


        if (!ChatColor.stripColor(event.getView().getTitle()).equals(nombreM)) return;

        event.setCancelled(true);

        if (event.getCurrentItem() != null && event.getSlot() == event.getRawSlot()) {
            Player jugador = (Player) event.getWhoClicked();
            event.setCancelled(true);
            YamlConfiguration config = ConfigManager.obtenerconfig();
            String path = "config.Message-Welcome";
            String path1 = "config.Join-message";
            String path2 = "config.Leave-message";
            String path3 = "config.Death";
            String path4 = "config.Join-particle";
            switch (event.getSlot()) {
                case 20:
                    if (config.getString(path).equals("true")) {
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lMessage-Welcome"));
                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2False"));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        config.set("config.Message-Welcome", false);
                        jugador.sendMessage(ChatColor.RED+"Message-Welcome is false");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }else if (config.getString(path).equals("false")){
                        List<String> lore2 = new ArrayList<String>();
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        lore2.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2True"));
                        meta.setLore(lore2);
                        item.setItemMeta(meta);
                        config.set("config.Message-Welcome", true);
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                        jugador.sendMessage(ChatColor.GREEN+"Message-Welcome is true");
                    }

                    return;


                case 22:
                    if (config.getString(path1).equals("true")) {
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lJoin-message"));
                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2False"));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        config.set("config.Join-message", false);
                        jugador.sendMessage(ChatColor.RED+"Join-message is false");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }else if (config.getString(path1).equals("false")){
                        List<String> lore2 = new ArrayList<String>();
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        lore2.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2True"));
                        meta.setLore(lore2);
                        item.setItemMeta(meta);
                        config.set("config.Join-message", true);
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                        jugador.sendMessage(ChatColor.GREEN+"Join-message is true");
                    }

                    return;


                case 24:
                    if (config.getString(path2).equals("true")) {
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lLeave-message"));
                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2False"));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        config.set("config.Leave-message", false);
                        jugador.sendMessage(ChatColor.RED+"Leave-message is false");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }else if (config.getString(path2).equals("false")){
                        List<String> lore2 = new ArrayList<String>();
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        lore2.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2True"));
                        meta.setLore(lore2);
                        item.setItemMeta(meta);
                        config.set("config.Leave-message", true);
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                        jugador.sendMessage(ChatColor.GREEN+"Leave-message is true");
                    }

                    return;

                case 30:
                    if (config.getString(path3).equals("true")) {
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lDeath-message"));
                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2False"));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        config.set("config.Death", false);
                        jugador.sendMessage(ChatColor.RED+"Death-message is false");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }else if (config.getString(path3).equals("false")){
                        List<String> lore2 = new ArrayList<String>();
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        lore2.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2True"));
                        meta.setLore(lore2);
                        item.setItemMeta(meta);
                        config.set("config.Death", true);
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                        jugador.sendMessage(ChatColor.GREEN+"Death-message is true");
                    }
                    return;

                case 32:
                    if (config.getString(path4).equals("true")) {
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lJoin-particle"));
                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2False"));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        config.set("config.Join-particle", false);
                        jugador.sendMessage(ChatColor.RED+"Message-Welcome is false");
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                    }else if (config.getString(path4).equals("false")){
                        List<String> lore2 = new ArrayList<String>();
                        ItemStack item = event.getCurrentItem();
                        ItemMeta meta = item.getItemMeta();
                        lore2.add(ChatColor.translateAlternateColorCodes('&', "&7Click to set true or false" +
                                " Now is: &2True"));
                        meta.setLore(lore2);
                        item.setItemMeta(meta);
                        config.set("config.Join-particle", true);
                        ConfigManager.obtenerconfig().save();
                        ConfigManager.obtenerconfig().reload();
                        jugador.sendMessage(ChatColor.GREEN+"Join-particle is true");
                    }
                    return;


            }

        }
            event.setCancelled(true);
    }

    }