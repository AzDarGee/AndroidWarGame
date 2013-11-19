package action;

import java.util.Random;

import log.Log;
import core.Attack;
import core.GameManager;
import core.Player;

public class AttackAction extends Action {

	private Attack attack;
	
	public AttackAction(Player user, Player target, Attack attack) {
		super(user, target);
		this.attack = attack;
	}

	public void doAction() {
		Log.post(user.getName() + " used " + attack.getName() + " on " + target.getName(), 1, true);
		this.target.changeHealth(-calcDamage(user,target));
		user.changeMana(-attack.getManaCost());
	}
	
	private double calcDamage(Player attacker, Player defender)
	{
		Random rand = new Random();
		
		if(rand.nextDouble()*100<(attack.getAccuracy()*defender.getDodgeRate()+attacker.getAccuracy()))
		{
			double damage = ((attack.getBaseDamage()*attacker.getAttack())-defender.getArmorRating())*attacker.getLevel();
			
			if(rand.nextDouble()<(attack.getCritRate()/attacker.getCritRate())+attacker.getLevel())
			{
				damage*=1.5;
			}
			
			return damage;
		}
		else
		{
			Log.post(attack.getName() + " missed!", 2, true);
			return 0;
		}
	}

}
