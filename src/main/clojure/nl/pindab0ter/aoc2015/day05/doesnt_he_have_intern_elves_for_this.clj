(ns nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]))

(defn nice? [s]
  (let [vowels          #{\a \e \i \o \u}
        forbidden       #{"ab" "cd" "pq" "xy"}
        three-vowels?   (>= (count (filter vowels s)) 3)
        double-letters? (boolean (some (fn [[a b]] (= a b)) (partition 2 s)))
        no-forbidden?   (not (some #(str/includes? s %) forbidden))]
    (and three-vowels? double-letters? no-forbidden?)))

(defn -main []
  (let [lines (str/split-lines (get-input 2015 5))]
    (println "Amount of strings that are nice:" (count (filter nice? lines)))))
