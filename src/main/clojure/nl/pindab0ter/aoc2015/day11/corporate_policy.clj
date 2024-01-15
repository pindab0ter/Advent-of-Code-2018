(ns nl.pindab0ter.aoc2015.day11.corporate-policy
  (:require
    [nl.pindab0ter.common.advent-of-code :refer [get-input]]))


(defn consecutive? [s]
  (every? (fn [[c1 c2]] (= (inc (int c1)) (int c2)))
          (partition 2 1 (map char s))))


(defn three-consecutive-letters? [s]
  (->> s (partition 3 1)
       (some consecutive?)
       (boolean)))


(defn no-disallowed-characters? [s]
  (not (re-find #"[iol]" s)))


(defn contains-two-pairs? [s]
  (boolean (re-find #"(?:(?:([a-z])\1).*?){2}" s)))


(defn valid-password? [s]
  (and
    (three-consecutive-letters? s)
    (no-disallowed-characters? s)
    (contains-two-pairs? s)))


(defn increment-char [^Character c]
  (-> c
      (int)
      (- (int \a) -1)
      (mod 26)
      (+ (int \a))
      (char)))


(defn increment [s]
  (->> s
       reverse
       rest
       (reduce
         (fn [acc c]
           (conj acc (if (every? #(= \a %) acc) (increment-char c) c)))
         [(->> s reverse first increment-char)])
       reverse
       (apply str)))


(defn next-password [s]
  (loop [candidate s]
    (if (valid-password? candidate)
      candidate
      (recur (increment candidate)))))


(defn -main []
  (let [input (get-input 2015 11)]
    (println (next-password input))))
