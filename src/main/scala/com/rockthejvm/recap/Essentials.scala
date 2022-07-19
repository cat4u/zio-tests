package com.rockthejvm.recap

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object Essentials {
  val aFunction: Int = 1 + 2 + 3

  // Futures
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(8))
  val aFuture: Future[Int] = Future {
    42
  }

  val rez = aFuture.onComplete {
    case Success(value) => println(s"The result is: ${value}")
    case Failure(exception) => println(s"The computation failed: ${exception}")
  }
  // for-comprehensions
  val checkerboard = List(1, 2, 3).flatMap(n => List('a', 'b', 'c').map(c => (c, n)))

  val anotherCheckerboard = for {
    n <- List(1, 2, 3)
    c <- List('a', 'b', 'c')
  } yield (c, n)

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 3 => 999
  }

  // HKT
  trait HigherKindedType[F[_]]

  trait SequenceChecker[F[_]] {
    def isSequential: Boolean
  }

  val listChecker = new SequenceChecker[List] {
    override def isSequential: Boolean = true
  }


  def main(args: Array[String]): Unit = {
    println("Recap")

    // wait for completion (async)
    /*aFuture.onComplete {
      case Success(value) => println(s"The result is: ${value}")
      case Failure(exception) => println(s"The computation failed: ${exception}")
    }*/

    //println(checkerboard)
    //println(anotherCheckerboard)

    println(listChecker.isSequential)
  }

}
