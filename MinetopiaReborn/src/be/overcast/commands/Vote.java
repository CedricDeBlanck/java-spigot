package be.overcast.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		Player p = (Player)sender;
		
		if(p instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("vote")) {
				p.sendMessage("=====================================================");
				p.sendMessage("       §bhttps://minecraft-mp.com/server-s278423");
				p.sendMessage("       §ahttps://topg.org/Minecraft/in-623197");
				p.sendMessage("       §chttps://minecraftservers.org/server/605076");
				p.sendMessage("=====================================================");

			}
		}
		
		return true;
	}

}
