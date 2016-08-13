package com.core.basiccommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class FlyCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /fly §o<Player>";

	public FlyCommand(Essentials e) {
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
		if (args.length == 0) {
			if (!PermissionCheck.check(player, "core.fly")) {
				player.sendMessage(essentials.prefix + essentials.permerror);
				return true;
			}
			player.sendMessage(essentials.prefix
					+ (player.isFlying() ? "§c" : "§a") + "Flying has been "
					+ (player.isFlying() ? "disabled" : "enabled") + " for you");
			player.setAllowFlight(!(player.isFlying()));
		} else if (args.length == 1) {
			if (!PermissionCheck.check(player, "core.flyothers")) {
				player.sendMessage(essentials.prefix + essentials.permerror);
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target != null) {
				target.sendMessage(essentials.prefix
						+ (target.isFlying() ? "§c" : "§a")
						+ "Flying has been "
						+ (target.isFlying() ? "disabled" : "enabled")
						+ " for you");
				player.sendMessage(essentials.prefix
						+ (target.isFlying() ? "§c" : "§a")
						+ "Flying has been "
						+ (target.isFlying() ? "disabled" : "enabled")
						+ " for §e" + target.getName());
				target.setAllowFlight(!(target.isFlying()));
			} else {
				player.sendMessage(essentials.prefix + essentials.playerror);
				return true;
			}
		} else {
			player.sendMessage(essentials.prefix + usage);
			return true;
		}
		return true;
	}
}