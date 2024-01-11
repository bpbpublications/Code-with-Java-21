package chapter6;

public class Item {

	private String name;
	private Item prevItem;
	private Item nextItem;
	
	public Item() {
		
	}
	
	public Item(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Item getPrevItem() {
		return this.prevItem;
	}
	
	public void setPrevItem(Item item) {
		this.prevItem = item;
	}
	
	public Item getNextItem() {
		return this.nextItem;
	}
	
	public void setNextItem(Item item) {
		this.nextItem = item;
	}
}
