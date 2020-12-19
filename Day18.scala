import scala.io.Source

object Day18 {
    val input = Source.fromFile("./input/input18.txt").getLines().toArray

    def evalinfix(precedence: Map[Char,Int]): String => BigInt = {
        def shunting_yard: (List[Char], List[Char]) => List[Char] = {
            case (Nil, Nil)                   => Nil
            case (Nil, top :: stack)          => top :: shunting_yard(Nil, stack)
            case (' ' :: input, stack)        => shunting_yard(input, stack)
            case (in :: input, stack)         if '0' to '9' contains in
                                              => in :: shunting_yard(input, stack)
            case ('(' :: input, stack)        => shunting_yard(input, '(' :: stack)
            case (')' :: input, '(' :: stack) => shunting_yard(input, stack)
            case (')' :: input, top :: stack) => top :: shunting_yard(')' :: input, stack)
            case (in :: input, Nil)           => shunting_yard(input, in :: Nil)
            case (in :: input, '(' :: stack)  => shunting_yard(input, in :: '(' :: stack)
            case (in :: input, top :: stack)  if precedence(in) <= precedence(top)
                                              => top :: shunting_yard(in :: input, stack)
            case (in :: input, top :: stack)  if precedence(in) > precedence(top)
                                              => shunting_yard(input, in :: top :: stack)
        }

        def evaluate: (List[Char], List[BigInt]) => BigInt = {
            case (Nil, stack) => stack.head
            case ('+' :: input, right :: left :: stack) => evaluate(input, left + right :: stack)
            case ('*' :: input, right :: left :: stack) => evaluate(input, left * right :: stack)
            case (arg :: input, stack) => evaluate(input, arg.asDigit :: stack)
        }

        return expression => evaluate(shunting_yard(expression.toList, Nil), Nil)
    } 

    println(input.map(evalinfix(Map('+'->0,'*'->0))).sum)

    println(input.map(evalinfix(Map('+'->1,'*'->0))).sum)
}
