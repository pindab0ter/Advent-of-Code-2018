(ns nl.pindab0ter.aoc2015.day05.doesnt-he-have-intern-elves-for-this
  (:require
    [clojure.string :as str]
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]))


;; Without regex

(defn old-nice? [s]
  (let [vowels          #{\a \e \i \o \u}
        forbidden       #{"ab" "cd" "pq" "xy"}
        three-vowels?   (>= (count (filter vowels s)) 3)
        double-letters? (boolean (some (fn [[a b]] (= a b)) (partition 2 s)))
        no-forbidden?   (not-any? #(str/includes? s %) forbidden)]
    (and three-vowels? double-letters? no-forbidden?)))


(defn first-pair-non-unique? "Checks if the first pair of the collection is non-unique."
  [coll]
  (let [a (first coll)]
    (some #(= a (nth coll %)) (range 2 (count coll)))))


(defn contains-two-pairs?
  "Checks if the string contains at least two pairs of letters that are non-unique."
  [s]
  (let [indices (range 0 (dec (count s)))
        pairs   (map #(take 2 (drop % s)) indices)]
    (boolean (some #(first-pair-non-unique? (drop % pairs)) indices))))


(defn contains-split-pair?
  "Checks if the string contains a pair of letters that is separated by one letter."
  [s]
  (let [indices (range 0 (- (count s) 2))
        triples (map #(take 3 (drop % s)) indices)]
    (boolean (some #(= (first %) (last %)) triples))))


(defn new-nice? [s]
  (and (contains-two-pairs? s) (contains-split-pair? s)))


;; With just regex

(defn old-nice?' [s]
  (and
    (>= (count (re-seq #"[aeiou]" s)) 3)
    (some? (re-find #"([a-z])\1" s))
    (not (some? (re-find #"(ab|cd|pq|xy)" s)))))


(defn new-nice?' [s]
  (and
    (some? (re-find #"([a-z]{2}).*\1" s))
    (some? (re-find #"([a-z]).\1" s))))


(defn -main []
  (let [lines (str/split-lines (get-input 2015 5))]
    (println "Amount of strings that are nice by the old rules:" (count (filter old-nice? lines)))
    (println "Amount of strings that are nice by the new rules:" (count (filter new-nice? lines)))))
