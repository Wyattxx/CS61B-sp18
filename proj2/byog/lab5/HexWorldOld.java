package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorldOld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    //private static final long SEED = 2873123;
    //private static final Random RANDOM = new Random(SEED);


    /** position of the hexagon to add */
    private static class Position {
        private int xx;
        private int yy;
        private Position(int x, int y) {
            xx = x;
            yy = y;

        }
    }

    /** return true if the hexagon to draw is within the world */
    private static boolean checkPosition(TETile[][] world, Position p, int size) {
        return true;
    }

    /**
     * calculate the length of each row, return an array
     */
    private static int[] getRowLength(int size) {
        int[] rowLength = new int[2 * size];
        for (int i = 0; i < 2 * size; i++) {
            if (i < size) {
                rowLength[i] = size + 2 * i;
            } else {
                rowLength[i] = size + 2 * (2 * size -1 - i); //数组左右对称分布
            }
        }
        return rowLength;
    }

    /**
     * calculate the start position of each row, return an array
     */
    private static Position[] getRowStartPos(Position p, int size) {
        Position[] RowStartPos = new Position[2 * size];
        for (int i = 0; i < 2 * size; i++) {
            if (i < size) {
                RowStartPos[i] = new Position(p.xx - i, p.yy + i);
            } else {
                RowStartPos[i] = new Position(RowStartPos[2 * size -1 - i].xx, p.yy + i);
            }
        }
        return RowStartPos;
    }

    /** draw a row starts from Position p with length l */
    private static void drawOneRow(TETile[][] world, Position p, int length, TETile t) {
        for (int i = p.xx; i < p.xx + length; i++) {
            world[i][p.yy] = t;
        }
    }

    /**
     * add a hexagon of size to the world at position p
     * @param p specifies the lower left corner of the hexagon.
     */
    public static void drawHexagon (TETile[][] world, Position p, int size, TETile t) {
        if (!checkPosition(world, p, size)) {
            throw new IllegalArgumentException("the hexagon is out of boundary");
        }
        Position[] pos = getRowStartPos(p, size);
        int[] length = getRowLength(size);
        for (int i = 0; i < size * 2; i++) {
            drawOneRow(world, pos[i], length[i], t);
        }
    }



    /**
     * add a column of N hexagons of size s to the world at position p
     * @param p specifies the lower left corner of the hexagon.
     */
    public static void drawRandomVerticalHexes (TETile[][] world, Position p, int s, int n, TETile t) {
        for (int i = 0; i < n; i++) {
            p.yy = p.yy + 2 * s;
            drawHexagon(world, p, s, t);
        }


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

        Position p = new Position(15, 0);
        int size = 3;
        TETile t = Tileset.PLAYER;
        //drawHexagon(world, p, size, t);
        drawRandomVerticalHexes(world, p, size, 5, t);

        //generate the hex world
        ter.renderFrame(world);
    }




}
