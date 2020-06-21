package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    /** position of the hexagon to add */
    private static class Position {
        private int xx;
        private int yy;
        private Position(int x, int y) {
            xx = x;
            yy = y;
        }
    }

    /** Pick a RANDOM tile */
    private static TETile randomTile() {
        Random rand = new Random();
        int x = rand.nextInt(6);
        switch (x) {
            case 0 : return Tileset.WALL;
            case 1 : return Tileset.FLOWER;
            case 2 : return Tileset.MOUNTAIN;
            case 3 : return Tileset.GRASS;
            case 4 : return Tileset.TREE;
            case 5 : return Tileset.WATER;
            default: return Tileset.PLAYER;
        }
    }

    /** return true if the hexagon to draw is within the world */
    private static boolean checkPosition(TETile[][] world, Position p, int size) {
        return true;
    }

    /** calculate the length of ith row */
    private static int getRowLength(int size, int i) {
        if (i < size) {
            return size + 2 * i;
        } else {
            return size + 2 * (2 * size - 1 - i); //数组左右对称分布
        }
    }

    /** calculate the most left position of ith row */
    private static Position getRowStartPos(Position p, int size, int i) {
        if (i < size) {
            return new Position(p.xx - i, p.yy + i);
        } else {
            return new Position(p.xx - (2 * size - 1 - i), p.yy + i); //数组左右对称分布
        }
    }

    /** draw a row starts from Position p with length l */
    private static void drawOneRow(TETile[][] world, Position p, int l, TETile t) {
        for (int i = 0; i < l; i++) {
            world[p.xx + i][p.yy] = t;
        }
    }

    /**
     * add a hexagon into the world, at position p, with size s
     * @param p specifies the lower left corner of the hexagon
     */
    public static void drawHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (!checkPosition(world, p, s)) {
            throw new IllegalArgumentException("the hexagon is out of boundary");
        }
        for (int i = 0; i < 2 * s; i++) {
            Position pos = getRowStartPos(p, s, i);
            int length = getRowLength(s, i);
            drawOneRow(world, pos, length, t);
        }
    }

    /**
     * add a column of n hexagons into the world at position p with size s
     * @param p specifies the lower left corner of the hexagon.
     */
    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int n) {
        for (int i = 0; i < n; i++) {
            TETile t = randomTile();
            drawHexagon(world, p, s, t);
            p = nextUp(p, s);
        }
    }

    /** return the position of the next up hexagon */
    private static Position nextUp(Position p, int s) {
        return new Position(p.xx, p.yy + 2 * s);
    }

    /** return the position of the next right hexagon */
    private static Position nextRight(Position p, int s) {
        return new Position(p.xx + 2 * s - 1, p.yy + s);
    }

    /** return the position of the next left hexagon */
    private static Position nextLeft(Position p, int s) {
        return new Position(p.xx - 2 * s - 1, p.yy + s);
    }


    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position p = new Position(10, 0);
        int size = 4;
        //TETile t = Tileset.PLAYER;
        //drawHexagon(world, p, size, t);
        int n = HEIGHT / 2 / size; // maximum number of hexagons in one column

        for (int i = 0; i < 5; i++) {
            drawRandomVerticalHexes(world, p, size, n);
            n -= 1;
            p = nextRight(p, size);
        }
        //generate the hex world
        ter.renderFrame(world);
    }
}
