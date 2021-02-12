package me.SnakesDevelopment.SnFreeze.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.SnakesDevelopment.SnFreeze.Main.Main;

public class Commands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("freeze")) {
			if(p.hasPermission("snfreezer.use")) {
				if(args.length == 0) {
					p.sendMessage(Main.prefix + " " + ChatColor.GOLD + "Je hebt geen speler genoemd!");
					return true;
				}
				
				
				if(Bukkit.getServer().getPlayer(args[0]) == null) {
					p.sendMessage(Main.prefix + " " + ChatColor.RED + "Speler" + " " + ChatColor.GRAY + args[0] + " " + ChatColor.RED + "niet gevonden!");
					return true;
				}
				
				Player t = Bukkit.getServer().getPlayer(args[0]);

				
				if(Main.frozen.contains(t)) {
					Main.frozen.remove(t);
					p.sendMessage(Main.prefix + " " + ChatColor.BLUE + args[0] + " " + "is niet meer bevroren!");
					t.sendMessage(Main.prefix + " " + ChatColor.BLUE + "Je bent niet meer bevroren door" + " " + p.getName() + "!");
					return true;
				} else {
					Main.frozen.add(t);
					p.sendMessage(Main.prefix + " " + ChatColor.BLUE + args[0] + " " + "is nu bevroren!");
					t.sendMessage(Main.prefix + " " + ChatColor.BLUE + "Je bent bevroren door" + " " + p.getName() + "!");
				}
			} else {
				p.sendMessage(" " + ChatColor.RED + "Je hebt geen toegang tot dit commando!");
				return true;
			}
		}
		return true;
	}
}
