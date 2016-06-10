package me.winterguardian.core.message;

import org.bukkit.ChatColor;

public class CoreMessage extends HardcodedMessage
{
	public static final CoreMessage USERDATA_USELESSRELOAD = new CoreMessage(ChatColor.RED.toString()+"Vous ne pouvez pas recharger un userdata non chargé.");
	public static final CoreMessage USERDATA_RELOAD = new CoreMessage(ChatColor.GREEN.toString()+"L'userdata sélectionné a été rechargé.");
	public static final CoreMessage USERDATA_SETDATA = new CoreMessage(ChatColor.GREEN.toString()+"Les données spécifiées ont été modifiées et enregistrées.");
	public static final CoreMessage USERDATA_SETDATA_ERROR = new CoreMessage(ChatColor.RED.toString()+"Les données spécifiés n'ont pas pu être modifiées et enregistrées à cause d'une erreur interne.");
	
	public static final CoreMessage WAND_POSITIONSET = new CoreMessage(ChatColor.GREEN.toString()+"Position # placé à "+ChatColor.WHITE.toString()+"(<x>, <y>, <z>)");

	public static final HardcodedMessage PURCHASE_NOTENOUGHPOINTS = new HardcodedMessage(ChatColor.RED.toString()+"Vous n'avez pas assez de points pour acheter ceci.");
	public static final HardcodedMessage PURCHASE_SUCCESS = new HardcodedMessage(ChatColor.RED.toString()+"Points -<price> "+ChatColor.WHITE.toString()+"(Solde: <bal>)");


	private CoreMessage(String content)
	{
		super(content);
	}

	private CoreMessage(String content, boolean prefix)
	{
		super(content, prefix);
	}
	
	private CoreMessage(String content, boolean prefix, MessageType type)
	{
		super(content, prefix, type);
	}

	private CoreMessage(String content, boolean prefix, String hoverEvent, String hoverEventContent, String clickEvent, String clickEventContent)
	{
		super(content, prefix, hoverEvent, hoverEventContent, clickEvent, clickEventContent);
	}

	private CoreMessage(String title, String subTitle, int delay, int fadeIn, int fadeOut)
	{
		super(title, subTitle, delay, fadeIn, fadeOut);
	}

	@Override
	protected String getPrefix()
	{
		return ChatColor.RED.toString()+ChatColor.BOLD.toString()+"Core "+ChatColor.WHITE.toString()+ChatColor.BOLD.toString()+">"+ChatColor.GRAY.toString()+" ";
	}
}
