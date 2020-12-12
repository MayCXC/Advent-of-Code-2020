import scala.io.Source
import scala.math

object Day12 {
    val input = Source.fromFile("./input/input12.txt").getLines().toArray
    var dx = 0
    var dy = 0
    var ox = 1
    var oy = 0

    var px = 0
    var py = 0
    var wx = 10
    var wy = 1

    for(i <- 0 until input.length)
        (input(i).head, input(i).tail.toInt) match {
            case ('N',n) => {
                dy += n
                wy += n
            }
            case ('S',n) => {
                dy -= n
                wy -= n
            }
            case ('E',n) => {
                dx += n
                wx += n
            }
            case ('W',n) => {
                dx -= n
                wx -= n
            }
            case ('F',n) => {
                dx += ox*n
                dy += oy*n
                px += wx*n
                py += wy*n
            }
            case (r,nn) => {
                val n = nn/90 * (r match {
                    case 'L' => +1
                    case 'R' => -1
                })
                for(i <- 0 until math.abs(n)) {
                    val nox = -math.signum(n)*oy
                    val noy = +math.signum(n)*ox
                    val nwx = -math.signum(n)*wy
                    val nwy = +math.signum(n)*wx
                    ox = nox
                    oy = noy
                    wx = nwx
                    wy = nwy
                }
            }
        }
    
    println(math.abs(dx)+math.abs(dy))
    println(math.abs(px)+math.abs(py))
}
