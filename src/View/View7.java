package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import Model.Calc;
/**
 * Created by olier1 on 13.07.2017.
 */

//Use physcics to calculate trajectory
public class View7 extends JPanel {
    JFrame frame= new JFrame();
    private int screenHeight, screenWidth;
    Font generalFont= new Font("Consolas",Font.PLAIN,15);
    JPanel panelNorth,panelEast;
   JLabel lab1,lab2,lab3,lab4,lab5;
   JTextField tf1,tf2,tf3,tf4,tf5;
   JButton backBtn,startbtn,resetbtn;
   JSlider acceslider,angleslider;
    Calc calc=new Calc();
   ArrayList<Ellipse2D.Double> al=new ArrayList<>();

    public View7() {
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension dimS= kit.getScreenSize();
        screenWidth= dimS.width;
        screenHeight= dimS.height;

        backBtn=new JButton("BACK");
        backBtn.setFont(generalFont);
        backBtn.setBackground(new Color(0,153,76));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e->{

        });
        tf1=new JTextField();
        tf1.setFont(generalFont);
        tf1.setColumns(8);
        tf2=new JTextField();
        tf2.setFont(generalFont);
        tf2.setColumns(8);
        tf3=new JTextField();
        tf3.setFont(generalFont);
        tf3.setColumns(8);
        lab1=new JLabel("Time(s)");
        lab1.setFont(generalFont);
        lab2=new JLabel("Max height");
        lab2.setFont(generalFont);
        lab3=new JLabel("Distance");
        lab3.setFont(generalFont);
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets = new Insets(3,10,3,3);
        panelNorth=new JPanel();
       panelNorth.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        panelNorth.add(lab1,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        panelNorth.add(tf1,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        panelNorth.add(lab2,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        panelNorth.add(tf2,gbc);
        gbc.gridx=2;
        gbc.gridy=0;
        panelNorth.add(lab3,gbc);
        gbc.gridx=2;
        gbc.gridy=1;
        panelNorth.add(tf3,gbc);

        lab4=new JLabel("Set initial velocity");
        lab4.setFont(generalFont);
        lab5=new JLabel("Set angle");
        lab5.setFont(generalFont);
        tf4=new JTextField();
        tf4.setFont(generalFont);
        tf4.setColumns(6);
        tf5=new JTextField();
        tf5.setFont(generalFont);
        tf5.setColumns(6);
        acceslider=new JSlider(1,30,1);
        acceslider.setMinorTickSpacing(1);
        acceslider.setMajorTickSpacing(10);
        acceslider.setPaintTicks(true);
        acceslider.setPreferredSize(new Dimension(300,20));
        acceslider.addChangeListener(a->{
            int acce=acceslider.getValue();
            tf4.setText(String.valueOf(acce));
        });
        angleslider=new JSlider(1,90,1);
        angleslider.setMinorTickSpacing(1);
        angleslider.setMajorTickSpacing(10);
        angleslider.setPaintTicks(true);
        angleslider.setPreferredSize(new Dimension(300,20));
        angleslider.addChangeListener(a->{
            int angle=angleslider.getValue();
            tf5.setText(String.valueOf(angle));
        });

        startbtn=new JButton("Start");
        startbtn.setFont(generalFont);
        startbtn.setBackground(new Color(0,153,76));
        startbtn.setForeground(Color.WHITE);
        startbtn.addActionListener(e->{
            boolean validatenr1= Pattern.matches("[1-9][\\p{Digit}]+", tf4.getText());
            boolean validatenr2= Pattern.matches("[1-9][\\p{Digit}]+", tf5.getText());
            if(validatenr1==true && validatenr2==true){
            int arg1 = Integer.parseInt(tf4.getText());
            int arg2 = Integer.parseInt(tf5.getText());
            al=new ArrayList<>();
            al.addAll(calc.count(arg1,arg2,screenHeight));

            double time = time = (2 * arg1 * Math.sin(Math.PI * arg2 / 180)) / 9.81;
            double Vx = arg1 * Math.cos(Math.PI * arg2 / 180);
            double s = Vx * time * Math.cos(Math.PI * arg2 / 180);
            double hmax = ((arg1 * arg1) * 2 * (Math.sin(Math.PI * arg2 / 180) * Math.cos(Math.PI * arg2 / 180))) / 2 * 9.81;
            tf1.setText(String.valueOf(Math.round(time*100)/100d));
            tf2.setText(String.valueOf(Math.round(hmax)/100d));
            tf3.setText(String.valueOf(Math.round(s*100)/100d));
            repaint();}
            else{
                JOptionPane.showMessageDialog(frame,"Incorrect data","Info",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        resetbtn=new JButton("Reset");
        resetbtn.setFont(generalFont);
        resetbtn.setBackground(new Color(0,153,76));
        resetbtn.setForeground(Color.WHITE);
        resetbtn.addActionListener(e->{
            al.clear();
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
            tf5.setText("");
            repaint();
        });

        GridBagConstraints gbc1=new GridBagConstraints();
        panelEast=new JPanel(new GridBagLayout());
        gbc1.gridx=0;
        gbc1.gridy=0;
        panelEast.add(lab4,gbc1);
        gbc1.gridx=0;
        gbc1.gridy=1;
        panelEast.add(acceslider,gbc1);
        gbc1.gridx=1;
        gbc1.gridy=1;
        panelEast.add(tf4,gbc1);
        gbc1.gridx=0;
        gbc1.gridy=2;
        panelEast.add(lab5,gbc1);
        gbc1.gridx=0;
        gbc1.gridy=3;
        panelEast.add(angleslider,gbc1);
        gbc1.gridx=1;
        gbc1.gridy=3;
        panelEast.add(tf5,gbc1);
        gbc1.gridx=0;
        gbc1.gridy=4;
        JPanel insidepanel=new JPanel();
        insidepanel.add(startbtn);
        insidepanel.add(resetbtn);
        panelEast.add(insidepanel,gbc1);
        frame.getContentPane().add(panelNorth,BorderLayout.NORTH);
        frame.getContentPane().add(panelEast,BorderLayout.EAST);
        frame.setTitle("Computer simulations");
        frame.setVisible(true);
        frame.setSize(screenWidth,screenHeight-30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
    }

    public void paint(Graphics g) {
        super.paint(g);

        StringBuilder coord = new StringBuilder();
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.BLACK);
        for (Ellipse2D.Double e2:al)

        {

            g2.fill(e2);
            coord.setLength(0);

        }
    }
    class Animate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
}
