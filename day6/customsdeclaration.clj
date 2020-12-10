(println (reduce + (map #(count (remove #{\newline} (set %))) (clojure.string/split (slurp "input") #"\n\n"))))
