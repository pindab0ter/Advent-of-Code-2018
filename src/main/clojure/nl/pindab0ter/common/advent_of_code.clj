(ns nl.pindab0ter.common.advent-of-code
  (:require
    [clj-http.client :as http]
    [clojure.java.io :as io]
    [clojure.string :as str]))


(defn get-input
  "Returns the input for the given year and day."
  [year day]
  (let [file (io/file (str "src/main/resources/" year "/day" (format "%02d" day) "/input"))]
    (io/make-parents file)
    (if-not (.exists file)
      (let [session-cookie (slurp ".session-cookie")
            ;; https://www.reddit.com/r/adventofcode/comments/z9dhtd/please_include_your_contact_info_in_the_useragent/
            options        {:headers              {"User-Agent" "https://github.com/pindab0ter/AdventOfCode"
                                                   "From"       "hansvanluttikhuizen@me.com"
                                                   "Cookie"     (str "session=" session-cookie)}
                            :as                   :byte-array
                            :unexceptional-status #(or (<= 200 % 299) (= 400 %))}
            response       (http/get (str "https://adventofcode.com/" year "/day/" day "/input") options)
            status         (:status response)
            body           (str/trim-newline (slurp (:body response)))]
        (cond
          (= 400 status) (throw (Exception. "Invalid session cookie. Please provide a valid session cookie in .session-cookie."))
          (not= 200 status) (throw (Exception. (str "Unexpected status code: " (:status response) "\n" (slurp (:body response)))))
          :else (spit file body))))
    (slurp file)))
