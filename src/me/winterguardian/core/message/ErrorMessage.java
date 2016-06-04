package me.winterguardian.core.message;

public class ErrorMessage extends HardcodedMessage
{
	public static final ErrorMessage COMMAND_INVALID_SENDER = new ErrorMessage("L'envoyeur de la commande est invalide."); 
	public static final ErrorMessage COMMAND_INVALID_PLAYER = new ErrorMessage("Le joueur spÃ©cifiÃ© est introuvable."); 
	public static final ErrorMessage COMMAND_INVALID_SUBCOMMAND = new ErrorMessage("La sous-commande envoyÃ©e est invalide.");
	public static final ErrorMessage COMMAND_INVALID_ARGUMENT = new ErrorMessage("La commande contient un ou plusieurs arguments invalides.");
	public static final ErrorMessage COMMAND_INVALID_PERMISSION = new ErrorMessage("Vous n'avez pas suffisament de permissions de faire ceci. "); 
	public static final ErrorMessage COMMAND_ASYNC_INTERNALERROR = new ErrorMessage("§cUne erreur s'est produite lors de l'Ã©xÃ©cution cette commande.");
	
	public static final ErrorMessage WORLD_INVALID_ITEM = new ErrorMessage("Cet item est invalide ou inexistant.");
	public static final ErrorMessage WORLD_INVALID_BLOCK = new ErrorMessage("Ce bloc est invalide.");
	public static final ErrorMessage WORLD_INVALID_ENTITY = new ErrorMessage("Cette entitÃ©e est invalide ou inexistante.");
	public static final ErrorMessage WORLD_INVALID_REGION = new ErrorMessage("Cette rÃ©gion est invalide.");

	public static final ErrorMessage
			COMMAND_INVALID_VIPRANK = new ErrorMessage("§cVous devez au minimum Ãªtre §6§lVip §cpour Ã§a."),
			COMMAND_INVALID_ELITERANK = new ErrorMessage("§cVous devez au minimum Ãªtre §f§lÃ‰lite §cpour Ã§a."),
			COMMAND_INVALID_GODRANK = new ErrorMessage("§cVous devez au minimum Ãªtre §a§lDieu §cpour Ã§a.");

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
		return "§c§lErreur §f§l>§7 ";
	}
}
