package core;
import item.Item;

import java.util.ArrayDeque;

import log.Log;
import action.Action;

/**
 * The core of the game. Stores the two players, whose turn it is, the ActionQueue, and log.
 * @author Joshua Saunders
 */
public class GameManager {

	public static GameManager gameManager;
	public static final int PLAYER1 = 0;
	public static final int PLAYER2 = 1;
	public Player[] players = new Player[2];
	public int currentPlayerTurn = 0;
	
	private ArrayDeque<Action> actionQueue = new ArrayDeque<Action>();
	private Log log = new Log(10);
	
	/**
	 * Creating a new GameManager object initializes all parts of the game.
	 */
	public GameManager()
	{
		Item.initializeItems();
	}
	
	/**
	 * Updates the GUI
	 */
	public void outputNewStatsToGUI()
	{
		//TODO
	}
	
	/**
	 * The method for adding both players to the game after character selection.
	 * @param player1 the first player
	 * @param player2 the second player
	 */
	public void addPlayers(Player player1, Player player2)
	{
		this.players[0] = player1;
		this.players[1] = player2;
	}
	
	/**
	 * Enqueues an Action to the ActionQueue
	 * @param action the action to be enqueued to the ActionQueue
	 */
	public void queueAction(Action action)
	{
		actionQueue.add(action);
	}
	
	/**
	 * Executes all of the Actions in the ActionQueue and allows the other player to take their turn.
	 */
	public void finishTurn()
	{
		while(!actionQueue.isEmpty())
		{
			actionQueue.poll().doAction();
		}
		
		if(currentPlayerTurn == 0)
		{
			currentPlayerTurn = 1;
		}
		else
		{
			currentPlayerTurn = 0;
		}
	}
	
	/**
	 * Gets the given player's oppenent.
	 * @param player the player whose opponent is to be returned
	 * @return the given player's opponent
	 */
	public Player returnOpponent(Player player)
	{
		if(player.equals(players[0]))
		{
			return players[1];
		}
		else
		{
			return players[0];
		}
	}
	
	/**
	 * Posts text to the log.
	 * @param text the String to be posted to the log
	 */
	public void postToLog(String text)
	{
		log.post(text);
	}	
}
