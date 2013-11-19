package turnEffect;

import log.Log;
import core.Player;

public class PoisonTargetTurnEffect extends TurnEffect {

	private double damagePerTurn;
	
	public PoisonTargetTurnEffect(Player user, Player target, int numTurnsActive, double damagePerTurn) {
		super(user, target, numTurnsActive);
		this.damagePerTurn = damagePerTurn;
		this.name = "PoisonTurnEffect";
	}

	@Override
	public void beginTurnEffect() {
		
	}

	@Override
	public void endTurnEffect() {
		Log.post(target.getName() + " has taken " + damagePerTurn + " damage!", 0, true);
		target.changeHealth(-1*damagePerTurn);
		numTurnsHasBeenActive++;
	}

}
