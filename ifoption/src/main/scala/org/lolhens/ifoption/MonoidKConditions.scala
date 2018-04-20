package org.lolhens.ifoption

import cats.implicits._
import cats.{Foldable, MonadError, MonoidK}

import scala.language.higherKinds

class MonoidKConditions[F[_] : Foldable : MonoidK, E, A](val self: F[A])(implicit F0: MonadError[F, E]) {

  def Then[B](function: => B): F[B] = Then_(_ => function)

  def Then_[B](function: A => B): F[B] = self.map[B](function)

  def ThenIf[B](function: => F[B]): F[B] = ThenIf_(_ => function)

  def ThenIf_[B](function: A => F[B]): F[B] = self.flatMap[B](function)

  def Else[B >: A](value: => B): B = self.widen[B].foldl(value)((_, e) => e)

  //def Else_[B >: A](f: E => B): B = ???

  def ElseIf[B >: A](value: => F[B]): F[B] = ElseIf_(_ => value)

  def ElseIf_[B >: A](f: E => F[B]): F[B] = self.widen[B].handleErrorWith(f)

  def ElseThen(value: => E): F[A] = ElseThen_(_ => value)

  def ElseThen_(f: E => E): F[A] = self.handleErrorWith(f.andThen(implicitly[MonadError[F, E]].raiseError))

  def If(condition: => Boolean): F[A] = If_(_ => condition)

  def If_(condition: A => Boolean): F[A] = self.flatMap(e => if (!condition(e)) implicitly[MonoidK[F]].empty else self)

  def IfNot(condition: => Boolean): F[A] = IfNot_(_ => condition)

  def IfNot_(condition: A => Boolean): F[A] = self.flatMap(e => if (condition(e)) implicitly[MonoidK[F]].empty else self)
}
