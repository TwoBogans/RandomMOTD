package com.github.anarchyplugins.randommotd;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class RandomMOTD extends JavaPlugin {

    public static RandomMOTD INSTANCE;

    public static List<String> MOTD_LIST;

    @Override
    public void onEnable() {
        INSTANCE = this;

        saveDefaultConfig();

        MOTD_LIST = getConfig().getStringList("motds");

        getServer().getPluginManager().registerEvents(new Listener(), this);

        getCommand("randommotd").setExecutor(new Command());

        getLogger().info("Enabled RandomMOTD");
        getLogger().info("Successfully Loaded " + MOTD_LIST.size() + " MOTDs");
    }
}
