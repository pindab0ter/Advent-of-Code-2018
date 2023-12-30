(ns nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this :refer [nice?]]))

(deftest nice?-test
  (are [expected input]
    (= expected (nice? input))
    true "ugknbfddgicrmopn"
    true "aaa"
    false "jchzalrnumimnmhp"
    false "haegwjzuvuyypxyu"
    false "dvszwmarrgswjxmb"))
