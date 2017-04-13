package org.lolhens.ifoption

import cats.implicits._

import scala.language.implicitConversions

/**
  * Created by u016595 on 17.01.2017.
  */
class OptionConditions[T](val self: Option[T]) extends AnyVal {

  def Then[E](function: => E): Option[E] = Then_[E]((_: T) => function)

  def Then_[E](function: T => E): Option[E] = self.map[E](function)

  def ThenIf[E](function: => Option[E]): Option[E] = ThenIf_[E]((_: T) => function)

  def ThenIf_[E](function: T => Option[E]): Option[E] = self.flatMap[E](function)

  def Else[E >: T](value: => E): E = self.getOrElse[E](value)

  def Else_[E >: T](value: () => E): E = Else[E](value())

  def ElseIf[E >: T](value: => Option[E]): Option[E] = self.orElse[E](value)

  def ElseIf_[E >: T](value: () => Option[E]): Option[E] = ElseIf[E](value())

  def ElseThen[A](value: => A): Either[A, T] = Either.fromOption(self, value)

  def ElseThen_[A](value: () => A): Either[A, T] = ElseThen[A](value())

  def If(condition: => Boolean): Option[T] = If_((_: T) => condition)

  def If_(condition: T => Boolean): Option[T] = self.filter(condition)

  def IfNot(condition: => Boolean): Option[T] = IfNot_((_: T) => condition)

  def IfNot_(condition: T => Boolean): Option[T] = self.filterNot(condition)
}

object OptionConditions {
  implicit def option2OptionConditions[T](option: Option[T]): OptionConditions[T] = new OptionConditions[T](option)
}
