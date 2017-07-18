package View;

import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by olier1 on 13.07.2017.
 */

//Use Buffon method to calculate PI
public class View2  extends JPanel {
    JButton btn1,btn2,btn3,backBtn;
    private int screenHeight, screenWidth;
    JPanel panelSouth,pane1East,panelNorth;
    JFrame frame= new JFrame();
    JSlider slid;
    Font generalFont= new Font("Consolas",Font.PLAIN,15);
    ArrayList<Line2D.Double>  linesarray=new ArrayList<>();
JTextField tf;
    public View2(){
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension dimS= kit.getScreenSize();
        screenWidth= dimS.width;
        screenHeight= dimS.height;
        frame.setVisible(true);
        try {
            for( UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e) {

            e.printStackTrace();
        }

        backBtn=new JButton("BACK");
        backBtn.setFont(generalFont);
        backBtn.setBackground(new Color(0,153,76));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e->{
            frame.dispose();
            JFrame view1=new View1();
        });
        panelNorth=new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorth.add(backBtn);

       panelSouth=new JPanel(new FlowLayout());
        slid=new JSlider(JSlider.HORIZONTAL,0,100,50);
        slid.setMinorTickSpacing(1);
        slid.setMajorTickSpacing(10);
        slid.setPreferredSize(new Dimension(400,20));
        slid.addChangeListener(e->{
            double d=slid.getValue()/100d;
            tf.setText(String.valueOf(d));
                });
        tf=new JTextField();
        tf.setColumns(5);
        tf.setFont(generalFont);
        slid.setFont(generalFont);
        slid.setMinorTickSpacing(1);
        slid.setPaintTicks(true);
        JLabel lengthlab=new JLabel("Nr of trials");
        lengthlab.setFont(generalFont);
        JTextField tflength=new JTextField();
        tflength.setColumns(8);
        tflength.setFont(generalFont);
        JLabel reslabel=new JLabel("Result");
        reslabel.setFont(generalFont);
        JTextField tfres=new JTextField();
        tfres.setColumns(8);
        tfres.setFont(generalFont);
        btn1=new JButton("<html>Start</html>");
        btn1.setBackground(new Color(0,153,76));
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(r->{


            boolean validatenr1= Pattern.matches("[1-9][\\p{Digit}]+", tflength.getText());
            boolean validatenr2=Pattern.matches("[\\p{Digit}]+[.][\\p{Digit}]+", tf.getText());
            if(validatenr1==true && validatenr2==true){
                int i1=Integer.parseInt(tflength.getText());
                double d1=Double.parseDouble(tf.getText());
                int count = 0;
                int getAngle;
                Random ran = new Random();
                double x;

                for (int i = 0; i < i1; i++)
                {
                    x = 0.5 * ran.nextDouble()*2;
                    getAngle = ran.nextInt(91);
                    int posx=ran.nextInt(600-200)+200;
                    int posy=ran.nextInt(400-200)+200;
                    double needle=d1*100;
                    double val = 0.5 * d1 * Math.sin(Math.PI * getAngle / 180);
                 double x1=posx+(100*(0.5*d1*Math.cos(Math.PI * getAngle / 180)));
                 double y1=posy-(0.5*(needle+Math.sin(Math.PI * getAngle / 180)));
                 double x2=posx-(100*(0.5*d1*Math.cos(Math.PI * getAngle / 180)));
                 double y2=posy+(0.5*(needle+Math.sin(Math.PI * getAngle / 180)));

                 linesarray.add(new Line2D.Double(x1,y1,x2,y2));
                    if (x <= val)
                    {
                        count++;
                    }
                    x = 0;
                    getAngle = 0;

                }

               double pival = Math.round(((2*d1*i1)/(double)(2*count))*100)/100d;
repaint();
               tfres.setText(" "+pival);
                count = 0;
            }else {
                JOptionPane.showMessageDialog(frame,"Incorrect data","Info",
                        JOptionPane.PLAIN_MESSAGE);
            }

        });

        btn2=new JButton("<html>Reset</html>");
        btn2.setBackground(new Color(0,153,76));
        btn2.setForeground(Color.WHITE);
      btn2.addActionListener(e ->{
            tflength.setText("");
            tfres.setText("");
            tf.setText("");
          linesarray.clear();
          repaint();
        });
        JLabel lab1=new JLabel("Set length 0-1");
        lab1.setFont(generalFont);
        panelSouth.add(lab1);
        panelSouth.add(slid);
        panelSouth.add(tf);

        pane1East=new JPanel();
        GridBagConstraints gbc=new GridBagConstraints();
        pane1East.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        pane1East.add(lengthlab,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        pane1East.add(tflength,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        pane1East.add(reslabel,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        pane1East.add(tfres,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        pane1East.add(btn1,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        pane1East.add(btn2,gbc);

        frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
        frame.getContentPane().add(panelSouth,BorderLayout.SOUTH);
        frame.getContentPane().add(pane1East,BorderLayout.EAST);
        frame.setTitle("Computer simulations");
        frame.setSize(screenWidth,screenHeight-30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
    }


    public void paint(Graphics g) {
        super.paint(g);

       Graphics2D g2=(Graphics2D)g;
    g2.draw(new Line2D.Double(200,200,600,200 ));
     g2.draw(new Line2D.Double(200,400,600,400 ));
        g2.setColor(Color.RED);

        for(Line2D.Double l2d :linesarray){
           g2.draw(l2d);
       }
    }
}
