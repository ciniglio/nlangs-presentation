(ns nlangs.api)

(defn grocery-list []
  [{:name "Carrots" :bought false}
   {:name "Short Ribs" :bought false}
   {:name "Bread" :bought false}
   {:name "Cookies" :bought false}])

(defn add-to-grocery-list [params]
  (prn params)
  "Success!")
