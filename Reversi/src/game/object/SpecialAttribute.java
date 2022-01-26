package game.object;

import game.enums.Special;

public class SpecialAttribute {
	private int id;
	private Special special;
	//0～100まで、重複しない
	private int probability;
	//flg管理
	private boolean needHorizontalFlg;
	private boolean needVerticalFlg;
	public SpecialAttribute(int id) {
		this.id = id;
	}
	public SpecialAttribute(int id,Special special,int probability,boolean needHorizontalFlg,boolean needVerticalFlg) {
		this.id = id;
		this.special = special;
		this.probability = probability;
		this.needHorizontalFlg = needHorizontalFlg;
		this.needVerticalFlg = needVerticalFlg;
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
	public int getProbability() {
		return probability;
	}
	public void setProbability(int probability) {
		this.probability = probability;
	}
	public boolean isNeedHorizontalFlg() {
		return needHorizontalFlg;
	}
	public void setNeedHorizontalFlg(boolean needHorizontalFlg) {
		this.needHorizontalFlg = needHorizontalFlg;
	}
	public boolean isNeedVerticalFlg() {
		return needVerticalFlg;
	}
	public void setNeedVerticalFlg(boolean needVerticalFlg) {
		this.needVerticalFlg = needVerticalFlg;
	}
}
