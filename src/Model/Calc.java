package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by olier1 on 18.07.2017.
 */
public class Calc {
     final double GRAVITY = 9.81;
    double SPEED,time,Vx, Y0, x, y1,y,arg1,arg2;

    public Calc() { }

    public Calc( double arg1,double arg2)
    {
        this.x = arg1;
        this.y = arg2;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getArg1() {
        return arg1;
    }

    public void setArg1(double arg1) {
        this.arg1 = arg1;
    }

    public ArrayList<Ellipse2D.Double> count(int x, int y,int h)
    {
        double arg1, arg2;
        this.SPEED = x;
        this.y1 = y;
        this.time = (2 * SPEED * Math.sin(Math.PI * y1 / 180)) / GRAVITY;
        this.Vx = SPEED * Math.cos(Math.PI * y1 / 180);
        this.Y0 = Math.abs(Math.sin(Math.PI * y1 / 180));
        ArrayList<Ellipse2D.Double> temp = new ArrayList<>();
        for (double t = 0.0; t <= time; t += 0.1)
        {
            arg1=Vx*t*Math.cos(Math.PI * y1 / 180);
            arg2 = SPEED* t*Math.sin(Math.PI * y1 / 180)-(9.81*Math.pow(t,2)/2);

            temp.add(new Ellipse2D.Double(10*arg1,h-(10*arg2),10,10));

        }
        return temp;
    }
}
