package core;

import log.Log;

/**
 * All Attack objects store attacks of different names, base damage, critical rates, accuracy and mana cost.
 * @author Joshua Saunders
 *
 */
public class Attack {

	//TODO how will attacks be stored? Same as items?
	public static Attack basicAttack = new Attack("Basic Attack", 10, 1, 10, 10);
	
	/**
	 * Creates an attack object with given parameters
	 * @param name name of the attack
	 * @param baseDamage base damage of attack
	 * @param critRate critical rate of attack
	 * @param accuracy accuracy of attack
	 * @param manaCost mana cost of attack
	 */
	public Attack(String name, double baseDamage, double critRate, double accuracy, double manaCost)
	{
		this.name = name;
		this.baseDamage = baseDamage;
		this.critRate = critRate;
		this.accuracy = accuracy;
		this.manaCost = manaCost;
		
		Log.post("Attack " + name + " added", 1, true);
	}
	
	private String name;
	private double baseDamage;
	private double critRate;
	private double accuracy;
	private double manaCost;
	
	public String getName() {
		return name;
	}
	public double getBaseDamage() {
		return baseDamage;
	}
	public double getCritRate() {
		return critRate;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public double getManaCost() {
		return manaCost;
	}
		
}
