import scala.io.Source

object Day7 {
    val input = Source.fromFile("./input/input7bb.txt").getLines().toArray
    val bags = input
        .map( _ match {
            case s"${outside} bags contain ${inside}." => (
                outside,
                inside match {
                    case "no other bags" => Map[String,Int]()
                    case _ => inside
                        .split(", ")
                        .map{ case s"${number} ${name} bag${_}" => (name, number.toInt) }
                        .toMap
                }
            )
        })
        .toMap
/*
    def carry(color: String): Set[String] = bags
        .filter(_._2.contains(color))
        .keySet
        .flatMap(carry)
        .incl(color)

    println(carry("shiny gold").size - 1)

    def count(color: String): Int = 1 + bags(color)
        .map{ case (outside, inside) => inside*count(outside) }
        .sum

    println(count("shiny gold") - 1)
*/
    val invert = bags
        .map{ case (outside, inside) => collection.immutable.HashMap().concat(
            inside.map{ case (name, number) =>
                (name, Map(outside->number))
            }
        )}
        .reduce( _.merged(_){ case ((lk,lv),(rk,rv)) =>
            (lk.toSeq.intersect(rk).unwrap, lv.concat(rv))
        })

    def fastcarry(memo: Set[String], next: Set[String]): Set[String] =
        if (next.isEmpty) memo
        else fastcarry(
            memo.union(next),
            next.diff(memo).flatMap(invert.lift).flatMap(_.keySet)
        )

    println(fastcarry(Set(), Set("shiny gold")).size - 1)

    def fastcount(memo: Map[String,Int], next: Set[String]): Map[String,Int] =
        if (next.isEmpty) memo
        else fastcount(
            memo.concat( next
                .filter( bags(_).keySet.subsetOf(memo.keySet) )
                .map( n => n -> (bags(n).map(m => memo(m._1) * m._2).sum + 1) )
                .toMap
            ),
            next.diff(memo.keySet).flatMap(invert.lift).flatMap(_.keySet)
        )

    println(fastcount(Map(), bags.filter(_._2.isEmpty).keySet)("shiny gold") - 1)
}
