package progfun

object JsonWriter {

  def MowersToJson(value: List[Mower]) =
    s"""[${value
      .map(v => MowerToJson(v))
      .mkString(",")}${indentation(1)}]"""

  def MowerToJson(Mower: Mower) =
    s"""${indentation(2)}{${indentation(3)}\"Debut\": {${indentation(4)}\"point\": ${positionToJson(
      Mower.positionInit
    )},${indentation(4)}\"direction\": ${directionToJson(
      Mower.directionInit
    )}${indentation(3)}},${indentation(4)}\"instructions\": ${instructionsToJson(
      Mower.instructions
    )},${indentation(3)}\"fin\": {${indentation(5)}\"point\": ${positionToJson(
      Mower.position
    )},${indentation(4)}\"direction\": ${directionToJson(Mower.direction)}${indentation(
      3
    )}}${indentation(2)}}"""

  def positionToJson(position: Position) =
    s"""{${indentation(6)}\"x\": ${position.x.toString},${indentation(6)}\"y\": ${position.y.toString}${indentation(
      5
    )}}"""

  def LimitsToJson(Limits: Limits) =
    s"""{${indentation(2)}\"x\": ${Limits.x.toString},${indentation(2)}\"y\": ${Limits.y.toString}${indentation(
      1
    )}}"""

  def directionToJson(direction: Direction) = direction match {
    case Nord           => "\"N\""
    case Sud            => "\"S\""
    case Est            => "\"E\""
    case Ouest          => "\"O\""
    case WrongDirection => "Don't care"
  }

  def instructionsToJson(value: List[Instruction]) =
    s"""[${value.map(v => instructionToJson(v)).mkString(",")}]"""

  def instructionToJson(instruction: Instruction) = instruction match {
    case Gauche => "\"G\""
    case Droite => "\"D\""
    case Avance => "\"A\""
  }

  def indentation(size: Int): String = {
    "\n" + "  " * size
  }

  def allToJson(Limits: Limits, Mowers: List[Mower]) =
    s"""{${indentation(1)}\"limite\": ${LimitsToJson(Limits)},${indentation(1)}\"Mowers\":${MowersToJson(
      Mowers
    )}${indentation(0)}}"""
}
