(ns tic-tac-toe.core
  (:use
    [tic-tac-toe.util
     :only [read-value
            read-int-value
            print-message
            winning-moves
            is-subset]]

    [tic-tac-toe.constant
     :only [SPACE
            PLAYER-1-SYMBOL
            PLAYER-2-SYMBOL
            ENTER-FIRST-PLAYER-TEXT
            ENTER-SECOND-PLAYER-TEXT
            PLAYER-MOVE-MESSAGE
            GREET-MESSAGE
            WINNING-MESSAGE
            TOTAL-MOVE-COUNT
            TURN
            NEWLINE
            ROW-SEPARATOR
            COLUMN-SEPARATOR
            ]]
    ))

(defn get-players-details []
  (print-message ENTER-FIRST-PLAYER-TEXT)
  (let [player1 (read-value)]
    (print-message ENTER-SECOND-PLAYER-TEXT)
    (let [player2 (read-value)]
      {:player1 {:name player1, :symbol PLAYER-1-SYMBOL}
       :player2 {:name player2, :symbol PLAYER-2-SYMBOL}})))

(defn draw-board [board] (->> board
                              (partition 3)
                              (map #(clojure.string/join (str SPACE COLUMN-SEPARATOR SPACE) %))
                              (clojure.string/join (str NEWLINE ROW-SEPARATOR NEWLINE))
                              println))

(defn update-board [board index move] (if (= SPACE (get board index)) (assoc board index move) board))

(defn get-winning-message [winner-name] (str GREET-MESSAGE SPACE winner-name SPACE WINNING-MESSAGE))

(defn get-player-moves [board symbol] (keep-indexed #(when (= %2 symbol) %1) board))

(defn has-won [board current-player]
  (let [player-moves (get-player-moves board (current-player :symbol))]
    (some #(is-subset player-moves %) winning-moves)))

(defn play-game [board current-player next-player]
  (do
    (print-message (str (current-player :name) TURN NEWLINE PLAYER-MOVE-MESSAGE))
    (let [current-move (dec (read-int-value))]
      (let [updated-board (update-board board current-move (current-player :symbol))]
        (draw-board updated-board)
        (if (has-won updated-board current-player)
          (print-message (get-winning-message (current-player :name)))
          (recur updated-board next-player current-player))))))

(defn start []
  (let [player-details (get-players-details) board (into [] (repeat TOTAL-MOVE-COUNT SPACE))]
    (play-game board (player-details :player1) (player-details :player2))))

(start)