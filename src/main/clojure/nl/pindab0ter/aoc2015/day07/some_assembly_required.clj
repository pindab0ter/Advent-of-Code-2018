(ns nl.pindab0ter.aoc2015.day07.some-assembly-required
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]))

(def UINT_MAX 65535)

(defn parse [^String s]
  (let [[_ a operator b target] (re-find #"^([a-z0-9]+)? ?([A-Z]+)? ?([a-z0-9]+)? -> ([a-z]+)$" s)]
    {:a a, :operator operator, :b b, :target target}))

(defn parse-uint [s]
  (try
    (Integer/parseUnsignedInt s)
    (catch NumberFormatException _ nil)
    (catch IllegalArgumentException _ nil)))

(defn emulate [signals instruction]
  (let [a (or (parse-uint (:a instruction)) (get signals (:a instruction)) 0)
        b (or (parse-uint (:b instruction)) (get signals (:b instruction)) 0)]
    (assoc
      signals
      (:target instruction)
      (case (:operator instruction)
        "AND" (bit-and a b)
        "OR" (bit-or a b)
        "LSHIFT" (bit-shift-left a b)
        "RSHIFT" (bit-shift-right a b)
        "NOT" (bit-xor b UINT_MAX)
        a))))

(defn -main []
  (let [input (get-input 2015 7)]
    (->> input
         str/split-lines
         (map parse)
         (reduce (fn [acc instruction] (emulate acc instruction)) {})
         (sort-by key)
         println)))
