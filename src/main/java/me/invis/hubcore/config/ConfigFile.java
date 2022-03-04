package me.invis.hubcore.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigFile {

    /**
     * ye idk why i did that there, instead of just putting those 2 lines in the HubCore class, either way it looks sexier so fuck it.
     */

    public FileConfiguration file;

    public ConfigFile(Plugin plugin) {
        plugin.saveDefaultConfig();
        file = plugin.getConfig();
    }
}
