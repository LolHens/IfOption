package org.lolhens.ifoption

import scala.language.implicitConversions

/**
  * Created by u016595 on 17.01.2017.
  */
class BooleanConditions(val self: Boolean) extends AnyVal {
  def Then[E](value: => E): Option[E] = if (self) Some(value) else None

  def Then_[E](value: () => E): Option[E] = Then[E](value())

  def ThenIf[E](value: => Option[E]): Option[E] = if (self) value else None

  def ThenIf_[E](value: () => Option[E]): Option[E] = ThenIf[E](value())

  def Else[E](value: => E): Option[E] = if (self) None else Some(value)

  def Else_[E](value: () => E): Option[E] = Else[E](value())

  def ElseIf[E](value: => Option[E]): Option[E] = if (self) None else value

  def ElseIf_[E](value: () => Option[E]): Option[E] = ElseIf[E](value())
}

object BooleanConditions {
  implicit def boolean2BooleanConditions(boolean: Boolean): BooleanConditions = new BooleanConditions(boolean)
}
