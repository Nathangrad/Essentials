package com.core.teleportation;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class TopCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "�cUsage: /top";

	public TopCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.top")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		for (int y = 255; y > 0; y--) {
			if (new Location(player.getWorld(), player.getLocation()
					.getBlockX(), y, player.getLocation().getBlockZ())
					.getBlock().getType() != null
					&& new Location(player.getWorld(), player.getLocation()
							.getBlockX(), y, player.getLocation().getBlockZ())
							.getBlock().getType() != Material.AIR) {
				player.teleport(new Location(player.getWorld(), player
						.getLocation().getBlockX(), y + 1, player.getLocation()
						.getBlockZ()));
				player.sendMessage(essentials.prefix
						+ "�aTeleported to the highest location");
				return true;
			}
		}
		return true;
	}
}