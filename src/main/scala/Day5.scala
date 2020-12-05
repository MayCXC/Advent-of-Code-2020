import scala.io.Source

object Day5 {
    val input = Source.fromFile("./input/input5.txt").getLines.toArray

    def bsp(lc: Char, rc: Char, li: Int, ri: Int): String => Int = {
        case left if left.headOption.contains(lc) => bsp(lc, rc, li, (li+ri)/2)(left.tail)
        case right if right.headOption.contains(rc) => bsp(lc, rc, (li+ri)/2, ri)(right.tail)
        case _ => (li+ri)/2
    }

    val ids = input.map(s => (bsp('F','B',0,128)(s) << 3) | bsp('L','R',0,8)(s.substring(7)))
    println(ids.max)

    def range(l: Array[Int]) = l.min to l.max

    range(ids).find(!ids.contains(_)).foreach(println)
}
