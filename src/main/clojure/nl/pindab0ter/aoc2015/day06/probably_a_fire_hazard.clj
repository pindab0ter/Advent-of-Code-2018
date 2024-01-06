(ns nl.pindab0ter.aoc2015.day06.probably-a-fire-hazard
  (:require
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]
    [nl.pindab0ter.common.collections :refer [sum]]))


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
        "turn on" (update-lights acc square (constantly 1))
        "turn off" (update-lights acc square (constantly 0))
        "toggle" (update-lights acc square #(if (zero? %) 1 0))))
    grid
    instructions))


(defn setup' [grid instructions]
  (reduce
    (fn [acc [instruction square]]
      (case instruction
        "turn on" (update-lights acc square inc)
        "turn off" (update-lights acc square #(max 0 (dec %)))
        "toggle" (update-lights acc square #(+ 2 %))))
    grid
    instructions))


(defn -main []
  (let [input        (get-input 2015 6)
        grid         (vec (repeat (* width width) 0))
        instructions (parse input)
        first-grid   (setup grid instructions)
        second-grid  (setup' grid instructions)]
    (println "Amount of lights that are on after following the instructions:" (sum first-grid))
    (println "Amount of lights that are on after following the instructions as read in Elvish:" (sum second-grid))))
