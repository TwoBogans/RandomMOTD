package org.au2b2t.randommotd;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddMotd extends Command {
    private final RandomMOTD plugin;

    public AddMotd(RandomMOTD plugin) {
        super("addmotd");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        try {
            if (args.length >= 1) {
                String motd = ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, " ")).replaceAll("\\s+$", "");

                List<String> list = plugin.getMotdList();

                if (list.contains(motd)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMOTD already added!"));
                    return;
                }

                list.add(ChatColor.stripColor(motd));

                Configuration config = plugin.getConfig();

                config.set("motds", list);
                plugin.saveConfig(config);

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully added MOTD: &6" + motd + "&r, &a" + plugin.getMotdList().size() + " MOTDs loaded."));
            }
        } catch (IOException e) {

        }
    }
}
