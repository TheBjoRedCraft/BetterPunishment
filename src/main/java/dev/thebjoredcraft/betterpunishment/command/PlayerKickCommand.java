package dev.thebjoredcraft.betterpunishment.command;

import dev.thebjoredcraft.betterpunishment.punishment.PunishmentManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerKickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 2) {//kick player reason
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    String reason = "";

                    for (int i = 1; i < args.length; i++) {
                        reason = reason + args[i] + " ";
                    }
                    PunishmentManager.kick(target, player, reason);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<red>The player was not found!"));
                }
            }else{
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Usage: <bold>/kick <player> <reason>"));
            }
        }
        return false;
    }
}
