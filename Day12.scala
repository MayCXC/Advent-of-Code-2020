import scala.io.Source
import scala.math

object Day12 {
    val input = Source.fromFile("./input/input12.txt").getLines().toArray
    var dx = 0 // part one displacement and orientation
    var dy = 0
    var ox = 1
    var oy = 0
    var px = 0 // part two position and waypoint
    var py = 0
    var wx = 10
    var wy = 1

    for(action <- input)
        (action.head, action.tail.toInt) match {
            case ('N',n) => dy += n; wy += n
            case ('S',n) => dy -= n; wy -= n
            case ('E',n) => dx += n; wx += n
            case ('W',n) => dx -= n; wx -= n
            case ('F',n) => {
                dx += ox*n
                dy += oy*n
                px += wx*n
                py += wy*n
            }
            case (r,n) => {
                val s = math.signum(n) * (r match {
                    case 'L' => +1
                    case 'R' => -1
                })
                for(i <- 0 until math.abs(n)/90) {
                    val nox = -oy*s
                    val noy = +ox*s
                    ox = nox
                    oy = noy
                    val nwx = -wy*s
                    val nwy = +wx*s
                    wx = nwx
                    wy = nwy
                }
            }
        }
    
    println(math.abs(dx) + math.abs(dy))

    println(math.abs(px) + math.abs(py))
}
