package org.lolhens.ifoption

import scala.language.implicitConversions

/**
  * Created by u016595 on 17.01.2017.
  */
class AnyConditions[T](val self: T) extends AnyVal {
  def If(condition: => Boolean): Either[T, T] = If_((_: T) => condition)

  def If_(condition: T => Boolean): Either[T, T] =
    if (condition(self))
      Right(self)
    else
      Left(self)

  def IfNot(condition: => Boolean): Either[T, T] = IfNot_((_: T) => condition)

  def IfNot_(condition: T => Boolean): Either[T, T] =
    if (condition(self))
      Left(self)
    else
      Right(self)
}

object AnyConditions {
  implicit def any2AnyConditions[T](any: T): AnyConditions[T] = new AnyConditions[T](any)
}
