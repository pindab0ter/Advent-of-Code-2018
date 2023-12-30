(ns nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this :refer [nice?]]))

(deftest nice?-test
  (are [input expected]
    (= expected (nice? input))
    "ugknbfddgicrmopn" true
    "aaa" true
    "jchzalrnumimnmhp" false
    "haegwjzuvuyypxyu" false
    "dvszwmarrgswjxmb" false))
