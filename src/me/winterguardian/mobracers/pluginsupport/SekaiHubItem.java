package me.winterguardian.mobracers.pluginsupport;

import me.winterguardian.core.game.Game;
import me.winterguardian.core.inventorygui.GUIItem;
import me.winterguardian.mobracers.MobRacersGame;
import me.winterguardian.mobracers.state.game.GameState;
import me.winterguardian.mobracers.state.lobby.ArenaSelectionState;
import me.winterguardian.mobracers.state.lobby.VehicleSelectionState;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-01-04.
 */
public class SekaiHubItem extends GUIItem
{
	private MobRacersGame game;

	public SekaiHubItem(MobRacersGame game)
	{
		super(14);
		this.game = game;
	}

	@Override
	public void click(Player player, ClickType click)
	{
		if(click.isLeftClick())
		{
			player.performCommand("mobracers join");
			return;
		}

		player.performCommand("mobracers stats");
	}

	@Override
	public ItemStack getItemStack(Player player)
	{
		ItemStack saddle = new ItemStack(Material.SADDLE, 1);
		ItemMeta meta = saddle.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE.toString()+ChatColor.BOLD.toString()+"Mob"+ChatColor.BLUE.toString()+ChatColor.BOLD.toString()
				+"Racers");
		List<String> lore = new ArrayList<>();

		if(game == null)
			return saddle;

		int players = game.getPlayers().size();
		lore.add(ChatColor.YELLOW.toString() + players + " player" + (players > 1 ? "s" : ""));

		if(game.getState() instanceof GameState)
		{
			lore.add(ChatColor.WHITE.toString()+"In race");
			lore.add(ChatColor.YELLOW.toString()+ ((GameState)game.getState()).getArena().getName());
		}
		else if(game.getState() instanceof VehicleSelectionState)
		{
			VehicleSelectionState vehicleSelectionState = (VehicleSelectionState)game.getState();

			lore.add(ChatColor.WHITE.toString()+"Choice of Vehicle");
			lore.add(ChatColor.YELLOW.toString()+ vehicleSelectionState.getArena().getName());
		}
		else if(game.getState() instanceof ArenaSelectionState)
		{
			lore.add(ChatColor.WHITE.toString()+"Choice of Arena");
		}
		else
		{
			lore.add(ChatColor.WHITE.toString()+"Waiting");
		}

		lore.add(" ");
		lore.add(ChatColor.GREEN.toString()+"Left click to play");
		lore.add(ChatColor.YELLOW.toString()+"Right click for stats");

		meta.setLore(lore);

		saddle.setItemMeta(meta);

		return saddle;
	}
}
