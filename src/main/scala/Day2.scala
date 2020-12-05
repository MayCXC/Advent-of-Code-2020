import scala.io.Source

object Day2 {
    val input = Source.fromFile("./input/input2.txt").getLines.toArray

    println( input
        .count( _.split("(: )|[- ]") match {
            case Array(min,max,letter,password) =>
                min.toInt to max.toInt contains password.count(letter.head.equals) 
        })
    )

    println( input
        .count( _.split("(: )|[- ]") match {
            case Array(min,max,letter,password) =>
                password(min.toInt-1).equals(letter.head) != password(max.toInt-1).equals(letter.head) 
        })
    )
}
