(def passports (map (fn [passport]
                      (into {}
                            (map #(hash-map
                                   (keyword
                                    (first (clojure.string/split % #":")))
                                   (second (clojure.string/split % #":")))
                                 (clojure.string/split passport #" "))))
                    (map #(clojure.string/replace % #"\n" " ")
                         (clojure.string/split (slurp "input") #"\n\n"))))

(def valid-ecl ["amb" "blu" "brn" "gry" "grn" "hzl" "oth"])

(defn passportvalidation [passport]
  (let [byr (read-string (:byr passport))
        iyr (read-string (:iyr passport))
        eyr (read-string (:eyr passport))
        hgt (:hgt passport)
        hcl (:hcl passport)
        ecl (:ecl passport)
        pid (:pid passport)]
    (and (and (>= byr 1920)
                (<= byr 2002))
           (and (>= iyr 2010)
                (<= iyr 2020))
           (and (>= eyr 2020)
                (<= eyr 2030))
           (not (nil? (re-find #"(59|6\d|7[0-6])in|1([5-8]\d|9[0-3])cm" hgt)))
           (not (nil? (re-find #"#[0-9a-f]{6}" hcl)))
           (= 1 (count (filter #(= ecl %) valid-ecl)))
           (= 9 (count pid)))))

(println (count
          (filter #(and (or (= (count %) 8)
                            (and (= (count %) 7)
                                 (nil? (:cid %))))
                        (passportvalidation %))
                  passports)))
