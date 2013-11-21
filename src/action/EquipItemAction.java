package action;

import log.Log;
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
		Log.post(user.getName() + " equipped " + item.getName() + ".", 1, true);
		item.equip(user);
	}

}
