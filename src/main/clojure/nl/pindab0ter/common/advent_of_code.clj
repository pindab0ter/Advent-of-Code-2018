(ns nl.pindab0ter.common.advent-of-code
  (:require [clj-http.client :as http]
            [clojure.java.io :as io]))

(defn get-input
  "Returns the input for the given year and day."
  [year day]
  (let [file (io/file (str "src/main/resources/" year "/day" (format "%02d" day) "/input"))]
    (io/make-parents file)
    (if-not (.exists file)
      (let [session-cookie (slurp ".session-cookie")
            ;; https://www.reddit.com/r/adventofcode/comments/z9dhtd/please_include_your_contact_info_in_the_useragent/
            headers        {:headers {"User-Agent" "https://github.com/pindab0ter/AdventOfCode"
                                      "From"       "hansvanluttikhuizen@me.com"
                                      "Cookie"     (str "session=" session-cookie)}
                            :as      :byte-array}
            response       (http/get (str "https://adventofcode.com/" year "/day/" day "/input") headers)
            status         (:status response)]
        (cond
          (= 400 status) (throw (Exception. "Invalid session cookie"))
          (not= 200 status) (throw (Exception. (str "Unexpected status code: " (:status response))))
          :else (spit file (String. (:body response))))))
    (slurp file)))
