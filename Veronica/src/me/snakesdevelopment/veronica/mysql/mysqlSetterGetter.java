package me.snakesdevelopment.veronica.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dempsey.mt.api.event.MTCoreLevelEvent;
import me.snakesdevelopment.veronica.Main;
import net.md_5.bungee.api.ChatColor;

public class mysqlSetterGetter implements Listener {

	static Main plugin = Main.getPlugin(Main.class);
	
	public static ArrayList<Integer> rewards = new ArrayList<>();

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		createPlayer(player.getUniqueId(), player);
	}
	
	@EventHandler
	public void onLevelUp(MTCoreLevelEvent e) {
		
		// GET WHAT YOU WANT
		
		
	}
	
	public boolean playerExists(UUID uuid) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createPlayer(final UUID uuid, Player player) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
			
			statement.setString(1, uuid.toString());
			ResultSet results = statement.executeQuery();
			results.next();
			if(playerExists(uuid) != true) {
				PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table + "(UUID,NAME,LEVEL) VALUE (?,?,?)");
				insert.setString(1, uuid.toString());
				insert.setString(2, player.getName());
				insert.setInt(3, 0);
				insert.executeUpdate();
				
				plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Player with uuid: " + uuid + " successfully inserted!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getLevel(final Player player, CommandSender sender) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT LEVEL FROM " + plugin.table + " WHERE NAME=?");
			
			statement.setString(1, player.getName());
			ResultSet results = statement.executeQuery();
			
			if(results.next()) {
				sender.sendMessage("LEVEL: " + results.getInt(1));
			} else {
				sender.sendMessage("§4Speler niet gevonden!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void getRewards(final Player player, CommandSender sender) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM rewards WHERE uuid=?");
			
			statement.setString(1, player.getUniqueId().toString());
			
			ResultSet results = statement.executeQuery();
			
			Inventory inventory = Bukkit.createInventory(player, 9, "§a§lDagelijkse Beloningen");
			
			
			while(results.next()) {
				
				if(results.getInt("claimed") == 1) {
					ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
					ItemMeta bookMeta = book.getItemMeta();
					bookMeta.setDisplayName("§b§l" + results.getString("titel") + " §a(CLAIMED)");
					ArrayList<String> loreArrayList = new ArrayList<>();
					loreArrayList.add("§c" + results.getString("reden"));
					loreArrayList.add("§f" + results.getString("bedrag"));
					bookMeta.setLore(loreArrayList);
					book.setItemMeta(bookMeta);
					
					inventory.addItem(book);

				} else {
					ItemStack book = new ItemStack(Material.BOOK, 1);
					ItemMeta bookMeta = book.getItemMeta();
					bookMeta.setDisplayName("§b§l" + results.getString("titel"));
					ArrayList<String> loreArrayList = new ArrayList<>();
					loreArrayList.add("§c" + results.getString("reden"));
					loreArrayList.add("§f" + results.getString("bedrag"));
					bookMeta.setLore(loreArrayList);
					book.setItemMeta(bookMeta);
					
					inventory.addItem(book);

				}
				
			
			}
			
			player.openInventory(inventory);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeRewards(final Player player, String title) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("DELETE FROM `rewards` WHERE `titel` = ? AND `uuid` = ?");
			
			statement.setString(1, title.replace("§b§l", ""));
			statement.setString(2, player.getUniqueId().toString());
					
			statement.executeUpdate();
			
			player.sendMessage("Boete betaald!");
			
			getRewards(player, player);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateRewards(final Player player, String title) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE rewards SET claimed = 1 WHERE `titel` = ? AND `uuid` = ?");
			
			statement.setString(1, title.replace("§b§l", ""));
			statement.setString(2, player.getUniqueId().toString());
					
			statement.executeUpdate();
			
			player.sendMessage("Boete betaald!");
			
			getRewards(player, player);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
