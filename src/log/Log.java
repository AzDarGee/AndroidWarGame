package log;

public class Log {

	private LeakyStack<String> leakyStack;
	
	public Log(int capacity)
	{
		leakyStack = new LeakyStack<String>(capacity);
	}
	
	public void post(String text)
	{
		leakyStack.push(text);
	}
	
	public String toString()
	{
		return leakyStack.toString();
	}
}
