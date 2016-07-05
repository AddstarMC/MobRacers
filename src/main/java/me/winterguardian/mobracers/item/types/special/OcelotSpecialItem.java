package me.winterguardian.mobracers.item.types.special;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.winterguardian.mobracers.CourseMessage;
import me.winterguardian.mobracers.item.ItemResult;
import me.winterguardian.mobracers.item.types.SpecialItem;
import me.winterguardian.mobracers.state.game.GameState;
import me.winterguardian.mobracers.vehicle.Vehicle;

public class OcelotSpecialItem extends SpecialItem
{
	@Override
	public String getName()
	{
		return CourseMessage.ITEM_SPECIAL_OCELOT.toString();
	}
	
	@Override
	public ItemStack getItemStack()
	{
		ItemStack item = new ItemStack(Material.RAW_FISH, 5);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET.toString()+ChatColor.DARK_AQUA.toString()+ CourseMessage.ITEM_SPECIAL_OCELOT.toString());
		item.setItemMeta(itemMeta);
		return item;
	}

	@Override
	public ItemResult onUse(Player player, Vehicle vehicle, GameState game)
	{
		vehicle.getEntity().setVelocity(new Vector(Math.sin(Math.toRadians(-player.getLocation().getYaw())) * 2, 0.2, Math.cos(Math.toRadians(-player.getLocation().getYaw())) * 2));

		int amount = player.getItemInHand().getAmount();
		
		if(amount <= 1)
			return ItemResult.DISCARD;
		else
		{
			player.getItemInHand().setAmount(amount - 1);
			return ItemResult.KEEP;
		}
	}

	@Override
	public void cancel()
	{
		
	}
}
