package log;

public class Log {

	private static Log log = new Log();
	private String logText = "";
	
	public static void post(String text, int level, boolean hypenated)
	{
		for(int n=0;n<level;n++)
		{
			text = "\t" + text;
		}

		if(hypenated)
		{
			text = "- " + text;
		}
		
		log.logText = log.logText + text + "\n";
	}

	public static String printLog()
	{
		return log.logText;
	}
}
