package me.snakebite.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;

import me.snakebite.jobs.Ambulance;
import me.snakebite.jobs.Crimineel;
import me.snakebite.jobs.Scubahead;
import me.snakebite.jobs.Smid;

public class Main extends JavaPlugin implements Listener {

	
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("§7[§eJobs§7] §aEnabled!");
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new Scubahead(), this);
		getServer().getPluginManager().registerEvents(new Smid(), this);
		getServer().getPluginManager().registerEvents(new Crimineel(), this);
		getServer().getPluginManager().registerEvents(new Ambulance(), this);
		
		getCommand("duikersoutfit").setExecutor(new Scubahead());
		
        this.getServer().getPluginManager().registerEvents(this, this);
       

	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§7[§eJobs§7] §cDisabled!");
	}
	
	/*
	 * 
	 * Imker Job -> Honing
	 * 
	 * */
	
//	@EventHandler
//	public void imkerJob(BlockBreakEvent e) {
//		 Block block = e.getBlock();
//	     if(e.getPlayer().hasPermission("imker.pluk")) {
//	    	 if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
//	    		 
//	    		 ItemStack honingstok = new ItemStack(Material.STICK, 1);
//	    		 ItemMeta honing1Meta = honingstok.getItemMeta();
//	    		 honing1Meta.setDisplayName("§eHoningstok");
//	    		 honingstok.setItemMeta(honing1Meta);
//	    	 
//		    	 if (block.getType() == Material.MAGMA) {
//		    		 if(e.getPlayer().getInventory().getItemInMainHand().equals(honingstok)) {
//		    		 		            
//			            ItemStack honing = new ItemStack(Material.CARROT_ITEM, 1);
//			    		ItemMeta honingMeta = honing.getItemMeta();
//			    		honingMeta.setDisplayName("§eHoning");
//			    		honing.setItemMeta(honingMeta);
//	
//			            e.setDropItems(false);
//			            block.setType(Material.AIR);
//			            e.getPlayer().getInventory().addItem(honing);
//			            
//			            Bukkit.getScheduler().runTaskLater(this, new Runnable() {
//	
//							@Override
//							public void run() {
//								
//								block.setType(Material.MAGMA);
//								
//							}
//			            	
//			            }, 6000); // 5 minuten
//		    		 } else {
//		    			 e.setCancelled(true);
//		    		 }
//		       	}
//	    	 } else {
//	    		 return;
//	    	 }
//	     } else {
//	    	
//	    	if(block.getType() == Material.MAGMA) {
//	    		e.setCancelled(true);
//	    		e.getPlayer().sendMessage("§4Je bent geen Imker dus je kan geen honing verzamelen!");
//	    	}
//	     }
//	}
	
	@EventHandler
	public void disableOrePlacing(BlockPlaceEvent e) {
		Block block = e.getBlock();
		
		if(block.getType() == Material.DIAMOND_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.COAL_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.EMERALD_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.GLOWING_REDSTONE_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.GOLD_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.IRON_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.LAPIS_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.QUARTZ_ORE) {
			e.setCancelled(true);
		} else if(block.getType() == Material.REDSTONE_ORE) {
			e.setCancelled(true);
		} else {
			return;
		}
		
	}

}
