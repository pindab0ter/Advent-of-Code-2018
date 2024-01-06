(ns nl.pindab0ter.aoc2023.day01.trebuchet
  (:require
    [clojure.string :as str]
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]
    [nl.pindab0ter.common.collections :refer [sum]]))


(defn get-digit [s]
  (let [digit (re-find #"^\d" s)]
    (cond
      (not (nil? digit)) (Integer/parseInt digit)
      (str/starts-with? s "one") 1
      (str/starts-with? s "two") 2
      (str/starts-with? s "three") 3
      (str/starts-with? s "four") 4
      (str/starts-with? s "five") 5
      (str/starts-with? s "six") 6
      (str/starts-with? s "seven") 7
      (str/starts-with? s "eight") 8
      (str/starts-with? s "nine") 9)))


(defn get-calibration-value
  "Get the calibration value from a line."
  ([s] (get-calibration-value s []))
  ([s coll]
   (if (empty? s)
     (let [digits (filter identity coll)]
       (Integer/parseInt (str (first digits) (last digits))))
     (let [digit (get-digit s)]
       (recur (subs s 1) (conj coll digit))))))


(defn -main []
  (let [input  (get-input 2023 1)
        values (map get-calibration-value (str/split-lines input))]
    (println (sum values))))
