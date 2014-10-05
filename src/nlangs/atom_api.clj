(ns nlangs.atom-api)

(def groceries (atom []))

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
