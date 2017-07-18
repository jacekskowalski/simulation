package View;

/**
 * Created by olier1 on 13.07.2017.
 */
import javafx.scene.layout.Background;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Graphics;
import javax.swing.UIManager.LookAndFeelInfo;

public class View1 extends JFrame{
   JFrame frame= new JFrame();
    JButton btn1,btn2,btn3,btn4,btn5,btn6;
    private int screenHeight, screenWidth;

JPanel panelNorth;
    public View1(){
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension dimS= kit.getScreenSize();
        screenWidth= dimS.width;
        screenHeight= dimS.height;
        try {
            for( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e) {

            e.printStackTrace();
        }
        JLabel titlelabel=new JLabel("Computer simulations");
        titlelabel.setFont(new Font("Consolas",Font.BOLD,40));
         panelNorth=new JPanel();
        panelNorth.add(titlelabel);
        panelNorth.setOpaque(false);
      panelNorth.setBounds((screenWidth/2-screenWidth/10)-200,screenHeight/10,500,50);

        JPanel innerPanel=new JPanel();
        GridBagConstraints gbc= new GridBagConstraints();
        innerPanel.setLayout(new BoxLayout(innerPanel,BoxLayout.Y_AXIS));
        btn1=new JButton("<html><h3>Buffon method</h3></html>");
        btn1.setBackground(new Color(0,153,76));
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(e-> {
            frame.dispose();
            JPanel view2=new View2();

        });

        btn2=new JButton("<html><h3>Monte Carlo PI</h3></html>");
        btn2.addActionListener(e->{
            frame.dispose();
            JPanel view3=new View3();
        });
        btn2.setBackground(new Color(0,153,76));
         btn2.setForeground(Color.WHITE);

        btn3=new JButton("<html><h3>Monte Carlo</h3></html>");
        btn3.setBackground(new Color(0,153,76));
        btn3.setForeground(Color.WHITE);
        btn3.addActionListener(e->{
            frame.dispose();
            JPanel view4=new View4();
        });

        btn4=new JButton("<html><h3>Game of life</h3></html>");
        btn4.setBackground(new Color(0,153,76));
        btn4.setForeground(Color.WHITE);
        btn4.addActionListener(e->{
            frame.dispose();
            JPanel view5=new View5();
        });

        btn5=new JButton("<html><h3>Dijkstra path</h3></html>");
        btn5.setBackground(new Color(0,153,76));
        btn5.setForeground(Color.WHITE);
        btn5.addActionListener(e->{
            frame.dispose();
            JPanel view6=new View6();
        });

        btn6=new JButton("<html><h3>Slanted throw</h3></html>");
        btn6.setBackground(new Color(0,153,76));
        btn6.setForeground(Color.WHITE);
     btn6.addActionListener(e->{
       frame.dispose();
       JPanel view7=new View7();
   });

        innerPanel.add(btn1);
        innerPanel.add(btn2);
        innerPanel.add(btn3);
        innerPanel.add(btn4);
        innerPanel.add(btn5);
        innerPanel.add(btn6);
     innerPanel.setBounds(screenWidth/2-screenWidth/10,screenHeight/4,150,300);

        frame.getContentPane().add(panelNorth);
        frame.getContentPane().add(innerPanel);
        frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        frame.setTitle("Computer simulation");
	    frame.setSize(screenWidth,screenHeight-30);
	     frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

}
