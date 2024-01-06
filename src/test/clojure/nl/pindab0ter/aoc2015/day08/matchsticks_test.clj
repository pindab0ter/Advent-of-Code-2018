(ns nl.pindab0ter.aoc2015.day08.matchsticks-test
  (:require
    [clojure.test :refer :all]
    [nl.pindab0ter.aoc2015.day08.matchsticks :refer [escape unescape]]))


(deftest unescape-test
  (are [expected input]
    (= (count (unescape input)) expected)
    0 "\"\""
    3 "\"abc\""
    7 "\"aaa\\\"aaa\""
    1 "\"\\x27\""

    ;; Extra inputs:
    1 "\"\\x66\""
    4 "\"\\\\x66\""
    2 "\"\\\\\\x66\""
    5 "\"\\\\\\\\x66\""))


(deftest escape-test
  (are [expected input]
    (= (count (escape input)) expected)
    6 "\"\""
    9 "\"abc\""
    16 "\"aaa\\\"aaa\""
    11 "\"\\x27\""))
