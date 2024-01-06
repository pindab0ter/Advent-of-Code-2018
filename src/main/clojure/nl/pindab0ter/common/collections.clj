(ns nl.pindab0ter.common.collections)

(defn sum
  "Returns the sum of the collection."
  [coll]
  (reduce + coll))

(defn find-first
  "Given a predicate and a collection, returns the first element
  in the collection for which the predicate evaluates to true."
  [pred coll]
  (some #(when (pred %) %) coll))

(defn inits
  "Returns a lazy sequence of all possible prefixes of the collection."
  [coll]
  (rest (reductions conj [] coll)))

(defn tails
  "Returns a lazy sequence of all possible suffixes of the collection."
  [coll]
  (take-while seq (iterate rest coll)))

(defn rotations
  "Returns a lazy sequence of all possible rotations of the collection."
  [coll]
  (let [vec (vec coll)]
    (for [i (range (count vec))]
      (concat (subvec vec i) (subvec vec 0 i)))))

(defn permutations
  "Returns a lazy sequence of all possible permutations of the collection."
  [coll]
  (if (empty? coll)
    (list ())
    (mapcat
      (fn [[x & xs]]
        (map #(cons x %) (permutations xs)))
      (rotations coll))))
