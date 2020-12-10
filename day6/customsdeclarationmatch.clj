(require '(clojure set))

(map (fn [sets]
       (reduce clojure.set/intersection sets)) 
     (map #(map
            (fn [answers] (set answers))
            (clojure.string/split % #"\n"))
          (clojure.string/split (slurp "input") #"\n\n")))
