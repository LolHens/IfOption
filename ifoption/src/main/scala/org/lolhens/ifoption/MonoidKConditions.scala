package org.lolhens.ifoption

import cats.implicits._
import cats.{Foldable, MonadError, MonoidK}

import scala.language.higherKinds

class MonoidKConditions[F[_] : Foldable : MonoidK, E, A](val self: F[A])(implicit F0: MonadError[F, E]) {

  def Then[B](f: => B): F[B] = self.map[B](_ => f)

  def Then_[B](f: A => B): F[B] = self.map[B](f)

  def ThenIf[B](f: => F[B]): F[B] = self.flatMap[B](_ => f)

  def ThenIf_[B](f: A => F[B]): F[B] = self.flatMap[B](f)

  def Else[B >: A](f: => B): B = self.widen[B].foldl(f)((_, e) => e)

  def Else_[B >: A](f: E => B): B = self.widen[B].handleError(f).foldl(null.asInstanceOf[B])((_, e) => e)

  def ElseIf[B >: A](f: => F[B]): F[B] = self.widen[B].handleErrorWith(_ => f)

  def ElseIf_[B >: A](f: E => F[B]): F[B] = self.widen[B].handleErrorWith(f)

  def ElseThen(value: => E): F[A] = ElseThen_(_ => value)

  def ElseThen_(f: E => E): F[A] = self.handleErrorWith(f.andThen(implicitly[MonadError[F, E]].raiseError))

  def If(condition: => Boolean): F[A] = If_(_ => condition)

  def If_(condition: A => Boolean): F[A] = self.flatMap(e => if (!condition(e)) implicitly[MonoidK[F]].empty else self)

  def IfNot(condition: => Boolean): F[A] = IfNot_(_ => condition)

  def IfNot_(condition: A => Boolean): F[A] = self.flatMap(e => if (condition(e)) implicitly[MonoidK[F]].empty else self)
}
