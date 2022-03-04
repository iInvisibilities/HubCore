package me.invis.hubcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.invis.hubcore.config.ConfigFile;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.listener.ClickEvent;
import me.invis.hubcore.listener.FlyToggleEvent;
import me.invis.hubcore.listener.GeneralPrevent;
import me.invis.hubcore.listener.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class HubCore extends JavaPlugin {
    public static HubCore PLUGIN;
    public static FileConfiguration CONFIG;
    public static PluginDescriptionFile DESCRIPTION;
    public static ConfigManager CONFIG_MANAGER;
    public static Runtime RUNTIME;
    public static ProtocolManager PROTOCOL_MANAGER;

    @Override
    public void onEnable() {
        PLUGIN = this;
        CONFIG = new ConfigFile(this).file;
        DESCRIPTION = this.getDescription();
        CONFIG_MANAGER = new ConfigManager();
        RUNTIME = Runtime.getRuntime();
        PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();

        event(new JoinEvent(),
                new ClickEvent(),
                new FlyToggleEvent(),
                new GeneralPrevent());
    }

    private void event(Listener... listeners) {
        Arrays.asList(listeners).forEach(eventListener -> Bukkit.getPluginManager().registerEvents(eventListener, this));
    }
}
