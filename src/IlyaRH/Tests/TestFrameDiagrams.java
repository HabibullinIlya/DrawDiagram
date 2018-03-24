package IlyaRH.Tests;

import IlyaRH.Diagrams.DiagramsFrame;

import java.util.ArrayList;

public class TestFrameDiagrams {
    static private double f(int N, double x){
        double k = 0.01;
        return k*x*(N-x);
    }
    static public ArrayList<Double> solutionEqu(int N, int a){

        double h = 0.01;//шаг

        ArrayList<Double> Y = new ArrayList<>();
        Y.add(0, (double) a);
        int i = 0;

        while (Y.get(i)<=N*0.95){
            double temp =  Y.get(i) + h * f(N,Y.get(i));
            Y.add(i+1,temp);
            i++;
        }
        return Y;
    }
    public static void main(String[] args){
        DiagramsFrame frameDiagrams = new DiagramsFrame();
        ArrayList<Double> arrayX = new ArrayList<>(0);
        ArrayList<Double> arrayY;

        double h = 0.01;
        double x;
        arrayY = solutionEqu(235,4);
        for(int i = 0;i<arrayY.size();i++){
            x = i*h;
            arrayX.add(i,x);
            System.out.print(" "+x+" ");
        }


        frameDiagrams.drawFrame(arrayX,arrayY);

    }
}
