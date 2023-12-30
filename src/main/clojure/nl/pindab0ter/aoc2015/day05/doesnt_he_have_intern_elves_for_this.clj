(ns nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]))

(defn three-vowels? [col]
  (let [vowels #{\a \e \i \o \u}]
    (>= (count (filter vowels col)) 3)))

(defn contains-double? [col]
  (cond
    (< (count col) 2) false
    (= (first col) (second col)) true
    :else (recur (rest col))))

(defn contains-forbidden? [s]
  (let [forbidden #{"ab" "cd" "pq" "xy"}]
    (some #(str/includes? s %) forbidden)))

(defn nice? [string]
  (and
    (three-vowels? string)
    (contains-double? string)
    (not (contains-forbidden? string))))

(defn -main []
  (let [lines (str/split-lines (get-input 2015 5))]
    (println "Amount of strings that are nice:" (count (filter nice? lines)))))
