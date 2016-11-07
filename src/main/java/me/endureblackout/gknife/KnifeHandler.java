package me.endureblackout.gknife;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class KnifeHandler implements Listener {
    GKnife plugin;

    public KnifeHandler(GKnife instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onHitEvent(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();

            if (p.getInventory().getItemInMainHand().equals(CommandHandler.gKnife) && !p.hasPermission("gknife.use")) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You are not allowed to use that!");
            }
        }
    }

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();

        if (p.getKiller() instanceof Player && p.hasPermission("gknife.use")) {
            Player killer = p.getKiller();

            if (killer.getInventory().getItemInMainHand() == CommandHandler.gKnife) {
                e.setDeathMessage(ChatColor.GOLD + killer.getDisplayName() + " used their Golden Knife to kill " + p.getDisplayName());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.isCancelled()) {
            if (e.getInventory().getType() == InventoryType.ANVIL || e.getInventory().getType() == InventoryType.WORKBENCH) {
                Player p = (Player) e.getWhoClicked();

                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Golden Knife")) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You cannot change that item!");
                }
            }
        }
    }

    @EventHandler
    public void onPreCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().contains("fix") || e.getMessage().contains("repair")) {
            if (e.getPlayer().getInventory().contains(CommandHandler.gKnife)) {

                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "A golden knife cannot be repaired");
            } else if (!e.getPlayer().getInventory().contains(CommandHandler.gKnife)) {
                return;
            }
        }
    }
}
