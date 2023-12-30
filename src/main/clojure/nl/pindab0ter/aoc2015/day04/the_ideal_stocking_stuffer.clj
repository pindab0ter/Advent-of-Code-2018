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
  "Find the lowest number that produces a hash with 5 leading zeroes"
  ([key] (mine key 0))
  ([key i]
   (let [hash (md5 (str key i))]
     (cond
       (str/starts-with? hash "00000") i
       :else (recur key (inc i))))))

(defn -main []
  (let [input  (get-input 2015 4)
        lowest (time (mine input))]
    (println "The lowest number that produces a hash with 5 leading zeroes: " lowest)))
