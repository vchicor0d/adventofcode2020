(defn calculate [current remaining]
  (let [trial (first remaining)]
    (println (str "trying " current " and " trial))
    (if (empty? remaining)
      []
      (if (= 2020 (+ current trial))
        (* current trial)
        (recur current (rest remaining))))))

(defn sum2020 [numlist]
  (let [[current & remaining] numlist
        trial (calculate current remaining)]
    (if (number? trial)
      (println trial)
      (recur (rest numlist)))))

(sum2020 (map read-string (clojure.string/split-lines (slurp "input.txt"))))
