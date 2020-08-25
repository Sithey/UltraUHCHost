package fr.sithey.ultrauhchost.management.enumeration;

import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;

public enum Time {
    TIME(0),
    DAMAGE(0),
    FINALHEAL(0),
    PVP(0),
    MEETUP(0);
    private int time;
    Time(int time){
        this.time = time;
    }

    public int getTime() {
        if (this != TIME)
        return time - Time.TIME.getTime();
        else return time;
    }

    public void setTime(int time) {
        if (time < 0)
            time = 0;
        this.time = time;
    }

    public void Broadcast(){
        if (getTime() == 1 || getTime() == 2 || getTime() == 3 || getTime() == 5 || getTime() == 10 || getTime() == 30 || getTime() == 60 || getTime() == 60 * 3  || getTime() == 60 * 5 || getTime() == 60 * 10 || getTime() == 60 * 15 || getTime() == 60 * 20 || getTime() == 60 * 30 || getTime() == 60 * 45 || getTime() == 60 * 60){
            if (this == DAMAGE){
                Bukkit.broadcastMessage(Message.DAMAGEIN.getMessage().replace("<timemin>", getMinute() + "").replace("<timesec>", getSeconde() + ""));
            }

            if (this == FINALHEAL){
                Bukkit.broadcastMessage(Message.FINALHEALIN.getMessage().replace("<timemin>", getMinute() + "").replace("<timesec>", getSeconde() + ""));
            }

            if (this == PVP){
                Bukkit.broadcastMessage(Message.PVPIN.getMessage().replace("<timemin>", getMinute() + "").replace("<timesec>", getSeconde() + ""));
            }

            if (this == MEETUP){
                Bukkit.broadcastMessage(Message.MEETUPIN.getMessage().replace("<timemin>", getMinute() + "").replace("<timesec>", getSeconde() + ""));
            }
        }
    }

    public String getString(){
        if (this != TIME)
        return getTime() >= 60 ? getTime() / 60 + "" : getTime() > 0 ? getTime() + "" : "On";
        else return getTime() >= 60 ? getTime() / 60 + "" : getTime() > 0 ? getTime() + "" : "0:00";
    }

    public int getMinute(){
        return getTime() > 60 ? getTime() / 60 : getTime() == 60 ? 1 : 0;
    }

    public int getSeconde(){
        int value = Integer.parseInt(new SimpleDateFormat("s").format(getTime() * 1000));
        return value;
    }

    public String getDate(){
        return getTime() == 60 ? "minute" : getTime() > 60 ? " minutes" : getTime() == 1 ? " seconde" : " secondes";
    }
}
