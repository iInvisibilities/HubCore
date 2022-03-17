package me.invis.hubcore.commands;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1) return false;
        if(!args[0].equalsIgnoreCase("reload")) return false;

        HubCore.PLUGIN.reloadConfig();
        HubCore.CONFIG = HubCore.PLUGIN.getConfig();
        HubCore.CONFIG_MANAGER = new ConfigManager();

        sender.sendMessage(ChatColor.GREEN + "Config reloaded...");
        return true;
    }
}
