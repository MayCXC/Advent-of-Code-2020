object Main extends App {
    def split[T](array: Array[T], predicate: T => Boolean): Iterator[Array[T]] =
        (-1 +: array.indices.filter(i => predicate(array(i))) :+ array.length)
            .sliding(2)
            .map{ case Vector(l, r) => array.slice(l+1, r) }
            .filterNot(_.isEmpty)
/*
    Day1 // 870331, 283025088
    Day2 // 447, 249
    Day3 // 250, 1592662500
    Day4 // 260, 153
    Day5 // 922, 747
    Day6 // 6703, 3430
    Day7 // 337, 50100
    Day8 // 1859, 1235
    Day9 // 18272118, 2186361
    Day10 // 1625, 3100448333024
    Day11 // 2113, 1865
    Day12 // 923, 24769
    Day13 // 138, 226845233210288
    Day14 // 6513443633260, 3442819875191
    Day15 // 468, 1801753
    Day16 // 22073, 1346570764607
    Day17 // 291, 1524
    Day18 // 8298263963837, 145575710203332
    Day19 // 104, 314
*/
    Day20
}