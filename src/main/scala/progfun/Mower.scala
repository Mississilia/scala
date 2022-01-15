package progfun

case class Mower (
                  positionInit: Position,
                  position: Position,
                  directionInit: Direction,
                  direction: Direction,
                  instructions: List[Instruction]
                ) {
  override def toString: String = {
    "position" + position.x.toString + " " + position.y.toString
  }
}
