package com.above.essentials.basiccommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.above.essentials.main.Essentials;
import com.above.essentials.utils.PermissionCheck;

public class BroadcastCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /broadcast <Message>";

	public BroadcastCommand(Essentials e) {
		essentials = e;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String comandlbl, String[] args) {
		if (!(sender instanceof Player)) {
			essentials.getLogger().info(essentials.conserror);
			return true;
		}
		Player player = (Player) sender;
		if (!PermissionCheck.check(player, "core.broadcast")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (args.length != 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < args.length; i++)
				builder.append(args[i].toString().replace("&", "§") + " ");
			Bukkit.broadcastMessage(essentials.prefix + builder.toString());
			return true;
		} else {
			player.sendMessage(essentials.prefix + usage);
		}
		return true;
	}
}