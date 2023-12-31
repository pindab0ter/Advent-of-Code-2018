(ns nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this-test
  (:require [clojure.test :refer :all])
  (:require [nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this :refer [old-nice? new-nice? old-nice?' new-nice?']]))

(deftest old-nice?-test
  (are [expected input]
    (= expected (old-nice? input))
    true "ugknbfddgicrmopn"
    true "aaa"
    false "jchzalrnumimnmhp"
    false "haegwjzuvuyypxyu"
    false "dvszwmarrgswjxmb"))

(deftest old-nice?'-test
  (are [expected input]
    (= expected (old-nice?' input))
    true "ugknbfddgicrmopn"
    true "aaa"
    false "jchzalrnumimnmhp"
    false "haegwjzuvuyypxyu"
    false "dvszwmarrgswjxmb"))

(deftest new-nice?-test
  (are [expected input]
    (= expected (new-nice? input))
    true "qjhvhtzxzqqjkmpb"
    true "xxyxx"
    false "uurcxstgmygtbstg"
    false "ieodomkazucvgmuy"))

(deftest new-nice?'-test
  (are [expected input]
    (= expected (new-nice?' input))
    true "qjhvhtzxzqqjkmpb"
    true "xxyxx"
    false "uurcxstgmygtbstg"
    false "ieodomkazucvgmuy"))
