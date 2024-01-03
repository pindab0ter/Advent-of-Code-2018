(ns nl.pindab0ter.aoc2015.day08.matchsticks
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]
            [nl.pindab0ter.common.collections :refer [sum]]))

(defn hex-to-char [^String s]
  (-> s second (Integer/parseInt 16) char str))

(defn parse [^String s]
  (-> s
      (str/replace "\\\\" "^")                              ; `\\` -> `^`
      (str/replace #"(?!<^\\)\\x([a-f0-9]{2})" hex-to-char) ; `\x45` -> `a`
      (str/replace "\\\"" "\"")                             ; `\"` -> `"`
      (str/replace "^" "\\")                                ; `^` -> `\`
      (str/replace #"^\"|\"$" "")))                         ; Removes first and last `"`

(defn -main []
  (let [lines      (str/split-lines (get-input 2015 8))
        pre-count  (sum (map count lines))
        parsed     (map parse lines)
        post-count (sum (map count parsed))]
    (println "Difference between character count pre and post parse:" (- pre-count post-count))))