(def passports (map (fn [passport]
                      (into {}
                            (map #(hash-map
                                   (keyword
                                    (first (clojure.string/split % #":")))
                                   (second (clojure.string/split % #":")))
                                 (clojure.string/split passport #" "))))
                    (map #(clojure.string/replace % #"\n" " ")
                         (clojure.string/split (slurp "input") #"\n\n"))))

(println (count
          (filter #(or (= (count %) 8)
                       (and (= (count %) 7)
                            (nil? (:cid %))))
                  passports)))
