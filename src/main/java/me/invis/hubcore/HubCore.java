package me.invis.hubcore;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.invis.hubcore.config.ConfigFile;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.config.managers.Scoreboard;
import me.invis.hubcore.listener.*;
import me.invis.hubcore.scheduler.UpdateScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
    public static JPerPlayerMethodBasedScoreboard SCOREBOARD;
    public static boolean PAPI;

    @Override
    public void onEnable() {
        PAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        PLUGIN = this;
        CONFIG = new ConfigFile(this).file;
        DESCRIPTION = this.getDescription();
        CONFIG_MANAGER = new ConfigManager();
        RUNTIME = Runtime.getRuntime();
        PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();
        SCOREBOARD = new JPerPlayerMethodBasedScoreboard();

        if(!PAPI) Bukkit.getConsoleSender().sendMessage("PlaceholderAPI isn't found.");

        event(new JoinEvent(),
                new ClickEvent(),
                new FlyToggleEvent(),
                new GeneralPrevent(),
                new ScoreboardApplier());

        new UpdateScoreboard().runTaskTimer(this, 20, 20);
    }

    private void event(Listener... listeners) {
        Arrays.asList(listeners).forEach(eventListener -> Bukkit.getPluginManager().registerEvents(eventListener, this));
    }
}
