package progfun

object Main extends App {
  println("Ici le programme principal")

  val Limits = Runner.getLimits()
  val MowersWI: List[MowerInstructions] =
    Runner.getMowersInit(List[MowerInstructions](), Limits)

  if (MowersWI.isEmpty)
    println("Bye !")
  else
    FileJsonWriter.writeFile(
      "result.json",
      JsonWriter.allToJson(
        Limits,
        MowersWI.map(_.toMower(Limits))
      )
    )

  def test() = {
    val Mower = Mower(
      Position(5, 5),
      Position(4, 6),
      Nord,
      Sud,
      List(Gauche, Avance, Droite, Avance)
    )
    val Mower2 = Mower(
      Position(1, 2),
      Position(3, 4),
      Est,
      Ouest,
      List(Avance, Avance, Droite, Droite, Avance)
    )
    val Limits = Limits(5, 5)
    val json = JsonWriter.allToJson(Limits, List(Mower, Mower2))
    FileJsonWriter.writeFile("result.json", json)
  }
}
