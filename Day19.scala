import scala.io.Source

object Day19 {
    val input = Source.fromFile("./input/input19.txt").getLines().toArray

    val List(rules, messages) = Main.split(input, "".equals).toList
    val memo = rules
        .map{ case s"${key}: ${value}" => (key.toInt -> value.filterNot('"'.equals).split(" ")) }
        .toMap

    def evaluate: Int => String = i => memo(i)
        .mapInPlace( m => m
            .toIntOption
            .map(evaluate)
            .map("("+_+")")
            .getOrElse(m)
        )
        .mkString("")

    println(messages.count(evaluate(0).r.matches))

    def otherzero(outer: String): Boolean = {
        val fortytwo = evaluate(42).r
        val thirtyone = evaluate(31).r

        def palindrome(inner: String): Boolean = inner.length <= 1 ||
            (inner.indices.drop(1).map(inner.splitAt).unzip match {
                case (p, s) =>
                    for(
                        prefix <- p.filter(fortytwo.matches);
                        suffix <- s.filter(thirtyone.matches)
                    )
                    yield (prefix, suffix)
            }).exists {
                case (p,s) => p.length + s.length <= inner.length && 
                    palindrome(inner.drop(p.length).dropRight(s.length))
            }

        def repetition(inner: String): Boolean = inner.isEmpty ||
            inner.indices.map(inner.length-_).map(inner.splitAt)
                .exists{ case (p, s) => fortytwo.matches(p) && repetition(s) }

        return outer.indices.drop(1).map(outer.splitAt)
            .exists{ case (p, s) => repetition(p) && palindrome(s) }
    }

    println(messages.count(otherzero))
}
