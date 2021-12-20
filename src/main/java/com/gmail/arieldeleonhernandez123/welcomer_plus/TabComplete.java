package com.gmail.arieldeleonhernandez123.welcomer_plus;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;


public class TabComplete implements TabCompleter {

    private Welcomer_Plus plugin;

    public TabComplete(Welcomer_Plus plugin) {
        this.plugin = plugin;
    }

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("version");
            arguments.add("help");

        }
        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {

                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);


            }
            return result;
        }
        return null;
    }
}