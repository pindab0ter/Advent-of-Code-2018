(ns nl.pindab0ter.aoc2015.day08.matchsticks
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]
            [nl.pindab0ter.common.collections :refer [sum]]))

(defn hex-to-char [^String s]
  (-> s second (Integer/parseInt 16) char str))

(defn unescape [^String s]
  (-> s
      (str/replace "\\\\" "^")                              ; `\\`   -> `^`
      (str/replace #"(?!<^\\)\\x([a-f0-9]{2})" hex-to-char) ; `\x45` -> `a`
      (str/replace "\\\"" "\"")                             ; `\"`   -> `"`
      (str/replace "^" "\\")                                ; `^`    -> `\`
      (str/replace #"^\"|\"$" "")))                         ; Removes first and last `"`

(defn escape [^String s]
  (-> s
      (str/escape {\\ "\\\\"
                   \" "\\\""})
      (#(str \" % \"))))

(defn -main []
  (let [lines     (str/split-lines (get-input 2015 8))
        raw       (sum (map count lines))
        unescaped (sum (map count (map unescape lines)))
        escaped   (sum (map count (map escape lines)))]
    (println "Difference between character count pre and post unescaping:" (- raw unescaped))
    (println "Difference between character count pre and post escaping:" (- escaped raw))))