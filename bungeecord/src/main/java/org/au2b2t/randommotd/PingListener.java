package org.au2b2t.randommotd;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PingListener implements Listener {

    private final RandomMOTD plugin;

    public PingListener(RandomMOTD plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPing(ProxyPingEvent e) {
        ServerPing motd = e.getResponse();
        motd.setDescription(plugin.getFormattedMotd());
        e.setResponse(motd);
    }

}
