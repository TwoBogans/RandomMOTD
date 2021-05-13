package com.github.anarchyplugins.randommotd;

import org.apache.commons.text.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PingListener implements Listener {

    private final RandomMOTD plugin;

    public PingListener(RandomMOTD plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPing(ServerListPingEvent event) {
        if (plugin.getConfig().getBoolean("enabled")) {
            List<String> motdList = plugin.getConfig().getStringList("motds");

            String motd = motdList.get(new Random().nextInt(motdList.size()));

            String[] split = wrap(motd);

            String prefix = plugin.getConfig().getString("prefix");
            String suffix = plugin.getConfig().getString("suffix");

            System.out.println(Arrays.toString(split[0].toCharArray()));

            String nonFormattedMOTD;

            switch (split.length) {
                case 1:
                    nonFormattedMOTD = prefix + split[0] + "\n" + suffix;
                    break;
                case 2:
                    nonFormattedMOTD = prefix + split[0].substring(0, split[0].length() - 1) + "\n" + suffix + split[1].replace("\\s", "");
                    break;
                default:
                    nonFormattedMOTD = prefix + "\n" + suffix;
                    break;
            }

            event.setMotd(ChatColor.translateAlternateColorCodes('&', nonFormattedMOTD));
        }
    }

    private String[] wrap(String str) {
        return WordUtils.wrap(str, 30, null, true).split("\n");
    }
}
