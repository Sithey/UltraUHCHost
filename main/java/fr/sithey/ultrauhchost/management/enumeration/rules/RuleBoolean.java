package fr.sithey.ultrauhchost.management.enumeration.rules;

public enum RuleBoolean {
    NETHER(false),
    POTION(false),
    STRENGTH(false),
    SPEED(false),
    POISON(false),
    INVISIBILITY(false),
    HEAL(false),
    REGEN(false),
    LEVEL2(false),

    NOTCHAPPLE(false),
    HEAD(true),
    GHEAD(false),
    GAPPLE(true),
    FIRE_ASPECT(true),
    FLAME(true),
    CRAFTGOLDENHEAD(true),
    WOOLTOSTRING(true),


    ROLLERCAOSTER(true),
    STIPMINING(true),
    POKEHOLLING(true),
    DIGDOWNATMEETUP(false),
    STALK(false),
    TRAP(true),

    LAVABUCKET(true),
    FLINTANDSTEEL(true),
    ITEMBURN(true),

    NETHERLOAD(false),
    WORLDLOAD(false),
    HOSTPLAY(false),
    LATESCATTER(true),

    FRIENDLYFIRE(true),
    RANDOMTEAM(false),

    SPEC(false),
    MUTECHAT(false);
    private boolean enabled;
    RuleBoolean(boolean value){
        this.enabled = value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
