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

import java.util.*;
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
        boolean enabled = welcomeMessageSection.getBoolean("CENTERED");
        List<String> messageContent = welcomeMessageSection.getStringList("CONTENT");
        messageContent.forEach(contentLine -> messageContent.set(messageContent.indexOf(contentLine), format(contentLine, enabled, target, true)));

        return new Welcome(welcomeMessageSection.getBoolean("ENABLED"),
                enabled,
                messageContent,
                target);
    }

    public VisibilityItem visibilityItem(Player target) {
        ConfigurationSection visibilityItemSection = config.getConfigurationSection("VISIBILITY-ITEM");
        ConfigurationSection itemSection = visibilityItemSection.getConfigurationSection("ITEM");

        return new VisibilityItem(visibilityItemSection.getBoolean("ENABLED"),
                Collections.singletonMap(itemStackFromConfig(itemSection, target), itemSection.getInt("SLOT")), itemSection.getStringList("ACTION"));
    }

    public ServersListItem serversListItem(Player target) {
        ConfigurationSection hubItemSection = config.getConfigurationSection("SERVERS-LIST-ITEM");
        ConfigurationSection itemStackSection = hubItemSection.getConfigurationSection("ITEMSTACK");

        return new ServersListItem(hubItemSection.getBoolean("ENABLED"),
                itemStackFromConfig(itemStackSection, target),
                hubItemSection.getInt("SLOT"),
                hubItemSection.getString("TRIGGER"),
                hubItemSection.getStringList("ACTION"));
    }

    public HubInventory hubInventory(Player target) {
        ConfigurationSection hubInventorySection = config.getConfigurationSection("HUB-INVENTORY");
        ConfigurationSection fillItemStackSection = hubInventorySection.getConfigurationSection("ITEMS.FILL-ITEM");
        ConfigurationSection gameModesSection = hubInventorySection.getConfigurationSection("ITEMS.GAMEMODES");

        List<GameMode> gameModes = new ArrayList<>();

        Set<String> gameModeNames = gameModesSection.getKeys(false);

        gameModeNames.forEach(gameMode -> {
            ConfigurationSection gameModeSection = gameModesSection.getConfigurationSection(gameMode);
            ConfigurationSection itemStackSection = gameModeSection.getConfigurationSection("ITEMSTACK");

            gameModes.add(new GameMode(gameModeSection.getString("NAME"),
                    gameModeSection.getInt("SLOT"),
                    itemStackFromConfig(itemStackSection, target)));
        });



        return new HubInventory(format(hubInventorySection.getString("TITLE"), false, true),
                hubInventorySection.getInt("SIZE"),
                hubInventorySection.getBoolean("ITEMS.FILL"),
                itemStackFromConfig(fillItemStackSection, target),
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

    private ItemStack itemStackFromConfig(ConfigurationSection section, Player target) {
        ItemStack itemStack = new ItemStack(Material.valueOf(section.getString("MATERIAL")), section.getInt("AMOUNT"));
        ItemMeta itemStackMeta = itemStack.getItemMeta();

        itemStackMeta.setDisplayName(format(section.getString("NAME.CONTENT"), section.getBoolean("NAME.CENTERED"), target, true));

        boolean isCentered_HUBITEM = section.getBoolean("LORE.CENTERED");
        List<String> lore = section.getStringList("LORE.CONTENT");
        lore.forEach(loreLine -> lore.set(lore.indexOf(loreLine), format(loreLine, isCentered_HUBITEM, target, true)));
        itemStackMeta.setLore(lore);

        if(section.getBoolean("GLOWING")) itemStackMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);


        itemStack.setItemMeta(hideFlags(itemStackMeta));

        return itemStack;
    }
}
