import scala.io.Source
import scala.math

object Day13 {
    val input = Source.fromFile("./input/input13.txt").getLines().toArray
    val stamp = input(0).toInt
    val times = input(1).split(",").map{
        case "x" => 0
        case t => t.toInt
    }

    println( times
        .filterNot(0.equals)
        .map(t  => (t, stamp + (t - stamp % t)))
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

    def fdiv(x: BigInt, y: BigInt): BigInt = x / y + math.min(x.signum*y.signum,0);

    def fmod(x: BigInt, y: BigInt): BigInt = x - fdiv(x,y)*y

    def crt: ((BigInt,BigInt), (BigInt,BigInt)) => (BigInt,BigInt) = {
        case ((lc, lm), (rc, rm)) => eea(lm, rm) match {
            case (x, y, g) => ((lc*rm*y + rc*lm*x), lm*rm/g) match {
                case (c, m) => (fmod(c,m), m)
            }
        }
    }

    println( times
        .map(BigInt(_))
        .zipWithIndex
        .filterNot(_._1 == BigInt(0))
        .map{ case (t,i) => (t - BigInt(i), t) }
        .reduce(crt)._1
    )
}
