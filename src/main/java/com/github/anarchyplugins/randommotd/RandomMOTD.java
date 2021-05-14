package com.github.anarchyplugins.randommotd;

import org.apache.commons.text.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public final class RandomMOTD extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PingListener(this), this);

        getCommand("randommotd").setExecutor(new Command(this));

        getLogger().info("Enabled RandomMOTD");

        getLogger().info("Successfully Loaded " + getConfig().getStringList("motds").size() + " MOTDs");
    }

    public List<String> getMotdList(){
        return getConfig().getStringList("motds");
    }

    public String getRandomMotd(){
        return getMotdList().get(new Random().nextInt(getMotdList().size()));
    }

    public String getFormattedMotd(){
        String[] split = wrap(getRandomMotd());

        String prefix = getConfig().getString("prefix");
        String suffix = getConfig().getString("suffix");

        String motd;

        if (split.length == 1) {
            motd = prefix + split[0] + "\n" + suffix;
        } else if (split.length == 2) {
            motd = prefix + split[0].substring(0, split[0].length() - 1) + "\n" + suffix + split[1];
        } else if (split.length > 2) {
            motd = prefix + split[0].substring(0, split[0].length() - 1) + "\n" + suffix + split[1].substring(0, split[1].length() - 1);
        } else {
            motd = prefix + "\n" + suffix;
        }

        return ChatColor.translateAlternateColorCodes('&', motd);
    }

    private String[] wrap(String str) {
        return WordUtils.wrap(str, getConfig().getInt("wrap", 40), null, true).split("\n");
    }
}
