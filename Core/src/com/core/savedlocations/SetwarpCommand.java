package com.core.savedlocations;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class SetwarpCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /setwarp §o<Warp>";

	public SetwarpCommand(Essentials e) {
		essentials = e;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String comandlbl, String[] args) {
		if (!(sender instanceof Player)) {
			essentials.getLogger().info(essentials.conserror);
			return true;
		}
		Player player = (Player) sender;
		if (!PermissionCheck.check(player, "core.setwarp")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (args.length == 0) {
			player.sendMessage(essentials.prefix + usage);
			return true;
		} else {
			Location l = player.getLocation();
			String path = "warp." + args[0].toString().toUpperCase() + ".";
			if (essentials.getConfig().get(path + "x") == null) {
				List<String> warps = essentials.getConfig().getStringList(
						"warplist");
				warps.add(args[0].toString().toUpperCase());
				essentials.getConfig().set("warplist", warps);
			}
			essentials.getConfig().set(path + "w", player.getWorld().getName());
			essentials.getConfig().set(path + "x", l.getX());
			essentials.getConfig().set(path + "y", l.getY());
			essentials.getConfig().set(path + "z", l.getZ());
			essentials.getConfig().set(path + "yaw", l.getYaw());
			essentials.getConfig().set(path + "pitch", l.getPitch());
			essentials.saveConfig();
			player.sendMessage(essentials.prefix + "§aSuccessfully set §eWarp "
					+ args[0].toString().toUpperCase());
		}
		return true;
	}
}
