package dev.thebjoredcraft.betterpunishment.command;

import dev.thebjoredcraft.betterpunishment.BetterPunishment;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class BetterPunishmentCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1 && args[0].equalsIgnoreCase("reset")){
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Achtung! Wenn du BetterPunishment resettest, werden alle Log Datein und die Config zurück gesetzt! Bestätige dies, mit <bold>/betterpunishment confirm<reset><red>!"));

            }else if(args.length == 2 && args[0].equalsIgnoreCase("reset") && args[1].equalsIgnoreCase("confirm")){
                File logFile = new File(BetterPunishment.INSTANCE.getDataFolder(), "log.txt");
                File configFile = new File(BetterPunishment.INSTANCE.getDataFolder(), "config.yml");

                logFile.delete();
                configFile.delete();

                BetterPunishment.INSTANCE.saveConfig();
            }else if(args.length == 1 && args[0].equalsIgnoreCase("info")){
                
            }
        }
        return false;
    }
}
