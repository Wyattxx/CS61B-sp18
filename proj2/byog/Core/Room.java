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

    /**
     * make sure the col2 bottom room is connected with col1 bottom room
     */
    public Room(Random random, Room prevColBottomRoom) {
        Position leftBottom = prevColBottomRoom.position;
        int newPosX = leftBottom.x + prevColBottomRoom.width;
        int newPosY = RandomUtils.uniform(random, leftBottom.y, leftBottom.y + prevColBottomRoom.height); // [a, b), 右边开区间！
        //int newPosY = 1; // [a, b), 右边开区间！
        position = new Position(newPosX, newPosY);
        width = RandomUtils.uniform(random, 3, 7);
        height = RandomUtils.uniform(random, 1, 7);
    }
}
