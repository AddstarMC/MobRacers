package me.winterguardian.core.message;

public class CoreMessage extends HardcodedMessage
{
	public static final CoreMessage USERDATA_USELESSRELOAD = new CoreMessage("ßcVous ne pouvez pas recharger un userdata non charg√©.");
	public static final CoreMessage USERDATA_RELOAD = new CoreMessage("ßaL'userdata s√©lectionn√© a √©t√© recharg√©.");
	public static final CoreMessage USERDATA_SETDATA = new CoreMessage("ßaLes donn√©es sp√©cifi√©es ont √©t√© modifi√©es et enregistr√©es.");
	public static final CoreMessage USERDATA_SETDATA_ERROR = new CoreMessage("ßcLes donn√©es sp√©cifi√©s n'ont pas pu √™tre modifi√©es et enregistr√©es √† cause d'une erreur interne.");
	
	public static final CoreMessage WAND_POSITIONSET = new CoreMessage("ßaPosition # plac√© √† ßf(<x>, <y>, <z>)");  

	public static final HardcodedMessage PURCHASE_NOTENOUGHPOINTS = new HardcodedMessage("ßcVous n'avez pas assez de points pour acheter ceci.");
	public static final HardcodedMessage PURCHASE_SUCCESS = new HardcodedMessage("ßcPoints -<price> ßf(Solde: <bal>)");


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
		return "ß3ßlCore ßfßl>ß7 ";
	}
}
