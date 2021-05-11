package com.github.anarchyplugins.randommotd;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Random;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPing(ServerListPingEvent e){
        if(RandomMOTD.INSTANCE.getConfig().getBoolean("enabled")){

            String motd = RandomMOTD.MOTD_LIST.get(
                    new Random().nextInt(RandomMOTD.MOTD_LIST.size())
            );

            int cutOff = Math.min(motd.length(), 44);

            String prefix = RandomMOTD.INSTANCE.getConfig().getString("prefix");
            String suffix = RandomMOTD.INSTANCE.getConfig().getString("suffix");

            String nonFormattedMOTD = prefix +
                            motd.substring(0, cutOff) + suffix + ((
                            motd.substring(cutOff).startsWith(" ")) ?
                            motd.substring(cutOff).replaceFirst(" ", "") :
                            motd.substring(cutOff)
            );

            String formattedMOTD = ChatColor.translateAlternateColorCodes('&', nonFormattedMOTD);

            RandomMOTD.INSTANCE.getServer().getConsoleSender().sendMessage("\n" + formattedMOTD);

            e.setMotd(formattedMOTD);
        }
    }
}
