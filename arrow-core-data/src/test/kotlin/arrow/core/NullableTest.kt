package arrow.core

import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.matchers.types.shouldBeNull
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

/*
----------------------------------------------------------------------------------------------------
 */

class NullableTest : StringSpec({
  "map1 short circuits if any arg is null" {
    map1(null) { _ -> Unit }.shouldBeNull()
  }

  "map1 performs action when arg is not null" {
    map1(1) { _ -> Unit }.shouldNotBeNull()
  }

  "map2 only performs action when all arguments are not null" {
    generateAllPathsForNForks("a", null, 2)
      .forEach { (a: String?, b: String?) ->
        if (listOf(a, b).all { it != null }) {
          map2(a, b, { _, _ -> Unit }).shouldNotBeNull()
        } else {
          map2(a, b, { _, _ -> Unit }).shouldBeNull()
        }
      }
  }

  "map3 only performs action when all arguments are not null" {
    generateAllPathsForNForks("a", null, 3)
      .forEach { (a: String?, b: String?, c: String?) ->
        if (listOf(a, b, c).all { it != null }) {
          map3(a, b, c, { _, _, _ -> Unit }).shouldNotBeNull()
        } else {
          map3(a, b, c, { _, _, _ -> Unit }).shouldBeNull()
        }
      }
  }

  "map4 only performs action when all arguments are not null" {
    generateAllPathsForNForks(1, null, 4)
      .forEach { (a: Int?, b: Int?, c: Int?, d: Int?) ->
        if (listOf(a, b, c, d).all { it != null }) {
          map4(a, b, c, d, { _, _, _, _ -> Unit }).shouldNotBeNull()
        } else {
          map4(a, b, c, d, { _, _, _, _ -> Unit }).shouldBeNull()
        }
      }
  }

  "map5 only performs action when all arguments are not null" {
    generateAllPathsForNForks(1, null, 5)
      .forEach { (a: Int?, b: Int?, c: Int?, d: Int?, e: Int?) ->
        if (listOf(a, b, c, d, e).all { it != null }) {
          map5(a, b, c, d, e, { _, _, _, _, _ -> Unit }).shouldNotBeNull()
        } else {
          map5(a, b, c, d, e, { _, _, _, _, _ -> Unit }).shouldBeNull()
        }
      }
  }

  "map6 only performs action when all arguments are not null" {
    generateAllPathsForNForks(1, null, 6)
      .forEach { (a: Int?, b: Int?, c: Int?, d: Int?, e: Int?, f: Int?) ->
        if (listOf(a, b, c, d, e, f).all { it != null }) {
          map6(a, b, c, d, e, f, { _, _, _, _, _, _ -> Unit }).shouldNotBeNull()
        } else {
          map6(a, b, c, d, e, f, { _, _, _, _, _, _ -> Unit }).shouldBeNull()
        }
      }
  }

  "map7 only performs action when all arguments are not null" {
    generateAllPathsForNForks(1, null, 7)
      .forEach { (a: Int?, b: Int?, c: Int?, d: Int?, e: Int?, f: Int?, g: Int?) ->
        if (listOf(a, b, c, d, e, f, g).all { it != null }) {
          map7(a, b, c, d, e, f, g, { _, _, _, _, _, _, _ -> Unit }).shouldNotBeNull()
        } else {
          map7(a, b, c, d, e, f, g, { _, _, _, _, _, _, _ -> Unit }).shouldBeNull()
        }
      }
  }

  "map8 only performs action when all arguments are not null" {
    generateAllPathsForNForks(1, null, 8)
      .forEach { (a: Int?, b: Int?, c: Int?, d: Int?, e: Int?, f: Int?, g: Int?, h: Int?) ->
        if (listOf(a, b, c, d, e, f, g, h).all { it != null }) {
          map8(a, b, c, d, e, f, g, h, { _, _, _, _, _, _, _, _ -> Unit }).shouldNotBeNull()
        } else {
          map8(a, b, c, d, e, f, g, h, { _, _, _, _, _, _, _, _ -> Unit }).shouldBeNull()
        }
      }
  }

  "map9 only performs action when all arguments are not null" {
    generateAllPathsForNForks(1, null, 9)
      .forEach { (a: Int?, b: Int?, c: Int?, d: Int?, e: Int?, f: Int?, g: Int?, h: Int?, i: Int?) ->
        if (listOf(a, b, c, d, e, f, g, h, i).all { it != null }) {
          map9(a, b, c, d, e, f, g, h, i, { _, _, _, _, _, _, _, _, _ -> Unit }).shouldNotBeNull()
        } else {
          map9(a, b, c, d, e, f, g, h, i, { _, _, _, _, _, _, _, _, _ -> Unit }).shouldBeNull()
        }
      }
  }
})

/*
----------------------------------------------------------------------------------------------------
Helper functions for generating exhaustive test scenarios
 */

private fun <A> List<A>.forkPath(choice1: A, choice2: A): Pair<List<A>, List<A>> =
  Pair(this + choice1, this + choice2)

private fun <A> List<List<A>>.forkPaths(choice1: A, choice2: A): List<List<A>> =
  this.fold(emptyList()) { acc: List<List<A>>, path: List<A> ->
    val paths: Pair<List<A>, List<A>> = path.forkPath(choice1, choice2)
    acc.plusElement(paths.first).plusElement(paths.second)
  }

private fun <A> generateAllPathsForNForks(choice1: A, choice2: A, n: Int): List<List<A>> =
  IntRange(1, n).fold(listOf(emptyList())) { acc: List<List<A>>, _ ->
    acc.forkPaths(choice1, choice2)
  }

class GenerateAllPathsForNForksTest : StringSpec({
  "for 0 forks" {
    generateAllPathsForNForks("a", "b", 0) shouldContainAll emptyList()
  }

  "for 1 fork" {
    generateAllPathsForNForks("a", "b", 1) shouldBe listOf(
      listOf("a"),
      listOf("b")
    )
  }

  "for 2 forks" {
    generateAllPathsForNForks(0, 1, 2) shouldBe listOf(
      listOf(0, 0),
      listOf(0, 1),
      listOf(1, 0),
      listOf(1, 1)
    )
  }
})

/*
----------------------------------------------------------------------------------------------------
 */

private operator fun <E> List<E>.component6(): E = this[5]
private operator fun <E> List<E>.component7(): E = this[6]
private operator fun <E> List<E>.component8(): E = this[7]
private operator fun <E> List<E>.component9(): E = this[8]
