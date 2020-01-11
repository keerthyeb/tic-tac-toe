(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.util :refer :all]))

(deftest core-test
  (testing "is-subset"
    (is (true? (is-subset [1 2 3] [1])))
    (is (false? (is-subset [1 2 3] [4])))
    ))
