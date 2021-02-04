package com.github.anarchyplugins.randommotd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.isOp()) {
            if(args.length > 0) {
                if ("reload".equalsIgnoreCase(args[0])) {
                    RandomMOTD.INSTANCE.saveDefaultConfig();
                    RandomMOTD.INSTANCE.reloadConfig();
                    int i = RandomMOTD.INSTANCE.getConfig().getStringList("motds").size();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully loaded " + i + "MOTDs"));
                    return true;
                }
            }
        }
        return false;
    }

}
