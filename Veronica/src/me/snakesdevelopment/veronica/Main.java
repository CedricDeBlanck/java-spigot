package me.snakesdevelopment.veronica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.snakesdevelopment.veronica.commands.Rewards;
import me.snakesdevelopment.veronica.commands.getLevel;
import me.snakesdevelopment.veronica.functions.ClickEvent;
import me.snakesdevelopment.veronica.mysql.*;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	

	private  Connection connection;
	public String host, database, username, password, table;
	public int port;
		
	@Override
	public void onEnable() {
		this.registerEvents();
		this.registerCommands();
		
		loadConfig();
		mysqlSetup();
		
		this.getServer().getPluginManager().registerEvents(new mysqlSetterGetter(), this);
		
		getServer().getConsoleSender().sendMessage("§7[§4Oswald§7] §aPlugin successfully initialized!");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§7[§4Oswald§7] §4Plugin successfully shutted down!");
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void registerEvents() {
		getServer().getPluginManager().registerEvents(new ClickEvent(), this);
	}
	
	public void registerCommands() {
		this.getCommand("level").setExecutor(new getLevel());
		this.getCommand("rewards").setExecutor(new Rewards());
	}

	public void mysqlSetup() {
		host = this.getConfig().getString("host");
		port = this.getConfig().getInt("port");
		database = this.getConfig().getString("database");
		username = this.getConfig().getString("username");
		password = this.getConfig().getString("password");
		table = this.getConfig().getString("table");
		
		try {
			synchronized (this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return;
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
