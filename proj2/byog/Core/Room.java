package byog.Core;

import java.util.Random;

/** position and shape of a Room */
public class Room {
    public int width;
    public int height;
    public Position position;

    public Room(Position position, int width, int height) {
        this.height = height;
        this.position = position;
        this.width = width;
    }

    public Room(Random random) {
        position = new Position(random.nextInt(10), random.nextInt(10)); //first room pos
        width = RandomUtils.uniform(random, 1, 10);
        height = RandomUtils.uniform(random, 1, 10);
    }
}
