import java.util.Arrays;
class LinkedBagTest 
{
	public static <T> void main (String[] args) {
		BagInterface <String> list1 = new LinkedBag<>();
		BagInterface <String> list2 = new LinkedBag<>();
		list1.add("B");
		list1.add("B");
		list1.add("D");
		list1.add("E");
		list2.add("A");
		list2.add("B");
		list2.add("C");
		System.out.println("LinkedBag 1 is " + Arrays.toString(list1.toArray()));
		System.out.println("LinkedBag 2 is " + Arrays.toString(list2.toArray()));
		System.out.println("Union of Array 1 and 2 is "+ Arrays.toString(list1.union(list2).toArray()));
		System.out.println("Intersection of Array 1 and two is "+ Arrays.toString(list1.intersection(list2).toArray()));
		System.out.println("Difference of Array 1 and two is "+ Arrays.toString(list1.difference(list2).toArray()));
	}
}