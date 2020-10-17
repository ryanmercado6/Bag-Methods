import java.util.Arrays;
class ArrayBagTest{
	public static <T> void main (String[] args) {
		BagInterface <String> array1 = new ResizeableArrayBag<>();
		BagInterface <String> array2 = new ResizeableArrayBag<>();
		array1.add("B");
		array1.add("B");
		array1.add("D");
		array1.add("E");
		array2.add("A");
		array2.add("B");
		array2.add("C");
		System.out.println("Bag 1 is " + Arrays.toString(array1.toArray()));
		System.out.println("Bag 2 is " + Arrays.toString(array2.toArray()));
		System.out.println("Union of Array 1 and 2 is "+ Arrays.toString(array1.union(array2).toArray()));
		System.out.println("Intersection of Array 1 and two is "+ Arrays.toString(array1.intersection(array2).toArray()));
		System.out.println("Difference of Array 1 and two is "+ Arrays.toString(array1.difference(array2).toArray()));
	}
}