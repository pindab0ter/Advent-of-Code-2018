(ns nl.pindab0ter.aoc2015.day04.the-ideal-stocking-stuffer-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day04.the-ideal-stocking-stuffer :refer [mine]]))

(deftest mine-test
  (are [expected input]
    (= expected (mine input 5))
    609043 "abcdef"
    1048970 "pqrstuv"))
