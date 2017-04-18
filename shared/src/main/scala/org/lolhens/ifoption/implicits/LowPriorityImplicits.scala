package org.lolhens.ifoption.implicits

import org.lolhens.ifoption.AnyConditions

import scala.language.implicitConversions

/**
  * Created by u016595 on 17.01.2017.
  */
trait LowPriorityImplicits {
  implicit def any2AnyConditions[T](any: T): AnyConditions[T] = new AnyConditions[T](any)
}
