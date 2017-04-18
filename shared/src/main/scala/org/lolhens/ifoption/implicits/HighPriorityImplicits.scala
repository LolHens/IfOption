package org.lolhens.ifoption.implicits

import org.lolhens.ifoption.{BooleanConditions, EitherConditions, OptionConditions}

import scala.language.implicitConversions

/**
  * Created by u016595 on 18.01.2017.
  */
trait HighPriorityImplicits {
  implicit def either2EitherConditions[A, B](either: Either[A, B]): EitherConditions[A, B] = new EitherConditions[A, B](either)

  implicit def option2OptionConditions[T](option: Option[T]): OptionConditions[T] = new OptionConditions[T](option)

  implicit def boolean2BooleanConditions(boolean: Boolean): BooleanConditions = new BooleanConditions(boolean)
}
