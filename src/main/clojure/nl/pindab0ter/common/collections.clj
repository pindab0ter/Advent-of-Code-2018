(ns nl.pindab0ter.common.collections)

(defn find-first
  "Given a predicate and a collection, returns the first element
  in the collection for which the predicate evaluates to true."
  [pred coll]
  (some #(when (pred %) %) coll))

(defn sum
  "Returns the sum of the collection."
  [coll]
  (reduce + coll))
