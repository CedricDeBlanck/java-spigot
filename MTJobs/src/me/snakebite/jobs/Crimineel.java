package me.snakebite.jobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Crimineel implements Listener {

	private ArrayList<Player> cuffed = new ArrayList<Player>();
	
	
	@EventHandler
	public void onPlayerClickWithString(PlayerInteractAtEntityEvent e) {
		
		ItemStack stringItemStack = new ItemStack(Material.STRING, 1);
		ItemMeta stringItemMeta = stringItemStack.getItemMeta();
		stringItemMeta.setDisplayName("§6Tie rips");
		stringItemStack.setItemMeta(stringItemMeta);
		
		if((e.getRightClicked() instanceof Player) 
				&& (e.getPlayer().getInventory().getItemInMainHand().equals(stringItemStack)) 
				&& (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Tie rips"))
				&& (e.getPlayer().hasPermission("crimineel.tierips"))
				&& (e.getPlayer().getPassengers().size() < 1)) {
			
			Player p = (Player)e.getRightClicked();
			cuffed.add(p);
			e.getPlayer().addPassenger(p);
			if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Tie rips")) {
				e.getPlayer().getInventory().remove(stringItemStack);
			}
			
		}else if((e.getRightClicked() instanceof Player) 
				&& (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STICK)) 
				&& (e.getPlayer().hasPermission("staff.getname"))) {
				
			Player p = (Player)e.getRightClicked();
			e.getPlayer().sendMessage("§7Spelernaam: §6" + p.getName());
			return;
			
		}
		
	}
	
	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent e) {
		if((e.getVehicle() instanceof Player) 
				&& (e.getExited() instanceof Player)) {
			
			Player p = (Player) e.getVehicle();
			Player t = (Player)e.getExited();
			if(cuffed.contains(t))  {
				p.addPassenger(t);
			}
			
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getPlayer().isSneaking() && (!e.getPlayer().isFlying())) {
			switch(e.getAction()) {
				case RIGHT_CLICK_BLOCK:
					if(e.getPlayer().getPassengers().size() > 0) {
						e.getPlayer().getPassengers().forEach(p -> cuffed.remove(p));
						e.getPlayer().getPassengers().forEach(p -> p.teleport(e.getPlayer()));
					}
					break;
				case RIGHT_CLICK_AIR:
					if(e.getPlayer().getPassengers().size() > 0) {
						e.getPlayer().getPassengers().forEach(p -> cuffed.remove(p));
						e.getPlayer().getPassengers().forEach(p -> p.teleport(e.getPlayer()));
					}
					break;
				default:
					break;
			}
		}
		
	}
	
	
}
