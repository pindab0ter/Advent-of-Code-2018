(ns nl.pindab0ter.aoc2015.day04.the-ideal-stocking-stuffer-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day04.the-ideal-stocking-stuffer :refer [mine]]))

(deftest mine-test
  (are [input expected]
    (= expected (mine input 5))
    "abcdef" 609043
    "pqrstuv" 1048970))
