(ns nl.pindab0ter.aoc2015.day10.elves-look-elves-say
  (:require
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]))


(defn look-and-say
  "Given a sequence of digits, returns the next sequence in the look-and-say sequence."
  ([sequence] (look-and-say sequence 1))
  ([sequence n]
   (let [groups        (map first (re-seq #"(\d)\1*" sequence))
         next-sequence (reduce (fn [acc group] (str acc (count group) (first group))) "" groups)]
     (if (= n 1)
       next-sequence
       (recur next-sequence (dec n))))))


(defn -main []
  (let [input      (get-input 2015 10)]
    (println "The length of sequence" (str \" input \") "after" 40 "iterations is:" (count (look-and-say input 40)))
    ;; Brute forcing with regex took ±20 minutes
    (println "The length of sequence" (str \" input \") "after" 50 "iterations is:" (count (look-and-say input 50)))))
