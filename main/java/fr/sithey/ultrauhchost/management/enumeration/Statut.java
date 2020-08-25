package fr.sithey.ultrauhchost.management.enumeration;

public enum Statut {
    WAIT(true),
    SCATTER(true),
    GAME(false),
    FINISH(false);

    private boolean lobby;
    Statut(boolean lobby){
        this.lobby = lobby;
    }

    public boolean isLobby() {
        return lobby;
    }
}
