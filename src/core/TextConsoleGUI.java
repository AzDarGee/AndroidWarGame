package core;

import java.util.Scanner;

import item.Item;
import action.*;

public class TextConsoleGUI {

	public static void main(String[] args) {

		Player player1 = new Player(100, 100, 10, 10, 10, 10, 10, 10);
		Player player2 = new Player(200, 50, 8, 8, 7, 8, 8, 8);

		GameManager.gameManager = new GameManager();
		GameManager.gameManager.addPlayers(player1, player2);


		while(player1.getHealth()>0 || player2.getHealth()>0)
		{
			playerTakeTurn(GameManager.gameManager.players[GameManager.gameManager.currentPlayerTurn]);
			System.out.println("");
		}

	}

	public static void playerTakeTurn(Player player)
	{
		System.out.println("What would you like to do?");
		System.out.println("1. Choose attack");
		System.out.println("2. Use item");
		System.out.println("3. Equip/dequip items");
		System.out.println("4. Visit shop");

		Scanner kbReader = new Scanner(System.in);
		char choice = kbReader.nextLine().charAt(0);

		if(choice == '1')
		{
			choiceChooseAttack(player);
		}
		else if(choice == '2')
		{
			choiceUseItem(player);
		}
		else if(choice == '3')
		{
			choiceEquipItem(player);
		}
		
		GameManager.gameManager.finishTurn();
	}

	public static void choiceChooseAttack(Player player)
	{
		System.out.println("Select an attack:");
		System.out.println("1. Basic attack");

		Scanner kbReader = new Scanner(System.in);
		char choice = kbReader.nextLine().charAt(0);

		if(choice == '1')
		{
			GameManager.gameManager.queueAction(new AttackAction(player, GameManager.gameManager.returnOpponent(player), Attack.basicAttack));
		}
	}

	public static void choiceUseItem(Player player)
	{
		System.out.println("Select an item to use:");
		System.out.println(player.printAllItems());

		Scanner kbReader = new Scanner(System.in);
		int choice = kbReader.nextInt();
		int numHits = 0;
		int targetIndex = -1;
		int[] consumableCounts = player.getInventory().getConsumableCounts();

		//eg. if choice == 0, determine which item is the 0th one that has count > 0
		for(int n=0;n<consumableCounts.length;n++)
		{
			if(consumableCounts[n]>0)
			{
				numHits++;
				if(numHits == choice)
				{
					targetIndex = numHits;
					break;
				}
			}
		}

		GameManager.gameManager.queueAction(new ConsumableItemAction(player, null, Item.allConsumableItems.get(targetIndex)));
	}

	public static void choiceEquipItem(Player player)
	{
		System.out.println("Select an item to equip:");
		System.out.println(player.printAllItems());

		Scanner kbReader = new Scanner(System.in);
		int choice = kbReader.nextInt();
		int numHits = 0;
		int targetIndex = -1;
		int[] equippableCounts = player.getInventory().getEquippableCounts();

		//eg. if choice == 0, determine which item is the 0th one that has count > 0
		for(int n=0;n<equippableCounts.length;n++)
		{
			if(equippableCounts[n]>0)
			{
				numHits++;
				if(numHits == choice)
				{
					targetIndex = numHits;
					break;
				}
			}
		}

		System.out.println("Do you want to equip or dequip this item?");
		System.out.println("1. Equip");
		System.out.println("2. Dequip");
		choice = kbReader.nextInt();

		if(choice == 1)		
		{
			GameManager.gameManager.queueAction(new EquipItemAction(player, null, Item.allEquippableItems.get(targetIndex)));
		}
		else
		{
			GameManager.gameManager.queueAction(new EquipItemAction(player, null, Item.allEquippableItems.get(targetIndex)));

		}
	}
}
