(ns nl.pindab0ter.aoc2015.day01.not-quite-lisp
  (:require
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]
    [nl.pindab0ter.common.collections :refer [sum]]))


(defn interpret
  "Interprets an instruction and calculates its impact on the floor."
  [instruction]
  (if (= instruction \() 1 -1))


(defn final-floor
  "Calculates the final floor based on a sequence of instructions"
  [input]
  (->> input (map interpret) sum))


(defn basement-instruction
  "Finds the position of the first instruction that leads to the basement"
  ([instructions] (basement-instruction 0 0 instructions))
  ([acc i instructions]
   (case acc
     -1 i
     (recur (->> instructions first interpret (+ acc))
            (inc i)
            (rest instructions)))))


(defn -main []
  (let [input (get-input 2015 1)]
    (println
      "The final floor is:"
      (final-floor input)
      "\n\nThe location of the first instruction leading to the basement is:"
      (basement-instruction input))))
