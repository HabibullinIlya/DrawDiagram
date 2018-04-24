package IlyaRH.Tasks;

import IlyaRH.Diagrams.DiagramsFrame;

import java.util.ArrayList;

public class SecondTask {
    private static ArrayList<Double> getY = new ArrayList<>();
    private static ArrayList<Double> getX = new ArrayList<>();

    public static void main(String[] args) {

        ArrayList<Integer> baseModel = new ArrayList<>();
        baseModel.add(-1);
        baseModel.add(1);

        ArrayList<Double> passingX = new ArrayList<>();
        ArrayList<Double> passingY = new ArrayList<>();

        ArrayList<Double> passingX1 = new ArrayList<>();
        ArrayList<Double> passingY1 = new ArrayList<>();

        ArrayList<Double> passingX2 = new ArrayList<>();
        ArrayList<Double> passingY2 = new ArrayList<>();

        ArrayList<Double> passingX3 = new ArrayList<>();
        ArrayList<Double> passingY3 = new ArrayList<>();

        int temp = 1000;

        for (int q = 0; q < 4; q++) {

            switch (q) {
                case 0:
                    passingX.add(1.0);
                    passingY.add(1.0);

                    for (int i = 0; i < temp; i++) {
                        double temp_x = passingX.get(i) + 0.01 * (4 * passingX.get(i) * passingX.get(i));
                        double temp_y = passingY.get(i) + 0.01 * (4 * passingY.get(i));
                        passingX.add(temp_x);
                        passingY.add(temp_y);
                    }

                    break;
                case 1:
                    passingX1.add(1.0);
                    passingY1.add(-1.0);

                    for (int i = 0; i < temp; i++) {
                        double temp_x = passingX1.get(i) + 0.01 * (4 * passingX1.get(i) * passingX1.get(i));
                        double temp_y = passingY1.get(i) + 0.01 * (4 * passingY1.get(i));
                        passingX1.add(temp_x);
                        passingY1.add(temp_y);
                    }
                    break;
                case 2:
                    passingX2.add(-1.0);
                    passingY2.add(1.0);

                    for (int i = 0; i < temp; i++) {
                        double temp_x = passingX2.get(i) + 0.01 * (4 * passingX2.get(i) * passingX2.get(i));
                        double temp_y = passingY2.get(i) + 0.01 * (4 * passingY2.get(i));
                        passingX2.add(temp_x);
                        passingY2.add(temp_y);
                    }
                    break;
                case 3:
                    passingX3.add(-1.0);
                    passingY3.add(-1.0);

                    for (int i = 0; i < temp; i++) {
                        double temp_x = passingX3.get(i) + 0.01 * (4 * passingX3.get(i) * passingX3.get(i));
                        double temp_y = passingY3.get(i) + 0.01 * (4 * passingY3.get(i));
                        passingX3.add(temp_x);
                        passingY3.add(temp_y);
                    }
                    break;
            }

/*
            System.out.println("GET X: " + passingX);
            System.out.println("GET Y: " + passingY);
*/
        }

        DiagramsFrame frameDiagrams = new DiagramsFrame();
        //frameDiagrams.drawFrame(passingX, passingY, passingX1, passingY1, passingX2, passingY2, passingX3, passingY3);
    }
}