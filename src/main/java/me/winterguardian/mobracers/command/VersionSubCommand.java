package me.winterguardian.mobracers.command;

import java.util.Arrays;

import me.winterguardian.core.command.AsyncSubCommand;
import me.winterguardian.core.json.JsonUtil;
import me.winterguardian.core.util.WebCommunicationUtil;
import me.winterguardian.mobracers.CourseMessage;
import me.winterguardian.mobracers.MobRacersPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.collect.ImmutableMap;

public class VersionSubCommand extends AsyncSubCommand
{
	private static String version = null;
	
	public VersionSubCommand(Plugin plugin)
	{
		super(plugin, "version", Arrays.asList("ver", "icanhasbukkit", "versions", "plugin", "about", "author"), null, null, ChatColor.RED.toString() + CourseMessage.COMMAND_USAGE + ": �f/mobracers version");
	}

	@Override
	protected void onAsyncSubCommand(CommandSender sender, String label, String[] args)
	{
		String version = getLatestVersion();
		if(version != null)
			if(isLatest(version, MobRacersPlugin.getPlugin()))
				version = ChatColor.GREEN.toString()+ "You are on the latest version";
			else
				version = ChatColor.YELLOW.toString()+ "Version " + version + " is available on Spigot !";
		else
			version = ChatColor.RED.toString()+ "Failed to find latest version";
		
		
		sender.sendMessage(ChatColor.WHITE.toString()+ ChatColor.BOLD.toString()+ "Mob" + ChatColor.DARK_GREEN.toString()+ ChatColor.BOLD.toString()+
				"Racers "+ChatColor.WHITE.toString()+ ChatColor.BOLD.toString()+"> " + ChatColor.WHITE.toString()+ "Plugin designed by WinterGuardian and now Maintained and updated by MajorProbes ");
		sender.sendMessage(ChatColor.GOLD.toString()+ "Version: "+ ChatColor.RESET.toString()+ MobRacersPlugin.getPlugin().getDescription().getVersion());
		sender.sendMessage(version);
		
		if(sender instanceof Player)
			JsonUtil.sendJsonMessage((Player) sender, JsonUtil.toJson(ChatColor.DARK_AQUA.toString()+"Click me to visit the spigot MobRacers page.", null, null, "open_url", "\"https://www.spigotmc.org/resources/mobracers-mariokart-on-mobs-now-free-1-8-8.20626//\""));
	
	}

	public static String getLatestVersion()
	{
		try
		{
			return version = WebCommunicationUtil.post("http://www.spigotmc.org/api/general.php", ImmutableMap.of("resource", "20626"));
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public static String getLastVersionFetch()
	{
		return version;
	}
	
	public static boolean isLatest(String version, Plugin plugin)
	{
		if(version == null)
			return true;
		
		return version.compareToIgnoreCase(plugin.getDescription().getVersion()) <= 0;
	}
}
