package item;

import java.util.ArrayList;

public abstract class Item {
	
	public static ArrayList<ConsumableItem> allConsumableItems = new ArrayList<ConsumableItem>();
	public static ArrayList<EquippableItem> allEquippableItems = new ArrayList<EquippableItem>();
	public static int nextConsumableIndex = 0;
	public static int nextEquippableIndex = 0;
	
	public static void initializeItems()
	{
		Item.addItem(new ConsumableItem("Potion", 5, 1, 25, 0, 0));
		Item.addItem(new EquippableItem("Sword", 100, 45, 20, 0, 0, 10, 0, 10, 0, 0));
		Item.addItem(new EquippableItem("Big Sword", 200, 45, 20, 0, 0, 10, 0, 10, 0, 0));
		Item.addItem(new EquippableItem("Big Sword", 200, 45, 20, 0, 0, 10, 0, 10, 0, 0));
		Item.addItem(new EquippableItem("Big Sword", 200, 45, 20, 0, 0, 10, 0, 10, 0, 0));
		Item.addItem(new EquippableItem("Big Sword", 200, 45, 20, 0, 0, 10, 0, 10, 0, 0));
	}
	
	public static void addItem(Item item)
	{
		if(!item.equippable)
		{
			item.id = nextConsumableIndex;
			allConsumableItems.add((ConsumableItem) item);
			nextConsumableIndex++;
		}
		else
		{
			item.id = nextEquippableIndex;
			allEquippableItems.add((EquippableItem) item);
			nextEquippableIndex++;
		}
	}
	
	protected int id;
	protected String name;
	protected int cost;
	protected int sellBackPrice;
	protected boolean equippable;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getSellBackPrice() {
		return sellBackPrice;
	}

	public boolean isEquippable() {
		return equippable;
	}

	public String toString()
	{
		String result = "id: " + id + "\n";
		result = result + "name: " + name + "\n";
		result = result + "cost: " + cost + "\n";
		result = result + "sellback price: " + sellBackPrice + "\n";
		result = result + "equippable: " + equippable;
		
		return result;
	}
}
