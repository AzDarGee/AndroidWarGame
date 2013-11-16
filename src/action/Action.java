package action;
import core.Player;

/**
 * Action objects store information about an action made by a given Player, including its target.
 * Subclasses of the Action class denote specific types of Actions that Players can make.
 * @author Joshua Saunders
 *
 */
public abstract class Action {

	protected Player user;
	protected Player target;
	
	public Action(Player user, Player target)
	{
		this.user = user;
		this.target = target;
	}
	
	/**
	 * Must be implemented by all types of Actions; the ActionQueue will run Actions via this method
	 */
	public abstract void doAction();
	
}
