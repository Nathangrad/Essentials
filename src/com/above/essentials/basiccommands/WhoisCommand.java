package com.above.essentials.basiccommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.above.core.exceptions.InvalidDataColumnException;
import com.above.core.player.EconomyAPI;
import com.above.essentials.main.Essentials;
import com.above.essentials.utils.AFKUtil;
import com.above.essentials.utils.MuteUtil;
import com.above.essentials.utils.PermissionCheck;

public class WhoisCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /whois <Player>";

	public WhoisCommand(Essentials e) {
		essentials = e;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String comandlbl, String[] args) {
		if (!(sender instanceof Player)) {
			essentials.getLogger().info(essentials.conserror);
			return true;
		}
		Player player = (Player) sender;
		if (!PermissionCheck.check(player, "core.whois")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (args.length == 0) {
			player.sendMessage(essentials.prefix + usage);
			return true;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
		EconomyAPI econ = new EconomyAPI();
		if (target != null) {
			try {
				player.sendMessage(essentials.prefix + "§eDisplaying information about " + target.getName() + "\n"
						+ "§a - UUID: §7" + target.getUniqueId().toString() + "\n" + "§a - Health: §7"
						+ target.getHealth() + "/20" + "\n§a - Hunger: §7" + target.getFoodLevel() + "/20"
						+ "\n§a - Balance: §7" + econ.getBalanceString(target) + "\n§a - Location: §7("
						+ target.getWorld().getName() + ", " + target.getLocation().getBlockX() + ", "
						+ target.getLocation().getBlockY() + ", " + target.getLocation().getBlockZ() + ")"
						+ "\n§a - IP Address: §7" + target.getAddress().getHostName() + "\n§a - Gamemode: §7"
						+ target.getGameMode() + "\n§a - OP: §7" + target.isOp() + "\n§a - AFK: §7"
						+ AFKUtil.isAFK(target) + "\n§a - Muted: §7" + MuteUtil.isMuted(target));
			} catch (NumberFormatException | InvalidDataColumnException e) {
				e.printStackTrace();
			}
		} else {
			player.sendMessage(essentials.prefix + essentials.playerror);
			return true;
		}
		return true;
	}
}