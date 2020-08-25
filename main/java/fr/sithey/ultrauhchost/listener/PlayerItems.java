package fr.sithey.ultrauhchost.listener;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Statut;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItems implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onCraft(PrepareItemCraftEvent event){
        ItemStack result = event.getInventory().getResult();

        if (Main.getInstance().getUHC().getStatut().equals(Statut.GAME)){
            if ((result != null && result.getType() != null && result.getType().equals(Material.GOLDEN_APPLE) && result.getDurability() == ((short) 0x1) && !RuleBoolean.NOTCHAPPLE.isEnabled())){
                event.getInventory().setResult(new ItemCreator(Material.AIR).getItem());
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onEnchants(EnchantItemEvent event){
        if (Main.getInstance().getUHC().getStatut().equals(Statut.GAME)){
            if ((event.getEnchantsToAdd().containsKey(Enchantment.FIRE_ASPECT) && !RuleBoolean.FIRE_ASPECT.isEnabled()) || (event.getEnchantsToAdd().containsKey(Enchantment.ARROW_FIRE) && !RuleBoolean.FLAME.isEnabled())) {
                event.setCancelled(true);
            }
        }
    }
}
