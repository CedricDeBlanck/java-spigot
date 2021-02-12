package me.Snakebite.playerfreezer;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	ArrayList<Player> frozen = new ArrayList<Player>();
	String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "OCFreezer" + ChatColor.GRAY + "]";
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(frozen.contains(p)) {
			e.setTo(e.getFrom());
			p.sendMessage(prefix + " " + ChatColor.RED + "Je bent bevroren!");
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("freeze")) {
			if(sender instanceof Player) {
				
			
			if(p.hasPermission("playerfreeze.freeze")) {
				if(args.length == 0) {
					p.sendMessage(prefix + " " + ChatColor.GOLD + "Je hebt geen speler genoemd!");
					return true;
				}
				
				
				if(Bukkit.getServer().getPlayer(args[0]) == null) {
					p.sendMessage(prefix + " " + ChatColor.RED + "Speler" + " " + ChatColor.GRAY + args[0] + " " + ChatColor.RED + "niet gevonden!");
					return true;
				}
				
				Player t = Bukkit.getServer().getPlayer(args[0]);

				
				if(frozen.contains(t)) {
					frozen.remove(t);
					p.sendMessage(prefix + " " + ChatColor.BLUE + args[0] + " " + "is niet meer bevroren!");
					t.sendMessage(prefix + " " + ChatColor.BLUE + "Je bent niet meer bevroren door" + " " + p.getName() + "!");
					return true;
				} else {
					frozen.add(t);
					p.sendMessage(prefix + " " + ChatColor.BLUE + args[0] + " " + "is nu bevroren!");
					t.sendMessage(prefix + " " + ChatColor.BLUE + "Je bent bevroren door" + " " + p.getName() + "!");
				}
			} else {
				p.sendMessage(" " + ChatColor.RED + "Je hebt geen toegang tot dit commando!");
				return true;
			}
		}
			
		}
		return true;
	}
	
}
