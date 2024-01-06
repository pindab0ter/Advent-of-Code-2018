(ns nl.pindab0ter.aoc2023.day01.trebuchet
  (:require
    [clojure.string :as str]
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]
    [nl.pindab0ter.common.collections :refer [sum]]))


(defn get-digit [s]
  (let [digit (re-find #"^\d" s)]
    (or (when digit (Integer/parseInt digit))
        (when (str/starts-with? s "one") 1)
        (when (str/starts-with? s "two") 2)
        (when (str/starts-with? s "three") 3)
        (when (str/starts-with? s "four") 4)
        (when (str/starts-with? s "five") 5)
        (when (str/starts-with? s "six") 6)
        (when (str/starts-with? s "seven") 7)
        (when (str/starts-with? s "eight") 8)
        (when (str/starts-with? s "nine") 9))))


(defn get-calibration-value
  ([line] (get-calibration-value line []))
  ([line acc]
   (if (empty? line)
     (let [digits (filter identity acc)] (+ (* 10 (first digits)) (last digits)))
     (recur (subs line 1) (conj acc (get-digit line))))))


(defn -main []
  (let [input  (get-input 2023 1)
        values (map get-calibration-value (str/split-lines input))]
    (println (sum values))))
