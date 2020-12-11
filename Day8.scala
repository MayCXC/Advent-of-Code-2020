import scala.io.Source

object Day8 {
    val input = Source.fromFile("./input/input8.txt").getLines().toArray
    val visited = Array.fill(input.length){false}
    var checked = 0
    var acc = 0
    var off = 0

    while(off >= 0) {
        if(off >= visited.length) {
            println(acc)
            off = -1
        }
        else if(visited(off)) {
            if(checked == 0) {
                println(acc)
            }
            for(i <- 0 until visited.length) {
                visited(i) = false
            }
            checked += 1
            acc = 0
            off = 0
        }
        else {
            visited(off) = true
            input(off) match {
                case s"acc $arg" => {
                    off += (if(off != checked) 1 else if(checked == 0) 1 else 0)
                    acc += arg.toInt
                }
                case s"jmp $arg" => off += (if(off != checked) arg.toInt else 1)
                case s"nop $arg" => off += (if(off != checked) 1 else arg.toInt)
            }
        }
    }
}
