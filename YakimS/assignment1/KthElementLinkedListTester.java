package CodeUAss1Package;


public class KthElementLinkedListTester {

    public static void main(String[] args) {
        KthElementLinkedList<Integer> myLinkedList = new KthElementLinkedList<>();;
        for(int i = 0 ; i < 10 ; i++)
        {
            myLinkedList.add(i);
        }
        System.out.println("");
        System.out.print("kth ele:"+myLinkedList.getKthLastElement(9));
    }

}
