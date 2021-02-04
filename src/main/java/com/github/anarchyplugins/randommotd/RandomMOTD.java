package com.github.anarchyplugins.randommotd;

import org.bukkit.plugin.java.JavaPlugin;

public final class RandomMOTD extends JavaPlugin {

    public static RandomMOTD INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Listener(), this);
        getCommand("randommotd").setExecutor(new Command());
        int i = getConfig().getStringList("motds").size();
        getLogger().info("Enabled RandomMOTD");
        getLogger().info("Successfully Loaded " + i + " MOTDs");
    }

}
