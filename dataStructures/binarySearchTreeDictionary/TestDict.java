import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestDict {

    static BinaryTree<Integer, String> tree;

    public static void main(String[] args) {
        tree = new BinaryTree<Integer, String>();

        System.out.println("Inserting <key, value>:");
        System.out.println("\t<5, may>, <3, mar>, <2, feb>, <1, jan>, <6, jun>,"
                + "<12, dec>, <10, oct>, <11, nov>, <4, apr>, <7, jul>,"
                + "<8, aug>, <9, sep>");
        try {
            tree.insert(5, "may");
            tree.insert(3, "mar");
            tree.insert(2, "feb");
            tree.insert(1, "jan");
            tree.insert(6, "jun");
            tree.insert(12, "dec");
            tree.insert(10, "oct");
            tree.insert(11, "nov");
            tree.insert(4, "apr");
            tree.prettyPrint();
            tree.insert(7, "jul");
            tree.insert(8, "aug");
            tree.insert(9, "sep");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println("Size:  " + tree.size());
            System.out.println("Height: " + tree.height());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        System.out.println("Testing Pretty Print ...");
        try {
            tree.prettyPrint();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        try {
            System.out.println("\nTesting find() ...");
            testFind(tree.find(1), 1, "jan");
            testFind(tree.find(2), 2, "feb");
            testFind(tree.find(3), 3, "mar");
            testFind(tree.find(7), 7, "jul");
            testFind(tree.find(8), 8, "aug");
            testFind(tree.find(11), 11, "nov");
            testFind(tree.find(12), 12, "dec");
            testFind(tree.find(20), 20, null);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println("\nInsering duplicate key: ...");
            tree.insert(2, "fff");
            testFind(tree.find(2), 2, "fff");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println("\nTesting getKeys() ...");
            Set<Integer> keys = tree.getKeys();
            for (Integer k : keys)
            {
                System.out.println("\tKey= " + k);
            }

            System.out.println("\nTesting getValues() ...");
            List<String> values = tree.getValues();
            for (String v : values)
            {
                System.out.println("\tValue= " + v);
            }

            System.out.println("\nTesting getEntries() ...");
            Set<Entry<Integer, String>> entries = tree.getEntries();
            for (Entry e : entries)
            {
                System.out.println("\tEntry= " + e.toString());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        try {
            Iterator<Entry<Integer, String>> it;
            
            System.out.println("\nTesting preorder iterator (while loop) ...");
            it = tree.preorder();
            while (it.hasNext()) {
                System.out.println("\tEntry= " + it.next().toString());
            }

            System.out.println("\nTesting preorder iterator (for each loop) ...");
            for (Entry<Integer, String> e : tree) {
                System.out.println("\tEntry= " + e.toString());
            }
            

            System.out.println("\nTesting inorder iterator (while loop) ...");
            it = tree.inorder();
            while (it.hasNext()) {
                System.out.println("\tEntry= " + it.next().toString());
            }

            System.out.println("\nTesting inorder iterator (for each loop) ...");
            tree.defaultIterator("inorder");
            for (Entry<Integer, String> e : tree) {
                System.out.println("\tEntry= " + e.toString());
            }

//        System.out.println("\nTesting inorder simple ...");
//        tree.inorder();
        
            System.out.println("\nTesting postorder iterator (while loop) ...");
            it = tree.postorder();
            while (it.hasNext()) {
                System.out.println("\tEntry= " + it.next().toString());
            }

            System.out.println("\nTesting postorder iterator (for each loop) ...");
            tree.defaultIterator("postorder");
            for (Entry<Integer, String> e : tree) {
                System.out.println("\tEntry= " + e.toString());
            }

//        System.out.println("\nTesting postorder simple ...");
//        tree.postorder();
            
            System.out.println("\nTesting breadth first iterator (while loop) ...");
            it = tree.breadthFirst();
            while (it.hasNext()) {
                System.out.println("\tEntry= " + it.next().toString());
            }

            System.out.println("\nTesting breadth first iterator (for each loop) ...");
            tree.defaultIterator("bfs");
            for (Entry<Integer, String> e : tree) {
                System.out.println("\tEntry= " + e.toString());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        
        try {
            tree.prettyPrint();
            System.out.println("Size:  " + tree.size());
            System.out.println("\nTesting remove() (for leaf nodes) ...");
            System.out.println("\n\tRemoving 1: ");
            tree.remove(1);
            tree.prettyPrint();
            System.out.println("Size:  " + tree.size());
            
            System.out.println("\n\tRemoving 11: ");
            tree.remove(11);
            tree.prettyPrint();
            System.out.println("Size:  " + tree.size());

            System.out.println("\nTesting remove() (nodes with one child) ...");
            System.out.println("\n\tRemoving 8: ");
            tree.remove(8);
            tree.prettyPrint();
            System.out.println("Size:  " + tree.size());

            System.out.println("\n\tRemoving 12: ");
            tree.remove(12);
            tree.prettyPrint();
            System.out.println("Size:  " + tree.size());

            tree.insert(-1, "n1");
            tree.insert(15, "n2");
            System.out.println("\nJust inserted nodes with -1 and 15 keys ...");
            tree.prettyPrint();

            System.out.println("\nNew height: " + tree.height());
            System.out.println("New size: " + tree.size());

            System.out.println("\nTesting remove() (nodes with two children) ...");
            System.out.println("\n\tRemoving 3: ");
            tree.remove(3);
            tree.prettyPrint();
            System.out.println("Size:  " + tree.size());

            System.out.println("\n\tRemoving 5: ");
            tree.remove(5);
            tree.prettyPrint();
            System.out.println("Size:  " + tree.size());

            System.out.println("Testing remove() (key not exists) ...");
            System.out.println("\n\tRemoving 20: ");
            tree.remove(20);
            System.out.println("Size:  " + tree.size());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }

    private static void testFind(Object v, Object k, Object truth) {
        System.out.println("Called find() on " + k);
        if (v == null) {
            System.out.println("  returned null.");
            if (truth != null) {
                System.out.println("  SHOULD BE " + truth + ".");
            }
        } else {
            System.out.println("  returned " + v + ".");
            if (!v.equals(truth)) {
                if (truth == null) {
                    System.out.println("  SHOULD BE null.");
                } else {
                    System.out.println("  SHOULD BE " + truth + ".");
                }
            }
        }
    }
}
