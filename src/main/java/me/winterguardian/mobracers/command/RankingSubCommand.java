package me.winterguardian.mobracers.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.winterguardian.core.command.SubCommand;
import me.winterguardian.core.util.TextUtil;
import me.winterguardian.mobracers.CourseMessage;
import me.winterguardian.mobracers.stats.ArenaStats;
import me.winterguardian.mobracers.stats.ArenaStats.PlayerArenaStats;
import me.winterguardian.mobracers.stats.CourseStats;
import me.winterguardian.mobracers.vehicle.VehicleType;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankingSubCommand extends SubCommand
{
	private static final String[] position = new String[]{
			ChatColor.DARK_RED.toString()+ChatColor.BOLD.toString()+"1",
			ChatColor.RED.toString()+ChatColor.BOLD.toString()+"2",
			ChatColor.GOLD.toString()+ChatColor.BOLD.toString()+"3",
			ChatColor.YELLOW.toString()+ChatColor.BOLD.toString()+"4",
			ChatColor.GREEN.toString()+ChatColor.BOLD.toString()+"5"};
	
	public RankingSubCommand()
	{
		super("ranking", Arrays.asList("arena-stats", 
						"arena-statistic", 
						"statistique-arene", 
						"statistiques-arene", 
						"statistique-arène", 
						"statistiques-arène", 
						"arena-score", 
						"arena-skills", 
						"arenastatistics",
						"arenastats", 
						"arenastatistic", 
						"statistiquearene", 
						"statistiquesarene", 
						"statistiquearène", 
						"statistiquesarène", 
						"arenascore", 
						"arenaskills",
						"top",
						"arenatop",
						"arena-top",
						"classement",
						"classements",
						"palmares",
						"rank"), null, null, ChatColor.RED.toString() + CourseMessage.COMMAND_USAGE + ": "+ChatColor.WHITE.toString()+"/mobracers ranking <arena>");
	}

	@Override
	public boolean onSubCommand(CommandSender sender, String label, String[] args)
	{
		if(!ArenaStats.isReady())
		{
			CourseMessage.COMMAND_ARENASTATS_NOTREADY.say(sender);
			return true;
		}
		
		if(args.length == 0)
		{
			CourseMessage.COMMAND_ARENASTATS_NOARGS.say(sender);
			return false;
		}
		
		ArenaStats stats = ArenaStats.getStats(args[0]);
		
		if(stats == null)
		{
			CourseMessage.COMMAND_ARENASTATS_INVALIDARENA.say(sender);
			return false;
		}
		
		sender.sendMessage(CourseMessage.COMMAND_ARENASTATS_HEADER.toString().replace("<arena>", args[0]));
		for(int i = 0; i < 5; i++)
		{
			if(stats.getPlayer(i) == null)
				break;
			
			PlayerArenaStats pstats = stats.getPlayerStats(stats.getPlayer(i));
			
			if(pstats == null)
				break;
			
			String pos = position[i];
			
			String player = stats.getPlayer(i);
			if(player == null)
				player = "Invalid";
			
			String time = CourseStats.timeToString(pstats.getTime());
			if(time == null)
				time = "---";
			
			String vehicle = VehicleType.valueOf(pstats.getVehicle()).getName();
			if(vehicle == null)
				vehicle = "Invalid";
			
			sender.sendMessage(CourseMessage.COMMAND_ARENASTATS_ENTRY.toString().replace("<pos>", pos).replace("<player>", player).replace("<time>", time).replace("<vehicle>", vehicle));
		}
			
		PlayerArenaStats senderStats;
		
		if(sender instanceof Player && (senderStats = stats.getPlayerStats(sender.getName())) != null)
		{
			String vehicle = VehicleType.valueOf(senderStats.getVehicle()).getName();
			if(vehicle == null)
				vehicle = "Invalid";
			
			sender.sendMessage(CourseMessage.COMMAND_ARENASTATS_SELF.toString().replace("<pos>", TextUtil.toString(stats.getPosition(sender.getName()) + 1)).replace("<total>", TextUtil.toString(stats.getSize())).replace("<time>", CourseStats.timeToString(senderStats.getTime())).replace("<vehicle>", vehicle));
		}
		return true;
		
	}

	@Override
	public List<String> onSubTabComplete(CommandSender sender, String label, String[] args)
	{
		if(!ArenaStats.isReady())
			return null;
		
		if(args.length == 1)
			return TextUtil.getStringsThatStartWith(args[0], new ArrayList<>(ArenaStats.getArenaNames()));
		
		return null;
	}
}
