package chapter6;

public class LinkedList {

	private Item firstItem;
	private Item lastItem;
	private int listCount = 0;
	
	public LinkedList() {
	}
	
	public LinkedList(Item item) {
		addItem(item);
	}

	private void setWithOneItem(Item newItem) {
		firstItem = newItem;
		lastItem = newItem;
	}

	public void addItem(Item newItem) {

		if (listCount == 0) {
			setWithOneItem(newItem);
		} else {
			// new item's "next" is the current "first"
			newItem.setNextItem(firstItem);
			// set the original first's "previous" to new
			firstItem.setPrevItem(newItem);
			// set new item as the new "first"
			firstItem = newItem;
		}

		listCount++;
	}
	
	public void addItemAtBack(Item newItem) {
		
		if (listCount == 0) {
			setWithOneItem(newItem);
		} else {
			// new item's "previous" is the current "last"
			newItem.setPrevItem(lastItem);
			// set the original last's "next" to new.
			lastItem.setNextItem(newItem);
			// set new item as the new "last"
			lastItem = newItem;
		}
	}
	
	public Item findItemByName(String name) {
		
		Item currentItem = firstItem;
		
		while (currentItem != null) {
			if (currentItem.getName().equals(name)) {
				// found!
				return currentItem;
			}
			
			currentItem = currentItem.getNextItem();
		}
		
		return null;
	}

	public boolean removeItemByName(String name) {
		
		Item itemFound = findItemByName(name);
		
		if (itemFound != null) {
			Item previous = itemFound.getPrevItem();
			Item next = itemFound.getNextItem();
			
			previous.setNextItem(next);
			next.setPrevItem(previous);
			
			listCount--;
			return true;
		}
		
		return false;
	}
	
	public Item getFirstItem() {
		return firstItem;
	}
	
	public Item getLastItem() {
		return lastItem;
	}
	
	public int getListCount() {
		return this.listCount;
	}
	
	public String toString() {
		
		StringBuilder returnVal = new StringBuilder("\n");
		
		Item item = firstItem;
		
		while (item != null) {
			returnVal.append(item.getName());
			returnVal.append("\n");
			item = item.getNextItem();
		}
		
		return returnVal.toString();
	}
}
