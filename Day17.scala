import scala.io.Source

object Day17 {
    val input = Source.fromFile("./input/input17.txt").getLines().toArray
    val pocket = scala.collection.mutable.Map[(Int,Int,Int),Boolean]().withDefaultValue(false)
    val fourd = scala.collection.mutable.Map[(Int,Int,Int,Int),Boolean]().withDefaultValue(false)

    for(y <- 0 until input.length)
        for(x <- 0 until input(y).length)
            if(input(y)(x) == '#') {
                pocket((x,y,0)) = true
                fourd((x,y,0,0)) = true
            }

    for(cycle <- 1 to 6) {
        val neighbors = scala.collection.mutable.Map[(Int,Int,Int),Int]().withDefaultValue(0)

        for( ((x,y,z), v) <- pocket; if v; xd <- -1 to 1; yd <- -1 to 1; zd <- -1 to 1 )
            neighbors((x+xd,y+yd,z+zd)) += (xd != 0 || yd != 0 || zd != 0).compare(false)

        for( ((x,y,z), v) <- neighbors ) {
            pocket((x,y,z)) = pocket((x,y,z)) && (v == 2 || v == 3) || v == 3
        }

        val fourn = scala.collection.mutable.Map[(Int,Int,Int,Int),Int]().withDefaultValue(0)

        for( ((x,y,z,w), v) <- fourd; if v; xd <- -1 to 1; yd <- -1 to 1; zd <- -1 to 1; wd <- -1 to 1 )
            fourn((x+xd,y+yd,z+zd,w+wd)) += (xd != 0 || yd != 0 || zd != 0 || wd != 0).compare(false)

        for( ((x,y,z,w), v) <- fourn ) {
            fourd((x,y,z,w)) = fourd((x,y,z,w)) && (v == 2 || v == 3) || v == 3
        }
    }

    println(pocket.values.count(_ == true))

    println(fourd.values.count(_ == true))
}
