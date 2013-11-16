package action;

import item.ConsumableItem;
import core.Player;

public class ConsumableItemAction extends Action {

	private ConsumableItem item;
	
	public ConsumableItemAction(Player user, Player target, ConsumableItem consumableItem) {
		super(user, target);
		this.item = consumableItem;
	}

	@Override
	public void doAction() {
		item.use(user);
	}

}
