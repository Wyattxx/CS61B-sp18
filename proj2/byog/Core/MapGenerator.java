package byog.Core;

import byog.TileEngine.*;
import edu.princeton.cs.algs4.Transaction;
import javax.imageio.plugins.tiff.TIFFTagSet;
import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.util.Random;

public class MapGenerator {

    /**
     * create the random world, main method
     * @param world
     * @param random
     * @return
     */
    public static TETile[][] createWorld(TETile[][] world, Random random) {
        worldInitialize(world);

        Room randomRoom = new Room(random);
        while (checkRoom(world, randomRoom)) {
            addRoom(world, randomRoom);
            randomRoom = nextRoom(world, randomRoom, random);
        }
        outsideWall(world);
        return world;
    }

    /** calculate the random positioned next room that is next to the current room */
    private static Room nextRoom(TETile[][] world, Room room, Random random) {
        Position leftBottom = room.position;
        int width = RandomUtils.uniform(random, 1, 11);
        int height = RandomUtils.uniform(random, 1, 11);

        int newPosX = RandomUtils.uniform(random, leftBottom.x, leftBottom.x + room.width);
        int newPosY = RandomUtils.uniform(random, leftBottom.y, leftBottom.y + room.height);
        Position newPos = new Position(newPosX, newPosY);

        return new Room(newPos, width, height);
    }

    /** return true if the room is within the world, check two diagonal vertices */
    private static boolean checkRoom(TETile[][] world, Room room) {
        Position leftBottom = room.position;
        Position rightTop = new Position(leftBottom.x + room.width - 1, leftBottom.y + room.height - 1);
        Position leftBottomPlus = new Position(leftBottom.x - 1, leftBottom.y - 1); // room has wall, wall must be within the world
        Position rightTopPlus = new Position(rightTop.x + 1, rightTop.y + 1); // room has wall, wall must be within the world
        return checkPos(world, leftBottomPlus) && checkPos(world, rightTopPlus);
    }

    /** return true if the position is within the world */
    private static boolean checkPos(TETile[][] world, Position position) {
        int x = position.x;
        int y = position.y;
        return x >= 0 && x <= world.length - 1 && y >= 0 && y <= world[0].length - 1;
    }


    /** add a rectangular room to the world */
    private static void addRoom(TETile[][] world, Room room) {
        for (int i = 0; i < room.width; i++) {
            for (int j = 0; j < room.height; j++) {
                world[room.position.x + i][room.position.y + j] = Tileset.FLOOR;
            }
        }
    }

    /** initialize the world with WALL */
    private static void worldInitialize(TETile[][] world) {
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }
    }

    /** return true if the tile at (x,y) should be nothing */
    private static boolean checkTile(TETile[][] world, int x, int y) {
        if (!world[x][y].equals(Tileset.WALL)) {
            return false;
        }
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || j < 0 || i > world.length -1 || j > world[0].length - 1) {
                    continue;
                }
                if (world[i][j].equals(Tileset.FLOOR)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** make tiles outside the wall be nothing */
    private static void outsideWall(TETile[][] world) {
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                if (checkTile(world, x, y)) {
                    world[x][y] = Tileset.NOTHING;
                }
            }
        }
    }

}


