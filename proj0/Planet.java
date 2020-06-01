import java.lang.Math;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    /** constructor 1 */
    public Planet(double xP, double yP, double xV, double yV, 
                double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }
    /** constructor 2 */
    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

    }


    public double calcDistance(Planet b) {
        return Math.sqrt(Math.pow(b.xxPos - this.xxPos, 2) + Math.pow(b.yyPos - this.yyPos, 2));
    }

    public double calcForceExertedBy(Planet b) {
        return G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
    }

    public double calcForceExertedByX(Planet b) {
        double dx = b.xxPos - this.xxPos;
        return calcForceExertedBy(b) * dx / calcDistance(b);
    }

    public double calcForceExertedByY(Planet b) {
        double dy = b.yyPos - this.yyPos;
        return calcForceExertedBy(b) * dy / calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet[] allBodies) {
        double total = 0;
        for (Planet b: allBodies) {
            if (!this.equals(b)) { //except pairing itself
                total = total + this.calcForceExertedByX(b);
            }
        }
        return total;   
    }

    public double calcNetForceExertedByY(Planet[] allBodies) {
        double total = 0;
        for (Planet b: allBodies) {
            if (!this.equals(b)) {
                total = total + this.calcForceExertedByY(b);
            }
        }
        return total;
    }

    /** update position */
    public void update(double dt, double xforce, double yforce) {
        double ax = xforce / this.mass;
        double ay = yforce / this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;   
    }

    /** draw the planet at its position */
    public void draw() {
        //StdDraw.enableDoubleBuffering();
		//StdDraw.setScale(-100, 100);
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
		//StdDraw.show();
        //StdDraw.pause(2000);
        
    }

}