(defn count-occurences [letter password]
  (loop [letter letter
         password password
         occurences 0]
    (if (empty? password)
      occurences
      (recur letter (rest password) (if (= letter (first password)) 
                                      (inc occurences) 
                                      occurences)))))

(defn passwordcheck [passlist]
  (loop [current (first passlist)
         passlist passlist
         valid 0]
    (let [occurences (count-occurences (:letter current) (:pass current))]
      (if (empty? passlist)
        (println valid)
        (recur (first (rest passlist)) 
               (rest passlist) 
               (if (and (<= (:min current) occurences) 
                        (>= (:max current) occurences)) 
                 (inc valid) 
                 valid))))))

(passwordcheck 
  (map #(hash-map :min (read-string (first (clojure.string/split (first (clojure.string/split (first %) #" ")) #"-"))), 
                  :max (read-string (second (clojure.string/split (first (clojure.string/split (first %) #" ")) #"-"))), 
                  :letter (first (second (clojure.string/split (first %) #" "))), 
                  :pass (second %)) 
       (map #(clojure.string/split % #": ") 
            (clojure.string/split-lines (slurp "input")))))
