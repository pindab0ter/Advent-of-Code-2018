(ns nl.pindab0ter.aoc2015.day11.corporate-policy-test
  (:require
    [clojure.test :refer :all]
    [nl.pindab0ter.aoc2015.day11.corporate-policy :refer [valid-password?]]))


(deftest valid-password?-test
  (are [input expected]
    (= (valid-password? input) expected)
    "hijklmmn" false
    "abbceffg" false
    "abbcegjk" false
    "abcdffaa" true
    "ghjaabcc" true))
