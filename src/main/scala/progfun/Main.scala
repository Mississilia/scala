package progfun

object Main extends App {
  println("Ici le programme principal")

  val limits = Runner.getLimits()
  val mowersWI: List[MowerInstructions] =
    Runner.getMowersInit(List[MowerInstructions](), limits)

  if (mowersWI.isEmpty)
    println("Bye !")
  else
    FileJsonWriter.writeFile(
      "result.json",
      JsonWriter.allToJson(
        limits,
        mowersWI.map(_.toMower(limits))
      )
    )

  def test() = {
    val mower = Mower(
      Position(5, 5),
      Position(4, 6),
      Nord,
      Sud,
      List(Gauche, Avance, Droite, Avance)
    )
    val mower2 = Mower(
      Position(1, 2),
      Position(3, 4),
      Est,
      Ouest,
      List(Avance, Avance, Droite, Droite, Avance)
    )
    val limits = Limits(5, 5)
    val json = JsonWriter.allToJson(limits, List(mower, mower2))
    FileJsonWriter.writeFile("result.json", json)
  }
}
