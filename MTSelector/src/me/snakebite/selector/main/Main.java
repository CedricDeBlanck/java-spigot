package me.snakebite.selector.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import me.snakebite.commands.Commands;
import me.snakebite.events.Events;
import me.snakebite.events.Functions;
import me.snakebite.menu.Menu;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable() {
		 Bukkit.getConsoleSender().sendMessage("§7[§6MTSelector§7] §aenabled!");
		 
		 
		 getServer().getPluginManager().registerEvents(this, this);
		 getServer().getPluginManager().registerEvents(new Events(), this);
		 
		 getCommand("jobs").setExecutor(new Commands());	

	}
	
	public void onDisable() {
		 Bukkit.getConsoleSender().sendMessage("§7[§6MTSelector§7] §2disabled!");
	}
	
	@EventHandler
	public void onFirstJoin(PlayerJoinEvent e) {
		if(e.getPlayer().hasPermission("mtselector.selected")) return;
		
		if(!e.getPlayer().hasPlayedBefore()) {			
			Bukkit.getScheduler().runTaskLater(this, new Runnable() {

				@Override
				public void run() {
					Menu.jobSelection(e.getPlayer());
					
				}
				
			}, 20);
			
			
		}
		
		if(!(e.getPlayer().hasPermission("mtselector.selected"))) {
			Bukkit.getScheduler().runTaskLater(this, new Runnable() {

				@Override
				public void run() {
					Menu.jobSelection(e.getPlayer());
					
				}
				
			}, 20);
		}
	}
	
	@EventHandler
	public void inventoryCloseEvent(InventoryCloseEvent e) {
		
		Player p = (Player)e.getPlayer();
		
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {

			@Override
			public void run() {
				if(e.getPlayer().hasPermission("mtselector.selected")) {
					return;
				} else if(!e.getPlayer().hasPermission("mtselector.selected")) {
					
					Menu.jobSelection(p);

				}
				
			}
			
		}, 5);
	}
	
}
