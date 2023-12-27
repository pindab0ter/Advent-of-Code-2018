(ns nl.pindab0ter.aoc2015.day01.not-quite-lisp-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day01.not-quite-lisp :refer :all]))

(deftest final-floor-test
  (are [actual input]
    (= actual (final-floor input))
    0 "(())"
    0 "()()"
    3 "((("
    3 "(()(()("
    3 "))((((("
    -1 "())"
    -1 "))("
    -3 ")))"
    -3 ")())())"))

(deftest basement-instruction-test
  (are [actual input]
    (= actual (basement-instruction input))
    1 ")"
    5 "()())"))
