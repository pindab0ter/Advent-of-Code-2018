(ns nl.pindab0ter.aoc2015.day07.some-assembly-required-test
  (:require [clojure.test :refer :all]
            [nl.pindab0ter.aoc2015.day07.some-assembly-required :refer [parse get-signal]]))

(deftest get-signal-test
  (let [input   "123 -> x\n456 -> y\nx AND y -> d\nx OR y -> e\nx LSHIFT 2 -> f\ny RSHIFT 2 -> g\nNOT x -> h\nNOT y -> i"
        circuit (parse input)]
    (are [expected wire]
      (= expected (get-signal circuit wire))
      72 "d"
      507 "e"
      492 "f"
      114 "g"
      65412 "h"
      65079 "i"
      123 "x"
      456 "y")))
