(ns nl.pindab0ter.aoc2015.day07.some-assembly-required-test
  (:require [clojure.test :refer :all])
  (:require [clojure.string :as str]
            [nl.pindab0ter.aoc2015.day07.some-assembly-required :refer [parse emulate]]))

(deftest emulate-test
  (is
    (=
      {"d" 72, "e" 507, "f" 492, "g" 114, "h" 65412, "i" 65079, "x" 123, "y" 456}
      (->> "123 -> x\n456 -> y\nx AND y -> d\nx OR y -> e\nx LSHIFT 2 -> f\ny RSHIFT 2 -> g\nNOT x -> h\nNOT y -> i"
           str/split-lines
           (map parse)
           (reduce (fn [acc instruction] (emulate acc instruction)) {})))))
