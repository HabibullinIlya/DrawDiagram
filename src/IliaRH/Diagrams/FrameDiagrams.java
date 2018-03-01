package IliaRH.Diagrams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private double StartX = 40;//КООРДИНАТЫ НАЧАЛА КООРДИНАТ КООРДИНАТНОЙ ПЛОСКОСТИ. Х. В СКЭ
    private double StartY = 40;//У
    private double StartX2;//В ОБЫЧНЫХ КООРДИНАТАХ
    private double StartY2;
    private double Grid;
    private double Scale = 10;

    private ArrayList<Double> ArrayX,ArrayY;// МАССИВЫ ПО КОТОРЫМ СТРОИТСЯ ГРАФИК
    Diagram diagram;
    JFrame frame;




    public void drawDiagram( ArrayList<Double> arrayX,ArrayList<Double> arrayY){//на вход функция принимает два массива ПО КОТОРЫМ СТРОИТСЯ ГРАФИК
        this.ArrayX = arrayX;
        this.ArrayY = arrayY;
        frame = new JFrame();


        //создание кнопок и добавление к ним действия
        JButton toRightBtn = new JButton("->");


        toRightBtn.addActionListener((event)-> {
                StartX += 5;
                diagram.repaint();
        });

        JButton toLeftBtn = new JButton("<-");
        toLeftBtn.addActionListener((event)->{
                StartX-=5;
                diagram.repaint();
        });

        JButton toNorthBtn = new JButton("^");
        toNorthBtn.addActionListener((event)->{
                StartY+=5;
                diagram.repaint();
        });

        JButton toSouthBtn = new JButton("\\/");
        toSouthBtn.addActionListener((event)->{
                StartY-=5;
                diagram.repaint();
        });
        JButton incScaleBtn = new JButton("+");
        incScaleBtn.addActionListener((event)-> {
            Scale*=5;
            diagram.repaint();});

        JButton decScaleBtn = new JButton("-");
        decScaleBtn.addActionListener((event)->{
            Scale/=5;
            diagram.repaint();
        });

        //размер окна
        frame.setSize(FrameWidth, FrameHeight);
        diagram = new Diagram();//создаем экземпляр внутреннего класса Diagram


        //компоновка элементов в окне
        JPanel panel = new JPanel();
        JPanel panelIn = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.WEST,toLeftBtn);
        panel.add(BorderLayout.EAST,toRightBtn);
        panel.add(BorderLayout.NORTH,toNorthBtn);
        panel.add(BorderLayout.SOUTH,toSouthBtn);
        panel.add(BorderLayout.CENTER,panelIn);
        panelIn.add(BorderLayout.WEST,incScaleBtn);
        panelIn.add(BorderLayout.CENTER,decScaleBtn);
        frame.getContentPane().add(BorderLayout.CENTER,diagram);
        frame.getContentPane().add(BorderLayout.SOUTH,panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    class Diagram extends JPanel{//КЛАСС РЕАЛИЗУЮЩИЙ ОТРИСОВКУ ГРАФИКА
        private int Height = 500;//МЭЙБИ НУЖНО ЗАМЕНИТЬ НА frame.getHeight()-некотрое число пикселей
        private int Width = 700;//но это не точно

        @Override
        protected void paintComponent(Graphics g) {//отрисвка графика
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());

            //задаем область на которой будет график
            Wcx = Left;//левый нижний угол области в СКЭ
            Wcy = Height-Bottom;//левый нижний угол области в СКЭ
            Wx = Width - Left - Right;//размер области по икс
            Wy = Height - Top - Bottom;//размер области по игрик

            Vcy = 1;//аналогично в КСК
            Vcx = Left;
            Vx = Width - Left - Right;
            Vy = Height - Top - Bottom;
            StartX2 = (StartX - Vcx)/Vx*Wx+Wcx;
            StartY2 = Wcy - ((StartY -Vcy)/Vy)*Wy;

            g.setColor(Color.BLACK);
            drawDiagrams(g,ArrayX,ArrayY);//отрисовка


        }
        private void drawDiagrams(Graphics g, ArrayList<Double>  arrayX, ArrayList<Double>  arrayY){
            drawAxesX(g,(int) StartY2);//оси
            drawAxesY(g,(int) StartX2);
            graph(g,arrayX,arrayY);//график
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
        private void graph(Graphics g,ArrayList<Double> arrayX , ArrayList<Double>  arrayY ){
            //Алгоритм построения графика
            Graphics2D g2d = (Graphics2D)g;
            BasicStroke bSAxes = new BasicStroke(2.0f);
            g2d.setStroke(bSAxes);

            System.out.println(arrayX.toString());
            System.out.println(arrayY.toString());



            double aX,aY;
            double bX,bY;
            aX = arrayX.get(0)+StartX2;
            aY = StartY - arrayY.get(0);
            for(int i = 1;i<arrayX.size()-1;i++){

                bX = (arrayX.get(i+1)*Scale+StartX2);
                System.out.println("scalable X:");
                System.out.println(" "+(bX - StartX)+" ");
                bY = (StartY2-arrayY.get(i+1)*Scale);
                System.out.println("scalable Y");
                System.out.println(" "+bY+" ");
                g2d.drawLine((int)aX,(int)aY,(int)bX,(int)bY);
                System.out.println("integer X:");
                System.out.println(" "+(int)(bX- StartX)+" ");
                System.out.println("integer Y:");
                System.out.print(" "+(int)bY+" ");

                //g2d.drawLine((int)((arrayX.get(i)+StartX2)),(int)((StartY2-arrayY.get(i))),(int)((arrayX.get(i+1)+StartX2)*Scale),(int)((StartY2-arrayY.get(i+1))*Scale));
                aX = bX;
                aY = bY;
            }
            /*for(int i = 0;i<arrayX.size();i+=5){
                g2d.drawString(String.valueOf(arrayX.get(i)),(int)(StartX2+arrayX.get(i)),(int)(StartY2+10));
            }*/

        }
    }

}
