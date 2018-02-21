public class TestFrameDiagrams {
    public static void main(String[] args){
        FrameDiagrams frameDiagrams = new FrameDiagrams();
        double[] arrayX = new double[21];
        double[] arrayY = new double[21];
        double a = -100;
        double b = 100;
        double h = 10;
        double x;
        for(int i = 0;i<21;i++){
            x = h*i+a;
            arrayX[i] = x;
            arrayY[i] = Math.pow(x/10,2);
        }
        for(int i = 0;i<21;i++){
            System.out.print(" "+arrayX[i]+" ");
        }
        System.out.println();
        for(int i = 0;i<21;i++){
            System.out.print(" "+arrayY[i]+" ");
        }
        frameDiagrams.drawDiagram(arrayX,arrayY);
    }
}
