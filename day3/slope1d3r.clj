(defn slope [treemap]
  (let [size (count (first treemap))]
    (println size)
    (loop [trees 0
           xpos 0
           treemap treemap]
       (if (empty? treemap)
         (println trees)
         (recur (if (= \# (nth (first treemap) xpos)) (inc trees) trees) 
                (if (>= (+ 3 xpos) size) (- (+ 3 xpos) size) (+ 3 xpos))
                (rest treemap))))))

(slope (clojure.string/split-lines (slurp "input")))
