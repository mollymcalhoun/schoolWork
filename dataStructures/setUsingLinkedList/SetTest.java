public class SetTest
{
    public static void main(String[] args)
    {
        int[] bartV = {1, 3, 5, 7, 9};
        Set bart = new Set("bart", bartV);

        int[] lisaV = {1, 3, 2};
        Set lisa = new Set("lisa", lisaV);

        Set elf = new Set("empty");

        /*
        System.out.println(bart.values.head.value.equals(2));
        System.out.println(bart.values.isInList(1));
        System.out.println(bart.values.isInList(2));
        System.out.println(bart.checkMembership(1));
        System.out.println(bart.checkMembership(2));
        System.out.println(bart.checkSubset(lisa));
        System.out.println(lisa.checkSubset(bart));
        System.out.println(lisa.checkSubset(elf));
        */

        System.out.println(bart.checkSorted());
        bart.values.printAll();
        System.out.println(lisa.checkSorted());
        System.out.println(elf.checkSorted());
        bart.union(lisa).values.printAll();
        bart.intersection(lisa).values.printAll();
        bart.intersection(elf).values.printAll();
        elf.values.printAll();
        lisa.subtraction(bart).values.printAll();
        elf.subtraction(bart).values.printAll();
    }
}