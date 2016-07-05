package me.winterguardian.mobracers.vehicle.types;

import java.util.ArrayList;
import java.util.Arrays;

import me.winterguardian.core.util.SoundEffect;
import me.winterguardian.mobracers.CourseMessage;
import me.winterguardian.mobracers.item.Item;
import me.winterguardian.mobracers.item.ItemType;
import me.winterguardian.mobracers.item.types.SugarItem;
import me.winterguardian.mobracers.item.types.WallItem;
import me.winterguardian.mobracers.item.types.WallItem.WallBlock;
import me.winterguardian.mobracers.item.types.special.CaveSpiderSpecialItem;
import me.winterguardian.mobracers.stats.CourseAchievement;
import me.winterguardian.mobracers.vehicle.VehicleGUIItem;
import me.winterguardian.mobracers.vehicle.VehicleType;
import me.winterguardian.mobracers.vehicle.WinnableVehicle;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CaveSpiderVehicle extends WinnableVehicle
{
	@Override
	protected EntityType getEntityType()
	{
		return EntityType.CAVE_SPIDER;
	}

	@Override
	public VehicleType getType()
	{
		return VehicleType.CAVE_SPIDER;
	}

	@Override
	public boolean canChoose(Player p)
	{
		return getWinAchievement().isComplete(p);
	}
	
	@Override
	public String getName()
	{
		return CourseMessage.VEHICLE_CAVESPIDER_NAME.toString();
	}

	@Override
	public String getDescription()
	{
		return CourseMessage.VEHICLE_CAVESPIDER_DESC.toString();
	}

	@Override
	public CourseAchievement getWinAchievement()
	{
		return CourseAchievement.SPIDER_WIN;
	}
	
	@Override
	public Item getItem(ItemType type)
	{
		switch(type)
		{
		case SUGAR:
			return new SugarItem(new SoundEffect(Sound.ENTITY_SPIDER_AMBIENT, 1, 1.2f));
		case WALL:
			return new WallItem(Arrays.asList(new WallBlock(Material.STAINED_CLAY, (byte)11)), new SoundEffect(Sound.ENTITY_SPIDER_AMBIENT, 1, 0.8f));
		case SPECIAL:
			return new CaveSpiderSpecialItem();
		default:
			return type.getDefault();
		
		}
	}

	@Override
	public VehicleGUIItem getGUIItem()
	{
		return new VehicleGUIItem(getType(), 31, Material.MONSTER_EGG, 1, (short)59, ChatColor.DARK_BLUE.toString()+ChatColor.BOLD.toString() + getName(), new ArrayList<String>());
	}
}
