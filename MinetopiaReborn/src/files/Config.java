package files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
	
	public static Plugin p;
	static File file;
		
	public Config() {
		
	}
	
	public Plugin getPlugin() {
		return p;
	}
	
	public void start(Plugin pl) {
		Config.p = pl;
		createConfig(pl);
	}
	
	public void init(Plugin pl) {
		if(!pl.getDataFolder().exists()) {
			pl.getDataFolder().mkdir();
			
		}
	}
	
	public static File f;
	
	public static void createConfig(Plugin pl) {
		File configFile = new File(pl.getDataFolder(), "config.yml");

		if(!configFile.exists()) {
			p.saveResource("config.yml", false);
		} else {
			return;
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		
		if(!config.contains("textChannel")) {
			config.set("textChannel", "0000000000000000");
		}
		if(!config.contains("reportChannel")) {
			config.set("reportChannel", "0000000000000000");
		}
		if(!config.contains("politieDiscordMeldingenChannelId")) {
			config.set("politieDiscordMeldingenChannelId", "0000000000000000");
		}
		if(!config.contains("ambulanceDiscordMeldingenChannelId")) {
			config.set("ambulanceDiscordMeldingenChannelId","0000000000000000");
		}
		if(!config.contains("anwbDiscordMeldingenChannelId")) {
			config.set("anwbDiscordMeldingenChannelId","0000000000000000");
		}
		if(!config.contains("brandweerDiscordMeldingenChannelId")) {
			config.set("brandweerDiscordMeldingenChannelId","0000000000000000");
		}
		if(!config.contains("politieDiscordRadioChannelId")) {
			config.set("politieDiscordRadioChannelId","0000000000000000");
		}
		if(!config.contains("ambulanceDiscordRadioChannelId")) {
			config.set("ambulanceDiscordRadioChannelId","0000000000000000");
		}
		if(!config.contains("brandweerDiscordRadioChannelId")) {
			config.set("brandweerDiscordRadioChannelId","0000000000000000");
		}
		if(!config.contains("anwbDiscordRadioChannelId")) {
			config.set("anwbDiscordRadioChannelId","0000000000000000");
		}
		if(!config.contains("politieDiscordAanhoudingsBevelenChannelId")) {
			config.set("politieDiscordAanhoudingsBevelenChannelId","0000000000000000");
		}
		if(!config.contains("politieDiscordNotitieChannelId")) {
			config.set("politieDiscordNotitieChannelId","0000000000000000");
		}
		
		try {
			config.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}

	
	public static FileConfiguration readConfig(Plugin pl) {
		
		File res = new File(pl.getDataFolder(), "config.yml");

		if(res.exists()) {
			if(writeCheck(res)) {
				FileConfiguration resconf = YamlConfiguration.loadConfiguration(res);
				return resconf;
			}else {
				return null;
			}
		} else {
			return null;
		}
		
		
		
	}
	
	public  void reload(FileConfiguration conf) {
		try {
			conf.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		YamlConfiguration.loadConfiguration(file);
	}
	
	public void save(FileConfiguration conf) {
		try {
			conf.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		
	
	
	public static boolean writeCheck(File file){
	     boolean f = true;
	     if(!file.exists()) try
	     {
	       if(!file.getParentFile().exists() && !file.getParentFile().mkdirs())
	       {
	         System.err.println(
	             "writeCheck error: Cannot create parent file " + file.getParentFile().getAbsolutePath());
	         f = false;
	       }
	       if(!file.createNewFile())
	       {
	         System.err.println("writeCheck error: Cannot create file " + file.getAbsolutePath());
	         f = false;
	       }
	     }
	     catch(IOException e)
	     {
	       e.printStackTrace();
	       f = false;
	     }
	     if(!file.canWrite())
	       f = false;
	     return f;
	}
	
	
	public static FileConfiguration readDiscordConfig(Plugin pl) {
		
		File res = new File(pl.getDataFolder(), "config.yml");

		if(res.exists()) {
			if(writeCheck(res)) {
				FileConfiguration resconf = YamlConfiguration.loadConfiguration(res);
				return resconf;
			}else {
				return null;
			}
		} else {
			return null;
		}
		
		
		
	}
	
	public String getPolitieChannel() {
		return readDiscordConfig(p).getString("politieDiscordMeldingenChannelId");
	}
	
	public String getAmbulanceChannel() {
		return readDiscordConfig(p).getString("ambulanceDiscordMeldingenChannelId");
	}
	
	public String getBrandweerChannel() {
		return readDiscordConfig(p).getString("brandweerDiscordMeldingenChannelId");
	}
	
	public String getAnwbChannel() {
		return readDiscordConfig(p).getString("anwbDiscordMeldingenChannelId");
	}
	
	public String getPolitieRadioChannel() {
		return readDiscordConfig(p).getString("politieDiscordRadioChannelId");
	}
	
	public String getAmbulanceRadioChannel() {
		return readDiscordConfig(p).getString("ambulanceDiscordRadioChannelId");
	}
	
	public String getBrandweerRadioChannel() {
		return readDiscordConfig(p).getString("brandweerDiscordRadioChannelId");
	}
	
	public String getAnwbRadioChannel() {
		return readDiscordConfig(p).getString("anwbDiscordRadioChannelId");
	}
	
	public String getTextChannel() {
		return readDiscordConfig(p).getString("textChannel");
	}
	
	public String getPolitieAanhoudingsBevelen() {
		return readDiscordConfig(p).getString("politieDiscordAanhoudingsBevelenChannelId");
	}
	
	public String getPolitieNotitieChannel() {
		return readDiscordConfig(p).getString("politieDiscordNotitieChannelId");
	}
	
	public String getReportChannel() {
		return readDiscordConfig(p).getString("reportChannel");
	}

}
