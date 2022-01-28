public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double disc_ex = (p.xxPos - this.xxPos) * (p.xxPos - this.xxPos)
                + (p.yyPos - this.xxPos) * (p.yyPos - this.xxPos);
        return Math.sqrt(disc_ex);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        double F;
        F = (G * this.mass * p.mass) / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double Fx = (F * (p.xxPos - this.xxPos)) / r;
        return Fx;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double Fy = (F * (p.yyPos - this.yyPos)) / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double F = 0;
        int i = 0;
        while (i < allPlanets.length) {
            if (!this.equals(allPlanets[i])) {
                F = F + this.calcForceExertedByX(allPlanets[i]);
            }
            i++;
        }
        return F;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double F = 0;
        int i = 0;
        while (i < allPlanets.length) {
            if (!this.equals(allPlanets[i])) {
                F = F + this.calcForceExertedByY(allPlanets[i]);
            }
            i++;
        }
        return F;
    }

    public void update(double dt, double fX, double fY) {
        double m = this.mass;
        double aX = fX / m;
        double aY = fY / m;
        this.xxVel = aX * dt + this.xxVel;
        this.yyVel = aY * dt + this.yyVel;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        String s = "./images/" + imgFileName;
        StdDraw(xxPos, yyPos, s);
    }

    
}