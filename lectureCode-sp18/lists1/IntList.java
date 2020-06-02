public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (this.rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Returns the ith value in this list. */
	public int get(int i) {
		if (i == 0) {
			return this.first;
		}
		return this.rest.get(i - 1);

	}

	/**
	 * Returns an IntList identical to L, but with each element incremented by x. L
	 * is not allowed to change.
	 */
	public static IntList incrList(IntList L, int x) {
		if (L.rest == null) {
			return new IntList(L.first + x, null);
		}
		return new IntList(L.first + x, incrList(L.rest, x));
	}

	/** 
	 * Returns an IntList identical to L, but with each element incremented by x.
	 * Not allowed to use the 'new' keyword.
	 * 这里写了好久，if else 都是return L
	 * 我觉得其实不需要return，因为就是修改了L本身
	 * 或者开头就intlist q = l; return q
	 */
	public static IntList dincrList(IntList L, int x) {
		if (L.rest == null) {
			L.first += x;
		} else {
			L.first += x;
			L.rest = dincrList(L.rest, x);
		}
		return L;
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		//System.out.println(L.size());
		System.out.print(incrList(L, 3).get(0));
		System.out.println(L.get(0));
		System.out.print(incrList(L, 3).get(1));
		System.out.println(L.get(1));

		System.out.print(dincrList(L, 3).get(0)); 
		System.out.println(L.get(0));
		System.out.print(dincrList(L, 3).get(1)); 
		System.out.println(L.get(1));
	}
}