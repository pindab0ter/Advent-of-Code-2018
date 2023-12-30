(ns nl.pindab0ter.aoc2015.day04.the-ideal-stocking-stuffer
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]])
  (:import (java.security MessageDigest)))

(defn md5
  "Calculate the MD5-hash of a string"
  [^String string]
  (->> string
       .getBytes
       (.digest (MessageDigest/getInstance "MD5"))
       (BigInteger. 1)
       (format "%032x")))

(defn mine
  "Find the lowest number that produces a hash with `n` leading zeroes"
  ([key n] (mine key n (str/join (repeat n \0)) 0))
  ([key n zeroes i]
   (let [hash (md5 (str key i))]
     (cond
       (str/starts-with? hash zeroes) i
       :else (recur key n zeroes (inc i))))))

(defn -main []
  (let [input (get-input 2015 4)]
    (println
      "The lowest number that produces a hash with 5 leading zeroes: "
      (time (mine input 5))
      \newline)
    (println
      "The lowest number that produces a hash with 6 leading zeroes: "
      (time (mine input 6)))))
