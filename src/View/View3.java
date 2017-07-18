package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by olier1 on 13.07.2017.
 */

//Use Monte Carlo to calculate PI
public class View3 extends JPanel {
    private int N;
    private int counthits = 0;
    Random ran = new Random();
    double x, y,res;
    JButton btn1,btn2,backBtn;
    JTextField tfres,tfquantity;
    private int screenHeight, screenWidth;
    Font generalFont= new Font("Consolas",Font.PLAIN,15);
    ArrayList<Ellipse2D.Double> reddots=new ArrayList<>();
    ArrayList<Ellipse2D.Double> greendots=new ArrayList<>();
    JFrame frame= new JFrame();
    JPanel panelEast,panelSouth;

    public View3() {
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension dimS= kit.getScreenSize();
        screenWidth= dimS.width;
        screenHeight= dimS.height;
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
        panelEast=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelEast.add(backBtn);
        GridBagConstraints gbc=new GridBagConstraints();
        tfres=new JTextField();
        tfres.setColumns(8);
        tfquantity=new JTextField();
        tfquantity.setColumns(8);
        btn1=new JButton("Start");
        btn1.setFont(generalFont);
        btn1.setBackground(new Color(0,153,76));
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(e->{
            boolean validatenr= Pattern.matches("[1-9][\\p{Digit}]+", tfquantity.getText());
            if(validatenr==true){
            int nr=Integer.parseInt(tfquantity.getText());

            fillArrays(nr);}
            else{
                JOptionPane.showMessageDialog(frame,"Incorrect data","Info",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        btn2=new JButton("Reset");
        btn2.setFont(generalFont);
        btn2.setBackground(new Color(0,153,76));
        btn2.setForeground(Color.WHITE);
      JLabel lab1=new JLabel("Quantity");
        lab1.setFont(generalFont);
     JLabel lab2=new JLabel("Result");
        lab2.setFont(generalFont);
        btn2.addActionListener(e->{
            tfres.setText("");
            tfquantity.setText("");
            reddots.clear();
            greendots.clear();
            repaint();
        });
        panelSouth=new JPanel();
        panelSouth.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        panelSouth.add(lab1,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        panelSouth.add(tfquantity,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        panelSouth.add(lab2,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        panelSouth.add(tfres,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        panelSouth.add(btn1,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        panelSouth.add(btn2,gbc);
        frame.getContentPane().add(panelEast,BorderLayout.EAST);
        frame.getContentPane().add(panelSouth,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setTitle("Computer simulations");
        frame.setSize(screenWidth,screenHeight-30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2=(Graphics2D)g;

        g2.setColor(Color.RED);

        for(Ellipse2D.Double l2d :reddots){
            g2.draw(l2d);
        }

        g2.setColor(Color.GREEN);
        for(Ellipse2D.Double l3d : greendots){
            g2.draw(l3d);
        }
    }

    public void fillArrays(int n){
        int N = n;
        for (int i = 1; i <= N; i++)
        {
            x = ran.nextDouble();
            y = ran.nextDouble();
            double fx = x * 500;
            double fy = y * 500;
                x = 2 * x - 1;
            y = 2 * y - 1;
            if (Math.pow(x, 2.0) + Math.pow(y, 2.0) <= 1.0)
            {
                counthits++;
                reddots.add(new Ellipse2D.Double(fx, fy, 2, 2));
            }
            else
            {
                greendots.add(new Ellipse2D.Double( fx, fy, 2, 2));
            }

        }
        res= 4 * ((double)counthits / (double)N);
       //
        counthits = 0;
        N = 0;
        tfres.setText(String.valueOf(res));
        repaint();
    }
}
