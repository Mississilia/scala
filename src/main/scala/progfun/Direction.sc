object Direction {}
sealed trait Direction {}

case object Nord extends Direction {}
case object Sud extends Direction {}
case object Est extends Direction {}
case object Ouest extends Direction {}
case object WrongDirection extends Direction {}
