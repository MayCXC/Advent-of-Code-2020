import scala.io.Source

object Day5 {
    val input = Source.fromFile("./input/input5.txt").getLines().toArray
    val ids = input
        .map(_.map(Map('F'->'0', 'B'->'1', 'L'->'0', 'R'->'1')))
        .map(Integer.parseInt(_,2))

    println(ids.max)

    (ids.min to ids.max).find(!ids.contains(_)).foreach(println)
}
