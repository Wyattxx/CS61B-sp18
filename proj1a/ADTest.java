/**
 * Performs some basic array list tests.
 */
public class ADTest {

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        ArrayDeque<Integer> ad = new ArrayDeque();
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(3);
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addLast(6);
        ad.addLast(7);
        ad.addLast(8);
        ad.addLast(9);
        ad.removeFirst();
        ad.removeLast();
        int x = ad.get(4);
        ad.printDeque();

    }
}
