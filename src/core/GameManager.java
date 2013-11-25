package core;
import item.Item;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

import com.example.gameUI.MainActivity;

import turnEffect.TurnEffect;
import log.Log;
import action.Action;
import action.AttackAction;

/**
 * The core of the game. Stores the two players, whose turn it is, the ActionQueue, and log.
 * @author Joshua Saunders
 */
public class GameManager {

	public static GameManager gameManager;
	public MainActivity androidGUI;
	public static final int PLAYER1 = 0;
	public static final int PLAYER2 = 1;
	public Player[] players = new Player[2];
	
	private int turnNumber = 1;
	private ArrayDeque<Action> actionQueue = new ArrayDeque<Action>();
	private ArrayList<TurnEffect> turnEffectList = new ArrayList<TurnEffect>();
	
	/**
	 * Creating a new GameManager object initializes all parts of the game.
	 */
	public GameManager(MainActivity androidGUI)
	{
		this.androidGUI = androidGUI;
		Item.initializeItems();
		Log.post("Game started!", 0, false);
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
		Log.post("Two players added: " + player1.getName() + " and " + player2.getName(), 0, false);
	}
	
	/**
	 * Enqueues an Action to the ActionQueue.
	 * @param action the action to be enqueued to the ActionQueue
	 */
	private void queueAction(Action action)
	{
		actionQueue.add(action);
	}
	
	/**
	 * Adds a TurnEffect to the TurnEffectList.
	 * @param turnEffect the TurnEffect to be added to the TurnEffectList
	 */
	public void addTurnEffectToList(TurnEffect turnEffect)
	{
		turnEffectList.add(turnEffect);
		Log.post(turnEffect.getName() + " is now in effect for " + turnEffect.getNumTurnsActive() + " turns.", 1, true);
	}
	
	
	/**
	 * Executes all of the Actions in the ActionQueue and allows the other player to take their turn.
	 */
	public void finishTurn()
	{		
		Log.post("--- Battle Turn " + turnNumber + " ---", 0, false);
		runAllBeginTurnEffect();
		
		if(players[0].getSpeed()>players[1].getSpeed())
		{
			loadPlayerActionQueueIntoActionQueue(players[0]);
			loadPlayerActionQueueIntoActionQueue(players[1]);
		}
		else
		{
			loadPlayerActionQueueIntoActionQueue(players[1]);
			loadPlayerActionQueueIntoActionQueue(players[0]);
		}
		
		while(!actionQueue.isEmpty())
		{
			actionQueue.poll().doAction();
			
		}
		checkIfGameOver();
		runAllEndTurnEffect();
		Log.post("--- End Turn " + turnNumber + " ---", 0, false);
		turnNumber++;
	}
	
	public void checkIfGameOver()
	{
		if(players[0].getHealth() <= 0 || players[1].getHealth() <= 0)
		{
			endGame();
			return;
		}
	}
	
	/**
	 * Runs end of game procedures
	 */
	public void endGame()
	{
		//TODO
		Log.post("Game over!", 0, false);
		System.exit(0);
	}
	
	/**
	 * Executes all of the beginTurnEffect()s of all of the TurnEffects in the TurnEffectList.
	 */
	private void runAllBeginTurnEffect()
	{
		Log.post("*BeginTurnEffects*", 0, false);
		Iterator<TurnEffect> iter = turnEffectList.iterator();
		
		while(iter.hasNext())
		{
			iter.next().beginTurnEffect();
			checkIfGameOver();
		}
		Log.post("*End BeginTurnEffects*", 0, false);

	}
	
	/**
	 * Executes all of the endTurnEffect()s of all of the TurnEffects in the TurnEffectList and clears out any TurnEffects that are over.
	 */
	private void runAllEndTurnEffect()
	{
		Log.post("*EndTurnEffects*", 0, false);
		Iterator<TurnEffect> iter = turnEffectList.iterator();
		
		while(iter.hasNext())
		{
			TurnEffect nextTurnEffect = iter.next();
			nextTurnEffect.endTurnEffect();
			checkIfGameOver();
			
			if(nextTurnEffect.effectFinished())
			{
				Log.post("TurnEffect " + nextTurnEffect.getName() + " used by " + nextTurnEffect.getUser().getName() + " on " + nextTurnEffect.getTarget().getName() + " is now over!", 0, true);
				turnEffectList.remove(nextTurnEffect);
			}
		}
		Log.post("*End EndTurnEffects*", 0, false);
	}
	
	/**
	 * Loads player's actions into the main ActionQueue, ensuring that AttackActions are enqueued last.
	 * @param player the Player whose Actions will be enqueued into the main ActionQueue
	 */
	private void loadPlayerActionQueueIntoActionQueue(Player player)
	{
		Log.post("Enqueuing " + player.getName() + "'s Actions into the ActionQueue...", 1, false);
		Action nextAction;
		while(!player.playerActionQueue.isEmpty())
		{
			nextAction = player.playerActionQueue.poll();
			if(!(nextAction instanceof AttackAction))
			{
				actionQueue.addFirst(nextAction);
			}
			else
			{
				actionQueue.add(nextAction);
			}
		}
		Log.post("Finished nqueuing " + player.getName() + "'s Actions into the ActionQueue.", 1, false);
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
}
