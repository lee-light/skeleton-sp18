public class NBody {
    public static double readRadius(String dir) {
        In in = new In(dir);
        int number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String dir) {
        In in = new In(dir);
        int number = in.readInt();
        Planet[] p = new Planet[number];
        double r = in.readDouble();
        for (int i = 0; i < number; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return p;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        StdDraw.show();
        for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }
        StdDraw.show();

        for (double time = 0; time <= T; time += dt) {
            double[] xForces = new double[Planets.length];
            double[] yForces = new double[Planets.length];
            for (int i = 0; i < Planets.length; i++) {
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            for (int i = 0; i < Planets.length; i++) {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            // StdDraw.clear();
            StdDraw.picture(0, 0, imgPath);
            for (int i = 0; i < Planets.length; i++) {
                Planets[i].draw();
            }
            StdDraw.show();
            int pauseTime = 10;
            StdDraw.pause(pauseTime);
        }
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet aPArray : Planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    aPArray.xxPos, aPArray.yyPos, aPArray.xxVel, aPArray.yyVel, aPArray.mass, aPArray.imgFileName);
        }

    }

        
  }

    
