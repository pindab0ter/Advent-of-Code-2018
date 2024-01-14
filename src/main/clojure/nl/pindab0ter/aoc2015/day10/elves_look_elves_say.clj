(ns nl.pindab0ter.aoc2015.day10.elves-look-elves-say
  (:require
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]))


(defn look-and-say [sequence]
  (loop [remaining-sequence (rest sequence)
         count              1
         number             (first sequence)
         new-sequence       []]
    (if (empty? remaining-sequence)
      (conj new-sequence count number)
      (let [element (first remaining-sequence)]
        (if (= element number)
          (recur (rest remaining-sequence) (inc count) number new-sequence)
          (recur (rest remaining-sequence) 1 element (conj new-sequence count number)))))))


(defn -main []
  (let [input       (get-input 2015 10)
        sequence-in (map #(Integer/parseInt (str %)) input)]
    (println "The length of the sequence after 40 iterations is:"
             (time (count (nth (iterate look-and-say sequence-in) 40))) \newline)
    (println "The length of the sequence after 50 iterations is:"
             (time (count (nth (iterate look-and-say sequence-in) 50))))))
