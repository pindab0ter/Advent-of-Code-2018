(ns nl.pindab0ter.aoc2015.day02.i-was-told-there-would-be-no-math
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]
            [nl.pindab0ter.common.math :refer [sum]]))

(defn parse
  "Parse a multiline string into a list of dimensions"
  [input]
  (for [line (str/split-lines input)]
    (map #(Integer/parseInt %) (str/split line #"x"))))

(defn wrapping-paper
  "Calculate the amount of wrapping paper required for a box with the given dimensions"
  [[l w h]]
  (let [sides        (list (* l w) (* l h) (* w h))
        min-side     (apply min sides)
        surface-area (sum (map #(* 2 %) sides))]
    (+ min-side surface-area)))

(defn ribbon
  "Calculate the amount of ribbon required for a box with the given dimensions"
  [[l w h]]
  (let [perimeters (map (fn [[x y]] (* 2 (+ x y))) [[l w] [l h] [w h]])
        volume     (* l w h)
        min-side   (apply min perimeters)]
    (+ min-side volume)))

(defn -main []
  (let [boxes                   (parse (get-input 2015 2))
        required-wrapping-paper (sum (map wrapping-paper boxes))
        required-ribbon         (sum (map ribbon boxes))]
    (println "Required wrapping paper for all boxes:"
             required-wrapping-paper
             "\n\nRequired ribbon for all boxes:"
             required-ribbon)))
