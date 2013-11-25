package oscf;
import java.io.*;


public class ServerConsole implements ChatIF 
{
  
  final public static int DEFAULT_PORT = 5555;
  
  EchoServer server; //EchoServer connected to this console

  
 /**
  * Sets server to the given EchoServer
  */
  public ServerConsole(EchoServer sv) 
  {
	server = sv;
  }

  public void accept() 
  {
    try
    {
      BufferedReader fromConsole = 
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true) 
      {
        message = fromConsole.readLine();
        //Checks whether or not the message is a command
        if(message.charAt(0) == '#'){
        	  server.runCommand(message); //Calls method to handle commands
          } else {  
        message = "SERVER MSG> " + message;
        server.sendToAllClients(message);
        display(message);
          }
      }
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }

  public void display(String message) 
  {
    System.out.println(message);
  }
}
