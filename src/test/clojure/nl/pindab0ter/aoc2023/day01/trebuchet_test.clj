(ns nl.pindab0ter.aoc2023.day01.trebuchet-test
  (:require
    [clojure.test :refer :all]
    [nl.pindab0ter.aoc2023.day01.trebuchet :refer [get-calibration-value]]
    [nl.pindab0ter.common.collections :refer [sum]]))


(deftest get-calibration-value-test
  (are [x y]
    (= (get-calibration-value y) x)
    12 "1abc2"
    38 "pqr3stu8vwx"
    15 "a1b2c3d4e5f"
    77 "treb7uchet"
    29 "two1nine"
    83 "eightwothree"
    13 "abcone2threexyz"
    24 "xtwone3four"
    42 "4nineeightseven2"
    14 "zoneight234"
    76 "7pqrstsixteen"
    77 "7g"
    83 "eighthree"
    45 "45"))


(deftest sum-of-all-test
  (testing "Sum of all part one"
    (let [result (map get-calibration-value ["1abc2" "pqr3stu8vwx" "a1b2c3d4e5f" "treb7uchet"])]
      (is (= 142 (sum result)))))
  (testing "Sum of all part two"
    (let [result (map get-calibration-value ["two1nine" "eightwothree" "abcone2threexyz" "xtwone3four" "4nineeightseven2" "zoneight234" "7pqrstsixteen"])]
      (is (= 281 (sum result))))))
