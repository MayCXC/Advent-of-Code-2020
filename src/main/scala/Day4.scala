import scala.io.Source

object Day4 {
    val input = Source.fromFile("./input/input4.txt").getLines.toArray

    println( input
        .map(l => if (l.equals("")) ";" else l)
        .mkString
        .split(";")
        .count( data => Array("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")
            .forall(data.contains)
        )
    )

    println( input
        .map(l => if (l.equals("")) ";" else l)
        .mkString(" ")
        .split("; ")
        .map( _.split(" ")
            .map(_.split(":"))
            .map{case Array(x,y) => (x,y)}
            .toMap
        )
        .count( pass =>
            pass.lift("byr").exists(1920 to 2002 contains _.toInt) &&
            pass.lift("iyr").exists(2010 to 2020 contains _.toInt) &&
            pass.lift("eyr").exists(2020 to 2030 contains _.toInt) &&
            pass.lift("hgt").exists(h => (h.indexOf("cm"), h.indexOf("in")) match {
                case (metric,imperial) if imperial < 0 && 0 < metric => 150 to 193 contains h.substring(0,metric).toInt
                case (metric,imperial) if metric < 0 && 0 < imperial => 59 to 76 contains h.substring(0,imperial).toInt
                case _ => false
            }) &&
            pass.lift("hcl").exists(_.matches("^#[0-9a-f]{6}$")) &&
            pass.lift("ecl").exists(_.matches("^(amb|blu|brn|gry|grn|hzl|oth)$")) &&
            pass.lift("pid").exists(_.matches("^[0-9]{9}$"))
        )
    )
}
