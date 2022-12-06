package org.au2b2t.randommotd;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.apache.commons.text.WordUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RandomMOTD extends Plugin {

    @Override
    public void onEnable() {
        try {
            saveDefaultConfig();
        } catch (IOException e) {
            getLogger().severe("Can't create default config.");
        }

        getProxy().getPluginManager().registerListener(this, new PingListener(this));

        getProxy().getPluginManager().registerCommand(this, new AddMotd(this));

        getLogger().info("Enabled RandomMOTD");

        getLogger().info("Successfully Loaded " + getMotdList().size() + " MOTDs");
    }

    public List<String> getMotdList() {
        try {
            return getConfig().getStringList("motds");
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public String getPrefix() {
        try {
            return getConfig().getString("prefix");
        } catch (IOException e) {
            return "";
        }
    }

    public String getSuffix() {
        try {
            return getConfig().getString("suffix");
        } catch (IOException e) {
            return "";
        }
    }

    public int getWrap() {
        try {
            return getConfig().getInt("wrap", 45);
        } catch (IOException e) {
            return 45;
        }
    }

    public String getRandomMotd(){
        return getMotdList().get(new Random().nextInt(getMotdList().size()));
    }

    public String getFormattedMotd(){
        String[] split = wrap(getRandomMotd());

        String prefix = getPrefix();

        String suffix = getSuffix();

        return ChatColor.translateAlternateColorCodes('&',
                split.length == 1 ? prefix + split[0] + "\n" + suffix : (
                split.length >= 2 ? prefix + split[0] + "\n" +
                suffix + split[1] : prefix + "\n" + suffix)
        );
    }

    public void saveDefaultConfig() throws IOException {
        // Create plugin config folder if it doesn't exist
        if (!getDataFolder().exists()) {
            getLogger().info("Created config folder: " + getDataFolder().mkdir());
        }

        File configFile = new File(getDataFolder(), "config.yml");

        // Copy default config if it doesn't exist
        if (!configFile.exists()) {
            FileOutputStream outputStream = new FileOutputStream(configFile); // Throws IOException
            InputStream in = getResourceAsStream("config.yml"); // This file must exist in the jar resources folder
            in.transferTo(outputStream); // Throws IOException
            in.close();
        }
    }

    public Configuration getConfig() throws IOException {
        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
    }

    public void saveConfig(Configuration configuration) throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "config.yml"));
    }

    private String[] wrap(String str) {
        return WordUtils.wrap(str, getWrap(), null, true).split("\n");
    }


}
