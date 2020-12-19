import scala.io.Source

object Day15 {
    val input = "6,19,0,5,7,13,1"
    class Game(var spoken: Map[Int,Int], var age: Int, var turn: Int) extends Iterator[Int] {
        def hasNext = true
        def next() = {
            val i = turn - spoken.getOrElse(age,turn)
            spoken = spoken.updated(age,turn)
            age = i
            turn = turn + 1
            i
        }
    }
    val start = input.split(",").map(_.toInt)

    for(part <- List(2020, 30000000)) println(
        new Game(
            start.init.zipWithIndex.toMap,
            start.last,
            start.length - 1
        )
        .drop(part - 1 - start.length)
        .next()
    )
}
