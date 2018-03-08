package IlyaRH.Tests;


import IliaRH.Diagrams.FrameDiagrams;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SimpleTest {
    public static void main(String[] args){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();

        x.add(2.0);
        x.add(4.0);

        y.add(2.0);
        y.add(4.0);

      FrameDiagrams frameDiagrams = new FrameDiagrams();
      frameDiagrams.drawDiagram(x,y);
    }
}
