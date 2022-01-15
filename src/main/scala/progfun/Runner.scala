package progfun

object Runner {
  def getLimits(): Limits = {
    println(
      "Entrez les Limits du jardin sous la forme \"X Y\" ou X et Y sont des nombres entiers plus grands que 0 :"
    )
    val ipt: String = scala.io.StdIn.readLine()
    stringsToLimits(ipt)
  }

  def stringsToLimits(input: String): Limits = {
    val tab: Array[String] = input.trim().split(" ")
    if (tab.length != 2) {
      IllegalNumberOfArgumentsException("Nombre de parametres incorrect !")
        .print()
      getLimits()
    } else {
      try {
        val x = tab.map(_.toInt)
        Limits(x(0), x(1))
      } catch {
        case e: Exception =>
          e.printStackTrace()
          IllegalFormatException("Format des nombres incorrect !").print()
          getLimits()
      }
    }
  }

  def getMowersInit(
                        Mowers: List[MowerInstructions],
                        Limits: Limits
                      ): List[MowerInstructions] = {
    println(
      "Entrez la position initiale de la prochaine Mower (comprise dans les Limits) ainsi que sa direction (N,S,E,O)"
    )
    println(
      "ou entrez \"result\" pour avoir le resultat final, \"exit\" pour quitter"
    )
    val input = scala.io.StdIn.readLine()
    input.trim().toUpperCase() match {
      case "EXIT"   => List()
      case "RESULT" => Mowers
      case _        => stringToMowerInit(Limits, input, Mowers)
    }
  }

  def stringToMowerInit(
                            Limits: Limits,
                            input: String,
                            Mowers: List[MowerInstructions]
                          ): List[MowerInstructions] = {
    val tab: Array[String] = input.trim().toUpperCase().split(" ")
    if (tab.length != 3) {
      IllegalNumberOfArgumentsException("Nombre de parametres incorrect !")
        .print()
      getMowersInit(Mowers, Limits)
    } else {
      toDirection(tab(2)) match {
        case d: Direction if d != WrongDirection =>
          toPosition(d, tab(0), tab(1), Limits, Mowers)
        case e: IncorrectDataException =>
          e.print()
          getMowersInit(Mowers, Limits)
      }
    }
  }

  def toDirection(input: String): Direction = {
    input match {
      case "N" => Nord
      case "S" => Sud
      case "O" => Ouest
      case "E" => Est
      case _   => WrongDirection
    }
  }

  def toPosition(
                  direction: Direction,
                  x: String,
                  y: String,
                  Limits: Limits,
                  Mowers: List[MowerInstructions]
                ): List[MowerInstructions] = {
    try {
      val coordonates = Array(x, y).map(_.toInt)
      val position = Position(coordonates(0), coordonates(1))
      if (position.x >= 0 && position.x < Limits.x && position.y >= 0 && position.y < Limits.y) {
        getMowersInit(
          Mowers.appended(
            getInstructions(MowerInit(position, direction))
          ),
          Limits
        )
      } else {
        OutOfLimitsException(
          "Coordonnees de depart de la Mower hors limite !"
        ).print()
        getMowersInit(Mowers, Limits)
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
        IllegalFormatException("Format de la position incorrect !").print()
        getMowersInit(Mowers, Limits)
    }
  }

  def getInstructions(Mower: MowerInit): MowerInstructions = {
    println("Entrer maintenant les instructions sous la forme IIIIIIIII :")
    println("Rappel, G: pivote a gauche, D: pivote a droite, A: avance")
    val input = scala.io.StdIn.readLine()
    checkInstructionsInput(input.trim().toUpperCase().toCharArray, Mower)
  }

  def checkInstructionsInput(
                              chars: Array[Char],
                              Mower: MowerInit
                            ): MowerInstructions = {
    val validChars: List[Char] = List('A', 'G', 'D')
    val instructions
    : Array[Char] = for { c <- chars if validChars.contains(c) } yield c
    areSizesEqual(instructions.length, chars.length) match {
      case Gauche =>
        IllegalDirectionException("Des directions ne sont pas valides").print()
        getInstructions(Mower)
      case Droite =>
        IllegalDirectionException("Une directions n'est pas valide").print()
        getInstructions(Mower)
      case Avance =>
        MowerInstructions(
          Mower.positionInit,
          Mower.directionInit,
          toInstructions(instructions)
        )
    }
  }

  def areSizesEqual(s1: Int, s2: Int): Instruction = {
    if (s1 > s2)
      Gauche
    else if (s1 < s2 && s1 > s2 - 2)
      Droite
    else
      Avance
  }

  def toInstructions(char: Array[Char]): List[Instruction] = {
    val returnValue: List[Instruction] = List[Instruction]().appendedAll(
      for (c <- char) yield c match {
        case 'A' => Avance
        case 'G' => Gauche
        case 'D' => Droite
      }
    )
    returnValue
  }
}

