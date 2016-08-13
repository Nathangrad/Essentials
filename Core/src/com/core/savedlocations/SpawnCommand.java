package com.core.savedlocations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class SpawnCommand implements CommandExecutor {

	static Essentials essentials;
	String usage = "§cUsage: /spawn";

	public SpawnCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.spawn")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		sendToSpawn(player);
		player.sendMessage(essentials.prefix
				+ "§aSuccessfully teleported to spawn");
		return true;
	}

	public static void sendToSpawn(Player player) {
		try {
			player.teleport(new Location(Bukkit.getServer().getWorld(
					(String) essentials.getConfig().get("spawn.w")), Double
					.parseDouble(essentials.getConfig().get("spawn.x")
							.toString()), Double.parseDouble(essentials
					.getConfig().get("spawn.y").toString()), Double
					.parseDouble(essentials.getConfig().get("spawn.z")
							.toString()), Float.parseFloat(essentials
					.getConfig().get("spawn.yaw").toString()), Float
					.parseFloat(essentials.getConfig().get("spawn.pitch")
							.toString())));
		} catch (IllegalArgumentException iae) {
			essentials.getLogger().info("Spawn must be set!");
		}
	}
}