import scala.io.Source
import scala.math

object Day13 {
    val input = Source.fromFile("./input/input13.txt").getLines().toArray
    val stamp = input(0).toInt
    val times = input(1).split(",").map{
        case "x" => 0
        case t => t.toInt
    }

    println( times.filterNot(0.equals).map(t  => (t, stamp + (t - stamp % t)))
        .minBy(_._2) match {
            case (x,y) => x*(y-stamp)
        }
    )

    def eea: (BigInt,BigInt) => (BigInt,BigInt,BigInt) = {
        case (a, b) if BigInt(0).equals(b) => (BigInt(1), BigInt(0), a)
        case (a, b) => eea(b, a%b) match {
            case (x,y,g) => (y, x - y*(a/b), g)
        }
    }

    def fdiv(x: BigInt, y: BigInt): BigInt =
        x / y + math.min(x.signum*y.signum, 0);

    def fmod(x: BigInt, y: BigInt): BigInt =
        x - fdiv(x,y)*y

    def crt(l: (BigInt,BigInt), r: (BigInt,BigInt)): (BigInt,BigInt) =
        eea(l._2, r._2) match {
            case (x, y, g) => ((l._1*r._2*y + r._1*l._2*x), l._2*r._2) match {
                case (c, m) => (fmod(c,m), m)
            }
        }

    println( times
        .map(BigInt(_))
        .zipWithIndex
        .filterNot(_._1 == BigInt(0))
        .map{ case (t,i) => (fmod(t-BigInt(i), t), t) }
        .reduce(crt)._1
    )
}
