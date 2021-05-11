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
            Random random = new Random();
            String motd = RandomMOTD.MOTD_LIST.get(random.nextInt(RandomMOTD.MOTD_LIST.size()));
            StringBuilder sb = new StringBuilder();

            if(!RandomMOTD.INSTANCE.getConfig().getString("prefix").isEmpty()){
                sb.append(RandomMOTD.INSTANCE.getConfig().getString("prefix"));
            }

            int i2 = 0;

            for (int i = 0; i < motd.length(); i += 45) {
                i2++;
                sb.append(motd, i, Math.min(i + 45, motd.length()));
                sb.append(" ");
                sb.append(RandomMOTD.INSTANCE.getConfig().getString("suffix"));
                if(i2 >= 2) break;
            }

            System.out.println(sb.toString());

            e.setMotd(ChatColor.translateAlternateColorCodes('&', sb.toString()));
        }
    }

}
