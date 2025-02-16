type Column = Seq[Option[Int]]
type Wheel = Seq[Column]

val NumWheels = 5
val NumVisibleRings = 4
val NumColumns = 12
val SolutionColumnSum = 42

// Build the cartesian product of all possible wheel rotations
// Note that we can consider any of the wheels fixed and just rotate the other wheels
// A wheel can be rotated anywhere from 0 steps to 1 less than the total number of wheel positions
val Rotations = for (
    topRotation <- (0 until NumColumns);
    secondRotation <- (0 until NumColumns);
    thirdRotation <- (0 until NumColumns);
    fourthRotation <- (0 until NumColumns)
  ) yield Seq(topRotation, secondRotation, thirdRotation, fourthRotation, 0)

// Represent each wheel as two copies of itself, so we can represent rotation with wheel.drop(rotation).take(NumColumns)
val Wheels: Seq[Wheel] = Seq(TopWheel, SecondWheel, MiddleWheel, FourthWheel, BottomWheel).map(l => l ++ l)

// Taking a set of wheel rotations and a wheel state, rotate each of the wheels as indicated
// by the correlated rotation value.
//
// We expect these two sequences to be exactly the same length, and the Nth rotation is applied
// to the Nth wheel state.
//
// Note that this routine assumes that the wheel state contains double entries for each wheel
// so that 12 sequential values are guaranteed to remain after dropping initial values to indicate the rotation
// 
// Returns the updated wheel state
def applyRotation(rotations: Seq[Int], wheelState: Seq[Wheel]): Seq[Wheel] =
  rotations
    .zip(wheelState)
    .map(p => p._2.drop(p._1).take(NumColumns))

// Given a wheel state, extract the correlated columns based on where the wheels line up with each other
// Expect to have 12 entries in the top-level returned structure (one for each column) and 5 entries for each of
// the second level structures (one for each wheel)
def retrieveCorrelatedColumns(wheelState: Seq[Wheel]): Seq[Seq[Column]] =
  (0 until NumColumns)
    .map(colIndex => (0 until NumWheels).map(wheelIndex => wheelState.drop(wheelIndex).head.drop(colIndex).head))

// Given a set of "columns" that correspond to a set of stacked wheels, evaluate the effective numbers
// when looking "down" at the puzzle. Each column on a wheel can represent gaps in the wheel with a "None"
// and this method stacks the correlated values in a list (with the topmost wheel first, then descending wheels
// in order), then finds the first visible value in that stack for each row.
def resolveColumns(columns: Seq[Column]): Seq[Int] =
  (0 until NumVisibleRings)
    .map(index => columns.map(column => column.drop(index).head))
    .map(_.find(_.isDefined).flatten.get)

// Given a wheel state, determine if this result is a valid solution. This is done by:
// 1. Grouping the overlapping wheel columns together
// 2. Resolving those columns to determine what numbers are actually visible when looking down upon the puzzle
// 3. Adding up the number in each column
// 4. Looking to see if there are any columns that do NOT add up to the desired solution value
//
// If there are no such columns in step 4, then it is a valid solution
def isSolution(wheelState: Seq[Wheel]): Boolean =
    retrieveCorrelatedColumns(wheelState)
      .map(resolveColumns)
      .map(_.sum)
      .find(_ != SolutionColumnSum)
      .isEmpty

// Displays a wheel state, as seen from the top looking down on the puzzle
// Each row is a single column, from the outside to the inside
// 
// In this form of the display, it's not clear which wheel is contributing each
// number, so you may have to play around with it a little bit to get things
// aligned correctly.
//
// You can always "println" the whole wheel state, and you will see exactly what
// is what, but I found it easier to reproduce the state in the physical puzzle
// With this format.
def display(wheelState: Seq[Wheel]): Unit =
  retrieveCorrelatedColumns(wheelState)
    .map(resolveColumns)
    .foreach(col =>
      col.foreach(num =>
        if (num < 10) print(" ")
        print(num)
        print(" ")
      )
      println
    )
    
// Solve the puzzle by:
// 1. Getting a sequence of all possible rotations
// 2. Applying those rotations to the base wheel state to determine all possible wheel states
// 3. Find the first solution out of the possible wheel states (there should be only one)
@main def hello(): Unit =
  Rotations
    .map(applyRotation(_, Wheels))
    .find(isSolution)
    .foreach(display)