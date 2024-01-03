(ns nl.pindab0ter.aoc2015.day04.the-ideal-stocking-stuffer-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day04.the-ideal-stocking-stuffer :refer [mine]]))

(deftest mine-test
  (is (= 609043 (mine "abcdef" "00000" 600000)))
  (is (= 1048970 (mine "pqrstuv" "00000" 1000000))))