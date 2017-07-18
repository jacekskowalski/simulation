package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by olier1 on 13.07.2017.
 */

//Dijkstra Shortest Path
public class View6 extends JPanel {
       JButton btn1,backBtn;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6;
    JLabel lab1,lab2,lab3,lab4,lab5,lab6;
    JLabel titlelab=new JLabel("Distance from A to");
    private int screenHeight, screenWidth;
    Font generalFont= new Font("Consolas",Font.PLAIN,25);
    Font generalFont1= new Font("Consolas",Font.PLAIN,15);
    JFrame frame= new JFrame();
    JPanel paneEast,panelSouth;
    int[][] dijkstr = new int[][]
            {
                    {0,4,2,0,0,0},
                    {4,0,1,5,0,0},
                    {2,1,0,8,10,0},
                    {0,5,8,0,2,6},
                    {0,0,10,2,0,3},
                    {0,0,0,6,3,0}
            };
    private static int size=6;


    public View6() {
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension dimS= kit.getScreenSize();
        screenWidth= dimS.width;
        screenHeight= dimS.height;

        titlelab.setFont(generalFont);
        tf1=new JTextField();
        tf1.setColumns(6);
        tf1.setFont(generalFont);
        tf2=new JTextField();
        tf2.setColumns(6);
        tf2.setFont(generalFont);
        tf3=new JTextField();
        tf3.setColumns(6);
        tf3.setFont(generalFont);
        tf4=new JTextField();
        tf4.setColumns(6);
        tf4.setFont(generalFont);
        tf5=new JTextField();
        tf5.setColumns(6);
        tf5.setFont(generalFont);
        tf6=new JTextField();
        tf6.setColumns(6);
        tf6.setFont(generalFont);
        lab1=new JLabel("A");
        lab1.setFont(generalFont);
        lab2=new JLabel("B");
        lab2.setFont(generalFont);
        lab3=new JLabel("C");
        lab3.setFont(generalFont);
        lab4=new JLabel("D");
        lab4.setFont(generalFont);
        lab5=new JLabel("E");
        lab5.setFont(generalFont);
        lab6=new JLabel("F");
        lab6.setFont(generalFont);
        GridBagConstraints gbc=new GridBagConstraints();
        paneEast=new JPanel();
        paneEast.setLayout(new GridBagLayout());
        gbc.gridx=1;
        gbc.gridy=0;
        paneEast.add(titlelab,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        paneEast.add(lab1,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        paneEast.add(tf1,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        paneEast.add(lab2,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        paneEast.add(tf2,gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        paneEast.add(lab3,gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        paneEast.add(tf3,gbc);
        gbc.gridx=0;
        gbc.gridy=4;
        paneEast.add(lab4,gbc);
        gbc.gridx=1;
        gbc.gridy=4;
        paneEast.add(tf4,gbc);
        gbc.gridx=0;
        gbc.gridy=5;
        paneEast.add(lab5,gbc);
        gbc.gridx=1;
        gbc.gridy=5;
        paneEast.add(tf5,gbc);
        gbc.gridx=0;
        gbc.gridy=6;
        paneEast.add(lab6,gbc);
        gbc.gridx=1;
        gbc.gridy=6;
        paneEast.add(tf6,gbc);
         btn1=new JButton("Start");
         gbc.gridx=1;
         gbc.gridy=7;
         paneEast.add(btn1,gbc);
         btn1.addActionListener(e->{

             DijkstraAlgorithm(dijkstr,0);
         });
        btn1.setFont(generalFont1);
        btn1.setBackground(new Color(0,153,76));
        btn1.setForeground(Color.WHITE);

        backBtn=new JButton("BACK");
        backBtn.setFont(generalFont1);
        backBtn.setBackground(new Color(0,153,76));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e->{
            frame.dispose();
            JFrame view1=new View1();
        });

        panelSouth=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSouth.add(backBtn);
        frame.getContentPane().add(paneEast,BorderLayout.EAST);
        frame.getContentPane().add(panelSouth,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setTitle("Computer simulations");
        frame.setSize(screenWidth,screenHeight-30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
    }


    public  void DijkstraAlgorithm(int[][] graph, int sourceNode) {
        int n = size;
        int[] distance = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[sourceNode] = 0;

        boolean[] used = new boolean[n];

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(distance, used);
            used[u] = true;
            for (int v = 0; v < n; v++) {
                if (!used[v] && graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v])
                    distance[v] = distance[u] + graph[u][v];
            }
        }
            printSolution(distance);

    }
    void printSolution(int dist[]) {
        JTextField[] tfarray = {tf1, tf2, tf3, tf4, tf5, tf6};
        for (int i = 0; i < size; i++) {
            System.out.println(i + " " + dist[i]);
            tfarray[i].setText(String.valueOf(dist[i]));
        }

    }
    int minDistance(int dist[], boolean sptSet[])
{
    int min = Integer.MAX_VALUE;
    int min_index=-1;

 for (int v = 0; v < 6; v++)
 if (sptSet[v] == false && dist[v] <= min)
{
        min = dist[v];
        min_index = v;
}
return min_index;}

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(100,200,200,100 ));
        g2.draw(new Line2D.Double(100,200,200,300 ));
        g2.draw(new Line2D.Double(200,100,400,100 ));
        g2.draw(new Line2D.Double(200,300,400,100 ));
        g2.draw(new Line2D.Double(200,300,400,300 ));
        g2.draw(new Line2D.Double(400,100,500,200 ));
        g2.draw(new Line2D.Double(400,300,500,200 ));
        g2.draw(new Line2D.Double(200,100,200,300 ));
        g2.draw(new Line2D.Double(400,100,400,300 ));

        g2.setColor(Color.RED);
        g2.setFont(generalFont);
        g2.drawString("A",70,200);
        g2.drawString("B",200,70);
        g2.drawString("C",200,320);
        g2.drawString("D",400,70);
        g2.drawString("E",400,320);
        g2.drawString("F",500,200);

        g2.setColor(Color.BLUE);
        g2.setFont(generalFont);
        g2.drawString("4",110,150);
        g2.drawString("2",130,270);
        g2.drawString("8",320,200);
        g2.drawString("2",410,200);
        g2.drawString("5",300,70);
        g2.drawString("6",470,130);
        g2.drawString("10",300,320);
        g2.drawString("3",450,270);
        g2.drawString("1",170,200);
    }
}
