import scala.io.Source

object Day16 {
    val input = Source.fromFile("./input/input16.txt").getLines().toArray

    def split[T](array: Array[T], predicate: T => Boolean): Iterator[Array[T]] =
        (-1 +: array.indices.filter(i => predicate(array(i))) :+ array.length)
            .sliding(2)
            .map{ case Vector(l, r) => array.slice(l+1, r) }
            .filterNot(_.isEmpty)

    var (ranges, your, nearby) = split(input, "^()|(your ticket:)|(nearby tickets:)$".r.matches).toList match {
        case List(rules, numbers, other) => (
            rules.map{
                case s"${_}: ${a}-${b} or ${c}-${d}" => x: Int =>
                    (a.toInt to b.toInt contains x) || (c.toInt to d.toInt contains x)
            },
            numbers.head.split(",").map(_.toInt),
            other.map(_.split(",").map(_.toInt))
        )
    }

    val (valid, invalid) = nearby.partition( n => n.forall( i => ranges.exists( r => r(i) ) ) )

    println( invalid.map( n => n.filterNot( i => ranges.exists( r => r(i)) ).sum ).sum )

    def unique: (Set[Int], List[IndexedSeq[Int]]) => Option[List[Int]] = {
        case (s, list) if s.isEmpty != list.isEmpty => None
        case (s, Nil) => Some(Nil)
        case (s, head :: tail) => s
            .intersect(head.toSet)
            .collectFirst(
                Function.unlift(i => unique(s.excl(i), tail).map(i :: _ ))
            )
    }

    val order = unique(
        ranges.indices.toSet,
        ranges.map( r =>
            ranges.indices.filter( i =>
                valid.forall( n =>
                    r(n(i))
                )
            )
        ).toList
    )
    .foreach( f => println( f
        .take(6)
        .map(your)
        .map(BigInt(_))
        .product
    ) )
}
