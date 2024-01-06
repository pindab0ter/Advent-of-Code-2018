(ns nl.pindab0ter.aoc2015.day09.all-in-a-single-night
  (:require [clojure.string :as str]
            [nl.pindab0ter.common.advent-of-code :refer [get-input]]
            [nl.pindab0ter.common.collections :refer [permutations sum]]))

(defn get-segment-distance
  "Given a line of input, returns a map of the form
  {\"London\" {\"Dublin\" 464}, \"Dublin\" {\"London\" 464}}."
  [line]
  (let [[_ from to distance] (re-find #"(\w+) to (\w+) = (\d+)" line)
        distance (Integer/parseInt distance)]
    {from {to distance}
     to   {from distance}}))

(defn get-segment-distances
  "Given a string of input, returns a map of all origins
  with distances to all destinations of the form
  {\"London\" {\"Dublin\" 464, \"Belfast\" 518}, \"Dublin\" {\"London\" 464, \"Belfast\" 141}, …}."
  [input]
  (->> (str/split-lines input)
       (map get-segment-distance)
       (apply (partial merge-with merge))))

(defn total-distance
  "Given a map of segment distances and a route, returns the total distance of the route."
  [segment-distances route]
  (->> route
       (partition 2 1)
       (map (fn [[from to]] (get-in segment-distances [from to])))
       sum))

(defn -main []
  (let [input             (get-input 2015 9)
        segment-distances (get-segment-distances input)
        route-distances   (map (partial total-distance segment-distances)
                               (permutations (keys segment-distances)))]
    (println "The shortest route visiting all destinations is:" (reduce min route-distances))
    (println "The longest route visiting all destinations is:" (reduce max route-distances))))