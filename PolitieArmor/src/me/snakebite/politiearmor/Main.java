package me.snakebite.politiearmor;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import me.snakebite.armors.Armors;
import me.snakebite.files.config;

public class Main extends JavaPlugin {
	
	
	public void onEnable() {
		System.out.println("(!) PolitieArmor ENABLED!");
		this.getCommand("politiearmor").setExecutor((CommandExecutor)new Armors());
		
		config.setup();
		config.get().addDefault("LoreAspirant", "&cAspirant");
		config.get().addDefault("LoreWijkagent", "&cWijkagent");
		config.get().addDefault("LoreAgent", "&cAgent");
		config.get().addDefault("LoreHoofdAgent", "&cHoofdAgent");
		config.get().addDefault("LoreInspecteur", "&cInspecteur");
		config.get().addDefault("LoreHoofdInspecteur", "&cHoofdInspecteur");
		config.get().addDefault("LoreCommisaris", "&cCommisaris");

		config.get().options().copyDefaults(true);
		config.save();

	}
	
	public void onDisable() {
		System.out.println("(!) PolitieArmor DISABLED!");
	}
	
	
}
