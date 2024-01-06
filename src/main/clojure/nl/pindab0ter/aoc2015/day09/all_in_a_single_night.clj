(ns nl.pindab0ter.aoc2015.day09.all-in-a-single-night
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]
            [nl.pindab0ter.common.collections :refer [permutations sum]]))

(defn get-distance [^String line]
  (let [[_ from to distance] (re-find #"(\w+) to (\w+) = (\d+)" line)
        distance (Integer/parseInt distance)]
    {from {to distance}
     to   {from distance}}))

(defn get-distances [^String input]
  (->> (str/split-lines input)
       (map get-distance)
       (apply (partial merge-with merge))))

(defn get-cities [^String input]
  (->> input
       (re-seq #"(\w+) to (\w+)")
       (map rest)
       flatten
       set))

(defn nested-get [coll key1 key2]
  (get (get coll key1) key2))

(defn -main []
  (let [input           (get-input 2015 9)
        cities          (get-cities input)
        distances       (get-distances input)
        route-distances (map
                          #(->> %
                                (partition 2 1)
                                (map (fn [[from to]] (nested-get distances from to)))
                                sum)
                          (permutations cities))]
    (println "The shortest route visiting all destinations is:" (reduce min route-distances))))