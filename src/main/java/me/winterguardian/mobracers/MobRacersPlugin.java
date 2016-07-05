package me.winterguardian.mobracers;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import me.winterguardian.core.Core;
import me.winterguardian.mobracers.item.types.CloudItem;
import me.winterguardian.mobracers.item.types.ShieldItem;
import me.winterguardian.mobracers.pluginsupport.MobRacersHook;
import me.winterguardian.mobracers.pluginsupport.VaultSupport;
import me.winterguardian.mobracers.stats.ArenaStats;

import me.winterguardian.mobracers.stats.CoursePurchase;
import me.winterguardian.mobracers.stats.CourseStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MobRacersPlugin extends JavaPlugin
{
	public static final Permission PLAY = new Permission("MobRacers.play", "MobRacers regular access", PermissionDefault.TRUE);
	public static final Permission VOTE = new Permission("MobRacers.vote", "MobRacers permission to vote for an arena", PermissionDefault.TRUE);
	public static final Permission VIP = new Permission("MobRacers.vip", "MobRacers complete Vip access", PermissionDefault.OP);
	public static final Permission STAFF = new Permission("MobRacers.staff", "MobRacers Mod/Staff access", PermissionDefault.OP);
	public static final Permission CMD_PROTECT_BYPASS = new Permission("MobRacers.command-protect-bypass", "MobRacers permission to type any command in-game", PermissionDefault.OP);
	public static final Permission AUTOJOIN_BYPASS = new Permission("MobRacers.autojoin-bypass", "MobRacers permission to bypass autojoin/teleportjoin feature.", PermissionDefault.FALSE);
	public static final Permission ADMIN = new Permission("MobRacers.admin", "MobRacers Admin/Owner access", PermissionDefault.OP);
	public static final Permission ALL_UNLOCKED = new Permission("MobRacers.unlock-all", "Access to all vehicles/discs without unlocking them", PermissionDefault.FALSE);
	
	private static YamlConfiguration messages;
	private static MobRacersGame game;
	
	private static MobRacersHook hook;
	private static VaultSupport vault;
	
	public void onEnable()
	{
		try
		{
			this.saveDefaultConfig();
			if (!new File(getDataFolder(), "config.yml").exists()){
				if(this.getConfig().contains("base-messages")){
					String langpref = this.getConfig().getString("base-messages");
					this.saveLangResource("config",langpref,true);
					this.saveLangResource("lang",langpref,true);
				}
			}
			if(!new File(getDataFolder(), "purchases.yml").exists())
				this.saveResource("purchases.yml", false);
		}
		catch(Exception e)
		{
			new Exception("MobRacers.jar is CORRUPTED please redownload from spigot.", e).printStackTrace();
		}

		MobRacersConfig baseConfig = new MobRacersConfig(new File(getDataFolder(), "config.yml"));
		baseConfig.load();

		messages = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "messages.yml"));

		Core.getCustomEntityManager().enable(this);
		Core.getBungeeMessager().enable(this);
        Core.getWand().enable(this, STAFF, CourseMessage.WAND);

		game = new MobRacersGame(this, baseConfig);
		game.onEnable();
		game.setOpen(true);

		CoursePurchase.init();
		CloudItem.init(this);
		ShieldItem.init(this);
		
		STAFF.addParent(ADMIN, true);
		VIP.addParent(STAFF, true);
		PLAY.addParent(VIP, true);
		VOTE.addParent(PLAY, true);
		CMD_PROTECT_BYPASS.addParent(ADMIN, true);

		if(((MobRacersConfig)game.getConfig()).enableStats())
		{
			MobRacersConfig config = (MobRacersConfig)game.getConfig();
			if(config.isSqlEnabled())
				Core.getUserDatasManager().enableDB(this, config.getSqlDriver(), config.getSqlURL(), config.getSqlUser(), config.getSqlPassword(), CourseStats.getTables());
			else
				Core.getUserDatasManager().enableYaml(this);

			ArenaStats.init();

			if(((MobRacersConfig) game.getConfig()).enableVault() && getServer().getPluginManager().getPlugin("Vault") != null)
			{
				vault = new VaultSupport();
				if(vault.isEnabled())
					getLogger().info(ChatColor.GREEN.toString()+"MobRacers has detected Vault and is going to use it as economy plugin");
				else
					vault = null;
			}
			else
				vault = null;
		}
		
		try
		{
			getClass().getClassLoader().loadClass("me.clip.placeholderapi.PlaceholderHook");
			getClass().getClassLoader().loadClass("me.clip.placeholderapi.PlaceholderAPI");
	      
			hook = new MobRacersHook(game);
			if(hook.register(this))
				getLogger().info(ChatColor.GREEN.toString()+"MobRacers Hook have been properly registred to PlaceHoldersAPI");
			else
				hook = null;
	    }
	    catch (Throwable t)
	    {
	    	hook = null;
	    }
	}
	
	public void onDisable()
	{
		if (hook != null)
			try
			{
				if (hook.unregister(this))
					getLogger().info("MobRacers Hook have been properly unregistred to PlaceHoldersAPI");
				hook = null;
			}
			catch (Throwable localThrowable)
			{
				hook = null;
			}
		
		game.onDisable();
		game = null;
		Core.disable(this);

		messages = null;
		vault = null;

		HandlerList.unregisterAll(this);
		Bukkit.getScheduler().cancelTasks(this);
	}
	
	public static MobRacersGame getGame()
	{
		return game;
	}

	public static YamlConfiguration getMessages()
	{
		return messages;
	}

	public static Plugin getPlugin()
	{
		return game.getPlugin();
	}
	
	public static VaultSupport getVault()
	{
		return vault;
	}

	/**
	 *
	 * @param resource the base name minus any file extension of the resource ie "config" or "messages"
	 * @param lang the language extension
	 * @param replace if true the resource is reloaded from the jar
     */
	public void saveLangResource(String resource, String lang, boolean replace){
		String resourceINPath = resource + lang + ".yml";
		String resourcePath = resource + ".yml";
		if (resourceINPath == null || resourceINPath.equals("")) {
			throw new IllegalArgumentException("ResourcePath cannot be null or empty");
		}

		resourceINPath = resourceINPath.replace('\\', '/');
		InputStream in = getResource(resourceINPath);
		if (in == null) {
			throw new IllegalArgumentException("The embedded resource '" + resourceINPath + "' cannot be found in " + getFile());
		}

		File outFile = new File(getDataFolder(), resourcePath);
		int lastIndex = resourcePath.lastIndexOf('/');
		File outDir = new File(getDataFolder(), resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));

		if (!outDir.exists()) {
			outDir.mkdirs();
		}
		try {
			if (!outFile.exists() || replace) {
				OutputStream out = new FileOutputStream(outFile);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.close();
				in.close();
			} else {
				getLogger().warning("Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
			}
		} catch (IOException ex) {
			getLogger().severe("Could not save " + outFile.getName() + " to " + outFile);
			ex.printStackTrace();
		}


	}


}
