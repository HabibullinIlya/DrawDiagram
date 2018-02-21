import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameDiagrams {
    private int FrameHeight = 560;
    private int FrameWidth = 700;


    //НУЖНЫ ДЛЯ ПЕРОБРАЗОВАНИЙ ИЗ СКЭ В ОБЫЧНЫЕ КООРИДИНАТЫ
    double Wcx, Wcy, Wy, Wx;
    double Vcx, Vcy, Vy, Vx;
    private double Left = 1;//ОТСТУП СЛЕВА
    private double Right = 1;//СПРАВА
    private double Top = 1;//СВЕРХУ
    private double Bottom = 1;//СНИЗУ
    private double StartX = 250;//КООРДИНАТЫ НАЧАЛА КООРДИНАТ КООРДИНАТНОЙ ПЛОСКОСТИ. Х. В СКЭ
    private double StartY = 250;//У
    private double StartX2;//В ОБЫЧНЫХ КООРДИНАТАХ
    private double StartY2;

    private double[] ArrayX,ArrayY;
    Diagram diagram;
    JFrame frame;




    public void drawDiagram(double[] arrayX,double[] arrayY){
        this.ArrayX = arrayX;
        this.ArrayY = arrayY;
        frame = new JFrame();

        JButton toRightBtn = new JButton("->");
        toRightBtn.addActionListener(new ToRightListener());

        JButton toLeftBtn = new JButton("<-");
        toLeftBtn.addActionListener(new ToLeftListener());

        JButton toNorthBtn = new JButton("^");
        toNorthBtn.addActionListener(new ToNorthListener());

        JButton toSouthBtn = new JButton("\\/");
        toSouthBtn.addActionListener(new ToSouthListener());


        frame.setSize(FrameWidth, FrameHeight);
        diagram = new Diagram();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.WEST,toLeftBtn);
        panel.add(BorderLayout.EAST,toRightBtn);
        panel.add(BorderLayout.NORTH,toNorthBtn);
        panel.add(BorderLayout.SOUTH,toSouthBtn);
        //panel.add(toLeftBtn);
        //panel.add(toRightBtn);

        frame.getContentPane().add(BorderLayout.CENTER,diagram);
        frame.getContentPane().add(BorderLayout.SOUTH,panel);
        //frame.getContentPane().add(BorderLayout.SOUTH,toLeftBtn);
        //frame.getContentPane().add(BorderLayout.SOUTH,toRightBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    class Diagram extends JPanel{
        private int Height = 500;//
        private int Width = 700;//



        public Diagram(){

            /*Wcx = Left;
            Wcy = Height-Bottom;
            Wx = Width - Left - Right;
            Wy = Height - Top - Bottom;

            Vcy = 30;
            Vcx = Left;
            Vx = Width - Left - Right;
            Vy = Height - Top - Bottom;
            StartX2 = (StartX - Vcx)/Vx*Wx+Wcx;
            StartY2 = Wcy - ((StartY -Vcy)/Vy)*Wy;*/

        }
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());
            Wcx = Left;
            Wcy = Height-Bottom;
            Wx = Width - Left - Right;
            Wy = Height - Top - Bottom;

            Vcy = 30;
            Vcx = Left;
            Vx = Width - Left - Right;
            Vy = Height - Top - Bottom;
            StartX2 = (StartX - Vcx)/Vx*Wx+Wcx;
            StartY2 = Wcy - ((StartY -Vcy)/Vy)*Wy;

            g.setColor(Color.BLACK);
            drawDiagrams(g,ArrayX,ArrayY);
            double pixelW = (1 / Wx)*Vx;
            for(int i = 0;i<5;i++){
                Vx -= pixelW;
                Vcx += pixelW / 2;
            }
            drawDiagrams(g,ArrayX,ArrayY);
            System.out.println("kkkk");
        }
        private void drawDiagrams(Graphics g, double[] arrayX, double[] arrayY){
            drawAxesX(g,(int) StartY2);
            drawAxesY(g,(int) StartX2);
            graph(g,arrayX,arrayY);
        }
        private void drawAxesX(Graphics g,int y0){

            Graphics2D g2d = (Graphics2D)g;
            BasicStroke bSAxes = new BasicStroke(2.0f);
            g2d.setStroke(bSAxes);

            g2d.drawLine((int)0,(int)y0,(int)(Width),(int)y0);

        }
        private void drawAxesY(Graphics g, int x0){

            Graphics2D g2d = (Graphics2D)g;
            BasicStroke bSAxes = new BasicStroke(2.0f);
            g2d.setStroke(bSAxes);

            g2d.drawLine(x0,0,x0,(int)Height);

        }
        private void graph(Graphics g, double[] arrayX, double[] arrayY ){
            Graphics2D g2d = (Graphics2D)g;
            BasicStroke bSAxes = new BasicStroke(2.0f);
            g2d.setStroke(bSAxes);
            for(int i = 0;i<arrayX.length-1;i++){
                g2d.drawLine((int)(arrayX[i]+StartX2),(int)(StartY2-arrayY[i]),(int)(arrayX[i+1]+StartX2),(int)(StartY2-arrayY[i+1]));
            }
            for(int i = 0;i<arrayX.length;i+=5){
                g2d.drawString(String.valueOf(arrayX[i]),(int)(StartX2+arrayX[i]),(int)(StartY2+10));
            }
            int stepForAxesY = 100;
            double maxEl = -1100000000;
            for(int i = 0;i<arrayY.length;i++){
                if(arrayY[i]>maxEl)
                    maxEl = ArrayY[i];
            }
            System.out.println("max element "+maxEl);
            for(int i = 0;i<=maxEl;i+=stepForAxesY){
                //System.out.println(StartY2-ArrayY[i]);
                g2d.drawString(String.valueOf(i),(int)(StartX2+5),(int)(StartY2-i));
            }
            System.out.println();


        }
    }


    private class ToRightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            StartX+=5;
            diagram.repaint();
        }
    }

    private class ToLeftListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            StartX-=5;
            diagram.repaint();
        }
    }

    private class ToNorthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            StartY+=5;
            diagram.repaint();
        }
    }

    private class ToSouthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            StartY-=5;
            diagram.repaint();
        }
    }
}
