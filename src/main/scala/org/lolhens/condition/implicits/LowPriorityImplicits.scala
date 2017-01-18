package org.lolhens.condition.implicits

import org.lolhens.condition.AnyConditions

import scala.language.implicitConversions

/**
  * Created by u016595 on 17.01.2017.
  */
trait LowPriorityImplicits {
  implicit def fromAny[T](any: T): AnyConditions[T] = new AnyConditions[T](any)
}
