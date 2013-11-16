package core;

import item.Item;

import java.util.LinkedList;

/**
 * Player objects are the two players representations, with many stats, an inventory, and it also keeps track of which items are currently equipped.
 * @author Joshua Saunders
 *
 */
public class Player {

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

	private Inventory inventory = new Inventory();
	public LinkedList<Integer> equippedItemIndices = new LinkedList<Integer>();

	public Player(double maxHealth, double maxMana, double attack, double armorRating, double manaRegen, double critRate, double dodgeRate, double accuracy)
	{
		this.health = maxHealth;
		this.maxHealth = maxHealth;
		this.attack = attack;
		this.armorRating = armorRating;
		this.mana = maxMana;
		this.maxMana = maxMana;
		this.manaRegen = manaRegen;
		this.critRate = critRate;
		this.dodgeRate = dodgeRate;
	}

	public void changeHealth(double value)
	{
		health += value;
	}

	public void changeAttack(double value)
	{
		attack += value;
	}

	public void changeArmor(double value)
	{
		armorRating += value;
	}

	public void changeMana(double value)
	{
		mana += value;
	}

	public void changeManaRegen(double value)
	{
		manaRegen += value;
	}

	public void changeCritRate(double value)
	{
		critRate += value;
	}

	public void changeDodgeRate(double value)
	{
		dodgeRate += value;
	}

	public void changeAccuracy(double value)
	{
		accuracy += value;
	}

	public void changeMaxHealth(double value)
	{
		maxHealth += value;
	}

	public void changeMaxMana(double value)
	{
		maxMana += value;
	}

	public void changeCarryCapacity(int value)
	{
		carryCapacity += value;
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

	public Inventory getInventory() {
		return inventory;
	}

	public LinkedList<Integer> getEquippedItemIndices() {
		return equippedItemIndices;
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
	}
}
