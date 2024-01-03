(ns nl.pindab0ter.aoc2015.day08.matchsticks-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day08.matchsticks :refer [parse]]))

(deftest parse-test
  (are [expected input]
    (= (count (parse input)) expected)
    0 "\"\""
    3 "\"abc\""
    7 "\"aaa\\\"aaa\""
    1 "\"\\x27\""

    ; Extra inputs:
    1 "\"\\x66\""
    4 "\"\\\\x66\""
    2 "\"\\\\\\x66\""
    5 "\"\\\\\\\\x66\""))
