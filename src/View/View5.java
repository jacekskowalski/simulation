package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

/**
 * Created by olier1 on 13.07.2017.
 */
public class View5 extends JPanel {
    int[][] array;
    Random rand,rand1,rand2;
    JButton btn1,btn2,btn3,backBtn;
    JLabel lab1,lab2;
    JList optionlist;
    private int screenHeight, screenWidth;
    JFrame frame= new JFrame();
    Font generalFont= new Font("Consolas",Font.PLAIN,15);
ArrayList<Rectangle2D.Double> whiterect=new ArrayList<>();
    ArrayList<Rectangle2D.Double> blackrect=new ArrayList<>();

JPanel paneEast,paneSouth;
    public View5() {
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension dimS= kit.getScreenSize();
        screenWidth= dimS.width;
        screenHeight= dimS.height;

        GridBagConstraints gbc=new GridBagConstraints();
        lab1=new JLabel("Choose object");
        lab1.setFont(generalFont);
        DefaultListModel dlm =new DefaultListModel();
        dlm.addElement("Block");
        dlm.addElement("Glider");
        dlm.addElement("Beehive");
        dlm.addElement("Blinker" );
        dlm.addElement("Toad" );
        optionlist=new JList(dlm);
        optionlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lab2=new JLabel("Choose quantity");
        JRadioButton radbt1=new JRadioButton("1");
        JRadioButton radbt2=new JRadioButton("2");
        ButtonGroup radbtns=new ButtonGroup();
        radbtns.add(radbt1);
        radbtns.add(radbt2);
        lab2.setFont(generalFont);
        paneEast=new JPanel(new GridBagLayout());

        gbc.gridx=0;
        gbc.gridy=0;
        paneEast.add(lab1,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        paneEast.add(optionlist,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        paneEast.add(lab2,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        paneEast.add(radbt1,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        paneEast.add(radbt2,gbc);
        ActionListener ticks=new RandomDrawingTick();
        btn1=new JButton("Random");
        Timer temptimer=new Timer(200,ticks);
        btn1.setFont(generalFont);
        btn1.setBackground(new Color(0,153,76));
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(e->{
                 temptimer.start();
        });

        ActionListener ticks1=new Drawingtick();
        Timer temptimer1=new Timer(200,ticks1);
        btn2=new JButton("Start");
        btn2.setFont(generalFont);
        btn2.setBackground(new Color(0,153,76));
        btn2.setForeground(Color.WHITE);
        btn2.addActionListener(e->{

            if(getSelectedNr(radbtns) != null && optionlist.getSelectedValue() !=null){

       int choosennr=Integer.parseInt(getSelectedNr(radbtns));
       String choosenobj=String.valueOf(optionlist.getSelectedValue());
       createRandomArray(choosenobj,choosennr);
       temptimer1.start();}
       else{
                JOptionPane.showMessageDialog(frame,"Incorrect data","Info",
                        JOptionPane.PLAIN_MESSAGE);
            }
                }
        );
        btn3=new JButton("Reset");
        btn3.setFont(generalFont);
        btn3.setBackground(new Color(0,153,76));
        btn3.setForeground(Color.WHITE);
        btn3.addActionListener(e->{
            blackrect.clear();
            whiterect.clear();
            temptimer.stop();
            temptimer1.stop();
            repaint();
        });
        backBtn=new JButton("BACK");
        backBtn.setFont(generalFont);
        backBtn.setBackground(new Color(0,153,76));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e->{
            frame.dispose();
            JFrame view1=new View1();
        });

        paneSouth=new JPanel();
        paneSouth.add(btn1);
        paneSouth.add(btn2);
        paneSouth.add(btn3);
        paneSouth.add(backBtn);
        frame.getContentPane().add(paneEast,BorderLayout.EAST);
        frame.getContentPane().add(paneSouth,BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setTitle("Algorytmy");
        frame.setSize(screenWidth,screenHeight-50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
    }


    public void createRandomArray(String name,int number)
    {
        array = new int[50][];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = new int[50];
        }
        switch (name)
        {
            case  "Block":
                if (number == 1)
                {
                    rand1 = new Random();
                    int nr= rand1.nextInt(49-1)+1;
                    array[nr][nr] = 1;
                    array[nr][nr + 1] = 1;
                    array[nr+1][nr] = 1;
                    array[nr + 1][nr + 1] = 1;
                }
                else if (number == 2)
                {
                    rand1 = new Random();
                    int nr2=  rand1.nextInt(25-1)+1;
                    rand2 = new Random();
                    int nr3 = rand2.nextInt(49-26)+26;
                    array[nr2][nr2] = 1;
                    array[nr2][nr2 + 1] = 1;
                    array[nr2 + 1][nr2] = 1;
                    array[nr2 + 1][nr2 + 1] = 1;
                    array[nr3][nr3] = 1;
                    array[nr3][nr3 + 1] = 1;
                    array[nr3 + 1][nr3] = 1;
                    array[nr3 + 1][nr3 + 1] = 1;
                }
                break;
            case "Glider" :

                if (number == 1)
                {
                    rand1 = new Random();
                    int nr1 = rand1.nextInt(48-1)+1;
                    array[nr1][nr1] = 1;
                    array[nr1][nr1+1] = 1;
                    array[nr1][nr1+2] = 1;
                    array[nr1+1][nr1] = 1;
                    array[nr1+2][nr1+1] = 1;
                }
                else if (number == 2)
                {
                    rand1 = new Random();
                    int nr2 = rand1.nextInt(25-1)+1;
                    rand2 = new Random();
                    int nr3 = rand2.nextInt(48-26)+26;
                    array[nr2][nr2] = 1;
                    array[nr2][nr2 + 1] = 1;
                    array[nr2][nr2 + 2] = 1;
                    array[nr2 + 1][nr2] = 1;
                    array[nr2 + 2][nr2 + 1] = 1;
                    array[nr3][nr3] = 1;
                    array[nr3][nr3 + 1] = 1;
                    array[nr3][nr3 + 2] = 1;
                    array[nr3 + 1][nr3] = 1;
                    array[nr3 + 2][nr3 + 1] = 1;
                }
                break;
            case "Beehive" :
                if (number == 1)
                {
                    rand1 = new Random();
                    int nr4 = rand1.nextInt(44-1)+1;
                    array[nr4][nr4] = 1;
                    array[nr4][nr4+1] = 1;
                    array[nr4][nr4+2] = 1;
                    array[nr4][nr4+3] = 1;
                    array[nr4][nr4+4] = 1;
                    array[nr4 ][nr4+5] = 1;

                }
                else if (number == 2)
                {
                    rand1 = new Random();
                    int nr7 = rand1.nextInt(25-1)+1;
                    rand2 = new Random();
                    int nr8 = rand2.nextInt(48-26)+26;
                    array[nr7][nr7] = 1;
                    array[nr7+1][nr7-1] = 1;
                    array[nr7 + 1][nr7+1] = 1;
                    array[nr7 + 2][nr7] = 1;
                    array[nr8][nr8] = 1;
                    array[nr8+1][nr8-1] = 1;
                    array[nr8 + 1][nr8+1] = 1;
                    array[nr8 + 2][nr8] = 1;
                }
                break;
            case "Blinker" :
                if (number == 1)
                {
                    rand1 = new Random();
                    int nr6 = rand1.nextInt(48-1)+1;
                    array[nr6][nr6] = 1;
                    array[nr6][nr6 + 1] = 1;
                    array[nr6][nr6 + 2] = 1;
                }
                else if (number == 2)
                {
                    rand1 = new Random();
                    int nr11 = rand1.nextInt(25-1)+1;
                    rand2 = new Random();
                    int nr12 = rand2.nextInt(48-26)+26;
                    array[nr11][nr11] = 1;
                    array[nr11][nr11 + 1] = 1;
                    array[nr11][nr11 + 2] = 1;
                    array[nr12][nr12] = 1;
                    array[nr12][nr12 + 1] = 1;
                    array[nr12][nr12 + 2] = 1;
                }
                break;
            case "Toad" :

                if (number == 1)
                {
                    rand1 = new Random();
                    int nr9 = rand1.nextInt(48-1);
                    array[nr9][nr9] = 1;
                    array[nr9 + 1][nr9] = 1;
                    array[nr9 + 1][nr9 + 1] = 1;
                    array[nr9 + 2][nr9 + 1] = 1;
                }
                else if (number == 2)
                {
                    rand1 = new Random();
                    int nr9 = rand1.nextInt(25-1)+1;
                    rand2 = new Random();
                    int nr10 = rand2.nextInt(48-26)+26;
                    array[nr9][nr9] = 1;
                    array[nr9 + 1][nr9] = 1;
                    array[nr9 + 1][nr9 + 1] = 1;
                    array[nr9 + 2][nr9+1] = 1;
                    array[nr10][nr10] = 1;
                    array[nr10 + 1][nr10] = 1;
                    array[nr10+1][nr10 + 1] = 1;
                    array[nr10 + 2][nr10+1] = 1;
                }
                break;
        }


    }
    public void createArray()
    {
        array = new int[50][];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = new int[50];
        }
        rand = new Random();

        for (int a = 0; a < array.length; a++)
        {

            for (int b = 0; b < array[a].length; b++)
            {
                array[a][b] = (int)rand.nextInt(2);
            }

        }


    }
    public void update()
    { int[][] temp = new int[50][];
        for (int i = 0; i < array.length; i++)
        {
            temp[i] = new int[50];
        }
        for (int x = 1; x <array.length - 1; x++)
        {
            for (int y = 1; y <array[x].length - 1; y++)
            {
                int var1 = Cell(array, x, y);
                if (array[x][y] == 1)
                {
                      if (var1 == 2 || var1 == 3)
                    {
                       temp[x][y] = 1;
                    }
                    if (var1 <= 1 || var1 > 3)
                      temp[x][y] = 0;
                                 }
                else
                {

                    if (var1 == 3)

                        temp[x][y] = 1;
                }
            }
        }
        array = temp;

    }

    public int Cell(int[][]array,int x1,int y1)
    {
        int count1 = array[x1 - 1][y1 - 1] + array[x1 - 1][y1] + array[x1 - 1][y1 + 1] +
                array[x1][y1 - 1] + array[x1][y1 + 1] + array[x1 + 1][y1 - 1] + array[x1 + 1][y1] + array[x1 + 1][y1 + 1];

        return count1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2=(Graphics2D)g;

        g2.setColor(Color.WHITE);
        for(Rectangle2D.Double l1 :whiterect){
            g2.fill(l1);
        }

        g2.setColor(Color.BLACK);
        for(Rectangle2D.Double l2 :blackrect){
            g2.fill(l2);
        }
    }
    class RandomDrawingTick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            createArray();
              blackrect.clear();
               whiterect.clear();  
            for (int x = 0; x < array.length; x++)
            {
                for (int y = 0; y < array[x].length; y++)
                {

                    if (array[x][y] == 1)
                    {
                        blackrect.add(new Rectangle2D.Double(x * 10, y * 10, 10, 10));
                   
                    }
                    else
                    {
                        whiterect.add(new Rectangle2D.Double(x * 10, y * 10, 10, 10));

                    }
                            repaint();
                }

            }

            update();

        }
    }
    class Drawingtick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            blackrect.clear();
            whiterect.clear();
            for (int x = 0; x < array.length; x++)
            {
                for (int y = 0; y < array[x].length; y++)
                {

                    if (array[x][y] == 1)
                    {
                        blackrect.add(new Rectangle2D.Double(x * 10, y * 10, 10, 10));

                    }
                    else
                    {
                        whiterect.add(new Rectangle2D.Double(x * 10, y * 10, 10, 10));

                    }
                    repaint();
                }

            }

            update();
        }
    }

    public String getSelectedNr(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
