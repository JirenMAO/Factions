package me.mao.minigame.core.factions.util.invite;

import me.mao.minigame.core.factions.Faction;
import me.mao.minigame.core.factions.util.FactionPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class FactionInvite extends BukkitRunnable {

    private FactionPlayer invited;
    private Faction toFaction;

    private int timeToAccept = 30;

    boolean accepted;

    public FactionInvite(FactionPlayer invited, Faction toFaction) {
        this.invited = invited;
        this.toFaction = toFaction;
    }

    public FactionPlayer getInvited() {
        return invited;
    }

    public void setInvited(FactionPlayer invited) {
        this.invited = invited;
    }

    public Faction getToFaction() {
        return toFaction;
    }

    public void setToFaction(Faction toFaction) {
        this.toFaction = toFaction;
    }

    public int getTimeToAccept() {
        return timeToAccept;
    }

    public void setTimeToAccept(int timeToAccept) {
        this.timeToAccept = timeToAccept;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }


    public void run() {
        if (getTimeToAccept() == 0) {
            if(isAccepted()) {

            }else {

            }
        }
    }
}
