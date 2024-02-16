package dev.thebjoredcraft.betterpunishment;

import dev.thebjoredcraft.betterpunishment.command.BetterPunishmentCommand;
import dev.thebjoredcraft.betterpunishment.command.PlayerBanCommand;
import dev.thebjoredcraft.betterpunishment.command.PlayerKickCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterPunishment extends JavaPlugin {
    public static BetterPunishment INSTANCE;

    @Override
    public void onEnable() {
        getCommand("ban").setExecutor(new PlayerBanCommand());
        getCommand("kick").setExecutor(new PlayerKickCommand());
        getCommand("betterpunishment").setExecutor(new BetterPunishmentCommand());
        INSTANCE = this;
        saveConfig();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        saveConfig();
        // Plugin shutdown logic
    }
}
