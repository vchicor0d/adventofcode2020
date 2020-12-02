(defn calculate3 [number1 number2 remaining]
  (if (= 2020 (+ number1 number2 (first remaining)))
    (do 
      (println "Found" number1 "," number2 "and" (first remaining))
      (* number1 number2 (first remaining)))
    (if (empty? (rest remaining))
      []
      (recur number1 number2 (rest remaining)))))

(defn calculate2 [number1 remaining]
  (if (> 2 (count remaining))
    []
    (let [result (calculate3 number1 (first remaining) (rest remaining))]
      (if (number? result)
        result
        (recur number1 (rest remaining))))))

(defn sum2020 [numlist]
  (let [[number1 & remaining] numlist
        result (calculate2 number1 remaining)]
    (if (number? result)
      (println result)
      (recur (rest numlist)))))

(sum2020 (map read-string (clojure.string/split-lines (slurp "input.txt"))))

