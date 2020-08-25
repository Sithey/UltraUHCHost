package fr.sithey.ultrauhchost.management.enumeration.rules;

public enum  RuleInteger {
    APPLERATE(1),
    FLINTRATE(100),

    FINALBORDERSIZE(200),

    TEAMSIZE(1),

    SLOTS(80),
    UHCNUMBER(0);
    private int value;
    RuleInteger(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
