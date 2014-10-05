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

(defn toggle-item-bought [name]
  (swap! groceries
         (fn [items]
           (let [matches-name #(= name (:name %))
                 item (first (filter matches-name items))]
             (if item
               (let [items (remove matches-name items)]
                 (conj items (update-in item [:bought] not)))
               items)))))
