package me.winterguardian.mobracers.item.types.special;



import me.winterguardian.mobracers.CourseMessage;
import me.winterguardian.mobracers.MobRacersPlugin;
import me.winterguardian.mobracers.item.ItemResult;
import me.winterguardian.mobracers.item.types.SpecialItem;
import me.winterguardian.mobracers.state.game.GamePlayerData;
import me.winterguardian.mobracers.state.game.GameState;
import me.winterguardian.mobracers.vehicle.Vehicle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class BlockSpecialItem extends SpecialItem
{
	private static final short[][][][][] house = 
		{	{	//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
				//
				//0 degrees
				//
				//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
			
				{	{{0}, {0}, {2}, {2}, {2}, {3, 2}, {2}, {2}, {0}, {0},},
					{{0}, {2}, {2}, {2}, {2}, {2}, {3, 2}, {2}, {2}, {0},},
					{{0}, {2}, {2}, {2}, {2}, {3, 2}, {3, 2}, {2}, {2}, {0},},
					{{0}, {2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {2}, {0},},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0},},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0},},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0},},
					{{0}, {0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {189}, {189}, {0}, {0}, {0}, {189}, {0}, {0},},
					{{0}, {189}, {189}, {31, 1}, {38, 8}, {0}, {0}, {189}, {189}, {0},},
					{{0}, {189}, {38, 8}, {31, 1}, {31, 1}, {0}, {0}, {31, 1}, {189}, {0},},
					{{0}, {139}, {98}, {67, 2/*ROTATE*/}, {98}, {0}, {0}, {98}, {139}, {0},},
					{{0}, {98}, {47}, {0}, {84}, {0}, {0}, {0}, {98}, {0},},
					{{0}, {67, 0/*ROTATE*/}, {126, 8}, {0}, {0}, {0}, {0}, {85}, {67, 1/*ROTATE*/}, {0},},
					{{0}, {98}, {47}, {0}, {58}, {61, 0 /*ROTATE*/}, {0}, {114, 2/*ROTATE*/}, {98}, {0},},
					{{0}, {139}, {98}, {67, 3/*ROTATE*/}, {98}, {98}, {67, 3/*ROTATE*/}, {98}, {139}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {0}, {0}, {98}, {139}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {0}, {0}, {0}, {98}, {0},},
					{{0}, {102}, {140/*FLOWER*/}, {0}, {0}, {0}, {0}, {72}, {102}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {67, 2/*ROTATE*/}, {0}, {0}, {98}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {98}, {102}, {98}, {139}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {67, 5/*ROTATE*/}, {67, 4/*ROTATE*/}, {98}, {139}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {0}, {0}, {0}, {98}, {0},},
					{{0}, {102}, {0}, {0}, {0}, {0}, {0}, {0}, {102}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {139}, {0}, {0}, {98}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {98}, {102}, {98}, {139}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {170, 4}, {170, 4}, {126, 8}, {0}, {0}, {126, 8}, {170, 4}, {170, 4}, {0},},
					{{170, 4}, {170, 4}, {98}, {98}, {98}, {98}, {98}, {98}, {170, 4}, {170, 4},},
					{{126, 8}, {98}, {0}, {0}, {0}, {0}, {0}, {0}, {98}, {126, 8},},
					{{0}, {98}, {0}, {0}, {0}, {0}, {0}, {0}, {98}, {0},},
					{{126, 8}, {98}, {0}, {0}, {0}, {139}, {0}, {0}, {98}, {126, 8},},
					{{170, 4}, {170, 4}, {98}, {98}, {98}, {98}, {98}, {98}, {170, 4}, {170, 4},},
					{{0}, {170, 4}, {170, 4}, {126, 8}, {0}, {0}, {126, 8}, {170, 4}, {170, 4}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0},},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0},},
					{{126, 0}, {170, 4}, {170, 4}, {0}, {0}, {0}, {0}, {170, 4}, {170, 4}, {126, 0},},
					{{170, 4}, {170, 4}, {0}, {0}, {0}, {0}, {0}, {0}, {170, 4}, {170, 4},},
					{{126, 0}, {170, 4}, {170, 4}, {0}, {0}, {139}, {0}, {170, 4}, {170, 4}, {126, 0},},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0},},
					{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {126, 0}, {126, 0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0},},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0},},
					{{0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {126, 0}, {126, 0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {126, 0}, {126, 0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
			},
			//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
			//
			//90 degrees
			//
			//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
			
			{	
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {5, 1}, {5, 1}, {5, 1}, {2}, {2}, {2}, {0}},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {2}, {2}, {2}},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {3, 2}, {2}, {3, 2}},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {3, 2}, {3, 2}, {2}},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {2}, {2}, {2}},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {2}, {2}, {2}},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {2}, {2}, {2}},
					{{0}, {0}, {5, 1}, {5, 1}, {5, 1}, {2}, {2}, {2}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {139}, {98}, {67, 2}, {98}, {139}, {189}, {189}, {0}},
					{{0}, {98}, {114, 1}, {85}, {0}, {98}, {31, 1}, {189}, {189}},
					{{0}, {67, 0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {98}, {61, 4}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {98}, {58}, {0}, {84}, {98}, {31, 1}, {38, 8}, {0}},
					{{0}, {67, 0}, {0}, {0}, {0}, {67, 1}, {31, 1}, {31, 1}, {189}},
					{{0}, {98}, {47}, {126, 8}, {47}, {98}, {38, 8}, {189}, {189}},
					{{0}, {139}, {98}, {67, 3}, {98}, {139}, {189}, {189}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {139}, {98}, {102}, {98}, {139}, {0}, {0}, {0}},
					{{0}, {98}, {0}, {72}, {0}, {98}, {0}, {0}, {0}},
					{{0}, {102}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {98}, {67, 1}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {98}, {0}, {0}, {0}, {98}, {0}, {0}, {0}},
					{{0}, {102}, {0}, {0}, {0}, {102}, {0}, {0}, {0}},
					{{0}, {98}, {47}, {140}, {47}, {98}, {0}, {0}, {0}},
					{{0}, {139}, {98}, {102}, {98}, {139}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {139}, {98}, {102}, {98}, {139}, {0}, {0}, {0}},
					{{0}, {98}, {0}, {0}, {0}, {98}, {0}, {0}, {0}},
					{{0}, {102}, {0}, {0}, {0}, {67, 7}, {0}, {0}, {0}},
					{{0}, {98}, {139}, {0}, {0}, {67, 6}, {0}, {0}, {0}},
					{{0}, {98}, {0}, {0}, {0}, {98}, {0}, {0}, {0}},
					{{0}, {102}, {0}, {0}, {0}, {102}, {0}, {0}, {0}},
					{{0}, {98}, {47}, {0}, {47}, {98}, {0}, {0}, {0}},
					{{0}, {139}, {98}, {102}, {98}, {139}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {170, 4}, {126, 8}, {0}, {126, 8}, {170, 4}, {0}, {0}, {0}},
					{{170, 4}, {170, 4}, {98}, {98}, {98}, {170, 4}, {170, 4}, {0}, {0}},
					{{170, 4}, {98}, {0}, {0}, {0}, {98}, {170, 4}, {0}, {0}},
					{{126, 8}, {98}, {0}, {0}, {0}, {98}, {126, 8}, {0}, {0}},
					{{0}, {98}, {139}, {0}, {0}, {98}, {0}, {0}, {0}},
					{{0}, {98}, {0}, {0}, {0}, {98}, {0}, {0}, {0}},
					{{126, 8}, {98}, {0}, {0}, {0}, {98}, {126, 8}, {0}, {0}},
					{{170, 4}, {98}, {0}, {0}, {0}, {98}, {170, 4}, {0}, {0}},
					{{170, 4}, {170, 4}, {98}, {98}, {98}, {170, 4}, {170, 4}, {0}, {0}},
					{{0}, {170, 4}, {126, 8}, {0}, {126, 8}, {170, 4}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}, {0}, {0}},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0}},
					{{0}, {170, 4}, {170, 4}, {0}, {170, 4}, {170, 4}, {0}, {0}, {0}},
					{{126, 0}, {170, 4}, {0}, {0}, {0}, {170, 4}, {126, 0}, {0}, {0}},
					{{170, 4}, {170, 4}, {139}, {0}, {0}, {170, 4}, {170, 4}, {0}, {0}},
					{{170, 4}, {170, 4}, {0}, {0}, {0}, {170, 4}, {170, 4}, {0}, {0}},
					{{126, 0}, {170, 4}, {0}, {0}, {0}, {170, 4}, {126, 0}, {0}, {0}},
					{{0}, {170, 4}, {170, 4}, {0}, {170, 4}, {170, 4}, {0}, {0}, {0}},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0}},
					{{0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {126, 0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}, {0}, {0}},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {0}, {0}, {0}, {0}},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0}},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0}},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {126, 0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {126, 0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {126, 0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
			},
			
			
			
			
			//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
			//
			//180 degrees
			//
			//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
			
			{	{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}, {0},},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0},},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0},},
					{{0}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0},},
					{{0}, {2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {2}, {0},},
					{{0}, {2}, {2}, {2}, {2}, {3, 2}, {3, 2}, {2}, {2}, {0},},
					{{0}, {2}, {2}, {2}, {2}, {2}, {3, 2}, {2}, {2}, {0},},
					{{0}, {0}, {2}, {2}, {2}, {3, 2}, {2}, {2}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {139}, {98}, {67, 2}, {98}, {98}, {67, 2}, {98}, {139}, {0},},
					{{0}, {98}, {47}, {0}, {58}, {61, 3}, {0}, {114, 3}, {98}, {0},},
					{{0}, {67, 0}, {126, 8}, {0}, {0}, {0}, {0}, {85}, {67, 1}, {0},},
					{{0}, {98}, {47}, {0}, {84}, {0}, {0}, {0}, {98}, {0},},
					{{0}, {139}, {98}, {67, 3}, {98}, {0}, {0}, {98}, {139}, {0},},
					{{0}, {189}, {38, 8}, {31, 1}, {31, 1}, {0}, {0}, {31, 1}, {189}, {0},},
					{{0}, {189}, {189}, {31, 1}, {38, 8}, {0}, {0}, {189}, {189}, {0},},
					{{0}, {0}, {189}, {189}, {0}, {0}, {0}, {189}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {98}, {102}, {98}, {139}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {67, 3}, {0}, {0}, {98}, {0},},
					{{0}, {102}, {140}, {0}, {0}, {0}, {0}, {72}, {102}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {0}, {0}, {0}, {98}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {0}, {0}, {98}, {139}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {98}, {102}, {98}, {139}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {139}, {0}, {0}, {98}, {0},},
					{{0}, {102}, {0}, {0}, {0}, {0}, {0}, {0}, {102}, {0},},
					{{0}, {98}, {47}, {0}, {0}, {0}, {0}, {0}, {98}, {0},},
					{{0}, {139}, {98}, {102}, {98}, {67, 5}, {67, 4}, {98}, {139}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	
					{{0}, {170, 4}, {170, 4}, {126, 8}, {0}, {0}, {126, 8}, {170, 4}, {170, 4}, {0},},
					{{170, 4}, {170, 4}, {98}, {98}, {98}, {98}, {98}, {98}, {170, 4}, {170, 4},},
					{{126, 8}, {98}, {0}, {0}, {0}, {139}, {0}, {0}, {98}, {126, 8},},
					{{0}, {98}, {0}, {0}, {0}, {0}, {0}, {0}, {98}, {0},},
					{{126, 8}, {98}, {0}, {0}, {0}, {0}, {0}, {0}, {98}, {126, 8},},
					{{170, 4}, {170, 4}, {98}, {98}, {98}, {98}, {98}, {98}, {170, 4}, {170, 4},},
					{{0}, {170, 4}, {170, 4}, {126, 8}, {0}, {0}, {126, 8}, {170, 4}, {170, 4}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0},},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0},},
					{{126, 0}, {170, 4}, {170, 4}, {0}, {0}, {139}, {0}, {170, 4}, {170, 4}, {126, 0},},
					{{170, 4}, {170, 4}, {0}, {0}, {0}, {0}, {0}, {0}, {170, 4}, {170, 4},},
					{{126, 0}, {170, 4}, {170, 4}, {0}, {0}, {0}, {0}, {170, 4}, {170, 4}, {126, 0},},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0},},
					{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {126, 0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {126, 0}, {126, 0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0},},
					{{0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0},},
					{{0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {126, 0}, {126, 0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {126, 0}, {126, 0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0},},
				},
			},
			
			//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
			//
			//270 degrees
			//
			//OQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQOQ
			
			{	
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {2}, {2}, {2}, {5, 1}, {5, 1}, {5, 1}, {0}, {0}},
					{{2}, {2}, {2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}},
					{{2}, {2}, {2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}},
					{{2}, {2}, {2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}},
					{{3, 2}, {2}, {3, 2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}},
					{{2}, {3, 2}, {3, 2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}},
					{{2}, {2}, {2}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {5, 1}, {0}},
					{{0}, {2}, {2}, {2}, {5, 1}, {5, 1}, {5, 1}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {189}, {189}, {139}, {98}, {67, 2}, {98}, {139}, {0}},
					{{189}, {189}, {38, 8}, {98}, {47}, {126, 8}, {47}, {98}, {0}},
					{{189}, {31, 1}, {31, 1}, {67, 0/*ROTATE*/}, {0}, {0}, {0}, {67, 1 /*ROTATE*/}, {0}},
					{{0}, {38, 8}, {31, 1}, {98}, {84}, {0}, {58}, {98}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {61, 4/*ROTATE*/}, {98}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {67, 1 /*ROTATE*/}, {0}},
					{{189}, {189}, {31, 1}, {98}, {0}, {85}, {114, 0/*ROTATE*/}, {98}, {0}},
					{{0}, {189}, {189}, {139}, {98}, {67, 3/*ROTATE*/}, {98}, {139}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {139}, {98}, {102}, {98}, {139}, {0}},
					{{0}, {0}, {0}, {98}, {47}, {140}, {47}, {98}, {0}},
					{{0}, {0}, {0}, {102}, {0}, {0}, {0}, {102}, {0}},
					{{0}, {0}, {0}, {98}, {0}, {0}, {0}, {98}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {67, 0/*ROTATE*/}, {98}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {102}, {0}},
					{{0}, {0}, {0}, {98}, {0}, {72}, {0}, {98}, {0}},
					{{0}, {0}, {0}, {139}, {98}, {102}, {98}, {139}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {139}, {98}, {102}, {98}, {139}, {0}},
					{{0}, {0}, {0}, {98}, {47}, {0}, {47}, {98}, {0}},
					{{0}, {0}, {0}, {102}, {0}, {0}, {0}, {102}, {0}},
					{{0}, {0}, {0}, {98}, {0}, {0}, {0}, {98}, {0}},
					{{0}, {0}, {0}, {67, 7}, {0}, {0}, {139}, {98}, {0}},
					{{0}, {0}, {0}, {67, 6}, {0}, {0}, {0}, {102}, {0}},
					{{0}, {0}, {0}, {98}, {0}, {0}, {0}, {98}, {0}},
					{{0}, {0}, {0}, {139}, {98}, {102}, {98}, {139}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {170, 4}, {126, 8}, {0}, {126, 8}, {170, 4}, {0}},
					{{0}, {0}, {170, 4}, {170, 4}, {98}, {98}, {98}, {170, 4}, {170, 4}},
					{{0}, {0}, {170, 4}, {98}, {0}, {0}, {0}, {98}, {170, 4}},
					{{0}, {0}, {126, 8}, {98}, {0}, {0}, {0}, {98}, {126, 8}},
					{{0}, {0}, {0}, {98}, {0}, {0}, {0}, {98}, {0}},
					{{0}, {0}, {0}, {98}, {0}, {0}, {139}, {98}, {0}},
					{{0}, {0}, {126, 8}, {98}, {0}, {0}, {0}, {98}, {126, 8}},
					{{0}, {0}, {170, 4}, {98}, {0}, {0}, {0}, {98}, {170, 4}},
					{{0}, {0}, {170, 4}, {170, 4}, {98}, {98}, {98}, {170, 4}, {170, 4}},
					{{0}, {0}, {0}, {170, 4}, {126, 8}, {0}, {126, 8}, {170, 4}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}},
					{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}},
					{{0}, {0}, {0}, {170, 4}, {170, 4}, {0}, {170, 4}, {170, 4}, {0}},
					{{0}, {0}, {126, 0}, {170, 4}, {0}, {0}, {0}, {170, 4}, {126, 0}},
					{{0}, {0}, {170, 4}, {170, 4}, {0}, {0}, {0}, {170, 4}, {170, 4}},
					{{0}, {0}, {170, 4}, {170, 4}, {0}, {0}, {139}, {170, 4}, {170, 4}},
					{{0}, {0}, {126, 0}, {170, 4}, {0}, {0}, {0}, {170, 4}, {126, 0}},
					{{0}, {0}, {0}, {170, 4}, {170, 4}, {0}, {170, 4}, {170, 4}, {0}},
					{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}},
					{{0}, {0}, {0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {126, 0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}},
					{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}},
					{{0}, {0}, {0}, {126, 0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}},
					{{0}, {0}, {0}, {0}, {170, 4}, {170, 4}, {170, 4}, {126, 0}, {0}},
					{{0}, {0}, {0}, {0}, {126, 0}, {170, 4}, {126, 0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {126, 0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
				
				{	{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {126, 0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {126, 0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
					{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}},
				},
			},
		};
	
	private Vehicle vehicle;
	
	@Override
	public String getName()
	{	
		return CourseMessage.ITEM_SPECIAL_BLOCK.toString();
	}

	@Override
	public ItemResult onUse(Player player, Vehicle vehicle, GameState game)
	{	
		this.vehicle = vehicle;
		
		GamePlayerData victim = null;
		if(game.getPlayer(1) == player)
			victim = game.getPlayerData(2);
		else
			victim = game.getPlayerData(1);
		
		if(victim == null || victim.getVehicle() == null || victim.getVehicle().getEntity() == null || victim.getPlayer() == null)
			return ItemResult.DISCARD;
		
		int direction = 3;
		float yaw = victim.getPlayer().getLocation().getYaw();
		
		if(yaw > -45 && yaw <= 45)
			direction = 0;
		else if(yaw > 45 && yaw <= 135)
			direction = 1;
		else if(yaw > 135 || yaw <= -135)
			direction = 2;
		
		try
		{
			spawnHouse(game, victim.getVehicle().getEntity().getLocation(), direction);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.vehicle.setSpeed(this.vehicle.getSpeed() + 0.5f);
		Bukkit.getScheduler().runTaskLater(MobRacersPlugin.getPlugin(), new Runnable()
		{
			@Override
			public void run()
			{
				cancel();
			}
		}, 120);
		
		return ItemResult.DISCARD;
	}

	@Override
	public ItemStack getItemStack()
	{	
		ItemStack item = new ItemStack(Material.BRICK, 1);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET.toString()+ ChatColor.WHITE.toString() + CourseMessage.ITEM_SPECIAL_BLOCK.toString());
		item.setItemMeta(itemMeta);
		return item;
	}

	@Override
	public void cancel()
	{
		if(this.vehicle != null)
			this.vehicle.setSpeed(this.vehicle.getSpeed() - 0.5f);
	}
	
	@SuppressWarnings("deprecation")
	public static void spawnHouse(GameState game, Location loc, int direction)
	{
		short[][][][] current = house[direction];
		
		
		for(int y = 0; y < current.length; y++)
			for(int z = 0; z < current[y].length; z++)
				for(int x = 0; x < current[y][z].length; x++)
				{
					Location location = new Location(loc.getWorld(), 
							loc.getBlockX() - current[y][z].length / 2 + x, 
							loc.getBlockY() - 1 + y, 
							loc.getBlockZ() - current[y].length / 2 + z);
					
					if(current[y][z][x][0] == 0)
						continue;
					
					if(game != null)
						game.addBlockToRegen(location);
					
					byte data = 0;
					
					if(current[y][z][x].length > 1)
						data = (byte) current[y][z][x][1];
					
					location.getBlock().setTypeIdAndData(current[y][z][x][0], data, false);
				}
	}
}