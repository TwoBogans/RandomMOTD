package com.github.anarchyplugins.randommotd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Locale;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender || sender.isOp()) {
            if(args.length > 0) {
                switch(args[0].toLowerCase(Locale.ROOT)){
                    case "reload": {
                        RandomMOTD.INSTANCE.saveDefaultConfig();
                        RandomMOTD.INSTANCE.reloadConfig();
                        RandomMOTD.MOTD_LIST = RandomMOTD.INSTANCE.getConfig().getStringList("motds");
                        int i = RandomMOTD.MOTD_LIST.size();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully loaded " + i + " MOTDs"));
                        return true;
                    }
                    case "add": {
                        if(args.length > 1){
                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < args.length; i++) {
                                sb.append(args[i]);
                                sb.append(" ");
                            }
                            String MOTD = ChatColor.translateAlternateColorCodes('&', sb.toString());
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully added MOTD: &6" + MOTD));
                            RandomMOTD.MOTD_LIST.add(MOTD);
                        }
                    }
                }
            }
        }
        return false;
    }

}
