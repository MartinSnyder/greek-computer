

@main def hello(): Unit =
  println("Hello world!")
  println(msg)

def msg = "I was compiled by Scala 3. :)"

val BottomWheel = List( List(Some( 8), Some( 9), Some(15), Some(14)),
                        List(Some( 8), Some( 4), Some( 4), Some(11)),
                        List(Some( 3), Some( 4), Some( 5), Some(11)),
                        List(Some( 4), Some( 6), Some( 6), Some(14)),
                        List(Some(12), Some( 6), Some( 7), Some(11)),
                        List(Some( 2), Some( 3), Some( 8), Some(14)),
                        List(Some( 5), Some( 3), Some( 9), Some(11)),
                        List(Some(10), Some(14), Some(10), Some(14)),
                        List(Some( 7), Some(14), Some(11), Some(14)),
                        List(Some(16), Some(21), Some(12), Some(11)),
                        List(Some( 8), Some(21), Some(13), Some(14)),
                        List(Some( 7), Some( 9), Some(14), Some(11)))

val ThirdWheel = List(  List(None    , Some( 5), Some(21), Some( 9)),
                        List(None    , None    , Some( 6), Some(13)),
                        List(None    , Some(10), Some(15), Some( 9)),
                        List(None    , None    , Some( 4), Some( 7)),
                        List(None    , Some( 8), Some( 9), Some(13)),
                        List(None    , None    , Some(18), Some(21)),
                        List(None    , Some(22), Some(11), Some(17)),
                        List(None    , None    , Some(26), Some( 4)),
                        List(None    , Some(16), Some(14), Some( 5)),
                        List(None    , None    , Some( 1), None    ),
                        List(None    , Some( 9), Some(12), Some( 7)),
                        List(None    , None    , None    , Some( 8)))

val SecondWheel = List( List(None    , None    , Some( 7), None    ),
                        List(None    , None    , Some(15), Some( 6)),
                        List(None    , None    , None    , None    ),
                        List(None    , None    , None    , Some(11)),
                        List(None    , None    , Some(14), Some(11)),
                        List(None    , None    , None    , Some( 6)),
                        List(None    , None    , Some( 9), Some(11)),
                        List(None    , None    , None    , None    ),
                        List(None    , None    , Some(12), Some( 6)),
                        List(None    , None    , None    , Some(17)),
                        List(None    , None    , Some( 4), Some( 7)),
                        List(None    , None    , None    , Some( 3)))

val TopWheel = List(    List(None    , None    , None    , Some( 3)),
                        List(None    , None    , None    , None    ),
                        List(None    , None    , None    , Some( 6)),
                        List(None    , None    , None    , None    ),
                        List(None    , None    , None    , Some(10)),
                        List(None    , None    , None    , None    ),
                        List(None    , None    , None    , Some( 7)),
                        List(None    , None    , None    , None    ),
                        List(None    , None    , None    , Some(15)),
                        List(None    , None    , None    , None    ),
                        List(None    , None    , None    , Some( 8)),
                        List(None    , None    , None    , None    ))
