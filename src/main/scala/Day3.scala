import scala.io.Source

object Day3 {
    val input = Source.fromFile("./input/input3.txt").getLines.toArray

    println( input
        .zipWithIndex.count{ case (line,index) => line((index*3)%line.length).equals('#') }
    )

    println( Array((1,1),(1,3),(1,5),(1,7),(2,1))
        .map{ case (y,x) =>
            input.grouped(y).map(_.head).zipWithIndex
                .count{ case (line,index) =>
                    line((index*x)%line.length).equals('#')
                }
        }
        .product
    )
}
