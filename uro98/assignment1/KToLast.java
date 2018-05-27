public class KToLast {
  
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);

		System.out.println(list.kToLastObject(1));
	}
}