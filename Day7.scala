import scala.io.Source

object Day7 {
    val input = Source.fromFile("./input/input7.txt").getLines().toArray

    val bags = input
        .map( _ match {
            case s"${outside} bags contain ${inside}." => (
                outside,
                inside match {
                    case "no other bags" => Map[String,Int]()
                    case _ => inside
                        .split(", ")
                        .map{ case s"${number} ${name} bag$_" => (name, number.toInt) }
                        .toMap
                }
            )
        })
        .toMap

    def carry(color: String): Set[String] = bags
        .filter(_._2.contains(color))
        .keySet
        .flatMap(carry)
        .incl(color)

    println(carry("shiny gold").size - 1)

    def count(color: String): Int = bags(color)
        .map{ case (outside, inside) => count(outside)*inside }
        .sum + 1

    println(count("shiny gold") - 1)
}
