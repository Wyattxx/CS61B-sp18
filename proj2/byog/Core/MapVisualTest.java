package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;
import org.junit.Test;

import java.util.Random;
import java.util.SortedSet;

import static org.junit.Assert.*;

public class MapVisualTest {

    public static void main(String[] args) {
        int WIDTH = 50;
        int HEIGHT = 50;
        long SEED = 12345;
        Random RANDOM = new Random();

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }

        // generate the random world
        MapGenerator.createWorld(world, RANDOM);

        //generate the hex world
        ter.renderFrame(world);

    }

}
