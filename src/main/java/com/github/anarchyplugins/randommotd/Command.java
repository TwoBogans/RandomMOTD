package com.github.anarchyplugins.randommotd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Command implements CommandExecutor {
    private final RandomMOTD plugin;

    public Command(RandomMOTD plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender || sender.isOp()) {
            if (args.length > 0) {
                switch (args[0].toLowerCase(Locale.ROOT)) {
                    case "reload": {
                        plugin.saveDefaultConfig();
                        plugin.reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully loaded " + plugin.getConfig().getStringList("motds").size() + " MOTDs"));
                        return true;
                    }
                    case "add": {
                        if (args.length > 1) {
                            String motd = ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.asList(args).subList(1, args.length))).replaceAll("\\s+$", "");

                            List<String> list = plugin.getConfig().getStringList("motds");

                            if (list.contains(motd)) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMOTD already added!"));
                                return true;
                            }

                            list.add(motd);

                            plugin.getConfig().set("motds", list);
                            plugin.saveConfig();

                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully added MOTD: &6" + motd));

                            plugin.saveDefaultConfig();
                            plugin.reloadConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully loaded " + plugin.getConfig().getStringList("motds").size() + " MOTDs"));
                        }
                    }
                }
            }
        }

        return false;
    }
}
