package com.rockthejvm.zio

import zio.*

import scala.io.StdIn

object ZioEffects {

  // success
  val meaningOfLife: ZIO[Any, Nothing, Int] = ZIO.succeed(42)
  // failure
  val aFailure: ZIO[Any, String, Nothing] = ZIO.fail("Something went wrong")
  // suspension/delay
  val aSuspendedZIO: ZIO[Any, Throwable, Int] = ZIO.suspend(meaningOfLife)

  //map + flatMap
  val improvedMOL = meaningOfLife.map(_ * 2)
  val printingMOL = meaningOfLife.flatMap(mol => ZIO.succeed(println(mol)))

  // for comprehen
  val smallProgram = for {
    _ <- ZIO.succeed(println("what's your name"))
    name <- ZIO.succeed(StdIn.readLine())
    _ <- ZIO.succeed(println(s"Welcome to ZIO, $name"))
  } yield ()

  // A LOT of combinators
  // zip, zipWith
  val anotherMOL = ZIO.succeed(100)
  val tupledZIO = meaningOfLife.zip(anotherMOL)
  val combinedZIO = meaningOfLife.zipWith(anotherMOL)(_ * _)

  /**
   *
   * Type aliases of ZIOs
   */
  // UIO[A] = ZIO[Any, Nothing, A] - no requirements, cannot fail, produces A
  val aUIO: UIO[Int] = ZIO.succeed(99)

  // Task[A] = ZIO[Any, Throwable, A] - no requirements, can fail with a Throwable, produces A


  def main(args: Array[String]): Unit = {

  }
}
