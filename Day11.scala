import scala.io.Source

object Day11 {
    val input = Source.fromFile("./input/input11.txt").getLines().toArray
    var (verso,recto) = (input.map(_.toArray), input.map(_.toArray))

    def adjacent(i: Int, j: Int, x: Int, y: Int): Boolean = verso
        .lift(i+x)
        .flatMap(_.lift(j+y))
        .contains('#')

    def visible(i: Int, j: Int, x: Int, y: Int): Boolean =
        if(0 <= i && i < verso.length && 0 <= j && j < verso(i).length) {
            if(verso(i)(j) == '#') true
            else if(verso(i)(j) == 'L') false
            else visible(i+x, j+y, x, y)
        }
        else false

    val (occupied, tolerance) =
        (adjacent(_,_,_,_), 4)
    //  (visible(_,_,_,_), 5)

    var mutated = true
    while(mutated) {
        mutated = false
        for(i <- 0 until verso.length)
            for(j <- 0 until verso(i).length)
                if(verso(i)(j) != '.') {
                    recto(i)(j) = List(
                        (-1,-1), (-1, 0), (-1, 1),
                        ( 0,-1),          ( 0, 1),
                        ( 1,-1), ( 1, 0), ( 1, 1)
                    )
                    .count{ case (x,y) => occupied(i,j,x,y) }
                    match {
                        case stand if verso(i)(j) == '#' && stand >= tolerance => 'L'
                        case sit if verso(i)(j) == 'L' && sit == 0 => '#'
                        case _ => verso(i)(j)
                    }
                    mutated |= recto(i)(j) != verso(i)(j)
                }

        val swap = verso
        verso = recto
        recto = swap

        ///*
        val unstable = verso.zip(recto).indexWhere(t => !(t._1 sameElements t._2))
        if(unstable >= 0) {
            println(unstable)
            println(verso(unstable).mkString)
            println(recto(unstable).mkString)
        }
        //*/
    }

    println(verso.map(_.count('#'.equals)).sum)
}
