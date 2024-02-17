package dev.thebjoredcraft.betterpunishment.punishment;


import org.bukkit.entity.Player;

import java.time.Duration;

public class Punishment {
    public PunishmentType type;
    public String reason;
    public boolean miniMessage;
    public Punishment(Player target, Player staff, PunishmentType type, String reason, Duration duration, boolean miniMessage){
        switch (type){
            case BAN -> {
                PunishmentManager.ban(target, staff, duration, reason);
            }
            case KICK -> {
                PunishmentManager.kick(target, staff, reason);
            }
            case MUTE -> {
                //mute
            }
            case WARN -> {
                PunishmentManager.warn(target, staff);
            }
        }
        this.type = type;
        this.reason = reason;
        this.miniMessage = miniMessage;
    }

    public PunishmentType getType() {
        return type;
    }

    public boolean isMiniMessage() {
        return miniMessage;
    }

    public String getReason() {
        return reason;
    }

}
