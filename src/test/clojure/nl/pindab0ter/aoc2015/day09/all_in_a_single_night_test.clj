(ns nl.pindab0ter.aoc2015.day09.all-in-a-single-night-test
  (:require
    [clojure.test :refer :all]
    [nl.pindab0ter.aoc2015.day09.all-in-a-single-night :refer :all]))


(deftest get-segment-distance-should-return-map-of-distances
  (is (= {"London" {"Dublin" 464}, "Dublin" {"London" 464}}
         (get-segment-distance "London to Dublin = 464"))))


(deftest get-segment-distances-should-return-map-of-all-origins-with-distances
  (is (= {"London"  {"Dublin" 464, "Belfast" 518},
          "Dublin"  {"London" 464, "Belfast" 141},
          "Belfast" {"London" 518, "Dublin" 141}}
         (get-segment-distances "London to Dublin = 464\nLondon to Belfast = 518\nDublin to Belfast = 141"))))


(deftest total-distance-should-return-total-distance-of-the-route
  (is (= 605
         (total-distance {"London"  {"Dublin" 464, "Belfast" 518},
                          "Dublin"  {"London" 464, "Belfast" 141},
                          "Belfast" {"London" 518, "Dublin" 141}}
                         ["London" "Dublin" "Belfast"]))))
