package game.object;

import game.enums.Special;

public class SpecialAttribute {
	private int id;
	private Special special;
	public SpecialAttribute(int id,Special special) {
		this.id = id;
		this.special = special;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Special getSpecial() {
		return special;
	}
	public void setSpecial(Special special) {
		this.special = special;
	}
}
