import scala.io.Source
import scala.collection.View
import scala.math

object Day20 {
    val input = Source.fromFile("./input/input20.txt").getLines().toArray
    val tiles = Main
        .split(input, "".equals)
        .map(t => (t.head -> t.tail))
        .map{ case (s"Tile ${id}:" -> tail) => (id.toInt -> tail) }
        .toMap

    def dihedral(n: String, s: String, w: String, e: String):
        View[(String,String,String,String)] = for(
            (tn,ts,tw,te) <- List((n,s,w,e), (w,e,n,s)).view;
            (hn,hs,hw,he) <- List((tn,ts,tw,te), (ts,tn,tw.reverse,te.reverse)).view;
            (vn,vs,vw,ve) <- List((hn,hs,hw,he), (hn.reverse,hs.reverse,he,hw)).view
        )
            yield (vn,vs,vw,ve)

    val sides = tiles.view.mapValues(s => (s.head, s.last, s.map(_.head).mkString, s.map(_.last).mkString))

    def assemble: (List[(Int,Int)], Set[Int], Map[(Int,Int), (Int,(String,String,String,String))]) =>
        Map[(Int,Int), (Int,(String,String,String,String))] = {
            case (Nil, ids, image) => image
            case (square :: squares, ids, image) => {
                ( for(id <- ids.view; d4 <- (dihedral _).tupled(sides(id))) yield (id, d4) )
                    .filter{ case (id, (n,s,w,e)) =>
                        image.lift((square._1-1,square._2)).map(_._2._2).forall(n.equals) &&
                        image.lift((square._1+1,square._2)).map(_._2._1).forall(s.equals) &&
                        image.lift((square._1,square._2-1)).map(_._2._4).forall(w.equals) &&
                        image.lift((square._1,square._2+1)).map(_._2._3).forall(e.equals)
                    }
                    .map{
                        case (id, d4) => assemble(
                            squares, ids.excl(id), image.updated(square, (id,d4))
                        )
                    }
                    .find(_.size >= image.size + ids.size)
                    .getOrElse(image)
            }
        }
    
    val partone = assemble(
            (for(
                x <- 0 until 12;
                y <- 0 until 12
            ) yield (x,y)).toList,
            sides.keySet.toSet,
            Map()
    )

    println(
        List(
            partone((0,0)),
            partone((0,11)),
            partone((11,0)),
            partone((11,11))
        )
        .map(_._1)
        .map(BigInt(_))
        .product
    )

    println(
        List(
            partone((0,0)),
            partone((0,11)),
            partone((11,0)),
            partone((11,11))
        )
    )

    println(
        for(x <- 0 until 12*10) yield
            for(y <- 0 until 12*10) yield
                tiles( partone((x/12,y/12))._1 )(y%10)(x%10)
    )
}
