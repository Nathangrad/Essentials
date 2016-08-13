package com.core.basiccommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class RulesCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /rules";

	public RulesCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.rules")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		StringBuilder builder = new StringBuilder();
		int counter = 1;
		for (String value : essentials.getConfig().getStringList(
				"configuration.rules")) {
			builder.append("§7" + counter + ". §f" + value.replace("&", "§")
					+ "\n");
			counter++;
		}
		player.sendMessage(essentials.prefix + "§aRules:\n"
				+ builder.toString());
		return true;
	}
}