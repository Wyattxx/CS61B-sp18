package byog.Core;

import byog.TileEngine.*;

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
        for (int i = 0; i < 5; i++) {
            addRoom(world, randomRoom);
            randomRoom = nextRoom(randomRoom, random);
        }

        outsideWall(world);
        return world;
    }

    /** calculate the next room that is next to the current room */
    private static Room nextRoom(Room room, Random random) {
        int nowPosX = room.position.x;
        int nowPosY = room.position.y;
        int newPosX = RandomUtils.uniform(random, nowPosX - 1, nowPosX + room.width);
        int newPosY = RandomUtils.uniform(random, nowPosY - 1, nowPosY + room.height);
        newPosX = nowPosX + room.width;
        newPosY = nowPosY + room.height - 1;

        Position newPos = new Position(newPosX, newPosY);
        int width = RandomUtils.uniform(random, 1, 10);
        int height = RandomUtils.uniform(random, 1, 10);
        return new Room(newPos, width, height);
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (world[x - 1 + i][y - 1 + j].equals(Tileset.FLOOR)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** make tiles outside the wall be nothing */
    private static void outsideWall(TETile[][] world) {
        for (int x = 1; x < world.length-1; x += 1) {
            for (int y = 1; y < world[0].length-1; y += 1) {
                if (checkTile(world, x, y)) {
                    world[x][y] = Tileset.NOTHING;
                }
            }
        }
    }


}


