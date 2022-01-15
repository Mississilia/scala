trait IncorrectDataException {
  def print(): Unit
}

case class IllegalNumberOfArgumentsException(message: String)
  extends IncorrectDataException {
  override def print() = {
    println("ERROR: " + message)
  }
}
case class IllegalFormatException(message: String)
  extends IncorrectDataException {
  override def print(): Unit = {
    println("ERROR: " + message)
  }
}

case class OutOfLimitsException(message: String)
  extends IncorrectDataException {
  override def print(): Unit = {
    println("ERROR: " + message)
  }
}

case class IllegalDirectionException(message: String)
  extends IncorrectDataException {
  override def print(): Unit = {
    println("ERROR: " + message)
  }
}
