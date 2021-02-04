package com.github.anarchyplugins.randommotd;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.List;
import java.util.Random;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPing(ServerListPingEvent e){
        if(RandomMOTD.INSTANCE.getConfig().getBoolean("enabled")){
            List<String> list = RandomMOTD.INSTANCE.getConfig().getStringList("motds");
            Random random = new Random();
            e.setMotd(ChatColor.translateAlternateColorCodes('&', list.get(random.nextInt(list.size()))));
        }
    }

}
