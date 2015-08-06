public class SplitTest
{
    public static void main(String[] args) {
        String bart = "a b c d e f g";
        String[] lisa = bart.split("");
        for (int i = 0; i < lisa.length; i++)
        {
            lisa[i] = lisa[i].strip(" ");
            System.out.println(lisa[i]);
        }
    }
}