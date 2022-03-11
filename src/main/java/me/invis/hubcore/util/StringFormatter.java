package me.invis.hubcore.util;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.util.centeredmessage.CenteredMessage;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;

public class StringFormatter {
    private static final ConfigManager configManager = HubCore.CONFIG_MANAGER;
    private static final FileConfiguration config = HubCore.CONFIG;
    private static final String trigger = config.getConfigurationSection("HUB-ITEM").getString("TRIGGER");
    private static final String serverName = config.getConfigurationSection("SERVER").getString("NAME");
    public static String format(String input, boolean isCentered, boolean sites) {

        if(sites) {
            for (Map.Entry<String, Object> entry : configManager.server().sites().entrySet()) {
                String siteName = entry.getKey();
                Object site = entry.getValue();
                input = input.replaceAll("<" + siteName + ">", String.valueOf(site));
            }
        }
        input = input.replaceAll("<server>", serverName);

        input = input.replaceAll("<trigger>", trigger);

        input = ChatColor.translateAlternateColorCodes('&', input);
        if(isCentered) input = new CenteredMessage(input).output;

        if(HubCore.PAPI) return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(null, input);

        return input;
    }

    public static String format(String input, boolean isCentered, Player target, boolean sites) {
        input = input.replaceAll("<player>", target.getDisplayName());


        if(sites) {
            for (Map.Entry<String, Object> entry : configManager.server().sites().entrySet()) {
                String siteName = entry.getKey();
                Object site = entry.getValue();
                input = input.replaceAll("<" + siteName + ">", String.valueOf(site));
            }
        }
        input = input.replaceAll("<server>", serverName);

        input = input.replaceAll("<trigger>", trigger);


        input = ChatColor.translateAlternateColorCodes('&', input);
        if(isCentered) input = new CenteredMessage(input).output;

        if(HubCore.PAPI) return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(target, input);

        return input;
    }
}
