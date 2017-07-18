package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by olier1 on 13.07.2017.
 */

//Another Monte Carlo mathod-use integrals to calculate PI
public class View4 extends JPanel {
    private int count = 0;
    double x, y;
    private int screenHeight, screenWidth;
    Random ran = new Random();
    int N = 0;
    JButton backBtn;
    ArrayList<Ellipse2D.Double> reddots=new ArrayList<>();
    ArrayList<Ellipse2D.Double>  greendots=new ArrayList<>();
    ArrayList<Ellipse2D.Double>  bluedots=new ArrayList<>();
    ArrayList<Ellipse2D.Double>  blackdots=new ArrayList<>();
    JFrame frame= new JFrame();
    Font generalFont= new Font("Consolas",Font.PLAIN,15);
    JPanel panelNorth,panelEast;
    JTextField tf1;

    public View4() {
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension dimS= kit.getScreenSize();
        screenWidth= dimS.width;
        screenHeight= dimS.height;

        backBtn=new JButton("BACK");
        backBtn.setFont(generalFont);
        backBtn.setBackground(new Color(0,153,76));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e->{
            frame.dispose();
            JFrame view1=new View1();
        });
        panelEast=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEast.add(backBtn);
       panelNorth=new JPanel();
       JLabel lab1=new JLabel("Podział OX 0-6");
       lab1.setFont(generalFont);
       tf1=new JTextField();
       tf1.setColumns(10);
       tf1.setFont(generalFont);
        JLabel lab2=new JLabel("" +
                "result");
        lab2.setFont(generalFont);

        count();
        panelNorth.add(lab1);
        panelNorth.add(tf1);
        panelNorth.add(lab2);
        frame.getContentPane().add(panelEast,BorderLayout.EAST);
        frame.getContentPane().add(panelNorth,BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setTitle("Algorithms");
        frame.setSize(screenWidth,screenHeight-50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
    }

    public static double MCfunc(double x)
    {
        return Math.sin(x) + (1);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2=(Graphics2D)g;

        g2.setColor(Color.RED);
        for(Ellipse2D.Double l1 :reddots){
            g2.draw(l1);
        }

        g2.setColor(Color.BLUE);
        for(Ellipse2D.Double l2 :bluedots){
            g2.draw(l2);
        }

        g2.setColor(Color.GREEN);
        for(Ellipse2D.Double l3 :greendots){
            g2.draw(l3);
        }

        g2.setColor(Color.BLACK);
        for(Ellipse2D.Double l4 :blackdots){
            g2.draw(l4);
        }
    }

    public void count(){
        double tempy;
        double xbeg=0;
        double xend=6;
        double ybeg=0;
        double yend=3;
        for (double d = 0; d <= 6; d += 0.1)
        {
            N++;
            x = ran.nextDouble()*6;
            y = ran.nextDouble()*3;
            double x1 = x * 100;
            double y1 = y * 100;
            tempy = MCfunc(d);
            reddots.add(new Ellipse2D.Double(d * 100, screenHeight/2 - (tempy * 100), 5, 5));
            if ((y>0) && (y<=MCfunc(x)))
            {

                bluedots.add(new Ellipse2D.Double( x1, screenHeight/2 -y1, 5, 5));
                count++;
            } else if (( y<0) && (y>=MCfunc(x)))
            {

                greendots.add(new Ellipse2D.Double( x1,screenHeight - y1, 5, 5));
                count--;
            }
            else
            {
                blackdots.add(new Ellipse2D.Double(x1,screenHeight/2- y1, 5, 5));
            }

        }

        double result = Math.round(((xend-xbeg)*(yend-ybeg)*(double)count/(double)N)*1000)/1000d;
      tf1.setText(String.valueOf(result));
    }
}
