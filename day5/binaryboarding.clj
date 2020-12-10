(def rowcalc [64 32 16 8 4 2 1])
(def colcalc [4 2 1])

;; Traducir de binario a decimal
(defn find-row [binary-row]
  (reduce + (map #(* %1 (read-string (str %2))) rowcalc binary-row)))

(defn find-column [binary-column]
  (reduce + (map #(* %1 (read-string (str %2))) colcalc binary-column)))

;;Convertir a binario B y R = 1, L y F = 0
(defn to-binary-seat [seat]
  (clojure.string/replace seat
                          #"[BFLR]"
                          #(if (or (= "B" %) (= "R" %)) "1" "0")))

(defn to-seat-id [binary-seat]
  (+ (* 8
        (find-row (subs binary-seat 0 7)))
     (find-column (subs binary-seat 7 10))))

(println 
 (apply max (map #(to-seat-id (to-binary-seat %))
                 (clojure.string/split-lines (slurp "input")))))
