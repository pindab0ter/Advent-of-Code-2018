(ns nl.pindab0ter.aoc2015.day06.probably-a-fire-hazard
  (:require [nl.pindab0ter.common.advent-of-code :refer [get-input]]))

(def width 1000)

(defn within? [i [from-x from-y to-x to-y]]
  (let [x (mod i width)
        y (int (/ i width))]
    (and (>= x from-x) (>= y from-y) (<= x to-x) (<= y to-y))))

(defn update-lights
  "Updates the lights in the grid according to the given operation."
  [grid square operation]
  (map-indexed
    (fn [i light] (if (within? i square) (operation light) light))
    grid))

(defn parse [s]
  (->> s
       (re-seq #"(turn (?:on|off)|toggle) (\d{1,3}),(\d{1,3}) through (\d{1,3}),(\d{1,3})")
       (map (fn [match]
              [(second match)
               [(Integer/parseInt (nth match 2)) (Integer/parseInt (nth match 3))
                (Integer/parseInt (nth match 4)) (Integer/parseInt (nth match 5))]]))))

(defn setup [grid instructions]
  (reduce
    (fn [acc [instruction square]]
      (case instruction
        "turn on" (update-lights acc square (constantly true))
        "turn off" (update-lights acc square (constantly false))
        "toggle" (update-lights acc square not)))
    grid
    instructions))

(defn -main []
  (let [input         (get-input 2015 6)
        grid          (vec (repeat (* width width) false))
        instructions  (parse input)
        finished-grid (setup grid instructions)]
    (println (count (filter identity finished-grid)))))