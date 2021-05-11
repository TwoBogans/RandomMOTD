package com.github.anarchyplugins.randommotd;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;
import java.util.Random;

public class Listener implements org.bukkit.event.Listener {

    private final RandomMOTD plugin;

    public Listener(RandomMOTD plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPing(ServerListPingEvent e){
        if(plugin.getConfig().getBoolean("enabled")){

            List<String> motdList = plugin.getConfig().getStringList("motds");

            String motd = motdList.get(new Random().nextInt(motdList.size()));

            int cutOff = Math.min(motd.length(), 44);

            String prefix = plugin.getConfig().getString("prefix");
            String suffix = plugin.getConfig().getString("suffix");

            String nonFormattedMOTD = prefix +
                            motd.substring(0, cutOff) + suffix + ((
                            motd.substring(cutOff).startsWith(" ")) ?
                            motd.substring(cutOff).replaceFirst(" ", "") :
                            motd.substring(cutOff)
            );

            String formattedMOTD = ChatColor.translateAlternateColorCodes('&', nonFormattedMOTD);

            plugin.getServer().getConsoleSender().sendMessage("\n" + formattedMOTD);

            e.setMotd(formattedMOTD);
        }
    }
}
