package me.invis.hubcore.config;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.managers.*;
import me.invis.hubcore.util.enums.Type;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static me.invis.hubcore.util.StringFormatter.format;

public class ConfigManager {
    private final FileConfiguration config;

    public ConfigManager() {
        config = HubCore.CONFIG;
        generalSettings();
    }

    public void generalSettings() {
        ConfigurationSection generalSettingsSection = config.getConfigurationSection("GENERAL-SETTINGS");
        new GeneralSettings(generalSettingsSection.getKeys(false).stream().map(generalSettingsSection::getBoolean).toArray(Boolean[]::new));
    }

    public Type type() {
        return Type.valueOf(config.getString("TYPE"));
    }

    public DoubleJump doubleJump() {
        ConfigurationSection doubleJumpSection = config.getConfigurationSection("DOUBLE-JUMP");
        return new DoubleJump(doubleJumpSection.getBoolean("ENABLED"),
                Sound.valueOf(doubleJumpSection.getString("SOUND")),
                        Effect.valueOf(doubleJumpSection.getString("PARTICLE")),
                        doubleJumpSection.getDouble("POWER"),
                        doubleJumpSection.getDouble("Y-AXIS"));
    }

    public Server server() {
        ConfigurationSection serverSection = config.getConfigurationSection("SERVER");
        return new Server(format(serverSection.getString("NAME"), false, false),
                serverSection.getConfigurationSection("LINKS").getValues(false));
    }

    public Welcome welcome(Player target) {
        ConfigurationSection welcomeMessageSection = config.getConfigurationSection("JOIN-MESSAGE");
        return new Welcome(welcomeMessageSection.getBoolean("ENABLED"),
                welcomeMessageSection.getBoolean("CENTERED"),
                welcomeMessageSection.getStringList("CONTENT"),
                target);
    }

    public HubItem hubItem(Player target) {
        ConfigurationSection hubItemSection = config.getConfigurationSection("HUB-ITEM");
        ConfigurationSection itemStackSection = hubItemSection.getConfigurationSection("ITEMSTACK");

        ItemStack itemStack = new ItemStack(Material.valueOf(itemStackSection.getString("MATERIAL")), itemStackSection.getInt("AMOUNT"));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(format(itemStackSection.getString("NAME.CONTENT"), itemStackSection.getBoolean("NAME.CENTERED"), target, true));

        boolean isCentered_HUBITEM = itemStackSection.getBoolean("LORE.CENTERED");
        List<String> lore = itemStackSection.getStringList("LORE.CONTENT");
        lore.forEach(loreLine -> lore.set(lore.indexOf(loreLine), format(loreLine, isCentered_HUBITEM, target, true)));
        itemMeta.setLore(lore);

        if(itemStackSection.getBoolean("GLOWING")) itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);


        itemStack.setItemMeta(hideFlags(itemMeta));

        return new HubItem(hubItemSection.getBoolean("ENABLED"),
                itemStack,
                hubItemSection.getInt("SLOT"),
                hubItemSection.getString("TRIGGER"),
                hubItemSection.getStringList("ACTION"));
    }

    public HubInventory hubInventory(Player target) {
        ConfigurationSection hubInventorySection = config.getConfigurationSection("HUB-INVENTORY");
        ConfigurationSection fillItemStackSection = hubInventorySection.getConfigurationSection("ITEMS.FILL-ITEM");
        ConfigurationSection gameModesSection = hubInventorySection.getConfigurationSection("ITEMS.GAMEMODES");

        ItemStack fillItemStack = new ItemStack(Material.valueOf(fillItemStackSection.getString("MATERIAL")), fillItemStackSection.getInt("AMOUNT"));
        ItemMeta fillItemMeta = fillItemStack.getItemMeta();

        fillItemMeta.setDisplayName(format(fillItemStackSection.getString("NAME.CONTENT"), fillItemStackSection.getBoolean("NAME.CENTERED"), target, true));

        boolean isCentered_FILLITEM = fillItemStackSection.getBoolean("LORE.CENTERED");
        List<String> lore = fillItemStackSection.getStringList("LORE.CONTENT");
        lore.forEach(loreLine -> lore.set(lore.indexOf(loreLine), format(loreLine, isCentered_FILLITEM, target, true)));
        fillItemMeta.setLore(lore);

        if(fillItemStackSection.getBoolean("GLOWING")) fillItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);

        fillItemStack.setItemMeta(hideFlags(fillItemMeta));

        List<GameMode> gameModes = new ArrayList<>();

        Set<String> gameModeNames = gameModesSection.getKeys(false);

        gameModeNames.forEach(gameMode -> {
            ConfigurationSection gameModeSection = gameModesSection.getConfigurationSection(gameMode);
            ConfigurationSection itemStackSection = gameModeSection.getConfigurationSection("ITEMSTACK");

            ItemStack itemStack = new ItemStack(Material.valueOf(itemStackSection.getString("MATERIAL")), itemStackSection.getInt("AMOUNT"));
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(format(itemStackSection.getString("NAME.CONTENT"), itemStackSection.getBoolean("NAME.CENTERED"), target, true));

            boolean isCentered_GAMEMODEITEM = itemStackSection.getBoolean("LORE.CENTERED");
            List<String> lore_GAMEMODEITEM = itemStackSection.getStringList("LORE.CONTENT");
            lore_GAMEMODEITEM.forEach(loreLine -> lore_GAMEMODEITEM.set(lore_GAMEMODEITEM.indexOf(loreLine), format(loreLine, isCentered_GAMEMODEITEM, target, true)));
            itemMeta.setLore(lore_GAMEMODEITEM);

            if(itemStackSection.getBoolean("GLOWING")) itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);

            itemStack.setItemMeta(hideFlags(itemMeta));

            gameModes.add(new GameMode(gameModeSection.getString("NAME"),
                    gameModeSection.getInt("SLOT"),
                    itemStack));
        });



        return new HubInventory(format(hubInventorySection.getString("TITLE"), false, true),
                hubInventorySection.getInt("SIZE"),
                hubInventorySection.getBoolean("ITEMS.FILL"),
                fillItemStack,
                gameModes);
    }

    public Title title(Player target) {
        ConfigurationSection joinTitleSection = config.getConfigurationSection("JOIN-TITLE");
        return new Title(joinTitleSection.getBoolean("ENABLED"),
                format(joinTitleSection.getString("HEADER"),
                        false,
                        target,
                        true),
                format(joinTitleSection.getString("FOOTER"),
                        false,
                        target,
                        true));
    }

    public TabList tabList(Player target) {
        ConfigurationSection tabListSection = config.getConfigurationSection("TABLIST");
        return new TabList(target,
                tabListSection.getBoolean("ENABLED"),
                format(tabListSection.getString("HEADER"),
                        false,
                        target,
                        true),
                format(tabListSection.getString("FOOTER"),
                        false,
                        target,
                        true));
    }

    public Scoreboard scoreboard(Player target) {
        ConfigurationSection sidebarSection = config.getConfigurationSection("SIDEBAR");
        List<String> content = sidebarSection.getStringList("CONTENT");
        IntStream.range(0, content.size()).forEach(i -> content.set(i, format(content.get(i), false, target, true)));

        return new Scoreboard(sidebarSection.getBoolean("ENABLED"),
                target,
                format(sidebarSection.getString("TITLE"), false, target, true),
                content);
    }

    private ItemMeta hideFlags(ItemMeta input) {
        Arrays.stream(ItemFlag.values()).forEach(input::addItemFlags);
        return input;
    }
}
