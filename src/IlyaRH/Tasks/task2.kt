package IlyaRH.Tasks

import IlyaRH.Diagrams.DiagramsFrame
import java.util.*
import kotlin.collections.ArrayList

class task2 {
    fun difur() {
        var sc :Scanner = Scanner(System.`in`)

        val arrayOfGraph = ArrayList<ArrayList<Double>>()

        val initPoints = doubleArrayOf(-1.0,0.5,-1.0,-0.5,0.3,1.5,0.8,-0.5,0.8,0.5,0.9,0.7)

        for(i in 0..11 step 2){
            var arrayX = ArrayList<Double>()
            var arrayY = ArrayList<Double>()
            //println("enter a1")
            val a1 = -1
            //println("enter a2")
            val a2 = -2
            println("enter x0")
            arrayX.add(initPoints[i])
            println("enter y0")
            arrayY.add(initPoints[i+1])
            var h = 0.01
            var numberOfLoop = 1000
            for (i in 0 until numberOfLoop) {
                var x = arrayX[i]
                var y = arrayY[i]
                arrayX.add(x + h * (a1 * x + x * x * y + x * x * x * y + x * x * x * x * y))
                arrayY.add(y + h * (a2 * y + y * y * x + y * y * y * x + y * y * y * y * x))
            }
            println(arrayX.toString())
            println(arrayY.toString())
            arrayOfGraph.add(arrayX)
            arrayOfGraph.add(arrayY)

        }
        val df = DiagramsFrame()
        df.drawFrame(arrayOfGraph[0],arrayOfGraph[1],arrayOfGraph[2],arrayOfGraph[3],arrayOfGraph[4],
                arrayOfGraph[5],arrayOfGraph[6],arrayOfGraph[7],arrayOfGraph[8],arrayOfGraph[9],arrayOfGraph[10],arrayOfGraph[11])
        /*while(true){
            var arrayX = ArrayList<Double>()
            var arrayY = ArrayList<Double>()
            //println("enter a1")
            val a1 = -1
            //println("enter a2")
            val a2 = -2
            println("enter x0")
            arrayX.add(sc.nextDouble())
            println("enter y0")
            arrayY.add(sc.nextDouble())
            var h = 0.01
            var numberOfLoop = 1000
            for (i in 0 until numberOfLoop) {
                var x = arrayX[i]
                var y = arrayY[i]
                arrayX.add(x + h * (a1 * x + x * x * y + x * x * x * y + x * x * x * x * y))
                arrayY.add(y + h * (a2 * y + y * y * x + y * y * y * x + y * y * y * y * x))
            }
            println(arrayX.toString())
            println(arrayY.toString())
            val df = DiagramsFrame()
            df.drawFrame(arrayX,arrayY)
            *//*val testX = ArrayList<Double>()
            testX.add(0.0)
            testX.add(2.0)
            testX.add(1.0)
            testX.add(3.0)
            val testY = ArrayList<Double>()
            testY.add(0.0)
            testY.add(1.0)
            testY.add(2.0)
            testY.add(3.0)*//*

            //df.drawFrame(testX,testY)
        }*/


    }

}

fun main(args: Array<String>) {
    var task = task2()
    task.difur()
}