package com.above.essentials.main;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.above.essentials.listeners.*;

public class Essentials extends JavaPlugin implements Listener {

	public String prefix = "";
	public static String apiPrefix = "";
	public String permerror = "§cYou don't have permission to execute this command";
	public String playerror = "§cThis player doesn't exist or isn't currently online";
	public String conserror = "This command cannot be executed from the console";
	public static Permission perms = null;
	public static Chat chat = null;

	@Override
	public void onEnable() {
		System.out.println("TEST");
		setupPermissions();
		setupChat();
		saveConfig();
		try {
			prefix = getConfig().get("configuration.prefix").toString().replace("&", "§") + " ";
		} catch (NullPointerException npe) {
			getConfig().set("configuration.prefix", "&7[&aAbove&7]");
			saveConfig();
			prefix = getConfig().get("configuration.prefix").toString().replace("&", "§") + " ";
		}
		Essentials.apiPrefix = getConfig().get("configuration.prefix").toString().replace("&", "§") + " ";
		Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new AFKListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SpawnListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BackListener(), this);
		new CommandHandler(this);
		getLogger().info("Enabled");
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		return chat != null;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
}
