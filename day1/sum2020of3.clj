(defn calculate3 [current1 current2 remaining]
  (let [trial (first remaining)]
    (println (str "trying " current1 ", " current2 " and " trial))
    (if (empty? remaining)
      []
      (if (= 2020 (+ current1 current2 trial))
        (* current1 current2 trial)
        (recur current1 current2 (rest remaining))))))

(defn calculate [current remaining]
  (let [current2 (first remaining)
        trial (if (<= 2020 (+ current current2)) [] (calculate3 current current2 (rest remaining)))]
    (println (str "trying " current " and " current2 " remaining " remaining))
    (if (empty? remaining)
      []
      (if (number? trial)
        trial
        (recur current (rest remaining))))))

(defn sum2020 [numlist]
  (let [[current & remaining] numlist
        trial (calculate current remaining)]
    (println (str "trying " current " remaining " remaining))
    (if (number? trial)
      (println trial)
      (recur (rest numlist)))))

(sum2020 (map read-string (clojure.string/split-lines (slurp "input.txt"))))
