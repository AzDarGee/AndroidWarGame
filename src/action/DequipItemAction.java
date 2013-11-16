package action;

import item.EquippableItem;
import core.Player;

public class DequipItemAction extends Action {

	private EquippableItem item;
	
	public DequipItemAction(Player user, Player target, EquippableItem equippableItem) {
		super(user, target);
		item = equippableItem;
	}

	@Override
	public void doAction() {
		item.dequip(user);
	}

}
