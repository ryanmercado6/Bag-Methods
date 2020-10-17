
class ResizeableArrayBag <T> implements BagInterface <T>{
private T[] bag;
private static final int DEFAULT_CAPACITY = 25;
private int numberOfEntries;
	//constructor
	public ResizeableArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	// constructor with parameter choosing array
	public ResizeableArrayBag(int capacity) {
		numberOfEntries = 0;
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[capacity];
		bag = tempBag;
	}
	// remove an entry given its index
	private T removeEntry(int givenIndex)
	{
		T result = null;
		if (!isEmpty()&&(givenIndex>=0))
		{
			result=bag[givenIndex];
			bag[givenIndex]=bag[numberOfEntries-1];
			bag[numberOfEntries-1]= null;
			numberOfEntries--;
		}
		return result;
	}
	// find where an entry is 
	private int getIndexOf(T anEntry)
	{
		int where = -1;
		boolean found = false;
		int index = 0;
		while(!found && (index<numberOfEntries))
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			}
			index++;
		}
		return where;
	}
	//get number of how many entries there are
	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	}
	//check if bag is empty
	@Override
	public boolean isEmpty() {
		return numberOfEntries==0;
	}
	//check if bag is full
	public boolean isFull() {
		return numberOfEntries==bag.length;
	}
	//add a new entry and double if its full so its resizeable
	@Override
	public boolean add(T newEntry) {
		boolean result = true;
		if(this.isFull())
		{
			@SuppressWarnings("unchecked")
			// Double the Size of an array when full
			T[] tempBag = (T[])new Object[numberOfEntries*2];
			for (int i=0;i<numberOfEntries;i++) {
				tempBag[i]=bag[i];
			}
			bag = tempBag;
			this.add(newEntry);
			result = false;
		}
		else
		{
			bag[numberOfEntries]= newEntry;
			numberOfEntries++;
		}
		return result;
	}
	//remove last entry
	@Override
	public T remove() {
		T result = removeEntry(numberOfEntries-1);
		return result;
	}
	//remove a entry
	@Override
	public boolean remove(T anEntry) {
		int index = getIndexOf(anEntry);
		T result = removeEntry(index);
		return anEntry.equals(result);
	}
	//empty the list
	@Override
	public void clear() {
		while(!isEmpty())
			remove();
	}
	//find how many times a entry repeats
	@Override
	public int getFrequencyOf(T anEntry) {
		int counter =0;
		for (int i=0; i< numberOfEntries; i++)
		{
			if (anEntry.equals(bag[i]))
			{
				counter++;
			}
		}
		return counter;
	}
	//check if the bag contains a certain value
	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		return getIndexOf(anEntry)>-1;
	}
	//Turn the bag into an array
	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		//making a new array
		T[] result = (T[])new Object[numberOfEntries];
		for (int index=0; index <numberOfEntries; index ++)
		{
			//adding bag to array
			result[index]= bag [index];
		}
		return result;
	}
	//Union, adding up all values into one bag
	@SuppressWarnings("unchecked")
	@Override
	public BagInterface<T> union(BagInterface<T> otherBag) {
		// creating arrays from the given bags
		T[] bag1 = this.bag;
		T[] bag2= otherBag.toArray();
		//creating bag to add the values in
		BagInterface <String> unionBag = new ResizeableArrayBag<>();
		//adding values from first array
		for (int index=0;index<numberOfEntries;) {
			unionBag.add((String) bag1[index]);
			index++;
		}
		//adding values from second array
		for (int i=0;i<bag2.length;) {
			unionBag.add((String) bag2[i]);
			i++;
		}
		return (BagInterface<T>) unionBag;
	}
	//Intersection bag gives the values that appear in both bags
	@SuppressWarnings("unchecked")
	@Override
	public BagInterface<T> intersection(BagInterface<T> otherBag) {
		//create new arrays
		T[] tempBag=this.toArray();
		T[]	copy = otherBag.toArray();
		//create copy of the other bag
		BagInterface <T> copyBag = new ResizeableArrayBag<>();
		for (int i=0;i<otherBag.getCurrentSize();i++) {
			copyBag.add(copy[i]);
		}
		//create bag to return
		BagInterface <String> intersectionBag = new ResizeableArrayBag<>();
		/*iterate through this bag and see if it is contained in the other bag and if it is then remove it 
		then add to the bag to be returned*/
		for (int i=0;i<numberOfEntries;i++) {
			if (copyBag.contains(tempBag[i])){
				intersectionBag.add((String) tempBag[i]);
				copyBag.remove(tempBag[i]);
			}
		}
		 return (BagInterface<T>) intersectionBag;
	}
	// Difference bag returns the values that would be left when removing the values that also occur in the second bag
	@SuppressWarnings("unchecked")
	@Override
	public BagInterface<T> difference(BagInterface<T> otherBag) {
		//create arrays of the bags
		T[] tempThisArray=this.toArray();
		T[]	tempOtherArray = otherBag.toArray();
		//create a copy of the other bag
		BagInterface <T> copyOtherBag = new ResizeableArrayBag<>();
		//copy the values
		for (int i=0;i<otherBag.getCurrentSize();i++) {
			copyOtherBag.add(tempOtherArray[i]);
		}
		// Create a bag to be returned
		BagInterface <String> differenceBag = new ResizeableArrayBag<>();
		/* Check if the first bag doesn't contain a value in the second and if it doesn't then add it to the return result
		if it does them remove it*/
		for (int i=0;i<tempThisArray.length;i++) {
			if (!copyOtherBag.contains(tempThisArray[i])){
				differenceBag.add((String) tempThisArray[i]);
			}
			else {
				copyOtherBag.remove(tempThisArray[i]);
			}
		}
		 return (BagInterface<T>) differenceBag;
	}
	}

	