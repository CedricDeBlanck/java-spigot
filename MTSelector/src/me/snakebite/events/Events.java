package me.snakebite.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Events implements Listener {

	@EventHandler
	public void inventoryClickEvent(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) {
			return;
		} else {
			if(e.getClickedInventory().getTitle().equalsIgnoreCase("§0Selecteer je job")) {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§0")) {
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Slager")) {
					Functions.Slager(p);
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBakker")) {
					Functions.Bakker(p);
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3Mijner")) {
					Functions.Mijner(p);
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bHouthakker")) {
					Functions.Houthakker(p);
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Smid")) {
					Functions.Smid(p);
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Boer")) {
					Functions.Boer(p);
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eImker")) {
					Functions.Imker(p);
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Solliciteren")) {
					Functions.Solliciteren(p);
					e.setCancelled(true);
				}
				e.setCancelled(true);
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§0Kies jou spawn locatie!")) {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§0")) {
					e.setCancelled(true);
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGemeentehuis")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp Gemeentehuis " + p.getName());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selectedspawn");
					e.setCancelled(true);
					p.closeInventory();
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aOCPlein")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp OCPlein " + p.getName());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selectedspawn");
					e.setCancelled(true);
					p.closeInventory();
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aStation")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp Station " + p.getName());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selectedspawn");
					e.setCancelled(true);
					p.closeInventory();
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aIndustrie")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp Industrie " + p.getName());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selectedspawn");
					e.setCancelled(true);
					p.closeInventory();
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aHaven")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp Haven " + p.getName());
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selectedspawn");
					e.setCancelled(true);
					p.closeInventory();
				}
				e.setCancelled(true);
			}
		}
	}
	
	
	
	
}
