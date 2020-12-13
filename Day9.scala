import scala.io.Source
import scala.math

object Day9 {
    val input = Source.fromFile("./input/input9.txt").getLines().toArray.map(_.toLong)
    val deque = scala.collection.mutable.ArrayDeque().addAll(
        for (i <- 0 until 24) yield
            for(j <- i until 25; if i != j) yield
                input(i) + input(j)
    )
    var invalid = 25

    while(deque.exists(_.contains(input(invalid)))) {
        deque.removeHead()
        for(i <- 0 until 23) {
            deque(i) = deque(i) :+ (input(invalid-24+i) + input(invalid))
        }
        deque.append(Vector(input(invalid-1) + input(invalid)))
        invalid += 1
    }

    println(input(invalid))

    def weakness(): Long = {
        var j = 1
        var sum = input(0)
        for(i <- 0 until input.length - 1) {
            while(j < input.length && sum < invalid) {
                sum += input(j)
                j += 1
            }
            if(sum == invalid) {
                var lower = input(i)
                var upper = input(i)
                for(k <- i to j) {
                    lower = math.min(lower, input(k))
                    upper = math.max(upper, input(k))
                }
                return lower + upper
            }
            sum -= input(i)
        }
        return 0L
    }

    println(weakness())
}
