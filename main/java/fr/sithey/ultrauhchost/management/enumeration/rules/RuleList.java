package fr.sithey.ultrauhchost.management.enumeration.rules;

import java.util.ArrayList;
import java.util.List;

public enum RuleList {
    SCOREBOARDWAIT(),
    SCOREBOARDSCATTER(),
    SCOREBOARDGAME();
    private List<String> value;
    RuleList(){
        this.value = new ArrayList<>();
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
