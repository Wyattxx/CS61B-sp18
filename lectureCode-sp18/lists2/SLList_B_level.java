/**
 * An SLList is a list of integers, which hides the terrible truth of the
 * nakedness within.
 */
public class SLList {
	private static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
			// System.out.println(size);
		}
	}

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(63, null);
		size = 0;
	}

	public SLList(int x) {
		sentinel = new IntNode(63, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	/** pass in an array of ints */
	public SLList(int[] x_array) {
		sentinel = new IntNode(63, null);
		size = 0;
		IntNode cache = sentinel; //declare the type!
		for (int x: x_array) {
			cache.next = new IntNode(x, null);
			size += 1;
			cache = cache.next;
		}
	}

	/** Adds x to the front of the list. */
	public void addFirst(int x) {
		sentinel.next = new IntNode(x, sentinel.next);
		size = size + 1;
	}

	/** Returns the first item in the list. */
	public int getFirst() {
		return sentinel.next.item;
	}

	/** Adds x to the end of the list. */
	public void addLast(int x) {
		size = size + 1;

		IntNode p = sentinel;

		/* Advance p to the end of the list. */
		while (p.next != null) {
			p = p.next;
		}

		p.next = new IntNode(x, null);
	}

	/** Returns the size of the list. */
	public int size() {
		return size;
	}

	/** Delete the first node */
	public void deleteFirst() {
		sentinel.next = sentinel.next.next;
	}

	public static void main(String[] args) {
		/* Creates a list of one integer, namely 10 */
		SLList L = new SLList();
		L.addLast(20);
		L.addFirst(10);
		L.addFirst(5);
		L.deleteFirst();
		int[] x_array = new int[]{10, 20, 30, 40};
		SLList L2 = new SLList(x_array);
		System.out.println(L2.size());
	}
}