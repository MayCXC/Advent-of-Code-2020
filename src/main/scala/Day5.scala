import scala.io.Source

object Day5 {
    val input = Source.fromFile("./input/input5.txt").getLines.toArray
    val ids = input.map(s => Integer.parseInt(s.map(Map('F' ->'0', 'B'->'1', 'L'->'0', 'R'->'1')(_)),2))
    println(ids.max)
    (ids.min to ids.max).find(!ids.contains(_)).foreach(println)
}
