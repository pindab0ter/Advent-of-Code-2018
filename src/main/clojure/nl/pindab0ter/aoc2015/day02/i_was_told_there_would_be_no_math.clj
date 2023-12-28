(ns nl.pindab0ter.aoc2015.day02.i-was-told-there-would-be-no-math
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.math :refer [sum]])
  (:import (nl.pindab0ter.common AdventOfCodeKt)))

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

(defn -main []
  (let [input                   (AdventOfCodeKt/getInput 2015 2)
        required-wrapping-paper (->> input
                                     parse
                                     (map wrapping-paper)
                                     sum)]
    (println "Required wrapping paper for all boxes:"
             required-wrapping-paper)))
