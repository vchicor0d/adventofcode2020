(defn passwordcheck [passlist]
  (loop [current (first passlist)
         passlist passlist
         valid 0]
    (let [firstpos (or (empty? passlist) (= (:letter current) (nth (:pass current) (dec (:min current)))))
          secndpos (or (empty? passlist) (= (:letter current) (nth (:pass current) (dec (:max current)))))]
      (if (empty? passlist)
        (println valid)
        (recur (first (rest passlist)) 
               (rest passlist) 
               (if (and (or firstpos secndpos) (not (and firstpos secndpos))) 
                 (inc valid) 
                 valid))))))

(passwordcheck 
  (map #(hash-map :min (read-string (first (clojure.string/split (first (clojure.string/split (first %) #" ")) #"-"))), 
                  :max (read-string (second (clojure.string/split (first (clojure.string/split (first %) #" ")) #"-"))), 
                  :letter (first (second (clojure.string/split (first %) #" "))), 
                  :pass (second %)) 
       (map #(clojure.string/split % #": ") 
            (clojure.string/split-lines (slurp "input")))))
