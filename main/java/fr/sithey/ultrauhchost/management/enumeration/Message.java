package fr.sithey.ultrauhchost.management.enumeration;

public enum  Message {
    CHAT(),
    SPEC(),
    HOST(),
    DEATH,
    PREFIX(),
    JOIN(),
    QUIT(),
    WELCOME(),
    SCATTERSTARTING(),
    SCATTERFINISH(),
    DAMAGEIN(),
    DAMAGENOW(),
    FINALHEALIN(),
    FINALHEALNOW(),
    PVPIN(),
    PVPNOW(),
    MEETUPIN(),
    MEETUPNOW(),
    DIEDSELF(),
    DIEDKILLED(),
    VICTORY(),
    HELPOPSENT(),
    HELPOPRECEIVE(),
    LATESCATTERJOIN(),
    LATESCATTERBROADCAST(),
    SCOREBOARDTITLE(),
    HEADER,
    FOOTER;
    private String message;
    Message(){
        message = "";
    }
    public String getMessage() {
        if (this != PREFIX && this != SCOREBOARDTITLE && this != CHAT && this != SPEC && this != HOST && this != DEATH && this != HEADER  && this != FOOTER)
        return PREFIX.message + message;
        else return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
