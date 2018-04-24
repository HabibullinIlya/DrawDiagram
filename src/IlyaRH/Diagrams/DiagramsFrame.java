package IlyaRH.Diagrams;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiagramsFrame {
    JFrame frame;
    DiagramPanel diagram;
    private int startX = 20;
    private int startY = 20;
    private int startY2;
    ArrayList<Double> x, y;

    private int ScaleX = 20;
    private int ScaleY = 20;
    private ArrayList<ArrayList<Double>> args;


    public void drawFrame(ArrayList<ArrayList<Double>> args) {
        /*this.x = args[0];
        this.y = args[1];*/
        this.args = args;

        frame = new JFrame();
        diagram = new DiagramPanel();
        //создание кнопок и добавление к ним действия
        JButton toRightBtn = new JButton("->");

        toRightBtn.addActionListener((event) -> {
            startX += 20;
            diagram.repaint();
        });

        JButton toLeftBtn = new JButton("<-");
        toLeftBtn.addActionListener((event) -> {
            startX -= 20;
            diagram.repaint();
        });

        JButton toNorthBtn = new JButton("^");
        toNorthBtn.addActionListener((event) -> {
            startY += 20;
            diagram.repaint();
        });

        JButton toSouthBtn = new JButton("\\/");
        toSouthBtn.addActionListener((event) -> {
            startY -= 20;
            diagram.repaint();
        });

        JButton incScaleBtn = new JButton("+");
        incScaleBtn.addActionListener((event) -> {
            ScaleX *= 2;
            ScaleY *= 2;

            diagram.repaint();
        });

        JButton decScaleBtn = new JButton("-");
        decScaleBtn.addActionListener((event) -> {
            ScaleX /= 2;
            ScaleY /= 2;

            diagram.repaint();
        });

        JButton incXScaleBtn = new JButton("x++");
        incXScaleBtn.addActionListener((e) -> {
            ScaleX *= 2;
            diagram.repaint();
        });
        JButton decXScaleBtn = new JButton(("x--"));
        decXScaleBtn.addActionListener((e) -> {
            ScaleX /= 2;
            diagram.repaint();
        });

        JButton incYScaleBtn = new JButton("y++");
        incYScaleBtn.addActionListener((e) -> {
            ScaleY *= 2;
            diagram.repaint();
        });
        JButton decYScaleBtn = new JButton("y--");
        decYScaleBtn.addActionListener((e) -> {
            ScaleY /= 2;
            diagram.repaint();
        });
        JButton restButton = new JButton("rest");
        restButton.addActionListener((e) -> {
            ScaleY = 20;
            ScaleX = 20;
            startX = 20;
            startY = 20;
            diagram.repaint();
        });


        //компоновка элементов в окне
        JPanel mainPanel = new JPanel();
        JPanel panelMove = new JPanel();
        JPanel panelIn = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelLeft = new JPanel();

        panelMove.setLayout(new BorderLayout());
        panelMove.add(BorderLayout.WEST, toLeftBtn);
        panelMove.add(BorderLayout.EAST, toRightBtn);
        panelMove.add(BorderLayout.NORTH, toNorthBtn);
        panelMove.add(BorderLayout.SOUTH, toSouthBtn);
        panelMove.add(BorderLayout.CENTER, panelIn);
        panelIn.add(BorderLayout.WEST, incScaleBtn);
        panelIn.add(BorderLayout.CENTER, decScaleBtn);
        panelLeft.add(BorderLayout.WEST, panelMove);

        panelRight.setLayout(new BorderLayout());
        panelRight.add(BorderLayout.NORTH, incYScaleBtn);
        panelRight.add(BorderLayout.SOUTH, decYScaleBtn);
        panelRight.add(BorderLayout.WEST, decXScaleBtn);
        panelRight.add(BorderLayout.EAST, incXScaleBtn);
        panelRight.add(BorderLayout.CENTER, restButton);


        mainPanel.add(BorderLayout.WEST, panelLeft);
        mainPanel.add(BorderLayout.EAST, panelRight);


        frame.getContentPane().add(BorderLayout.CENTER, diagram);
        frame.getContentPane().add(BorderLayout.SOUTH, mainPanel);
        frame.setVisible(true);
        frame.setSize(700, 637);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    class DiagramPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            startY2 = getHeight() - startY;
            System.out.println(" длина панели? " + getHeight());
            System.out.println(startX);
            System.out.println(startY);


            System.out.println("startY2 = " + startY2);
            grid(g);
            diagramAxes(g);
            //graph(g);
            multiGraph(g,args);
            label(g);
        }



        private void graph(Graphics g) {
            g.setColor(Color.BLACK);
            System.out.println(x.toString());
            System.out.println(y.toString());
            double aX, aY;
            double bX, bY;
            System.out.println(" log X scalable " + x.get(0) * ScaleX);
            System.out.println(" log Y scalable " + y.get(0) * ScaleY);
            aX = x.get(0) * ScaleX + startX;
            aY = startY2 - y.get(0) * ScaleY;
            System.out.println(" log X  " + aX);
            System.out.println(" log Y  " + aY);

            for (int i = 1; i < x.size(); i++) {

                bX = (x.get(i) * ScaleX + startX);
                bY = (startY2 - y.get(i) * ScaleY);

                /*System.out.println(" log X  " + bX);
                System.out.println(" log Y  " + bY);

                System.out.println(" log X scalable " + x.get(i) * ScaleX);
                System.out.println(" log Y scalable " + y.get(i) * ScaleY);*/

                g.drawLine((int) aX, (int) aY, (int) bX, (int) bY);

                aX = bX;
                aY = bY;
            }

        }
        private void multiGraph(Graphics g,ArrayList<ArrayList<Double>> args){
            if(args.size()%2!=0){
                System.out.println("нечетное колв-во параметров");
            }
            else{
                for(int i = 0;i<args.size();i+=2){
                    x = args.get(i);
                    y = args.get(i+1);
                    g.setColor(Color.BLACK);
                    System.out.println(x.toString());
                    System.out.println(y.toString());
                    double aX, aY;
                    double bX, bY;
                    System.out.println(" log X scalable " + x.get(0) * ScaleX);
                    System.out.println(" log Y scalable " + y.get(0) * ScaleY);
                    aX = x.get(0) * ScaleX + startX;
                    aY = startY2 - y.get(0) * ScaleY;
                    System.out.println(" log X  " + aX);
                    System.out.println(" log Y  " + aY);

                    for (int j = 1; j < x.size(); j++) {

                        bX = (x.get(j) * ScaleX + startX);
                        bY = (startY2 - y.get(j) * ScaleY);

                        /*System.out.println(" log X  " + bX);
                        System.out.println(" log Y  " + bY);

                        System.out.println(" log X scalable " + x.get(j) * ScaleX);
                        System.out.println(" log Y scalable " + y.get(j) * ScaleY);*/

                        g.drawLine((int) aX, (int) aY, (int) bX, (int) bY);

                        aX = bX;
                        aY = bY;
                    }

                }
            }
        }
        private void diagramAxes(Graphics g) {
            //System.out.println("startY2 = "+startY2);

            g.setColor(Color.BLACK);
            g.drawLine(startX, 0, startX, getHeight());
            g.drawLine(0, startY2, getWidth(), startY2);

        }
        private void grid(Graphics g) {
            for (int i = 0; i < getHeight(); i += 20) {
                g.setColor(Color.GREEN);
                g.drawLine(0, i, getWidth(), i);
            }
            for (int i = 0; i < getWidth(); i += 20) {
                g.setColor(Color.GREEN);
                g.drawLine(i, 0, i, getHeight());
            }
        }
        private void label(Graphics g) {
            for (int i = 0; i < getWidth(); i += 40) {
                double j = i;
                double tempScale = ScaleX;
                g.drawString(String.valueOf(j / tempScale), (int) (i + startX), (int) (startY2 + 10));
            }
            for (int i = 0; i < getHeight(); i += 40) {
                double j = i;
                double tempScale = ScaleY;
                g.drawString(String.valueOf(j / tempScale), startX, startY2 - i);

            }
        }
    }
}
