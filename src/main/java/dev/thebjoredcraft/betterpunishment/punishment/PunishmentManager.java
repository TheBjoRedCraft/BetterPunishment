package dev.thebjoredcraft.betterpunishment.punishment;

import dev.thebjoredcraft.betterpunishment.BetterPunishment;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class PunishmentManager {
    public static NamespacedKey warmKey = new NamespacedKey(BetterPunishment.INSTANCE, "warns");
    public static void ban(Player player, Player banner, Duration duration, String reason){
        long durationMillis = duration.toMillis();

        FileConfiguration config = BetterPunishment.INSTANCE.getConfig();
        Calendar calendar = Calendar.getInstance();

        Date banDate = calendar.getTime();
        Date unbanDate = new Date(banDate.getTime() + durationMillis);

        config.set("data." + player.getUniqueId() + ".latestBan.name", player.getName());
        config.set("data" + player.getUniqueId() + ".latestBan.bannedBy", banner.getName());
        config.set("data." + player.getUniqueId() + ".latestBan.reason", reason);
        config.set("data." + player.getUniqueId() + ".latestBan.unbanDate", unbanDate.toString());
        config.set("data." + player.getUniqueId() + ".latestBan.banDate", banDate.toString());

        String logDate = new SimpleDateFormat("ss:mm:HH dd-MM-yyyy").format(new Date());
        File logFile = new File(BetterPunishment.INSTANCE.getDataFolder(), "log.txt");

        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logDate + " " + banner.getName() + " banned " + player.getName() + " with reason: " + reason + " - Unban: " + unbanDate + "\n");
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[BP] " + e.getMessage());
        }

        BetterPunishment.INSTANCE.saveConfig();

        player.ban(reason, duration, "?", true);
    }
    public static void kick(Player player, Player kicker, String reason){

        FileConfiguration config = BetterPunishment.INSTANCE.getConfig();
        Calendar calendar = Calendar.getInstance();

        Date kickDate = calendar.getTime();

        config.set("data." + player.getUniqueId() + ".latestKick.name", player.getName());
        config.set("data." + player.getUniqueId() + ".latestKick.kickedBy", kicker.getName());
        config.set("data." + player.getUniqueId() + ".latestKick.reason", reason);
        config.set("data." + player.getUniqueId() + ".latestKick.kickDate", kickDate.toString());

        String logKick = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(new Date());
        File logFile = new File(BetterPunishment.INSTANCE.getDataFolder(), "log.txt");

        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logKick + " " + kicker.getName() + " kicked " + player.getName() + " with reason: " + reason + "\n");
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[BP] " + e.getMessage());
        }

        BetterPunishment.INSTANCE.saveConfig();

        player.kick(MiniMessage.miniMessage().deserialize(reason));
    }
    public static void warn(Player player, Player warner){

        player.getPersistentDataContainer().set(warmKey, PersistentDataType.INTEGER, 1);
    }
}
