package nl.pindab0ter.common

fun <A> identity(a: A): A = a
fun <A> A.asLeft(): Either<A, Nothing> = Either.Left(this)
fun <A> A.asRight(): Either<Nothing, A> = Either.Right(this)
