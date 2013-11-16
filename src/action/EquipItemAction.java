package action;

import item.EquippableItem;
import core.Player;

public class EquipItemAction extends Action {

	private EquippableItem item;
	
	public EquipItemAction(Player user, Player target, EquippableItem equippableItem) {
		super(user, target);
		this.item = equippableItem;
	}

	@Override
	public void doAction() {
		item.equip(user);
	}

}
