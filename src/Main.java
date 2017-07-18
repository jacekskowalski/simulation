import View.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
/**
 * Created by olier1 on 13.07.2017.
 */
public class Main extends JPanel{


    public static void main(String[] args) {

              EventQueue.invokeLater(()->{
                  JFrame view1=new View1();
              });

    }


}
