(ns nl.pindab0ter.aoc2015.day02.i-was-told-there-would-be-no-math
  (:require [clojure.string :as str])
  (:import (nl.pindab0ter.common AdventOfCodeKt)))

(defn line-to-dimensions
  "Parse a line `2x3x4` into a list of Integers `(2 3 4)`"
  [line]
  (map #(Integer/parseInt %) (str/split line #"x")))

(defn parse
  "Parse a multiline string into a list of dimensions"
  [input]
  (map line-to-dimensions (str/split-lines input)))

(defn wrapping-paper
  "Calculate the amount of wrapping paper required for a box with the given dimensions `l w h`"
  [[l w h]]
  (let [sides (list (* l w) (* l h) (* w h))]
    (+
      (apply min sides)
      (reduce + (map #(* 2 %) sides)))))

(defn -main []
  (let [input  (AdventOfCodeKt/getInput 2015 2)
        parsed (parse input)]
    (print
      "Required wrapping paper for all boxes:"
      (reduce + (map wrapping-paper parsed)))))
