import scala.io.Source

object Day10 {
    val input = Source.fromFile("./input/input10.txt").getLines().toArray

    val heap = scala.collection.mutable.PriorityQueue(input.map(_.toInt).toIndexedSeq: _*)
    heap.enqueue(0)
    heap.enqueue(heap.head+3)

    var ones = 0
    var threes = 0
    val deque = scala.collection.mutable.ArrayDeque().addOne(BigInt(1)).addAll(Seq.fill(3)(0))
    for(Seq(a, b, c, d) <- heap.dequeueAll.appendedAll(Seq.fill(4-2)(0)).sliding(4)) {
        if(a == b+1)
            ones += 1
        if(a == b+3)
            threes +=1

        val ways = deque.removeHead()
        deque.append(0)
        if(a <= b+3)
            deque(0) += ways
        if(a <= c+3)
            deque(1) += ways
        if(a <= d+3)
            deque(2) += ways
    }
    println(ones*threes)
    println(deque.head)
}
