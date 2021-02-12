package be.overcast.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Addpermission implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		Player p = (Player)sender;
		if(p instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("permissionsadd")) {
				if(p.hasPermission("minetopiareborn.permissions") && p.isOp()) {
					if(args.length == 0) {
						p.sendMessage("§4Je moet een permissie meegeven!");
					} else {
						Bukkit.dispatchCommand(p, "pex group Slager add " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Bakker add " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Houthakker add " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Smid add " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Boer add " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Visser add " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Mijner add " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Imker add " + args[0]);

						p.sendMessage("§aSuccesvol de permission " + args[0] + " §atoegevoegd aan alle groepen!");
					}
				} else {
					p.sendMessage("§4Je hebt geen toegang tot deze commando!");
				}
			} else if(cmd.getName().equalsIgnoreCase("permissionsremove")) {
				if(p.hasPermission("minetopiareborn.permissions") && p.isOp()) {
					if(args.length == 0) {
						p.sendMessage("§4Je moet een permissie meegeven!");
					} else {
						Bukkit.dispatchCommand(p, "pex group Slager remove " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Bakker remove " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Houthakker remove " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Smid remove " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Boer remove " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Visser remove " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Mijner remove " + args[0]);
						Bukkit.dispatchCommand(p, "pex group Imker remove " + args[0]);

						p.sendMessage("§aSuccesvol de permission " + args[0] + " §atoegevoegd aan alle groepen!");
					}
				} else {
					p.sendMessage("§4Je hebt geen toegang tot deze commando!");
				}
			}
		}
		
		return true;
	}

	
	
}
