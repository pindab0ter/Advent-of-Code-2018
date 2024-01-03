(ns nl.pindab0ter.common.collections)

(defn sum
  "Returns the sum of the collection."
  [coll]
  (reduce + coll))
