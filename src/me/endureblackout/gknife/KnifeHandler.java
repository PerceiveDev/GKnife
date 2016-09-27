package me.endureblackout.gknife;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class KnifeHandler implements Listener {
	GKnife plugin;
	
	public KnifeHandler(GKnife instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onDeathEvent(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		if(p.getKiller() instanceof Player && p.hasPermission("gknife.use")) {
			Player killer = p.getKiller();
			
			if(ChatColor.stripColor(killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName()).equalsIgnoreCase("Golden Knife")) {
				ItemStack knife = killer.getInventory().getItemInMainHand();
				
				if(killer.getInventory().contains(knife)) {
					killer.getInventory().remove(knife);
					killer.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
				}
				
				e.setDeathMessage(ChatColor.GOLD + killer.getDisplayName() + " used their Golden Knife to kill " + p.getDisplayName());
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!e.isCancelled()) {
			if(e.getInventory().getType() == InventoryType.ANVIL || e.getInventory().getType() == InventoryType.WORKBENCH) {
				Player p = (Player) e.getWhoClicked();
				
				if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Golden Knife")) {
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "You cannot change that item!");
				}
			}
		}
	}
}
