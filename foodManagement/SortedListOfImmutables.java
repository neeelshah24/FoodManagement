package foodManagement;

/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {

	/*
	 * STUDENTS:  You may NOT add any other instance variables to this class!
	*/
	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.  (Note that this is NOT the same thing as setting the internal
	 * instance variable to null.) 
	 */
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  The copy must be made in such a way that future changes to
	 *  either of these two lists will not affect the other. In other words, 
	 *  after this constructor runs, adding or removing things from one of 
	 *  the lists must not have any effect on the other list.
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		Listable [] temporary = new Listable[other.getSize()];
		for (int start = 0; start < other.getSize(); start++) {
			temporary[start] = other.get(start);
		}
		items = temporary;
	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	public int getSize() {
		return items.length;
	}
	
	/**
	 * Returns a reference to the item in the ith position in the list.  (Indexing
	 * is 0-based, so the first element is element 0).
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {
		return items[i];
	}
	
	/**
	 * Adds an item to the list.  This method assumes that the list is already
	 * sorted in alphabetical order based on the names of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain alphabetized by names.
	 * 
	 * In order to accomodate the new item, the internal array must be re-sized 
	 * so that it is one unit larger than it was before the call to this method.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	public void add(Listable itemToAdd) {
		Listable[] temperory = new Listable[items.length + 1];
		boolean added = false;
		
		for(int index = 0; index < items.length; index++) {
			temperory[index] = items[index];
		}
			
		for (int start = 0; start < items.length; start++) {
			
			if (itemToAdd.getName().compareTo(items[start].getName()) < 0 
						&& added == false) {

				temperory[start] = itemToAdd;
					
				for (int restart = start + 1; restart < temperory.length; 
						restart++) {
					temperory[restart] = items[restart - 1];
				}
					
				added = true;		
			}
		}
			
		if (added == false) {
			temperory[temperory.length - 1] = itemToAdd;
		}
		
		items = temperory;
	}

		


	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items.
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	public void add(SortedListOfImmutables listToAdd) {
		for(int index = 0; index < listToAdd.getSize(); index++) {
			this.add(listToAdd.items[index]);
		}
		
		if (this == listToAdd) {
			SortedListOfImmutables temp = new SortedListOfImmutables(this);
		}
	}
	
	/**
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be 
	 * removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	public void remove(Listable itemToRemove) {
		boolean isItemInList = false;
		int positionItem = 0;
		
		for (int index = 0; index < items.length; index++) {
			if (items[index].getName().equals(itemToRemove.getName())) {
				isItemInList = true;
				positionItem = index;
				break;
			}
		}
		
		if (isItemInList) {
			Listable [] temporary = new Listable[items.length - 1];
			
				for (int index = 0; index < positionItem; index++) {
					temporary[index] = items[index];
					
				}
				
				for (int secondIndex = positionItem; secondIndex < 
						temporary.length; secondIndex++) {
					temporary[secondIndex] = items[secondIndex + 1];
					
				
			}
				items = temporary; 
		}
	}

	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		for (int index = 0; index < listToRemove.getSize(); index++) {
			this.remove(listToRemove.items[index]);
		}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int wholesaleSum = 0;
		for (int start = 0; start < items.length; start++) {
			wholesaleSum += items[start].getWholesaleCost();
		}
		return wholesaleSum;
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {
		int retailSum = 0;
		for (int start = 0; start < items.length; start++) {
			retailSum += items[start].getRetailValue();
		}
		return retailSum;
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		boolean isItemPresent = false; 
		
		for (int start = 0; start < items.length; start++) {
			if (items[start].equals(itemToFind)) {
				isItemPresent = true;
			}
		}
		return isItemPresent;
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 * (In other words, this method will return true if ALL of the items in 
	 * the parameter are contained in the current list.  If anything is missing,
	 * then the return value will be false.)
	 * 
	 * @param listToCheck list of items that may or may not be a subset of the
	 * current list
	 * @return true if the parameter is a subset of the current list; false 
	 * otherwise
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		SortedListOfImmutables temporary = new SortedListOfImmutables(this);

		boolean isItemPresent = false;
		
		for (int index = 0; index < listToCheck.getSize(); index++) {
			if (temporary.checkAvailability(listToCheck.items[index]) 
					== false) {
				
				isItemPresent = false;
				break;
				
			} else {
				
				isItemPresent = true;
				for (int position = 0; position < temporary.getSize(); 
						position++) {
					if (temporary.items[position].equals
							(listToCheck.items[index])) {
						
						temporary.remove(temporary.items[position]);
						break;
					}
				}
			}
		}
		return isItemPresent;

	}

	/*
	 * We'll give you this one for free.  Do not modify this method or you
	 * will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
