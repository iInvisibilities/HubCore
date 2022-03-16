package me.invis.hubcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.listener.*;
/* import me.invis.hubcore.scheduler.UpdateScoreboard; */
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
/* import org.bukkit.plugin.PluginDescriptionFile; */
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class HubCore extends JavaPlugin {

    public static HubCore PLUGIN;
    public static FileConfiguration CONFIG;
    /* public static PluginDescriptionFile DESCRIPTION; */
    public static ConfigManager CONFIG_MANAGER;
    /* public static Runtime RUNTIME; */
    public static ProtocolManager PROTOCOL_MANAGER;
    public static JPerPlayerMethodBasedScoreboard SCOREBOARD;
    public static boolean PAPI;


    @Override
    public void onEnable() {
        PLUGIN = this;

        PAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();

        saveDefaultConfig();
        CONFIG = getConfig();
        CONFIG_MANAGER = new ConfigManager();

        SCOREBOARD = new JPerPlayerMethodBasedScoreboard();

        /* DESCRIPTION = this.getDescription(); */
        /* RUNTIME = Runtime.getRuntime(); */

        if(!PAPI) Bukkit.getConsoleSender().sendMessage("PlaceholderAPI isn't found.");

        event(
                new JoinEvent(),
                new ClickEvent(),
                new FlyToggleEvent(),
                new GeneralPrevent(),
                new ScoreboardApplier()
             );
    }

    private void event(Listener... listeners) {
        Arrays.asList(listeners).forEach(eventListener -> Bukkit.getPluginManager().registerEvents(eventListener, this));
    }
}
