package me.SnakesDevelopment.SnFreeze.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.SnakesDevelopment.SnFreeze.Main.Main;

public class Events implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(Main.frozen.contains(p)) {
			e.setTo(e.getFrom());
			p.sendMessage(Main.prefix + " " + ChatColor.RED + "Je bent bevroren!");
		}
	}
}
