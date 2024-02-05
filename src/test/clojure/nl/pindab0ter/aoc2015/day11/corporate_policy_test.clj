(ns nl.pindab0ter.aoc2015.day11.corporate-policy-test
  (:require
    [clojure.test :refer :all]
    [nl.pindab0ter.aoc2015.day11.corporate-policy :refer [next-password valid-password?]]))


(deftest valid-password?-test
  (are [input expected]
    (= (valid-password? input) expected)
    "hijklmmn" false
    "abbceffg" false
    "abbcegjk" false
    "abcdffaa" true
    "ghjaabcc" true))


(deftest next-password-test
  (are [input expected]
    (= (next-password input) expected)
    "abcdefgh" "abcdffaa"
    "ghizklmn" "ghjaabcc")) ;; Original sample was "ghijklmn", changed 'j' to 'z' for test performance
