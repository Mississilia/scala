package progfun

case class InitMower(
                      positionInit: Position,
                      directionInit: Direction
                    ) {}

case class MowerInstructions(
                              position: Position,
                              direction: Direction,
                              instructions: List[Instruction]
                            ) {

  override def toString: String = {
    "position " + position.x.toString + " " + position.y.toString + "\ninstructions " + instructions.length.toString
  }

  def toMower(Limits: Limits): Mower = {
    val finalMower = runInstructions(this, Limits)
    println(finalMower)
    Mower(
      position,
      finalMower.position,
      direction,
      finalMower.direction,
      instructions
    )
  }
  def runInstructions(
                       MowerWI: MowerInstructions,
                       Limits: Limits
                     ): MowerInstructions = {
    println(MowerWI.toString)
    MowerWI.instructions.headOption match {
      case Some(Avance) =>
        runInstructions(
          MowerInstructions(
            avance(Limits, MowerWI.position, MowerWI.direction),
            MowerWI.direction,
            MowerWI.instructions.drop(1)
          ),
          Limits
        )
      case Some(Gauche) =>
        runInstructions(
          MowerInstructions(
            MowerWI.position,
            pivoteGauche(MowerWI.direction),
            MowerWI.instructions.drop(1)
          ),
          Limits
        )
      case Some(Droite) =>
        runInstructions(
          MowerInstructions(
            MowerWI.position,
            pivoteDroite(MowerWI.direction),
            MowerWI.instructions.drop(1)
          ),
          Limits
        )
      case None => MowerWI
    }
  }

  def avance(
              Limits: Limits,
              currentPosition: Position,
              currentDirection: Direction
            ) = {
    currentDirection match {
      case Sud if (currentPosition.y - 1) >= 0 =>
        Position(currentPosition.x, currentPosition.y - 1)
      case Nord if currentPosition.y + 1 < Limits.y =>
        Position(currentPosition.x, currentPosition.y + 1)
      case Est if currentPosition.x + 1 < Limits.x =>
        Position(currentPosition.x + 1, currentPosition.y)
      case Ouest if currentPosition.x - 1 >= 0 =>
        Position(currentPosition.x - 1, currentPosition.y)
      case _ => currentPosition
    }

  }

  def pivoteGauche(currentDirection: Direction): Direction = {
    currentDirection match {
      case Nord           => Ouest
      case Sud            => Est
      case Est            => Nord
      case Ouest          => Sud
      case WrongDirection => WrongDirection
    }
  }

  def pivoteDroite(currentDirection: Direction): Direction = {
    currentDirection match {
      case Nord           => Est
      case Sud            => Ouest
      case Est            => Sud
      case Ouest          => Nord
      case WrongDirection => WrongDirection
    }
  }

}

