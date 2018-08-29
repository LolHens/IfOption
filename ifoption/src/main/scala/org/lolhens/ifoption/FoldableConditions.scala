package org.lolhens.ifoption

import cats.implicits._
import cats.{Foldable, MonadError, MonoidK}

import scala.language.{higherKinds, implicitConversions}

class FoldableConditions[F[_] : Foldable, E, A](val self: F[A])(implicit F0: MonadError[F, E]) {

  def Then[B](f: => B): F[B] = self.map[B](_ => f)

  def Then_[B](f: A => B): F[B] = self.map[B](f)

  def ThenIf[B](f: => F[B]): F[B] = self.flatMap[B](_ => f)

  def ThenIf_[B](f: A => F[B]): F[B] = self.flatMap[B](f)

  def Else[B >: A](f: => B): B = self.widen[B].handleError(_ => f).foldl[Option[B]](None)((_, e) => Some(e)).getOrElse(f)

  def Else_[B >: A](f: E => B): B = self.widen[B].handleError(f).foldl[Option[B]](None)((_, e) => Some(e)).get

  def ElseIf[B >: A](f: => F[B]): F[B] = self.widen[B].handleErrorWith(_ => f)

  def ElseIf_[B >: A](f: E => F[B]): F[B] = self.widen[B].handleErrorWith(f)

  def ElseThen(f: => E): F[A] = self.handleErrorWith(_ => F0.raiseError(f))

  def ElseThen_(f: E => E): F[A] = self.handleErrorWith(f.andThen(F0.raiseError))

  def If(condition: => Boolean)(implicit F1: MonoidK[F]): F[A] = If_(_ => condition)

  def If_(condition: A => Boolean)(implicit F1: MonoidK[F]): F[A] = self.flatMap(e => if (!condition(e)) F1.empty else self)

  def IfNot(condition: => Boolean)(implicit F1: MonoidK[F]): F[A] = IfNot_(_ => condition)

  def IfNot_(condition: A => Boolean)(implicit F1: MonoidK[F]): F[A] = self.flatMap(e => if (condition(e)) F1.empty else self)
}

object FoldableConditions {
  implicit def foldable2foldableConditions[F[_] : Foldable, E, A](self: F[A])(implicit F0: MonadError[F, E]): FoldableConditions[F, E, A] =
    new FoldableConditions(self)

  implicit def either2foldableConditions[E, A](self: Either[E, A]) = {
    type EitherWithE[A] = Either[E, A]
    new FoldableConditions(self: EitherWithE[A])
  }
}