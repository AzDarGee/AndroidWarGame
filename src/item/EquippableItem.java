package item;
import java.util.Iterator;

import core.Player;


public class EquippableItem extends Item {

	private double attack;
	private double armorRating;
	private double manaRegen;
	private double critRate;
	private double dodgeRate;
	private double accuracy;
	private double maxHealth;
	private double maxMana;
	
	public EquippableItem(String name, int cost, int sellBackPrice, double attack, double armorRating, double manaRegen, double critRate, double dodgeRate, double accuracy, double maxHealth, double maxMana)
	{
		equippable = true;
		
		this.name = name;
		this.cost = cost;
		this.sellBackPrice = sellBackPrice;
		this.attack = attack;
		this.armorRating = armorRating;
		this.manaRegen = manaRegen;
		this.critRate = critRate;
		this.dodgeRate = dodgeRate;
		this.accuracy = accuracy;
		this.maxHealth = maxHealth;
		this.maxMana = maxMana;
	}
	
	public void equip(Player player)
	{
		player.equippedItemIndices.add(this.id);
		player.changeAccuracy(accuracy);
		player.changeArmor(armorRating);
		player.changeAttack(attack);
		player.changeCritRate(critRate);
		player.changeDodgeRate(dodgeRate);
		player.changeManaRegen(manaRegen);
		player.changeMaxHealth(maxHealth);
		player.changeMaxMana(maxMana);
	}
	
	public void dequip(Player player)
	{
		int index = -1;
		Iterator<Integer> iter = player.equippedItemIndices.iterator();
		
		int n = 0;
		while(index == -1)
		{
			if(iter.next() == this.id)
			{
				index = n;
			}
			n++;
		}
		
		player.equippedItemIndices.remove(index);
		player.changeAccuracy(-1*accuracy);
		player.changeArmor(-1*armorRating);
		player.changeAttack(-1*attack);
		player.changeCritRate(-1*critRate);
		player.changeDodgeRate(-1*dodgeRate);
		player.changeManaRegen(-1*manaRegen);
		player.changeMaxHealth(-1*maxHealth);
		player.changeMaxMana(-1*maxMana);
	}
	
}
