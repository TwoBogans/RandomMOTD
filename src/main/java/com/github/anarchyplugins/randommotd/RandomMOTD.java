package com.github.anarchyplugins.randommotd;

import org.bukkit.plugin.java.JavaPlugin;

public final class RandomMOTD extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new Listener(this), this);

        getCommand("randommotd").setExecutor(new Command(this));

        getLogger().info("Enabled RandomMOTD");

        getLogger().info("Successfully Loaded " + getConfig().getStringList("motds").size() + " MOTDs");
    }
}
