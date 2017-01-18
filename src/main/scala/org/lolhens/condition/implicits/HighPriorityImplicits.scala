package org.lolhens.condition.implicits

import org.lolhens.condition.{BooleanConditions, EitherConditions, OptionConditions}

import scala.language.implicitConversions

/**
  * Created by u016595 on 18.01.2017.
  */
trait HighPriorityImplicits {
  implicit def fromEither[A, B](either: Either[A, B]): EitherConditions[A, B] = new EitherConditions[A, B](either)

  implicit def fromOption[T](option: Option[T]): OptionConditions[T] = new OptionConditions[T](option)

  implicit def fromBoolean(boolean: Boolean): BooleanConditions = new BooleanConditions(boolean)
}
