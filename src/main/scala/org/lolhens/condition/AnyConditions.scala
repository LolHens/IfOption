package org.lolhens.condition

import cats.implicits._

import scala.language.implicitConversions

/**
  * Created by u016595 on 17.01.2017.
  */
class AnyConditions[T](val self: T) extends AnyVal {
  def If(condition: => Boolean): Either[T, T] = If_((_: T) => condition)
  def If_(condition: T => Boolean): Either[T, T] =
    if (condition(self))
      Either.right(self)
    else
      Either.left(self)

  def IfNot(condition: => Boolean): Either[T, T] = IfNot_((_: T) => condition)
  def IfNot_(condition: T => Boolean): Either[T, T] =
    if (condition(self))
      Either.left(self)
    else
      Either.right(self)
}

object AnyConditions {
  implicit def fromAny[T](any: T): AnyConditions[T] = new AnyConditions[T](any)
}
