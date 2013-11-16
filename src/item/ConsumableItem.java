package item;

import core.Player;

public class ConsumableItem extends Item {

	private double health;
	private double mana;
	private double manaRegen;
	
	public ConsumableItem(String name, int cost, int sellBackPrice, double health, double mana, double manaRegen)
	{
		equippable = false;
		
		this.name = name;
		this.cost = cost;
		this.sellBackPrice = sellBackPrice;
		this.health = health;
		this.mana = mana;
		this.manaRegen = manaRegen;
	}
	
	public void use(Player player)
	{
		player.changeHealth(health);
		player.changeMana(mana);
		player.changeManaRegen(manaRegen);
	}
}
