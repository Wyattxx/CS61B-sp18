public class NBody {
    /**
     * read radius learn to use In class
     */
    public static double readRadius(String path) {
        In in = new In(path);
        int num_of_planets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** return an array of the bodys in path.txt */
    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int num_of_planets = in.readInt(); 
        double radius = in.readDouble();
        Planet[] allBodies = new Planet[num_of_planets]; // need to use new to init!
        for (int i = 0; i < num_of_planets; i++) { // use num_of_planets to fit all kinds of inputs
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allBodies[i] = new Planet(xP, yP, xV, yV, m, img); // remember to check the index
        }
        return allBodies;
    }

    public static void main(String[] args) {
        /** input parameters */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allBodies = readPlanets(filename);

        for (double t = 0; t <= T; t = t + dt) {
            double[] xForces = new double[allBodies.length]; // use this to fit all kinds of inputs!
            double[] yForces = new double[allBodies.length];
            int i = 0;
            for (Planet b : allBodies) {
                xForces[i] = b.calcNetForceExertedByX(allBodies);
                yForces[i] = b.calcNetForceExertedByY(allBodies);
                i++;
            }
            i = 0;
            for (Planet b : allBodies) {
                b.update(dt, xForces[i], yForces[i]);
                i++;
            }

            /** draw the background: universe */
            String imageToDraw = "images/starfield.jpg";
            StdDraw.enableDoubleBuffering();
            /** Sets up the size of our universe hhh
             * 
             */
            StdDraw.setScale(-radius*1.2, radius*1.2);
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            /** draw many planets */
            for (Planet b : allBodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        /** print final parameters */
        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", allBodies[i].xxPos, allBodies[i].yyPos,
                    allBodies[i].xxVel, allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);
        }

    }
}
