(ns nl.pindab0ter.aoc2015.day11.corporate-policy)


(defn- consecutive? [s]
  (every? (fn [[c1 c2]] (= (inc (int c1)) (int c2)))
          (partition 2 1 (map char s))))


(defn- three-consecutive-letters? [s]
  (->> s (partition 3 1)
       (some consecutive?)
       (boolean)))


(defn- no-disallowed-characters? [s]
  (not (re-find #"[iol]" s)))


(defn- contains-two-pairs? [s]
  (>= (->> s (map char) (partition 2 1) (filter (fn [[a b]] (= a b))) count) 2))


(defn valid-password? [s]
  (and
    (three-consecutive-letters? s)
    (no-disallowed-characters? s)
    (contains-two-pairs? s)))


(defn -main []
  (let [input "abbcegjk"]
    (println (valid-password? input))))
