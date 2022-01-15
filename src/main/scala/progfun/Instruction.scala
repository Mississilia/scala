package progfun

object Instruction { }

sealed trait Instruction extends Product with Serializable {}

case object Gauche extends Instruction

case object Droite extends Instruction

case object Avance extends Instruction