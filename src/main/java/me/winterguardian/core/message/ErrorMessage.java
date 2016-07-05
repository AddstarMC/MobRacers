package me.winterguardian.core.message;

import org.bukkit.ChatColor;

public class ErrorMessage extends HardcodedMessage
{
	public static final ErrorMessage COMMAND_INVALID_SENDER = new ErrorMessage("L'envoyeur de la commande est invalide."); 
	public static final ErrorMessage COMMAND_INVALID_PLAYER = new ErrorMessage("Le joueur spécifié est introuvable."); 
	public static final ErrorMessage COMMAND_INVALID_SUBCOMMAND = new ErrorMessage("La sous-commande envoyée est invalide.");
	public static final ErrorMessage COMMAND_INVALID_ARGUMENT = new ErrorMessage("La commande contient un ou plusieurs arguments invalides.");
	public static final ErrorMessage COMMAND_INVALID_PERMISSION = new ErrorMessage("Vous n'avez pas suffisament de permissions de faire ceci. "); 
	public static final ErrorMessage COMMAND_ASYNC_INTERNALERROR = new ErrorMessage(ChatColor.RED.toString()+"Une erreur s'est produite lors de l'éxécution cette commande.");
	
	public static final ErrorMessage WORLD_INVALID_ITEM = new ErrorMessage("Cet item est invalide ou inexistant.");
	public static final ErrorMessage WORLD_INVALID_BLOCK = new ErrorMessage("Ce bloc est invalide.");
	public static final ErrorMessage WORLD_INVALID_ENTITY = new ErrorMessage("Cette entitée est invalide ou inexistante.");
	public static final ErrorMessage WORLD_INVALID_REGION = new ErrorMessage("Cette région est invalide.");

	public static final ErrorMessage
			COMMAND_INVALID_VIPRANK = new ErrorMessage(ChatColor.RED.toString()+"Vous devez au minimum être "+ChatColor.GOLD.toString()+ChatColor.BOLD.toString()+"Vip "+ChatColor.RED.toString()+"pour ça."),
			COMMAND_INVALID_ELITERANK = new ErrorMessage(ChatColor.RED.toString()+"Vous devez au minimum être "+ChatColor.WHITE.toString()+ChatColor.BOLD.toString()+"Élite "+ChatColor.RED.toString()+"pour ça."),
			COMMAND_INVALID_GODRANK = new ErrorMessage(ChatColor.RED.toString()+"Vous devez au minimum être "+ChatColor.GREEN.toString()+"lDieu "+ChatColor.RED.toString()+"pour ça.");

	public ErrorMessage(String content, boolean prefix, MessageType type)
	{
		super(content, prefix, type);
	}
	
	public ErrorMessage(String content, boolean prefix, String hoverEvent, String hoverEventContent, String clickEvent, String clickEventContent)
	{
		super(content, prefix, hoverEvent, hoverEventContent, clickEvent, clickEventContent);
	}
	
	public ErrorMessage(String content, boolean prefix)
	{
		super(content, prefix);
	}
	
	public ErrorMessage(String content, String subtitle, int delay, int fadeIn, int fadeOut)
	{
		super(content, subtitle, delay, fadeIn, fadeOut);
	}
	
	public ErrorMessage(String content)
	{
		super(content);
	}
	
	@Override
	protected String getPrefix()
	{
		return ChatColor.RED.toString()+ChatColor.BOLD.toString()+"Erreur "+ChatColor.WHITE.toString()+ChatColor.BOLD.toString()+">"+ChatColor.GRAY.toString()+" ";
	}
}
