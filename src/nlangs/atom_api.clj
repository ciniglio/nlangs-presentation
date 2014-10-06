(ns nlangs.atom-api)

(def groceries
  (atom [{:name "Carrots" :bought false}
         {:name "Short Ribs" :bought false}
         {:name "Bread" :bought false}
         {:name "Cookies" :bought false}]))

(defn grocery-list []
  @groceries)

(defn add-to-grocery-list [name]
  (let [new-item {:name name :bought false}]
    (swap! groceries conj new-item)))

(defn update-item-bought
  ([name] (update-item-bought name true))
  ([name bought]
     (swap! groceries
            (fn [items]
              (map (fn [item]
                     (if (= name (:name item))
                       (assoc item :bought bought)
                       item)) items)))))
