package byog.Core;

import java.util.Random;

/** position of a tile */
public class Position {
    public int x;
    public int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Random random) {
        x = random.nextInt(10);
        y = random.nextInt(10);
    }
}
