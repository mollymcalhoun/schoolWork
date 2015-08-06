public class ListTest {
    public static void main(String[] args) {
        System.out.println();
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.addToHead(5);
        ll.addToHead(6);
        ll.addToHead(9);
        ll.addToHead(1);
        ll.sortAscending();
        ll.printAll();
        ll.addToHead(6);
        ll.addToHead(2);
        ll.addToTail(999);
        ll.addToTail(888);
        ll.printAll();
        //ll.deleteFromHead();
        boolean is999 = ll.isInList(999);
        System.out.printf("999 is in list? %b\n", is999);
        ll.deleteFromHead();
        ll.printAll();
        ll.deleteFromTail();
        ll.printAll();
        ll.delete(9);
        ll.printAll();
        ll.addAfter(49, 6);
        ll.printAll();
        ll.addAfter(123, 999);
        ll.printAll();
        //System.out.printf("Minimum value is %d\n", ll.min());
        ll.addAfter(0, 6);
        ll.printAll();
        //System.out.printf("Minimum value is %d\n", ll.min());
        ll.sortAscending();
        ll.printAll();
        ll.sortDescending();
        ll.printAll();

        LinkedList<String> sl = new LinkedList<String>();
        sl.addToHead("bart");
        sl.addToHead("lisa");
        sl.addToHead("homer");
        sl.addToHead("marge");
        sl.addToHead("maggie");
        sl.addToHead("flanders");
        sl.printAll();
        sl.delete("flanders");
        sl.addAfter("moe", "homer");
        sl.printAll();
        boolean isLisa = sl.isInList("lisa");
        System.out.printf("lisa is in list? %b\n", isLisa);
        sl.sortAscending();
        sl.printAll();
        sl.sortDescending();
        sl.printAll();
        System.out.println();
    }
}