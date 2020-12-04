(defn slope [treemap right down]
  (println right down)
  (let [size (count (first treemap))]
    (loop [trees 0
           xpos 0
           treemap treemap]
       (if (empty? treemap)
         (do 
           (println trees)
           trees)
         (recur (if (= \# (nth (first treemap) xpos)) (inc trees) trees) 
                (if (>= (+ right xpos) size) (- (+ right xpos) size) (+ right xpos))
                (drop down treemap))))))

(def slopes [{:r 1 :d 1} {:r 3 :d 1} {:r 5 :d 1} {:r 7 :d 1} {:r 1 :d 2}])

(println (reduce * (map #(slope (clojure.string/split-lines (slurp "input")) (:r %) (:d %)) slopes)))
