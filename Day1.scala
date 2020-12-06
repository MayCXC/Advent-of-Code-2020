import scala.io.Source

object Day1 {
    val input = Source.fromFile("./input/input1.txt").getLines().toArray

    input.map(_.toInt)
        .combinations(2)
        .find(_.sum == 2020)
        .map(_.product)
        .foreach(println)
    
    input.map(_.toInt)
        .combinations(3)
        .find(_.sum == 2020)
        .map(_.product)
        .foreach(println)
}
