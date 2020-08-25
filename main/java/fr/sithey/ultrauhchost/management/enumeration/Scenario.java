package fr.sithey.ultrauhchost.management.enumeration;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.management.scenarios.*;
import fr.sithey.ultrauhchost.management.scenarios.onepertwo.OnePerTwo;
import fr.sithey.ultrauhchost.management.scenarios.switchteam.SwitchTeam;

import java.util.ArrayList;
import java.util.List;

public enum Scenario {
    UNSURDEUXORES(new OnePerTwo(), "For every 2 ores you mine, 1 drops. Every single ore is seperate from one and another.", true),
    THREEARROWS(new ThreeArrows(), "When you shoot with your bow, not one, but three arrows get shot out.", false),
    ACHIEVEMENTPARANOIA(new AchievementParanoia(), "Achievements show up in chat like in vanilla minecraft but at the end off each achievement it shows the coordinates of the player who earned that achievement.", true),
    APPLEFAMINE(new AppleFamine(), "Apples do not drop, meaning you can only heal with potions and golden heads.", false),
    ARMAGEDDON(new Armageddon(), "Lava, gravel, potions, and nuggets fall from the sky.", true),
    ARMORVSHEALTH(new ArmorVsHealth(), "For every half Armor Point you add to your Armor bar, half a Health Point will be taken away from your Health. Any health lost to armor will not be healed back.", false),
    BALANCE(new Balance(), "After the 5th diamond, it gets progressively harder to obtain diamonds.", false),
    BAREBONES(new Barebones(), "The Nether is disabled, Iron is the highest tier you can obtain through gearing up, When a player dies, they will drop 1 diamond, 1 golden apple, 32 arrows, and 2 string, You cannot craft an enchantment table, anvil, or golden apple, Mining any ore except coal or iron will drop an iron ingot.", false),
    BATS(new Bats(), "When a player kills a bat, there's a 95% chance of dropping a Golden Apple, and a 5% chance of killing the player.", false),
    BENCHBLITZ(new BenchBlitz(), "You can only craft one crafting table", false),
    BETAZOMBIES(new BetaZombies(), "Zombies drop feathers.", false),
    BIOMEPARANOIA(new BiomeParanoia(), "When players are in a different biome their name will turn a certain color on tab.", false),
    BLEEDINGSWEET(new BleedingSweet(), "When a player dies, they drop 1 diamond, 5 gold, 16 arrows and 1 string.", false),
    BLOCKED(new Blocked(), "You can't break the blocks that you place. Other players can break blocks that you place, and you can break blocks that other players place.", false),
    BLOCKRUSH(new BlockRush(), "Mining a specific block type for the first time gives you a reward, usually 1 gold ingot.", true),
    BLOODCYCLE(new BloodCycle(), "In this gamemode every 10 minutes it will cycle to a new ore, these ores consist of Emerald, Diamond, Gold, Iron, Coal, Lapis, and Redstone. If it cycles to one of these ores, when you mine it; it has a chance of damaging you by half-a-heart, but you could it lucky and it will cycle to no ore", true),
    BLOODDIAMOND(new BloodDiamond(), "When a diamond ore is mined, the player mining the diamond ore will take half a heart of damage.", false),
    BOMBER(new Bomber(), "Everyone starts out with an unbreakable flint and steel, most animals and monsters drop TNT.", true),
    BOW(new Bow(), "All kinds of melee, including sword, punch, or axe, is disabled. Even iPVP is disabled. The only way to damage a player is with a bow. You are able to damage mobs with sword/melee, however.", false),
    BOWFIGHTERS(new BowFighters(), "Everyone is given 1 arrow,2 string and an Infinity book. The highest tier of melee weapon you can get is wood sword/stone axe.", false),
    CARROTCOMBO(new CarrotCombo(), "When you craft a sword, it gives you a carrot with an enchantment equal to the sword.", false),
    CATEYES(new CatEyes(), "All players are given Night Vision at the start of the game.", false),
    CHICKEN(new Chicken(), "Everyone starts on 1/2 heart with a notch apple.", false),
    CRAFTINGTELEPORTION(new CraftingTeleportation(), "If you rename an ender pearl to a online player's name it will remove the ender pearl from your inventory and teleport you within a 50 blocks radius of that player. Teleports are not instant, as skript has to find a safe location to teleport the player to.", true),
    CRIPPLE(new Cripple(), "If you take fall damage you will get slowness for 30 seconds", false),
    COCO(new Coco(), "Everyone starts out with 5 cocoa beans. If you right click the cocoa bean, you 'eat' it and receive speed I for 30 seconds and strength I for 10 seconds. After 30 seconds, you receive 15 seconds of slowness I and 5 seconds of weakness I.", false),
    CUTCLEAN(new Cutclean(), "Ores and animal drops are automatically smelted, no furnaces needed.", false),
    DIAMONDLESS(new DiamondLess(), "Diamonds do not drop. Players drop 4 gold and 1 diamond upon death.", false),
    DOUBLEORNOTHING(new DoubleOrNothing(), "On mine of iron, diamond, emerald or gold ore you have a 50% chance of 2 of the ore dropping or no ores dropping.", true),
    EGGS(new Eggs(), "When you throw an egg, a random mob will spawn where it lands. This includes enderdragons and withers. When you kill a chicken, there is a 5% chance of it dropping an egg.", true),
    ENCHANTEDDEATH(new EnchantedDeath(), "You cannot craft enchantment tables, the only way of getting them is from killing players", false),
    ENCHANTLESS(new EnchantLess(), "The only way to get XP levels is by killing a player. You get 5 XP levels per kill.", false),
    FASTSMELTING(new FastSmelting(), "Smelting time is reduced.", false),
    FIRSTHITLOVE(new FirstHitLove(), "The first person you hit after pvp is on becomes your teammate. Friendly fire is disabled.", true),
    FLOWERPOWER(new FlowerPower(), "When a player breaks any random flower, there is a chance that the flower will drop a random item (excluding notch apples).", true),
    GOINHAM(new GoInHam(), "All players start with a raw porkchop named 'Ham'. Upon eating it, the player will receive 10 absorption hearts, strength 1, and speed 2 for 1 minute.", false),
    GOLDENFLEECE(new GoldenFleece(), "Sheep have a 60% of dropping 1 iron ingot, 30% chance of dropping 1 gold ingot, and a 10% chance of dropping 1 diamond. There is also a chance that when you kill a sheep, a skeleton wearing full gold armor will spawn.", false),
    GOLDENRETRIEVER(new GoldenRetriever(), "When a player dies, they drop 1 golden head.", false),
    GONEFISHIN(new GoneFishin(), "Each player starts with an Lure 5 and Luck of the Sea 250 fishing rod along with 20 anvils.", false),
    GOTOHELL(new GoToHell(), "you must be in the nether or you will take damage every 30 seconds at the meetup.", false),
    GUNNROSES(new GunsNRoses(), "When you break a poppy you get 1 arrow. When you break a rose bush you get 4 arrows. You have a 2% chance of getting a bow from both of them.", true),
    HASTEYBOYS(new HasteyBoys(), "Everyone mines faster.", false),
    HEALTHDONOR(new HealthDonor(), "Players can donate their health to their teammates with /team donate <mate> <health>.", true),
    HIGHWAYTOHELL(new HighwayToHell(), "Each player/team is given 14 Obsidian, 1 Flint & Steel, 1 Lava Bucket, 1 Iron Pickaxe, and a stack of food (Steak or Pork). Intended to encourage Nethering. Trapping/camping is not allowed.", false),
    IRONDROP(new IronDrop(), "Mining Iron Ore does not drop Iron Ore. Killing a zombie will drop 1 iron ingot and a chance to drop a poppy.", false),
    INFESTATION(new Infestation(), "Everytime you kill a mob there's a 40% chance that the same mob spawns in the same place.", true),
    INFINITEENCHANTER(new InfiniteEnchanter(), "All players start with 128 Bookshelves, Infinite XP Levels, 64 Anvils and 64 Enchantment Tables.", false),
    INVENTORS(new Inventors(), "The first person to craft any item will be broadcasted in chat.", true),
    JACKPOT(new Jackpot(), "Every time someone mines gold or diamond, an equivalent amount of what they received is added to the Pot. Write jackpot in the chat to see the jackpot. When a player dies, they will drop all of the items currently in the Pot, and it will reset back to 0 gold and 0 diamonds. If the death was caused by PvE, the player's death location will be broadcasted.", true),
    KILLSWITCH(new KillSwitch(), "You take your victim's inventory in place of yours upon killing them.", false),
    KING(new King(), "Team based fun, where one member on your team is the king. The King has some buffs, and when he dies his teammates either get debuffs.", true),
    KRENZINATOR(new Krenzinator(), "Krenzinator always mines redstone, therefore 9 redstone blocks can be crafted into 1 diamond to make bloody sure you will mine all of the redstone. Krenzinator doesn't like diamond swords, therefore crafting a diamond sword will result in a loss of 1 heart (armor does not matter). Krenzinator likes throwing eggs at people, therefore eggs do damage in this gamemode (0.5 heart, is reduced with armor). Krenzinator doesn't like Nether, so it's off :) .", false),
    LASTRESORT(new LastResort(), "When having 1 heart or less hp, you can type /lastresort will heal you up to full, but it also gives you infinite Wither I.", false),
    LIGHTSOUT(new LightsOut(), "Torches cannot be placed.", false),
    LONGSHOTS(new Longshots(), "If you hit a shot 75+ Blocks away you get healed 1 heart and do 1.5x more damage.", false),
    MELEEFUN(new MeleeFun(), "cancels out the 'noDamageTicks' so there is no delay between hits. However fast you click is how fast you hit someone", false),
    MOBPASSIVE(new MobPassive(), "Mob can't target players", false),
    MOBSHOTS(new MobShots(), "If someone gets shot with a bow from a certain distance it spawns a mob on the player. 5-20 blocks:3 Silverfish. 21-40 blocks: 3 Zombie. 41-60 blocks: 5 Skeletons. 61-85: 2 Witches. 86-Greater: 7 Ghasts", true),
    NINESLOTS(new NineSlots(), "You can only use your hotbar to hold items.", false),
    NINJANAUT(new Ninjanaut(), "A random player is selected on the first night and is given an infinite Speed II effect and an infinite Strength II effect. If a player kills the Ninjanaut, they get to take its place.", true),
    NOABSO(new NoAbso(), "You can't get abso effect", false),
    NOCLEANUP(new NoCleanUp(), "Kill player give you 4 hearts.", false),
    NOFALL(new NoFall(), "You can't get fall damage.", false),
    NOFISHINGRODS(new NoFishingRods(), "You can't use a fishing rod to knock back players.", false),
    NOFURNACE(new NoFurnace(), "Furnaces cannot be crafted.", false),
    NOSPRINT(new NoSprint(), "Sprinting is disabled.", false),
    NOSHINYENOUGH(new NoShinyEnough(), "Enchant tables and anvils are not craftable.", false),
    ONEHEAL(new OneHeal(), "At start, you get a golden hoe, which fully heals you once. After your heal, the hoe disappears. You can still heal from golden apples and golden heads, however.", false),
    OVERCOOK(new Overcook(), "When smelting you get time to cook 64 of any item in a furnace, after it has finished smelting 64 items it explodes. The result drops on the ground after explosion so you do not loose items.", false),
    PERMAKILL(new PermaKill(), "When a player dies, it toggle perma day/night.", false),
    POPEYE(new Popeye(), "At the start of the game everyone will receive spinach(cactus green or green wool). When you right click it, you will get Strength I for 5 seconds.", false),
    POTIONSWAP(new PotionSwap(), "After scatter, each player will receive a random potion effect (Speed I, Strength I, Haste I, Jump Boost I, Invisibility II, Resistance I or Fire Resistance I). Every 5 minutes, a new effect will be given, and previous effects will be removed. Players do not all get the same effect.", true),
    PUPPYPOWER(new PuppyPower(), "Everyone starts with 64 Bones, 64 Rotten Flesh, and 64 Wolf Spawn Eggs.", false),
    PYRO(new Pyro(), "All Players start with a flame 1 book and a fire aspect 1 book.", false),
    RANDOMCRAFT(new RandomCraft(), "When a craft craft player or cooks an object, this one will give an object different from his usual craft.", true),
    RANDOMDROP(new RandomDrop(), "blocks are randomized! When a player breaks a block / object or kill entities, it will give an object different from his usual loot.", true),
    RAGDOLLS(new RagDolls(), "All players start with a knockback 2 and punch 2 book.", false),
    REDARROWS(new RedArrows(), "When a player dies a red arrow spawns above the surface to show where the death was.", true),
    REWARDINGLONGSHOTS(new RewardingLongshots(), "When shooting and hitting people with an arrow from a variable distance, you will be rewarded with various different items. 30-50 Block Longshots: 1 iron ingot. 51-100 Block Longshots: 1 iron ingot + 1 gold ingot. 101 - 200 Block Longshots: 1 diamond + 1 gold ingot + 1 iron ingot. 200+ Block Longshots: 5 diamonds + 3 gold ingots + 2 iron ingot", true),
    RUSH(new Rush(), "The match is shorter than usual.", false),
    ROTTENPOTIONS(new RottenPotions(), "Eating rotten flesh can give a player a random effect for 10 seconds. You can get positive or negative effects.", true),
    SHAREDHEALTH(new SharedHealth(), "Damage is shared across players in the team.", true),
    SHEEPLOVERS(new SheepLovers(), "Sheep become useful! Upon killing them, they drop 1-3 uncooked beef. If you sheer them, there's a 5% chance that they could drop a gold ingot.", false),
    SKYHIGH(new SkyHigh(), "you will take damage every 30 seconds if you are below y:150 at meetup.", false),
    SNOWBALLFLIGHT(new SnowballFlight(), "When you throw a snowball you ride it! After like 1 second the snowball hits you so you need to constantly be throwing them to maintain a sustainable flight.", true),
    SLUTCLEAN(new SlutClean(), "Ores drop smelted if you're not wearing armour.", false),
    SUPERHEROES(new SuperHeroes(), "Each player will gain a special ability. The powers are speed 1, strength 1, resistance 2, invisibility, 6 extra hearts, and jump boost 4.", false),
    SUPERHEROESPLUS(new SuperHeroesPlus(), "Each player will gain an extra ability. The powers are: speed 2 and haste 2; jump 4, haste 2, and saturation 10; resistance 2 and fire resistance 1; health boost (20 hearts); invisibility 1 and water breathing 1; and strength 1.", true),
    SWITCHEROO(new Switcheroo(), "When you shoot someone, you trade places with them.", false),
    SWITCHTEAM(new SwitchTeam(), "Every 10 minutes, a random player from every teams switch in other team", true),
    TEAMINVENTORY(new TeamInventory(), "Throughout the game, you and your teammates can access an inventory that is shared among your team. There is no physical inventory, so any teammate can access it from anywhere with /team inventory.", true),
    THEHOBBIT(new TheHobbit(), "At the start of the game, everyone will be given a golden nugget(which signifies as your own ring). Once you right click it, it will disappear from your inventory and will give invisibility for 30 seconds.", false),
    TIMBER(new Timber(), "Breaking a log of a tree will cause the whole tree to fall down", false),
    TIMEBOMB(new TimeBomb(), "After killing a player, their loot will drop into a chest. A chosen amount of seconds later (usually 30 seconds), the chest will explode.", false),
    WEBCAGE(new Webcage(), "A sphere of cobwebs spawns around a player's death location.",false),

    /**/

    ;

    private CustomScenario scenario;
    private boolean enabled, premium;
    private String description;
    Scenario(CustomScenario scenario, String description, boolean premium){
        this.scenario = scenario;
        this.description = description;
        this.enabled = false;
        this.premium = premium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggleEnabled(){
        enabled = !enabled;
    }

    public CustomScenario getScenario() {
        return scenario;
    }

    public static List<Scenario> getEnabled(){
        List<Scenario> value = new ArrayList<>();
        for (Scenario scenario : Scenario.values()){
            if (scenario.isEnabled()){
                value.add(scenario);
            }
        }
        return value;
    }

    public boolean isPremium() {
        if (Main.getInstance().isPremium())
            return false;
        return premium;
    }
}
