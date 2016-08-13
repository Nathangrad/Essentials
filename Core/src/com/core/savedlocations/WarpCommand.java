package com.core.savedlocations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class WarpCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "�cUsage: /warp";

	public WarpCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.warp")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (args.length == 0) {
			StringBuilder builder = new StringBuilder();
			builder.append("(");
			for (String warp : essentials.getConfig().getStringList("warplist")) {
				builder.append(", " + warp);
			}
			player.sendMessage(essentials.prefix + "�eWarps:\n"
					+ builder.toString().replace("(, ", "").replace("(", ""));
		} else {
			String warpname = args[0].toString().toUpperCase();
			try {
				player.teleport(new Location(Bukkit.getServer().getWorld(
						(String) essentials.getConfig().get(
								"warp." + warpname + ".w")), Double
						.parseDouble(essentials.getConfig()
								.get("warp." + warpname + ".x").toString()),
						Double.parseDouble(essentials.getConfig()
								.get("warp." + warpname + ".y").toString()),
						Double.parseDouble(essentials.getConfig()
								.get("warp." + warpname + ".z").toString()),
						Float.parseFloat(essentials.getConfig()
								.get("warp." + warpname + ".yaw").toString()),
						Float.parseFloat(essentials.getConfig()
								.get("warp." + warpname + ".pitch").toString())));
				player.sendMessage(essentials.prefix
						+ "�aSuccessfully teleported to �eWarp " + warpname);
			} catch (NullPointerException e) {
				StringBuilder builder = new StringBuilder();
				builder.append("(");
				for (String warp : essentials.getConfig().getStringList(
						"warplist")) {
					builder.append(", " + warp);
				}
				player.sendMessage(essentials.prefix
						+ "�eWarps:\n"
						+ builder.toString().replace("(, ", "")
								.replace("(", ""));
			} catch (NumberFormatException e) {
				StringBuilder builder = new StringBuilder();
				builder.append("(");
				for (String warp : essentials.getConfig().getStringList(
						"warplist")) {
					builder.append(", " + warp);
				}
				player.sendMessage(essentials.prefix
						+ "�eWarps:\n"
						+ builder.toString().replace("(, ", "")
								.replace("(", ""));
			} catch (IllegalArgumentException e) {
				StringBuilder builder = new StringBuilder();
				builder.append("(");
				for (String warp : essentials.getConfig().getStringList(
						"warplist")) {
					builder.append(", " + warp);
				}
				player.sendMessage(essentials.prefix
						+ "�eWarps:\n"
						+ builder.toString().replace("(, ", "")
								.replace("(", ""));
			}
		}
		return true;
	}
}