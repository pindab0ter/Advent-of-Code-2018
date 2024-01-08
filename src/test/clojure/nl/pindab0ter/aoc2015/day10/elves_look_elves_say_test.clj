(ns nl.pindab0ter.aoc2015.day10.elves-look-elves-say-test
  (:require
    [clojure.test :refer :all]
    [nl.pindab0ter.aoc2015.day10.elves-look-elves-say :refer [look-and-say]]))


(deftest look-and-say-test
  (are [expected input]
    (= (look-and-say input) expected)
    [1 1] [1]
    [2 1] [1 1]
    [1 2 1 1] [2 1]
    [3 1 2 2 1 1] [1 1 1 2 2 1]))
