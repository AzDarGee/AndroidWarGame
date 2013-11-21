package core;

import item.Item;

import java.util.ArrayDeque;
import java.util.LinkedList;

import log.Log;
import action.Action;

/**
 * Player objects are the two players representations, with many stats, an inventory, and it also keeps track of which items are currently equipped.
 * @author Joshua Saunders
 *
 */
public class Player {

	private String name;
	private int level = 1;
	private double health;
	private double attack;
	private double armorRating;
	private double experience = 0;
	private double mana;
	private double manaRegen;
	private double critRate;
	private double dodgeRate;
	private double accuracy;
	private int gold = 0;
	private double maxHealth;
	private double maxMana;
	private int carryCapacity = 10;
	private double speed;

	public ArrayDeque<Action> playerActionQueue = new ArrayDeque<Action>();
	private Inventory inventory = new Inventory();
	public LinkedList<Integer> equippedItemIndices = new LinkedList<Integer>();

	public Player(String name, double maxHealth, double maxMana, double attack, double armorRating, double manaRegen, double critRate, double dodgeRate, double accuracy, double speed)
	{
		this.name = name;
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.attack = attack;
		this.armorRating = armorRating;
		this.mana = maxMana;
		this.maxMana = maxMana;
		this.manaRegen = manaRegen;
		this.critRate = critRate;
		this.dodgeRate = dodgeRate;
		this.speed = speed;
	}

	public void enqueueAction(Action action)
	{
		playerActionQueue.add(action);
	}

	public void changeHealth(double value)
	{
		health += value;
		Log.post(name + "'s health is now " + health + ".", 2, true);
	}

	public void changeAttack(double value)
	{
		attack += value;
		Log.post(name + "'s attack is now " + attack + ".", 2, true);
	}

	public void changeArmor(double value)
	{
		armorRating += value;
		Log.post(name + "'s armor rating is now " + armorRating + ".", 2, true);
	}

	public void changeMana(double value)
	{
		mana += value;
		Log.post(name + "'s mana is now " + mana + ".", 2, true);
	}

	public void changeManaRegen(double value)
	{
		manaRegen += value;
		Log.post(name + "'s mana regen is now " + manaRegen + ".", 2, true);
	}

	public void changeCritRate(double value)
	{
		critRate += value;
		Log.post(name + "'s crit rate is now " + critRate + ".", 2, true);
	}

	public void changeDodgeRate(double value)
	{
		dodgeRate += value;
		Log.post(name + "'s dodge rate is now " + dodgeRate + ".", 2, true);
	}

	public void changeAccuracy(double value)
	{
		accuracy += value;
		Log.post(name + "'s accuracy is now " + accuracy + ".", 2, true);
	}

	public void changeMaxHealth(double value)
	{
		maxHealth += value;
		Log.post(name + "'s max health is now " + maxHealth + ".", 2, true);
	}

	public void changeMaxMana(double value)
	{
		maxMana += value;
		Log.post(name + "'s max mana is now " + maxMana + ".", 2, true);
	}

	public void changeCarryCapacity(int value)
	{
		carryCapacity += value;
		Log.post(name + "'s carry capacity is now " + carryCapacity + ".", 2, true);
	}


	public int getLevel() {
		return level;
	}

	public double getHealth() {
		return health;
	}

	public double getAttack() {
		return attack;
	}

	public double getArmorRating() {
		return armorRating;
	}

	public double getExperience() {
		return experience;
	}

	public double getMana() {
		return mana;
	}

	public double getManaRegen() {
		return manaRegen;
	}

	public double getCritRate() {
		return critRate;
	}

	public double getDodgeRate() {
		return dodgeRate;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public int getGold() {
		return gold;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public double getMaxMana() {
		return maxMana;
	}

	public int getCarryCapacity() {
		return carryCapacity;
	}

	public double getSpeed(){
		return speed;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public LinkedList<Integer> getEquippedItemIndices() {
		return equippedItemIndices;
	}

	public String getName() {
		return name;
	}

	/**
	 * Prints out all of the items owned.
	 * @return a String containing all of the items owned, as well as how many of each are owned
	 */
	public String printAllItems()
	{
		String result = "";

		int[] consumableCounts = this.inventory.getConsumableCounts();
		int[] equippableCounts = this.inventory.getEquippableCounts();

		result = result + "Consumable Items\n\n";
		for(int n=0;n<consumableCounts.length;n++)
		{
			if(consumableCounts[n] > 0)
			{
				result = result + "n = " + consumableCounts[n] + "\n" + Item.allConsumableItems.get(n).toString();
				result = result + "\n---\n";
			}
		}

		result = result + "Equippable Items\n\n";
		for(int n=0;n<equippableCounts.length;n++)
		{
			if(equippableCounts[n] > 0)
			{
				result = result + "n = " + equippableCounts[n] + "\n" + Item.allEquippableItems.get(n).toString();
				result = result + "\n---\n";
			}
		}

		return result;
	}

	/**
	 * Adds an item to this player's Inventory.
	 * @param item Item to be added to this player's Inventory
	 */
	public void addToInventory(Item item)
	{
		this.inventory.addToInventory(item);
		Log.post(item.getName() + " has been added to " + name + "'s inventory.", 0, true);
	}
}
