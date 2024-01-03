(ns nl.pindab0ter.aoc2015.day07.some-assembly-required
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.collections :refer [find-first]]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]))

(def USHORT_MAX 65535)

(defn parse [^String s]
  (map
    #(let [[_ a operator b target] (re-find #"^([a-z0-9]+)? ?([A-Z]+)? ?([a-z0-9]+)? -> ([a-z]+)$" %)]
       {:a a, :operator operator, :b b, :target target})
    (str/split-lines s)))

(defn parse-uint
  "Returns the unsigned integer value of the string, or nil if it is not a valid unsigned integer."
  [s]
  (when s
    (try
      (Integer/parseUnsignedInt s)
      (catch NumberFormatException _ nil)
      (catch IllegalArgumentException _ nil))))

(def get-signal
  (memoize
    (fn [wires wire]
      (let [instruction (find-first #(= (get % :target) wire) wires)
            a           (if-let [a (get instruction :a)] (or (parse-uint a) (get-signal wires a)))
            b           (if-let [b (get instruction :b)] (or (parse-uint b) (get-signal wires b)))]
        (case (:operator instruction)
          nil a
          "AND" (bit-and a b)
          "OR" (bit-or a b)
          "LSHIFT" (bit-shift-left a b)
          "RSHIFT" (bit-shift-right a b)
          "NOT" (bit-xor b USHORT_MAX))))))

(defn -main []
  (let [circuit (parse (get-input 2015 7))
        wire    "a"
        signal  (get-signal circuit wire)]
    (println "Wire" wire "is provided with signal:" signal)))
