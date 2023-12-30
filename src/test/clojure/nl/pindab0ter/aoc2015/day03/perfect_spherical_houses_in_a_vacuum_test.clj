(ns nl.pindab0ter.aoc2015.day03.perfect-spherical-houses-in-a-vacuum-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day03.perfect-spherical-houses-in-a-vacuum :refer [follow]]))

(deftest follow-test
  (are [expected input]
    (= expected (->> input follow set count))
    2 ">"
    4 "^>v<"
    2 "^v^v^v^v^v"))
