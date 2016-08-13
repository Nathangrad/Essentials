package com.core.savedlocations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class HomeCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "�cUsage: /home";

	public HomeCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.home")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (essentials.getConfig().get("homes." + player.getUniqueId() + ".w") != null) {
			player.teleport(new Location(Bukkit.getServer().getWorld(
					(String) essentials.getConfig().get(
							"homes." + player.getUniqueId() + ".w")), Double
					.parseDouble(essentials.getConfig()
							.get("homes." + player.getUniqueId() + ".x")
							.toString()), Double.parseDouble(essentials
					.getConfig().get("homes." + player.getUniqueId() + ".y")
					.toString()), Double.parseDouble(essentials.getConfig()
					.get("homes." + player.getUniqueId() + ".z").toString()),
					Float.parseFloat(essentials.getConfig()
							.get("homes." + player.getUniqueId() + ".yaw")
							.toString()), Float.parseFloat(essentials
							.getConfig()
							.get("homes." + player.getUniqueId() + ".pitch")
							.toString())));
			player.sendMessage(essentials.prefix
					+ "�aSuccessfully teleported to your home");
		} else {
			player.sendMessage(essentials.prefix
					+ "�cCould not teleport you home. "
					+ "Set it first using �e/sethome");
		}
		return true;
	}
}