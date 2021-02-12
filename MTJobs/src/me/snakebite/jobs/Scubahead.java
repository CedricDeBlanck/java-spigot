package me.snakebite.jobs;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Scubahead implements Listener, CommandExecutor {
		
	@EventHandler
	public static void onWaterBreath(PlayerMoveEvent e) {
		Material water = e.getPlayer().getLocation().getBlock().getType();
		Player player = e.getPlayer();
		if(water == Material.WATER || water == Material.STATIONARY_WATER) {
			Location l = player.getLocation();
			l.setY(l.getY() + 1);
			Block b = l.getBlock();
			
			ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
			meta.setDisplayName("§0Duikers helm");
			meta.setColor(Color.BLACK);
			helmet.setItemMeta(meta);
			
			if(b.isLiquid()) {
				if(e.getPlayer().getInventory().getHelmet() != null) {
					if(e.getPlayer().getInventory().getHelmet().equals(helmet) && e.getPlayer().getInventory().getHelmet() != null) {
						Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("MTJobs"), new Runnable() {
		
							@Override
							public void run() {
								if(e.getPlayer().getRemainingAir() < 280) {
									e.getPlayer().setRemainingAir(280);
								}
							}
							
						}, 20, 20);
					} 
				} else {
					return;
				}
			} else {
				return;
			}
		} else {
			return;
		}
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
			Player p = (Player)sender;
			
			if(p instanceof Player) {
				if(p.hasPermission("duiker.getItem")) {
					if(cmd.getName().equalsIgnoreCase("duikersoutfit")) {
						ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
						LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
						meta.setDisplayName("§0Duikers helm");
						meta.setColor(Color.BLACK);
						helmet.setItemMeta(meta);
						
						p.getInventory().addItem(helmet);
					}
				}
			}
		return true;
	}
}
