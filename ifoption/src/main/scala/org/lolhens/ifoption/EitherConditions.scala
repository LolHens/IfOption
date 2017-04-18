package org.lolhens.ifoption

import scala.language.implicitConversions

/**
  * Created by u016595 on 17.01.2017.
  */
class EitherConditions[A, B](val self: Either[A, B]) extends AnyVal {
  def Then[D](function: => D): Either[A, D] = Then_[D]((_: B) => function)

  def Then_[D](function: B => D): Either[A, D] = self.map[D](function)

  def ThenIf[C, D](function: => Option[D])(implicit ca: A <:< C, cb: B <:< C): Either[C, D] = ThenIf_[C, D]((_: B) => function)

  //def ThenIf[C, D](function: => Either[_, D])(implicit ca: A <:< C, cb: B <:< C, dummy: DummyImplicit): Either[C, D] = ThenIf[C, D](function.toOption)

  def ThenIf_[C, D](function: B => Option[D])(implicit ca: A <:< C, cb: B <:< C): Either[C, D] = self match {
    case Left(left) => Left[C, D](left)
    case Right(right) => function(right) match {
      case Some(result) => Right[C, D](result)
      case None => Left[C, D](right)
    }
  }

  //def ThenIf_[C, D](function: B => Either[_, D])(implicit ca: A <:< C, cb: B <:< C, dummy: DummyImplicit): Either[C, D] = ThenIf_[C, D](function.andThen(_.toOption))

  def Else[D >: B](function: => D): D = Else_[D]((_: A) => function)

  def Else_[D >: B](function: A => D): D = self match {
    case Left(left) => function(left)
    case Right(right) => right
  }

  def ElseIf[D >: B](function: => Option[D]): Either[A, D] = ElseIf_[D]((_: A) => function)

  def ElseIf[D >: B](function: => Either[_, D])(implicit dummy: DummyImplicit): Either[A, D] = ElseIf_[D]((_: A) => function)

  def ElseIf_[D >: B](function: A => Option[D]): Either[A, D] = self match {
    case Left(left) => function(left) match {
      case Some(result) => Right[A, D](result)
      case None => Left[A, D](left)
    }
    case Right(right) => Right[A, D](right)
  }

  def ElseIf_[D >: B](function: A => Either[_, D])(implicit dummy: DummyImplicit): Either[A, D] = ElseIf_[D](function.andThen(_.toOption))

  def ElseThen[C](function: => C): Either[C, B] = ElseThen_[C]((_: A) => function)

  def ElseThen_[C](function: A => C): Either[C, B] = self match {
    case Left(left) => Left[C, B](function(left))
    case Right(right) => Right[C, B](right)
  }

  def ElseNone: Option[B] = self.toOption

  def If[C](condition: => Boolean)(implicit ca: A <:< C, cb: B <:< C): Either[C, B] = If_[C]((_: B) => condition)

  def If_[C](condition: B => Boolean)(implicit ca: A <:< C, cb: B <:< C): Either[C, B] = self match {
    case Left(left) => Left[C, B](left)
    case Right(right) =>
      if (condition(right))
        Right[C, B](right)
      else
        Left[C, B](right)
  }

  def IfNot[C](condition: => Boolean)(implicit ca: A <:< C, cb: B <:< C): Either[C, B] = IfNot_[C]((_: B) => condition)

  def IfNot_[C](condition: B => Boolean)(implicit ca: A <:< C, cb: B <:< C): Either[C, B] = self match {
    case Left(left) => Left[C, B](left)
    case Right(right) =>
      if (condition(right))
        Left[C, B](right)
      else
        Right[C, B](right)
  }

  def get: B = self match {
    case Left(_) => throw new NoSuchElementException("Either.get")
    case Right(value) => value
  }
}

object EitherConditions {
  implicit def either2EitherConditions[A, B](either: Either[A, B]): EitherConditions[A, B] = new EitherConditions[A, B](either)
}
