package com.gmail.arieldeleonhernandez123.welcomer_plus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;




public class ComandoPrincipal implements CommandExecutor {



    private final Welcomer_Plus plugin;



    public ComandoPrincipal(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }




    @Override
    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {


        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + plugin.name + ChatColor.RED + ": You cannot run this command from the console");
            return false;

        } else {
            Player jugador = (Player) sender;
            if (args.length > 0) {
                YamlConfiguration config = ConfigManager.obtenerconfig();
                String version = config.getString("config.version");
                if (args[0].equalsIgnoreCase("version")) {
                    jugador.sendMessage(ChatColor.BLUE + plugin.name + ChatColor.WHITE + version + ChatColor.YELLOW + plugin.Version);
                    return true;

                }
                if (args.length>=3){

                    if (args[0].equalsIgnoreCase("msg")){

                        Player player = Bukkit.getPlayer(args[1]);
                        String msg = "";
                        for (int i = 2; i < args.length; i++){
                            msg += args[i] + " ";
                        }
                        if (args[1].equals("all")){
                            for (Player player2 : Bukkit.getOnlinePlayers()){

                                player2.sendTitle(ChatColor.YELLOW+"Warning "+ChatColor.WHITE+msg,"");
                            }


                        }else {

                            player.sendTitle(ChatColor.YELLOW+"Warning "+ChatColor.WHITE+msg,"");

                        }




                    }
                    return true;

                }
                else if (args[0].equalsIgnoreCase("gui")){
                    Inventario inv =new Inventario(plugin);
                    inv.crearInventario(jugador);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("particles")) {
                    Particles inv = new Particles(plugin);
                    inv.crearInventario(jugador);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("reload")) {
                    String reload = config.getString("config.reload");
                    ConfigManager.obtenerconfig().reload();
                    jugador.sendMessage(ChatColor.BLUE + plugin.name + ChatColor.GREEN + reload);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("help")) {
                    String help = config.getString("config.help");
                    jugador.sendMessage((ChatColor.BLUE + plugin.name + ChatColor.WHITE + help));
                    jugador.sendMessage((ChatColor.WHITE + "1./welcomer\n" +
                            "2./welcomer version\n" +
                            "3./welcomer reload\n" +
                            "4./welcomer gui\n" +
                            "5./welcomer particles\n"+
                            "6./welcomer msg"));

                    return true;

                }
                else {
                    String error = config.getString("config.error");
                    jugador.sendMessage(ChatColor.BLUE + plugin.name + ChatColor.RED + error);
                    return true;
                }


                }
                 else {
                     FileConfiguration config = plugin.getConfig();
                     String help2 = config.getString("config.help2");
                jugador.sendMessage(ChatColor.BLUE + plugin.name + ChatColor.WHITE + help2);
                return true;

            }

        }
    }
}
