package turnEffect;

import java.io.*;

import core.Player;
public abstract class TurnEffect{

	//GameManager has a list of GameEffects, runs all beginTurnEffect()s at the beginning of a turn (like Sandstorm) and, runs all endTurnEffect()s at the end of a turn (most effects will undo the begin effect and check if its turn limit is up)

	protected String name;
	protected int numTurnsActive;
	protected int numTurnsHasBeenActive = 0;
	protected Player user;
	protected Player target;
	//TODO add team variable to Player class

	public TurnEffect(Player user, Player target, int numTurnsActive)
	{
		this.numTurnsActive = numTurnsActive;
		this.user = user;
		this.target = target;
	}
	
	public abstract void beginTurnEffect();

	public abstract void endTurnEffect();

	public int getNumTurnsActive() {
		return numTurnsActive;
	}
	
	public boolean effectFinished() {
		return numTurnsHasBeenActive == numTurnsActive;
	}

	public Player getUser() {
		return user;
	}

	public Player getTarget() {
		return target;
	}
	
	public String getName(){
		return name;
	}
}