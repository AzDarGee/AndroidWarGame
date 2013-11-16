package core;

import item.Item;

/**
 * Inventory's are essentially two int arrays, both containing how many of each possible item is possessed.
 * @author Joshua Saunders
 *
 */
public class Inventory {
	
	private int[] consumableCounts = new int[Item.allConsumableItems.size()];
	private int[] equippableCounts = new int[Item.allEquippableItems.size()];
	
	public int[] getConsumableCounts() {
		return consumableCounts;
	}
	public int[] getEquippableCounts() {
		return equippableCounts;
	}
	
	/**
	 * Increases the count of the given Item in this inventory by 1.
	 * @param item the item whose count in this inventory should be increased by 1
	 */
	public void addToInventory(Item item) {
		if(!item.isEquippable())
		{
			consumableCounts[item.getId()]++;
		}
		else
		{
			equippableCounts[item.getId()]++;	
		}
	}
}
