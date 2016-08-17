package com.above.essentials.listeners;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.above.core.player.EconomyAPI;
import com.above.essentials.main.Essentials;
import com.above.essentials.savedlocations.SpawnCommand;
import com.above.essentials.utils.ChatFormatting;

public class SpawnListener implements Listener {

	public static Chat chat;
	public static Permission perms;
	public static Essentials economy;

	public SpawnListener(Essentials essentials) {
		chat = Essentials.chat;
		perms = Essentials.perms;
		economy = essentials;
	}

	@EventHandler
	public void onPlayerJoining(PlayerJoinEvent joinEvent) {
		joinEvent.setJoinMessage("§7[§a+§7] "
				+ ChatFormatting.amperSand(chat.getGroupPrefix(joinEvent.getPlayer().getWorld(),
						perms.getPrimaryGroup(joinEvent.getPlayer()))).replace("[", "").replace("]", "")
				+ "§7" + joinEvent.getPlayer().getName());
		if (!joinEvent.getPlayer().hasPlayedBefore()) {
			EconomyAPI econ = new EconomyAPI();
			econ.setup(joinEvent.getPlayer(), (double) 0);
			SpawnCommand.sendToSpawn(joinEvent.getPlayer());
		}
	}

	@EventHandler
	public void onPlayerLeaving(PlayerQuitEvent quitEvent) {
		quitEvent.setQuitMessage("§7[§c-§7] "
				+ ChatFormatting.amperSand(chat.getGroupPrefix(quitEvent.getPlayer().getWorld(),
						perms.getPrimaryGroup(quitEvent.getPlayer()))).replace("[", "").replace("]", "")
				+ "§7" + quitEvent.getPlayer().getName());
	}
}
