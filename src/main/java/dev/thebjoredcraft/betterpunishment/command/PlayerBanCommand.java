package dev.thebjoredcraft.betterpunishment.command;

import dev.thebjoredcraft.betterpunishment.punishment.Punishment;
import dev.thebjoredcraft.betterpunishment.punishment.PunishmentType;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;


public class PlayerBanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 3) {//ban player duration reason
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    Duration duration = Duration.ofSeconds(Long.parseLong(args[1]));
                    StringBuilder reason = new StringBuilder();

                    for (int i = 2; i < args.length; i++) {
                        reason.append(args[i]).append(" ");
                    }
                    new Punishment(target, player, PunishmentType.BAN, reason.toString(), duration, false);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<red>The player was not found!"));
                }
            }else{
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Usage: <bold>/ban <player> <duration> <reason>"));
            }
        }
        return false;
    }
}
