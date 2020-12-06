import scala.io.Source

object Day6 {
    val input = Source.fromFile("./input/input6.txt").getLines().toArray
    val answers = input
        .mkString(",")
        .split(",,")
        .map(_.split(",").map(_.toSet))

    println(answers.map(_.reduce(_.union(_)).size).sum)    

    println(answers.map(_.reduce(_.intersect(_)).size).sum)
}
