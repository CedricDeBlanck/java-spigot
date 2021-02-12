package files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Data {
	
	public static Plugin p;
	
	public static File cfile;
	static File file;
    static FileConfiguration config;
    static File folder = new File(Bukkit.getPluginManager().getPlugin("MinetopiaReborn").getDataFolder(), "userdata" + File.separator);
    static File df = Bukkit.getPluginManager().getPlugin("MinetopiaReborn").getDataFolder();
 
    public static FileConfiguration create(Player player) {
    	if(!folder.exists()) {
    		try {
    			folder.mkdir();
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
        cfile = new File(df, "userdata" + File.separator + player.getUniqueId().toString() + ".yml");
        if (!df.exists()) df.mkdir();
        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
            } catch(Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + cfile.getName() + "!");
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(cfile);
		
		if(!config.contains("level")) {
			config.set("level", 0);
		}
		if(!config.contains("naam")) {
			config.set("naam", player.getName());
		}
		if(!config.contains("exp")) {
			config.set("exp", 0);
		}
		
		try {
			config.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return config;
    }
 
    public static File getfolder() {
        return folder;
    }
 
    public static File getfile() {
        return cfile;
    }
    
    public Plugin getPlugin() {
    	return p;
    }
 
    public static void load(Player p) {
        cfile = new File(df, "userdata" + File.separator + p.getUniqueId().toString() + ".yml");
        config = YamlConfiguration.loadConfiguration(cfile);
    }
 
    public static FileConfiguration get() {
        return config;
    }
 
    public static void save() {
        try {
            config.save(cfile);
        } catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + cfile.getName() + "!");
        }
    }
    
	public FileConfiguration read(Plugin pl, Player p) {
		
		File res = new File(pl.getDataFolder(), p.getUniqueId().toString()+".yml");
		
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
    
	public static FileConfiguration setup(Plugin p, Player pl) {
		
        file = new File(df, "userdata" + File.separator + pl.getUniqueId().toString() + ".yml");
		
		
		if(writeCheck(file)) {
			try {
				file.createNewFile();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
				
		if(!config.contains("level")) {
			config.set("level", 0);
		}
		if(!config.contains("naam")) {
			config.set("naam", pl.getName());
		}
		if(!config.contains("exp")) {
			config.set("exp", 0);
		}
		
		try {
			config.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return config;
	}

	public int getLevel(Player pl) {
		return read(p,pl).getInt("level");
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
	
	
	
}
