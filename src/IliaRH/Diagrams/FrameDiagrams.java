package IliaRH.Diagrams;

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

    private double[] ArrayX,ArrayY;// МАССИВЫ ПО КОТОРЫМ СТРОИТСЯ ГРАФИК
    Diagram diagram;
    JFrame frame;




    public void drawDiagram(double[] arrayX,double[] arrayY){//на вход функция принимает два массива ПО КОТОРЫМ СТРОИТСЯ ГРАФИК
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
                StartY+=5;
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

        //размер окна
        frame.setSize(FrameWidth, FrameHeight);
        diagram = new Diagram();//создаем экземпляр внутреннего класса Diagram


        //компоновка элементов в окне
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.WEST,toLeftBtn);
        panel.add(BorderLayout.EAST,toRightBtn);
        panel.add(BorderLayout.NORTH,toNorthBtn);
        panel.add(BorderLayout.SOUTH,toSouthBtn);
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
        private void drawDiagrams(Graphics g, double[] arrayX, double[] arrayY){
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
        private void graph(Graphics g, double[] arrayX, double[] arrayY ){
            //Алгоритм построения графика
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

            for(int i = 0;i<=maxEl;i+=stepForAxesY){

                g2d.drawString(String.valueOf(i),(int)(StartX2+5),(int)(StartY2-i));
            }



        }
    }

}
