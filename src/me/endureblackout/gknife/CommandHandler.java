package me.endureblackout.gknife;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class CommandHandler implements CommandExecutor {
	
	GKnife plugin;
	
	ItemStack gKnife = new ItemStack(Material.GOLD_SWORD);
	ItemMeta knifeMeta = gKnife.getItemMeta();
	
	public CommandHandler(GKnife instance) {
		this.plugin = instance;
		
		knifeMeta.setDisplayName(ChatColor.GOLD + "Golden Knife");
		knifeMeta.addEnchant(Enchantment.DAMAGE_ALL, 1000, true);
		gKnife.setItemMeta(knifeMeta);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("gknife")) {
			if(args.length == 2 && args[0].equalsIgnoreCase("give")) {
				if(sender.hasPermission("gknife.give")) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getName().equalsIgnoreCase(args[1])) {
							sender.sendMessage(ChatColor.GREEN + p.getName() + " was given the golden knife!");
							p.getInventory().addItem(gKnife);
							p.sendMessage(ChatColor.GOLD + "You have been given a Golden Knife!");
							return true;
						} else if(args[1].equalsIgnoreCase(sender.getName())) {
							if(sender instanceof Player) {
								sender.sendMessage(ChatColor.GOLD + "You were given the Golden Knife!");
								((Player) sender).getInventory().addItem(gKnife);
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + "Only players in game can give themselves the gold knife!");
								return true;
							}
						}
					}
				}  else {
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}
		}
		
		
		return true;
	}

}
