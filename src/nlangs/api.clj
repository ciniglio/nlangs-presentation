(ns nlangs.api)

(def internal-list
  (atom [{:name "Carrots" :bought false}
         {:name "Short Ribs" :bought false}
         {:name "Bread" :bought false}
         {:name "Cookies" :bought true}]))

(defn grocery-list []
  @internal-list)

(defn mark-item-as-bought [name bought]
  (swap! internal-list (fn [l]
                         )))


(defn add-to-grocery-list [params]
  (prn params)
  "Success!")
