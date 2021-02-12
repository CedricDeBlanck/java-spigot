package me.SnakesDevelopment.SnFreeze.Main;

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

	public static ArrayList<Player> frozen = new ArrayList<Player>();
	public static String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "SnFreeze" + ChatColor.GRAY + "]";
	
	public void onEnable() {
		
		getServer().getConsoleSender().sendMessage("                                       ");
		getServer().getConsoleSender().sendMessage("                 §aEnabled             ");
		getServer().getConsoleSender().sendMessage("             §3SnFreeze §av1.0         ");
		getServer().getConsoleSender().sendMessage("                    By                 ");
		getServer().getConsoleSender().sendMessage("            §3SnakesDevelopment        ");
		getServer().getConsoleSender().sendMessage("                                       ");
		
		
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("                                       ");
		getServer().getConsoleSender().sendMessage("                §cDisabled             ");
		getServer().getConsoleSender().sendMessage("             §3SnFreeze §av1.0         ");
		getServer().getConsoleSender().sendMessage("                    By                 ");
		getServer().getConsoleSender().sendMessage("            §3SnakesDevelopment        ");
		getServer().getConsoleSender().sendMessage("                                       ");
	}
	
}
