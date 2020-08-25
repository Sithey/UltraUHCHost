package fr.sithey.ultrauhchost.management;

import fr.sithey.ultrauhchost.gui.host.*;
import fr.sithey.ultrauhchost.gui.host.Rules;
import fr.sithey.ultrauhchost.gui.player.Rule;
import fr.sithey.ultrauhchost.gui.player.Scenarios;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Gui {

    public Gui(){
        registersGUI();
    }

    public Map<Class<? extends CustomInventory>, CustomInventory> registeredMenus = new HashMap<>();

    private void addMenu(CustomInventory m) {
        this.registeredMenus.put(m.getClass(), m);
    }


    public void open(Player player, Class<? extends CustomInventory> gClass) {

        if (!this.registeredMenus.containsKey(gClass))
            return;

        CustomInventory menu = this.registeredMenus.get(gClass);
        Inventory inv = Bukkit.createInventory(null, menu.getSlots(), menu.getName());
        inv.setContents(menu.getContents(player).get());
        player.openInventory(inv);

    }

    public void registersGUI() {
        addMenu(new Configuration());
        addMenu(new Timer());
        addMenu(new Nether());
        addMenu(new Items());
        addMenu(new World());
        addMenu(new Rules());
        addMenu(new Server());
        addMenu(new Teams());
        addMenu(new Scenarios1());
        addMenu(new Scenarios2());
        addMenu(new Scenarios3());
        addMenu(new Rule());
        addMenu(new Scenarios());
        addMenu(new fr.sithey.ultrauhchost.gui.host.Inventory());
    }
}
