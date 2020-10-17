
 public class LinkedBag<T> implements BagInterface<T>
 {
	private Node firstNode;
	private int numberOfEntries;
	//constructor
	public LinkedBag()
	{
		firstNode=null;
		numberOfEntries = 0;
	}
	//class for node methods
	private class Node

	{
		private T data;
		private Node next;
		private Node(T dataPortion)
		{
			this(dataPortion,null);
		}
		private Node(T dataPortion, Node nextNode)
		{
			data=dataPortion;
			next = nextNode;
		}
		private T getData()
		{
			return data;
		}
		private void setData(T newData)
		{
			data=newData;
		}
		private Node getNextNode()
		{
			return next;
		}
		@SuppressWarnings("unused")
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		}
	}
	// find where a node is
	private Node getReferenceTo(T anEntry) 
	{
		boolean found = false;
		Node currentNode = firstNode;
		while(!found && (currentNode!=null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		}
		return currentNode;
	}
	// get size
	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	}
	// check if empty
	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	//add a value
	@Override
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.next = firstNode;
		firstNode=newNode;
		numberOfEntries++;
		return true;
	}
	//remove the last value
	@Override
	public T remove() {
		// TODO Auto-generated method stub
		T result=null;
		if (firstNode !=null)
		{
			result = firstNode.getData();
			firstNode=firstNode.getNextNode();
			numberOfEntries--;
		}
		return result;
	}
	//remove a specific entry
	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		if (nodeN != null)
		{
		nodeN.setData(firstNode.getData());
		firstNode = firstNode.getNextNode();
		numberOfEntries--;
		result = true;
		} 
		return result;
	}
	//empty the list
	@Override
	public void clear() {
		while (!isEmpty())
			remove();
	}
	//see how many times a value occurs
	@Override
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int counter =0;
		Node currentNode = firstNode;
		while((counter<numberOfEntries)&&(currentNode !=null))
		{
			if (anEntry.equals(currentNode.getData()))
			{
				frequency++;
			}
			counter ++;
			currentNode = currentNode.getNextNode();
		}
		return frequency;
	}
	//see if a value is contained
	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode =firstNode;
		while(!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found=true;
			else
				currentNode = currentNode.getNextNode();
		}
		return found;
	}
	//turn the values into arrays
	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		T[] result=(T[])new Object[numberOfEntries];
		int index=0;
		Node currentNode = firstNode;
		while ((index<numberOfEntries)&& (currentNode != null))
		{
			result[index]=currentNode.getData();
			index++;
			currentNode = currentNode.getNextNode();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BagInterface<T> union(BagInterface<T> otherBag) {
		//create arrays from the given lists
		T[] array1 = this.toArray();
		T[] array2= otherBag.toArray();
		//create a list to return
		BagInterface<String> result = new LinkedBag<>();
		//add values from first array
		for (int i=0;i<array1.length;i++)
		{
			result.add((String) array1[i]);
		}
		for (int i=0;i<array2.length;i++)
		//add values from second array
		{
			result.add((String) array2[i]);
		}
		return (BagInterface<T>) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BagInterface<T> intersection(BagInterface<T> otherBag) {
		//create arrays from given lists
		T[] tempBag=this.toArray();
		T[]	copy = otherBag.toArray();
		//create bag to look through and remove as needed
		BagInterface <T> copyBag = new LinkedBag<>();
		//copy values
		for (int i=0;i<otherBag.getCurrentSize();i++) {
			copyBag.add(copy[i]);
		}
		//create bag to return
		BagInterface <String> intersectionBag = new LinkedBag<>();
		//check if values from the first bag are in the second and if they are add it to the return bag
		for (int i=0;i<numberOfEntries;i++) {
			if (copyBag.contains(tempBag[i])){
				intersectionBag.add((String) tempBag[i]);
				copyBag.remove(tempBag[i]);
			}
		}
		 return (BagInterface<T>) intersectionBag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BagInterface<T> difference(BagInterface<T> otherBag) {
		//create arrays of the bags
		T[] tempThisArray=this.toArray();
		T[]	tempOtherArray = otherBag.toArray();
		//create a copy of the other bag
		BagInterface <T> copyOtherBag = new LinkedBag<>();
		//copy the values
		for (int i=0;i<otherBag.getCurrentSize();i++) {
			copyOtherBag.add(tempOtherArray[i]);
		}
		// Create a bag to be returned
		BagInterface <String> differenceBag = new LinkedBag<>();
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



