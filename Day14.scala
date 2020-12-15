import scala.io.Source
import scala.math

object Day14 {
    val input = Source.fromFile("./input/input14.txt").getLines().toArray
    val one = scala.collection.mutable.Map[Long,Long]()
    val two = scala.collection.mutable.Map[Long,Long]()
    var bitmask = ""

    def float: List[Char] => Iterator[List[Char]] = {
        case 'X' :: tail => float('0' :: tail) ++ float('1' :: tail)
        case b :: tail => float(tail).map(b :: _)
        case Nil => Iterator(Nil)
    }

    for(l <- input) l match {
        case s"mask = ${mask}" => bitmask = mask
        case s"mem[${index}] = ${number}" => {
            val i = index.toInt
            val n = number.toLong.toBinaryString
            one(i) = java.lang.Long.parseLong(
                bitmask.zip("0" * (bitmask.length - n.length) + n).map{
                    case ('X', c) => c
                    case (b, c) => b
                }.mkString,
                2
            )

            val d = i.toBinaryString
            for(j <- float( bitmask.zip("0" * (bitmask.length - d.length) + d)
                    .map{
                        case ('0', c) => c
                        case (b, c) => b
                    }.toList
                )
                .map(_.mkString)
                .map(java.lang.Long.parseLong(_, 2).toLong)
            ) {
                two(j) = number.toLong
            }
        }
    }

    println(one.values.sum)

    println(two.values.sum)
}
