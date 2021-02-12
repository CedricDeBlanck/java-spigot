package me.snakesdevelopment.veronica.functions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.snakesdevelopment.veronica.mysql.mysqlSetterGetter;

public class ClickEvent implements Listener {

	
	@EventHandler
	public static void onInventoryClick(InventoryClickEvent e) {
		if(e.getClickedInventory() != null) {
			if(e.getClickedInventory().getTitle().equalsIgnoreCase("§c§lBoetes")) {
				if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
					if(e.getCurrentItem().getType().equals(Material.BOOK)) {
						String title = e.getCurrentItem().getItemMeta().getDisplayName();
						Player author = (Player) e.getWhoClicked();
						
						mysqlSetterGetter.updateRewards(author, title);
						e.setCancelled(true);
					} else if(e.getCurrentItem().getType().equals(Material.WRITTEN_BOOK)) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
