package me.winterguardian.mobracers.pluginsupport;

import me.winterguardian.core.game.Game;
import me.winterguardian.core.inventorygui.GUIItem;
import me.winterguardian.mobracers.MobRacersGame;
import me.winterguardian.mobracers.state.game.GameState;
import me.winterguardian.mobracers.state.lobby.ArenaSelectionState;
import me.winterguardian.mobracers.state.lobby.VehicleSelectionState;
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
		meta.setDisplayName("ßfßlMobß2ßlRacers");
		List<String> lore = new ArrayList<>();

		if(game == null)
			return saddle;

		int players = game.getPlayers().size();
		lore.add("ße" + players + " joueur" + (players > 1 ? "s" : ""));

		if(game.getState() instanceof GameState)
		{
			lore.add("ßfEn course sur");
			lore.add("ße" + ((GameState)game.getState()).getArena().getName());
		}
		else if(game.getState() instanceof VehicleSelectionState)
		{
			VehicleSelectionState vehicleSelectionState = (VehicleSelectionState)game.getState();

			lore.add("ßfChoix du v√©hicule");
			lore.add("ße" + vehicleSelectionState.getArena().getName());
		}
		else if(game.getState() instanceof ArenaSelectionState)
		{
			lore.add("ßfChoix de l'ar√®ne");
		}
		else
		{
			lore.add("ßfEn attente");
		}

		lore.add(" ");
		lore.add("ßaClic gauche pour jouer");
		lore.add("ßeClic droit pour les stats");

		meta.setLore(lore);

		saddle.setItemMeta(meta);

		return saddle;
	}
}
