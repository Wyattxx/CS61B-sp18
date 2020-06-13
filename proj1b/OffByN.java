public class OffByN implements CharacterComparator{

    int N;
    public OffByN(int n) {
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == N;
    }
}
