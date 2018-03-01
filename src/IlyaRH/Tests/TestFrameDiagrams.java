package IlyaRH.Tests;

import IliaRH.Diagrams.FrameDiagrams;

import java.util.ArrayList;

public class TestFrameDiagrams {
    public static void main(String[] args){
        FrameDiagrams frameDiagrams = new FrameDiagrams();
        ArrayList<Double> arrayX = new ArrayList<>(0);
        ArrayList<Double> arrayY;
        double a = -100;
        double b = 100;
        double h = 0.01;
        double x;
        arrayY = solutionEqu(235,4);
        for(int i = 0;i<arrayY.size();i++){
            x = i*h;
            arrayX.add(i,x);
            System.out.print(" "+x+" ");
        }


        frameDiagrams.drawDiagram(arrayX,arrayY);
    }
    static private double f(int N, double x){
        double k = 0.01;
        return k*x*(N-x);
    }
    static public ArrayList<Double> solutionEqu(int N, int a){
        //int N = 100000, a = 2;

        double h = 0.01;//шаг

        int n = 999;
        double[] x = new double[n];
        ArrayList<Double> X = new ArrayList<>();
        x[0] = a;
        X.add(0, (double) a);


        int i = 0;

        while (X.get(i)<=N*0.95){
            double temp=  X.get(i) + h * f(N,X.get(i));
            X.add(i+1,temp);

            i++;
            //System.out.println(X.get(i));
        }
        return X;
    }
}
