type Column = Seq[Option[Int]]
type Wheel = Seq[Column]

val NumWheels = 5
val NumVisibleRings = 4
val NumColumns = 12
val SolutionColumnSum = 42

val Rotations = 
  for (
    topRotation <- (0 until NumColumns);
    secondRotation <- (0 until NumColumns);
    thirdRotation <- (0 until NumColumns);
    fourthRotation <- (0 until NumColumns)
  ) yield Seq(topRotation, secondRotation, thirdRotation, fourthRotation, 0)

@main def hello(): Unit =
  println(s"Exploring ${Rotations.size} potential solutions")
  Rotations.map(applyRotation(_, Wheels)).find(isSolution).foreach(printWheelState)

def applyRotation(rotations: Seq[Int], wheels: Seq[Wheel]) =
  rotations.zip(wheels).map(p => p._2.drop(p._1).take(NumColumns))

def isSolution(wheelState: Seq[Wheel]) =
  val evaluation = (0 until NumColumns)
    .map(colIndex => (0 until NumWheels).map(wheelIndex => wheelState.drop(wheelIndex).head.drop(colIndex).head))
    .map(resolveColumns)
    .map(_.sum)

  evaluation
    .find(_ != SolutionColumnSum)
    .isEmpty

def printWheelState(wheels: Seq[Wheel]) =
  wheels.foreach(x =>
    println("*****************")
    println(x)
  )

def printColumnState(column: Column) =
  println("*****************")
  println(column)

// Seq of "columns"
def resolveColumns(columns: Seq[Column]): Seq[Int] =
  // println("Resolving Columns")
  // columns.foreach(printColumnState)
  val correlatedColumns = (0 until NumVisibleRings).map(index => columns.map(column => column.drop(index).head))
  // println(correlatedColumns)
  val resolved = correlatedColumns.map(_.find(_.isDefined).flatten.get)
  // println(resolved)
  resolved

val TopWheel: Wheel = Seq(    Seq(None    , None    , None    , Some( 3)),
                              Seq(None    , None    , None    , None    ),
                              Seq(None    , None    , None    , Some( 6)),
                              Seq(None    , None    , None    , None    ),
                              Seq(None    , None    , None    , Some(10)),
                              Seq(None    , None    , None    , None    ),
                              Seq(None    , None    , None    , Some( 7)),
                              Seq(None    , None    , None    , None    ),
                              Seq(None    , None    , None    , Some(15)),
                              Seq(None    , None    , None    , None    ),
                              Seq(None    , None    , None    , Some( 8)),
                              Seq(None    , None    , None    , None    ))

val SecondWheel: Wheel = Seq( Seq(None    , None    , Some( 7), None    ),
                              Seq(None    , None    , Some(15), Some( 6)),
                              Seq(None    , None    , None    , None    ),
                              Seq(None    , None    , None    , Some(11)),
                              Seq(None    , None    , Some(14), Some(11)),
                              Seq(None    , None    , None    , Some( 6)),
                              Seq(None    , None    , Some( 9), Some(11)),
                              Seq(None    , None    , None    , None    ),
                              Seq(None    , None    , Some(12), Some( 6)),
                              Seq(None    , None    , None    , Some(17)),
                              Seq(None    , None    , Some( 4), Some( 7)),
                              Seq(None    , None    , None    , Some( 3)))

val MiddleWheel: Wheel = Seq( Seq(None    , Some( 5), Some(21), Some( 9)),
                              Seq(None    , None    , Some( 6), Some(13)),
                              Seq(None    , Some(10), Some(15), Some( 9)),
                              Seq(None    , None    , Some( 4), Some( 7)),
                              Seq(None    , Some( 8), Some( 9), Some(13)),
                              Seq(None    , None    , Some(18), Some(21)),
                              Seq(None    , Some(22), Some(11), Some(17)),
                              Seq(None    , None    , Some(26), Some( 4)),
                              Seq(None    , Some(16), Some(14), Some( 5)),
                              Seq(None    , None    , Some( 1), None    ),
                              Seq(None    , Some( 9), Some(12), Some( 7)),
                              Seq(None    , None    , None    , Some( 8)))

val FourthWheel: Wheel = Seq( Seq(Some( 6), Some( 9), Some(14), Some(14)),
                              Seq(None    , None    , Some(12), None    ),
                              Seq(Some(10), Some(17), Some( 3), Some(11)),
                              Seq(None    , Some(19), Some( 8), Some(11)),
                              Seq(Some(10), Some( 3), Some( 9), Some(14)),
                              Seq(None    , Some(12), None    , Some(11)),
                              Seq(Some( 1), Some( 3), Some( 9), Some(14)),
                              Seq(None    , Some(26), Some(20), Some(11)),
                              Seq(Some( 9), Some( 6), Some(12), Some(14)),
                              Seq(None    , None    , Some( 3), Some(14)),
                              Seq(Some(12), Some( 2), Some( 6), Some(11)),
                              Seq(None    , Some(13), None    , Some(14)))

val BottomWheel: Wheel = Seq( Seq(Some( 8), Some( 9), Some(15), Some(14)),
                              Seq(Some( 8), Some( 4), Some( 4), Some(11)),
                              Seq(Some( 3), Some( 4), Some( 5), Some(11)),
                              Seq(Some( 4), Some( 6), Some( 6), Some(14)),
                              Seq(Some(12), Some( 6), Some( 7), Some(11)),
                              Seq(Some( 2), Some( 3), Some( 8), Some(14)),
                              Seq(Some( 5), Some( 3), Some( 9), Some(11)),
                              Seq(Some(10), Some(14), Some(10), Some(14)),
                              Seq(Some( 7), Some(14), Some(11), Some(14)),
                              Seq(Some(16), Some(21), Some(12), Some(11)),
                              Seq(Some( 8), Some(21), Some(13), Some(14)),
                              Seq(Some( 7), Some( 9), Some(14), Some(11)))

// Represent each wheel as two copies of itself, so we can represent rotation with wheel.drop(rotation).take(NumColumns)
val Wheels: Seq[Wheel] = Seq(TopWheel, SecondWheel, MiddleWheel, FourthWheel, BottomWheel).map(l => l ++ l)
