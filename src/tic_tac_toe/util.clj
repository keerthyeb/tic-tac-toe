(ns tic-tac-toe.util
  (:use
    [tic-tac-toe.constant
     :only [SPACE
            ROW-SEPARATOR
            COLUMN-SEPARATOR
            NEWLINE
            ]]))

(defn read-value [] (read-line))

(defn read-int-value [] (try (Integer/parseInt (read-line)) (catch NumberFormatException e nil)))

(defn print-message [message] (println message))

(def winning-moves [[0 1 2], [3 4 5], [6 7 8], [0 3 6], [1 4 7], [2 5 8], [0 4 8], [2 4 6]])

(defn add-suffix [x]
  (cond
    (= x 8) nil
    (= (mod x 3) 2) (str SPACE NEWLINE ROW-SEPARATOR NEWLINE)
    :else COLUMN-SEPARATOR))

(defn is-starting-with [value collection] (= (first collection) value))

(defn is-subset [set1 set2] (every? #(contains? (set set1) %) set2))